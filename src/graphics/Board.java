package graphics;

import engine.Entity;
import sound.MSound;

import java.util.LinkedList;
import java.util.Observable;

public class Board extends Observable {

    private MSound actualMusic;
    private LinkedList<Entity> entities;
    private Background background;

    public Board(MSound actualMusic, LinkedList<Entity> entities, Background background) {
        this.actualMusic = actualMusic;
        this.entities = (LinkedList<Entity>) entities.clone();
        this.background = background;
    }

    public Board() {
        this.actualMusic = null;
        this.entities = new LinkedList<>();
        this.background = new Background();
    }
}
