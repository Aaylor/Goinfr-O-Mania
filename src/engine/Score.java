package engine;

import java.util.Comparator;
import java.util.Observable;

public class Score implements Comparable<Score>, Comparator<Score> {

    private String who;
    private int value;


    //CONSTRUCTORS

    public Score(String name, int value) {
        this.who = name;
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


    public String getWho() {
        return who;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int compareTo(Score o) {
        if (value - o.value == 0) {
            return who.compareTo(o.who);
        }

        return value - o.value;
    }

    @Override
    public int compare(Score o1, Score o2) {
        return o1.compareTo(o2);
    }
}
