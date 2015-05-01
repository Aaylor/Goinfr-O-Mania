package graphics;

import engine.*;
import log.IGLog;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Date;


public class BoardController extends Thread implements MouseListener, KeyListener {

    /* Attributes */

    /**
     * The game board that must be controlled
     */
    private Board board;

    private BoardView boardView;

    private boolean gameState;



    /* Constructors */

    public BoardController(Board board, BoardView view) {
        this.board = board;
        this.boardView = view;
        boardView.addKeyListener(this);
        board.addObserver(boardView);
        gameState = true;
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

    @Override
    public void run() {
        EntityManager manager = board.getLevel().getEntityManager();
        while (true) {

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
        if (!gameState) return;

        /*
         *  Use for any actions on the character :
         *      - up
         *      - down
         *      - left
         *      - right
         */

        KeyConfiguration keyConfiguration = board.getPlayer().getKeyConfiguration();
        if (e.getKeyCode() == keyConfiguration.getUp()) {
            board.sendGluttonMovement(Movable.Direction.FRONT);
        } else if (e.getKeyCode() == keyConfiguration.getDown()) {
            board.sendGluttonMovement(Movable.Direction.BELOW);
        } else if (e.getKeyCode() == keyConfiguration.getLeft()) {
            board.sendGluttonMovement(Movable.Direction.LEFT);
        } else if (e.getKeyCode() == keyConfiguration.getRight()) {
            board.sendGluttonMovement(Movable.Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        /*
         *  Used for any options keys :
         *      - load
         *      - save
         *      - pause
         *      - quit
         */

        KeyConfiguration keyConfiguration = board.getPlayer().getKeyConfiguration();
        if (e.getKeyCode() == keyConfiguration.getPause()) {
            /* TODO : show the pause panel */
            if (gameState) {
                pauseGame();
            } else {
                resumeGame();
            }
        } else if (e.getKeyCode() == keyConfiguration.getQuit()) {
            /* TODO: Maybe ask to save ?? */
            System.exit(0);
        }

    }
}
