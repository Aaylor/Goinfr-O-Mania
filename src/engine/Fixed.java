package engine;

import sound.MSound;

/**
 * Created by tratost on 13/04/15.
 */
public class Fixed extends Entity {

    protected MSound sonorEffect;

    //CONSTRUCTORS

    public Fixed(int effect, MSound sonorEffect) {
        super(effect);
        this.sonorEffect = sonorEffect;
    }

    public Fixed(int effect) {
        super(effect);
    }

    public Fixed() {
    }

}
