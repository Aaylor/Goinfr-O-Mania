package graphics;

import com.sun.javaws.exceptions.InvalidArgumentException;
import engine.EntityAssociation;
import engine.Settings;
import log.IGLog;
import sound.MSound;

public class SoundEffectController extends AbstractVolumeController {

    public SoundEffectController(Settings currentSettings) {
        super(currentSettings, currentSettings.getSoundEffects());
    }

    @Override
    public void updateSonorVolume(int volume) {
        try {
            double optionVolume = new Integer(volume).doubleValue() / 100;
            IGLog.info("Options updates <sound Effects> : " + optionVolume);
            currentSettings.setSoundEffects(optionVolume);
            MSound.setVolumeGlobal(optionVolume);
            MSound m = new MSound(EntityAssociation.DEFAULT_LIFECAKE);
            m.play();
        }
        catch (InvalidArgumentException e){
            IGLog.error("Invalide Sonor Volume");
        }

    }
}
