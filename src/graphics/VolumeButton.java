package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by PixelMan on 24/05/15.
 */
public class VolumeButton extends JButton implements MouseListener {

    Icon mute;
    Icon soundy;

    boolean muted;

    /**
     * Creates a button with no set text or icon.
     */
    public VolumeButton() {
        super();

        muted = false;

        mute = new ImageIcon("pictures/Mute.png");
        soundy = new ImageIcon("pictures/Soundy.png");

        this.setIcon(soundy);

        this.setBackground(null);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.addMouseListener(this);
    }

    public boolean isMuted() {
        return muted;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        muted = !muted;
        if(muted)
            this.setIcon(mute);
        else
            this.setIcon(soundy);
    }


    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
