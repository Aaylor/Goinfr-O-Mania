package graphics;

import engine.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by PixelMan on 26/05/15.
 */
public class VolumeView extends JPanel {

    Settings currentSettings;

    VolumeButton muteButton;
    VolumeSlider volumeSlider;

    public VolumeView(Settings currentSettings) {
        super(new BorderLayout());
        this.setOpaque(false);

        this.currentSettings = currentSettings;
        this.muteButton = new VolumeButton(currentSettings.getVolume());
        this.volumeSlider = new VolumeSlider(currentSettings.getVolume());
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
