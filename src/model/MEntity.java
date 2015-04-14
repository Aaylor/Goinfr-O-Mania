package Model;

import java.util.Observable;

/**
 * Created by tratost on 13/04/15.
 */
public class MEntity extends Observable {

    protected int effect;
    protected String skin;

    //CONSTRUCTORS


    public MEntity(int effect, String skin) {
        this.effect = effect;
        this.skin = skin;
    }

    public MEntity(int effect) {
        this.effect = effect;
    }

    public MEntity() {
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
