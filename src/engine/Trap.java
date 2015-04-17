package engine;

import sound.MSound;
import sound.Soundable;

/**
 * Created by tratost on 13/04/15.
 */
public class Trap implements Soundable {

    MSound sound;

    //CONSTRUCTORS

    public Trap(MSound sound) {
        this.sound = sound;
    }

    public Trap(int effect) {
        super();
    }

    @Override
    public void playSound() {

    }
}
