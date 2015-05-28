package graphics;

import com.sun.javaws.exceptions.InvalidArgumentException;
import engine.Settings;
import log.IGLog;

/**
 * Created by PixelMan on 28/05/15.
 */
public class SoundEffectController extends AbstractVolumeController {

    public SoundEffectController(Settings currentSettings) {
        super(currentSettings);
    }

    @Override
    public void updateSonorVolume(int volume) {
        //try {
            double optionVolume = new Integer(volume).doubleValue() / 100;
            IGLog.info("Options updates <sound Effects> : " + optionVolume);
            //currentSettings.setVolume(optionVolume);
            //MainFrame.getCurrentInstance().getMainMusic().setVolume(currentSettings.getVolume());
        //}
        //catch (InvalidArgumentException e){
            //IGLog.error("Invalide Sonor Volume");
        //}

    }
}
