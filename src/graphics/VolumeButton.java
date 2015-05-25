package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by PixelMan on 24/05/15.
 */
public class VolumeButton extends JButton{

    private Icon mute;
    private Icon soundy;

    private boolean muted;

    /**
     * Creates a button with no set text or icon.
     */
    public VolumeButton(double volume) {
        super();

        mute = new ImageIcon("pictures/Mute.png");
        soundy = new ImageIcon("pictures/Soundy.png");

        setMuted(volume == 0);

        this.setBackground(null);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
        if(muted)
            this.setIcon(mute);
        else
            this.setIcon(soundy);
    }

}
