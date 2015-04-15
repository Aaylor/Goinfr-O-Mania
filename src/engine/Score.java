package engine;

import java.util.Observable;

/**
 * Created by tratost on 13/04/15.
 */
public class Score extends Observable {
    //Plutot un MPlayer ?
    private String name;
    private int value;


    //CONSTRUCTORS

    public Score(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Score() {
        this.name = "Tester";
        this.value = 0;
    }

    //SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
        setChanged();
        notifyObservers();
    }

    //GETTERS

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
