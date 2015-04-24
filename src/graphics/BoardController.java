package graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


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
        System.out.println("MWAHAHAHAHAHAHAHA");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("released");
    }
}
