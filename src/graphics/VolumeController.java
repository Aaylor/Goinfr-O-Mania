package graphics;

import com.sun.javaws.exceptions.InvalidArgumentException;
import engine.Settings;
import log.IGLog;

/**
 * Created by PixelMan on 28/05/15.
 */
@SuppressWarnings("DefaultFileTemplate")
public class VolumeController extends AbstractVolumeController {

    public VolumeController(Settings currentSettings) {
        super(currentSettings, currentSettings.getVolume());
    }

    @Override
    public void updateSonorVolume(int volume) {
        try {
            double optionVolume = new Integer(volume).doubleValue() / 100;
            IGLog.info("Options updates <volume> : " + optionVolume);
            currentSettings.setVolume(optionVolume);
            MainFrame.getCurrentInstance().getMainMusic().setVolume(currentSettings.getVolume());
        }
        catch (InvalidArgumentException e){
            IGLog.error("Invalide Sonor Volume");
        }
    }
}
