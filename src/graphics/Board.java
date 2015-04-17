package graphics;

import engine.Entity;
import sound.MSound;

import java.util.LinkedList;
import java.util.Observable;

public class Board extends Observable {

    private Background background;

    public Board(Background background) {
        this.background = background;
    }

    public Board() {
        //this.background = new Background();
    }
}
