package engine;

import java.util.Observable;

public class Score {
    private int value;


    //CONSTRUCTORS

    public Score(String name, int value) {
        this.value = value;
    }

    public Score() {
        this.value = 0;
    }

    //SETTERS

    public void setValue(int value) {
        this.value = value;
    }

    public void add(int value) {
        this.value += value;
    }

    //GETTERS

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
