package engine;

import sound.MSound;

/**
 * Created by tratost on 13/04/15.
 */
public class Bonus extends Fixed {


    //CONSTRUCTORS

    public Bonus(int effect, MSound SoundBonus) {
        super(effect, SoundBonus);
    }

    public Bonus(int effect) {
        super(effect);
    }

    public Bonus() {
        super();
    }

}
