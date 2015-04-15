package engine;

import sound.MSound;

/**
 * Created by tratost on 13/04/15.
 */
public class MBonus extends MFixed {


    //CONSTRUCTORS

    public MBonus(int effect, MSound SoundBonus) {
        super(effect, SoundBonus);
    }

    public MBonus(int effect) {
        super(effect);
    }

    public MBonus() {
        super();
    }

}
