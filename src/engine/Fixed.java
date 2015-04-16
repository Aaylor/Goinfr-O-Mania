package engine;

import sound.MSound;

/**
 * Created by tratost on 13/04/15.
 */
public abstract class Fixed extends Entity {

    protected MSound sonorEffect;

    //CONSTRUCTORS

    public Fixed(MSound sonorEffect) {
        super();
        this.sonorEffect = sonorEffect;
    }

    public Fixed() {
    }

}
