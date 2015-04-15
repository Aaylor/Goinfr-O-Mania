package engine;

import sound.MSound;

/**
 * Created by tratost on 13/04/15.
 */
public class Malus extends Fixed {


    //CONSTRUCTORS


    public Malus(int effect, MSound musicMalus) {
        super(effect, musicMalus);
    }

    public Malus(int effect) {
        super(effect);
    }

    public Malus() {
        super();
    }
}
