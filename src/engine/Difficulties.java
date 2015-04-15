package engine;

/**
 * Created by tratost on 13/04/15.
 */
public class Difficulties {

    private int value;

    //CONSTRUCTORS

    public Difficulties(int value) {
        this.value = value;
    }

    public Difficulties() {
        this.value = 1;
    }

    //SETTERS

    public void setValue(int value) {
        this.value = value;
    }

    //GETTERS

    public int getValue() {
        return value;
    }
}
