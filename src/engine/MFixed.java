package engine;

import sound.MSound;

/**
 * Created by tratost on 13/04/15.
 */
public class MFixed extends MEntity{

    protected MSound sonorEffect;

    //CONSTRUCTORS

    public MFixed(int effect, MSound sonorEffect) {
        super(effect);
        this.sonorEffect = sonorEffect;
    }

    public MFixed(int effect) {
        super(effect);
    }

    public MFixed() {
    }

}
