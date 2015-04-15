package graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Aaylor, Tratost, PixelMan on 15/04/15.
 */
public class BoardController implements MouseListener {

    /* Attributes */

    /**
     * The game board that must be controlled
     */
    private Board board;



    /* Constructors */

    public BoardController(Board board) {
        this.board = board;
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
}
