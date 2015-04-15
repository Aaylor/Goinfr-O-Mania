package graphics;

import engine.Entity;
import sound.MSound;

import java.util.Observable;

/**
 * Created by tratost on 13/04/15.
 */
public class Board extends Observable {

    private MSound actualMusic;
    private Entity[] entities;
    private Background background;

    public Board(MSound actualMusic, Entity[] entities, Background background) {
        this.actualMusic = actualMusic;
        this.entities = entities;
        this.background = background;
    }

    public Board() {
        this.actualMusic = null;
        this.entities = new Entity[10];
        this.background = new Background();
    }
}
