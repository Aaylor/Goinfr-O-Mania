package graphics;

import engine.*;
import engine.nutritionists.AbstractNutritionist;
import engine.weapons.Weapon;
import helpers.Chrono;
import helpers.ExtDate;
import helpers.ExtMath;
import helpers.PopTimer;
import log.IGLog;
import sound.MSound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class BoardController extends Thread implements MouseListener, KeyListener {

    /* Attributes */

    /**
     * The game board that must be controlled
     */
    private Board board;
    private BoardView boardView;
    private boolean gameState;
    private final Set<Integer> pressedKeys;
    private MSound gameSound;

    private PopTimer nextRandomPop;
    private PopTimer nextRandomTrap;
    private PopTimer nextRandomNutritionists;

    /* Constructors */

    public BoardController(Board board, BoardView view) {
        this.board = board;

        this.boardView = view;
        boardView.addKeyListener(this);
        board.addObserver(boardView);
        getBoard().getLevel().getEntityManager().setBoardDimension(boardView.getSize());

        gameState = true;

        gameSound = new MSound("ingame", "music/ingame00.wav");

        pressedKeys = new HashSet<>();
    }


    /* Getters */

    public Board getBoard() {
        return board;
    }


    /* Others */

    public void pauseGame() {
        IGLog.info("BoardController, gamed paused.");
        boardView.setPaused(true);
        gameSound.stop();

        // Pause chrono and timer.
        getBoard().getLevel().getChrono().pause();
        nextRandomPop.pauseTimer();
        nextRandomTrap.pauseTimer();
        nextRandomNutritionists.pauseTimer();

        board.notification();
        gameState = false;
    }

    public synchronized void resumeGame() {
        IGLog.info("BoardController, game resumed.");
        boardView.setPaused(false);
        gameSound.play();

        // Resume chrono and timer.
        getBoard().getLevel().getChrono().resume();
        nextRandomPop.resumeTimer();
        nextRandomNutritionists.resumeTimer();

        gameState = true;
        notify();
    }


    /* Override Methods from MouseListener */

    private void waitForResume() {
        synchronized (this) {
            while (!gameState) {
                try {
                    IGLog.info("BoardController, waiting for notification.");
                    wait();
                } catch (InterruptedException e) {
                    IGLog.info("BoardController, wait interruption. Continuing.");
                    continue;
                }
            }
        }
    }

    private void sleepFor(long time) {
        Date wanted = new Date(new Date().getTime() +  time);
        do {
            try {
                Thread.sleep(time);
                break;
            } catch (InterruptedException e) {
                if (new Date().after(wanted))
                    break;
            }
        } while (true);
    }

    private void movePlayer() {
        KeyConfiguration keyConfiguration = board.getPlayer().getKeyConfiguration();

        synchronized (pressedKeys) {
            for (Integer pressedKey : pressedKeys) {
                if (pressedKey == keyConfiguration.getUp()) {
                    board.sendGluttonMovement(Movable.Direction.FRONT);
                } else if (pressedKey == keyConfiguration.getDown()) {
                    board.sendGluttonMovement(Movable.Direction.BELOW);
                } else if (pressedKey == keyConfiguration.getLeft()) {
                    board.sendGluttonMovement(Movable.Direction.LEFT);
                } else if (pressedKey == keyConfiguration.getRight()) {
                    board.sendGluttonMovement(Movable.Direction.RIGHT);
                } else if (pressedKey == keyConfiguration.getAttack()) {
                    board.sendGluttonAttack();
                }
            }
        }
    }

    private void initialization(EntityManager manager) {
        /* Music */
        gameSound.playInfinite();

        /* Initialize every one. */
        manager.addAtRandomPosition(
                EntityAssociation.getEntity("default_glutton"),
                EntityAssociation.getEntityView("default_glutton"),
                0, boardView.getHeight(), 0, boardView.getWidth()
        );
        manager.getGlutton().setWeapon(Weapon.make("punch"));

        /* Initialize timer. */
        nextRandomPop = new PopTimer(10);
        nextRandomTrap = new PopTimer(2);
        nextRandomNutritionists = new PopTimer(5);
    }

    private void randomPop() {
        if (nextRandomPop.hasPassed()) {
            IGLog.write("BoardController::run -> Next Random Pop has to be done.");

            String cakeName = EntityAssociation.randomCakes.getRandomlyName();
            getBoard().getLevel().getEntityManager().addAtRandomPosition(
                    EntityAssociation.getEntity(cakeName),
                    EntityAssociation.getEntityView(cakeName)
            );

            nextRandomPop = new PopTimer(10);
        }
    }

    private void randomTrapPop() {
        if (nextRandomTrap.hasPassed()) {
            IGLog.write("BoardController::run -> Next Random Trap has to be done.");

            String trapName = EntityAssociation.randomTraps.getRandomlyName();
            getBoard().getLevel().getEntityManager().addAtRandomPosition(
                    EntityAssociation.getEntity(trapName),
                    EntityAssociation.getEntityView(trapName)
            );

            nextRandomTrap = new PopTimer(2);
        }
    }

    private void randomNutritionistsPop() {
        if (nextRandomNutritionists.hasPassed()) {
            IGLog.write("BoardController::run -> Next Random Nutritionists Pop has to be done.");

            EntityManager manager = getBoard().getLevel().getEntityManager();

            String who = EntityAssociation.randomNutritionist.getRandomlyName();
            Entity e   = EntityAssociation.getEntity(who);
            manager.setRandomPosition(e, 0, boardView.getHeight(), 0, boardView.getWidth());

            /* FIXME */
            e.setWeapon(Weapon.make("punch"));

            board.getLevel().getEntityManager().addNutritionist(
                    (AbstractNutritionist)e,
                    EntityAssociation.getEntityView(who)
            );

            nextRandomNutritionists = new PopTimer(15);
        }
    }

    @Override
    public void run() {
        EntityManager manager = board.getLevel().getEntityManager();

        /* Game initialization */
        initialization(manager);

        getBoard().getLevel().getChrono().start();

        while (true) {
            waitForResume();
            if (board.getLevel().getEntityManager().getGlutton().getLife() <= 0) {
                IGLog.info("Glutton is dead.");
                gameSound.stop();
                MainFrame.getCurrentInstance().popPanel();
                new EndGameController(getBoard());
                return;
            }

            /* Glutton move */
            waitForResume();
            movePlayer();

            /* Entities move */
            waitForResume();
            manager.entityLoop();


            /* Entity creation */
            waitForResume();

            /* cake */
            if (manager.getCakes().size() == 0) {
                IGLog.info("BoardController::run -> A new cake has to appear.");

                String cakeName = EntityAssociation.randomCakes.getRandomlyName();
                manager.addAtRandomPosition(
                        EntityAssociation.getEntity(cakeName),
                        EntityAssociation.getEntityView(cakeName)
                );
            }

            randomPop();
            randomNutritionistsPop();
            randomTrapPop();

            board.notification();
            sleepFor(15);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /* TODO : mouseClicked */
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /* TODO : mousePressed */
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /* TODO : mouseReleased */
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        /* TODO : mouseEntered */
    }

    @Override
    public void mouseExited(MouseEvent e) {
        /* TODO : mouseExited */
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        e.consume();

        Integer code = e.getKeyCode();
        KeyConfiguration kc = getBoard().getPlayer().getKeyConfiguration();

        if (code == kc.getPause()) {
            if (gameState) {
                pauseGame();
            } else {
                resumeGame();
            }
        } else if (code == kc.getMenu()) {
            if (gameState)
                pauseGame();

            new PauseController(boardView, this, getBoard().getPlayer());
        } else if (code == kc.getQuit()) {
            /* TODO: Maybe ask to save ?? */
            getBoard().getLevel().getChrono().stop();
            System.exit(0);
        } else {
            synchronized (pressedKeys) {
                if (code == kc.getUp() && pressedKeys.contains(kc.getDown())) {
                    pressedKeys.remove(kc.getDown());
                } else if (code == kc.getDown() && pressedKeys.contains(kc.getUp())) {
                    pressedKeys.remove(kc.getUp());
                } else if (code == kc.getLeft() && pressedKeys.contains(kc.getRight())) {
                    pressedKeys.remove(kc.getRight());
                } else if (code == kc.getRight() && pressedKeys.contains(kc.getLeft())) {
                    pressedKeys.remove(kc.getLeft());
                } else {
                    pressedKeys.add(code);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        e.consume();

        synchronized (pressedKeys) {
            pressedKeys.remove(e.getKeyCode());
        }
    }
}
