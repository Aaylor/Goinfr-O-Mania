package engine;

import java.util.Observable;

/**
 * Created by tratost on 13/04/15.
 */
public class Entity extends Observable {

    protected int effect;
    protected String skin;

    //CONSTRUCTORS


    public Entity(int effect, String skin) {
        this.effect = effect;
        this.skin = skin;
    }

    public Entity(int effect) {
        this.effect = effect;
    }

    public Entity() {
        this.effect = 0;
    }

    //SETTERS

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public void setSkin(String skin) {
        this.skin = skin;
        setChanged();
        notifyObservers();
    }

    //GETTERS

    public int getEffect() {
        return effect;
    }

    public String getSkin() {
        return skin;
    }
}
