package graphics;

import engine.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;


public class BoardController implements MouseListener, KeyListener {

    /* Attributes */

    /**
     * The game board that must be controlled
     */
    private Board board;

    private BoardView boardView;



    /* Constructors */

    public BoardController(Board board, BoardView view) {
        this.board = board;
        this.boardView = view;
        boardView.addKeyListener(this);
        board.addObserver(boardView);
    }



    /* Getters */

    public Board getBoard() {
        return board;
    }


    /* Override Methods from MouseListener */

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
        KeyConfiguration keyConfiguration = board.getPlayer().getKeyConfiguration();
        if (e.getKeyCode() == keyConfiguration.getUp()) {
            board.gluttonTranslate(0, -1);
        } else if (e.getKeyCode() == keyConfiguration.getDown()) {
            board.gluttonTranslate(0, 1);
        } else if (e.getKeyCode() == keyConfiguration.getLeft()) {
            board.gluttonTranslate(-1, 0);
        } else if (e.getKeyCode() == keyConfiguration.getRight()) {
            board.gluttonTranslate(1, 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
