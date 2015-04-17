package engine;

import sound.MSound;
import sound.Soundable;

/**
 * Created by tratost on 13/04/15.
 */
public class Cake implements Soundable {

    MSound sound;

    //CONSTRUCTORS

    public Cake(MSound sound) {
        this.sound = sound;
    }

    @Override
    public void playSound() {

    }
}
