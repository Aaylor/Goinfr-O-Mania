package graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by PixelMan on 26/05/15.
 */
@SuppressWarnings("DefaultFileTemplate")
public class VolumeView extends JPanel {

    VolumeButton muteButton;
    VolumeSlider volumeSlider;

    public VolumeView(double initialVolume) {
        super(new BorderLayout());
        this.setOpaque(false);

        this.muteButton = new VolumeButton(initialVolume);
        this.volumeSlider = new VolumeSlider(initialVolume);
        this.add(muteButton, BorderLayout.WEST);
        this.add(volumeSlider, BorderLayout.CENTER);
    }

    public VolumeButton getMuteButton() {
        return muteButton;
    }

    public VolumeSlider getVolumeSlider() {
        return volumeSlider;
    }
}
