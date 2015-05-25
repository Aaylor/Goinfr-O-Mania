package graphics;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by PixelMan on 24/05/15.
 */
public class VolumeSlider extends JSlider {

    private int volume;

    /**
     * Creates a horizontal slider with the range 0 to 100 and
     * an initial value of 100.
     */
    public VolumeSlider(double volume) {
        super(JSlider.HORIZONTAL, 0, 100, (int) (volume*100));

    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
