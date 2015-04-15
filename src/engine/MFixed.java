package engine;

import sound.MMusic;

/**
 * Created by tratost on 13/04/15.
 */
public class MFixed extends MEntity{

    protected MMusic sonorEffect;

    //CONSTRUCTORS

    public MFixed(int effect, MMusic sonorEffect) {
        super(effect);
        this.sonorEffect = sonorEffect;
    }

    public MFixed(int effect) {
        super(effect);
    }

    public MFixed() {
    }

}
