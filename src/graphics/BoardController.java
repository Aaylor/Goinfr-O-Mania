package graphics;

import engine.*;
import log.IGLog;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    /* Constructors */

    public BoardController(Board board, BoardView view) {
        this.board = board;
        this.boardView = view;
        boardView.addKeyListener(this);
        board.addObserver(boardView);
        gameState = true;
        pressedKeys = new HashSet<>();
    }


    /* Getters */

    public Board getBoard() {
        return board;
    }


    /* Others */

    public void pauseGame() {
        IGLog.info("BoardController, gamed paused.");
        gameState = false;
    }

    public synchronized void resumeGame() {
        IGLog.info("BoardController, game resumed.");
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

    @Override
    public void run() {
        EntityManager manager = board.getLevel().getEntityManager();
        while (true) {

            /* Glutton move */
            waitForResume();
            movePlayer();

            /* Entities move */
            waitForResume();
            manager.entityLoop();


            /* Entity creation */
            // waitForResume();

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
            /* TODO : show the pause panel */
            if (gameState) {
                pauseGame();
            } else {
                resumeGame();
            }
        } else if (code == kc.getQuit()) {
            /* TODO: Maybe ask to save ?? */
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
