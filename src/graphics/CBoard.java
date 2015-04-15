package graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Aaylor, Tratost, PixelMan on 15/04/15.
 */
public class CBoard implements MouseListener {

    /* Attributes */

    /**
     * The game board that must be controlled
     */
    MBoard board;



    /* Constructors */

    public CBoard(MBoard board) {
        this.board = board;
    }



    /* Getters */

    public MBoard getBoard() {
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
