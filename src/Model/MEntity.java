package Model;

/**
 * Created by tratost on 13/04/15.
 */
public class MEntity {

    protected int effect;

    //CONSTRUCTORS

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

    //GETTERS

    public int getEffect() {
        return effect;
    }
}
