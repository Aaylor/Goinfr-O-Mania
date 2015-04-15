package engine;

import java.util.Observable;

/**
 * This class represent the modelisation of a player
 */
public class MPlayer extends Observable {

    private String name;
    private char up;
    private char down;
    private char left;
    private char right;

    //CONSTRUCTORS

    public MPlayer(String name, char up, char down, char left, char right) {
        this.name = name;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public MPlayer() {
        this.name = "Tester";
        this.up = 'z';
        this.down = 's';
        this.left = 'q';
        this.right = 'd';
    }


    //SETTERS

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public void setUp(char up) {
        this.up = up;
        setChanged();
        notifyObservers();
    }

    public void setDown(char down) {
        this.down = down;
        setChanged();
        notifyObservers();
    }

    public void setLeft(char left) {
        this.left = left;
        setChanged();
        notifyObservers();
    }

    public void setRight(char right) {
        this.right = right;
        setChanged();
        notifyObservers();
    }


    //GETTERS


    public String getName() {
        return name;
    }

    public char getUp() {
        return up;
    }

    public char getDown() {
        return down;
    }

    public char getLeft() {
        return left;
    }

    public char getRight() {
        return right;
    }
}
