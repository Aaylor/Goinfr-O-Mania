package graphics;

import engine.MEntity;
import sound.MSound;

import java.util.Observable;

/**
 * Created by tratost on 13/04/15.
 */
public class MBoard extends Observable {

    private MSound actualMusic;
    private MEntity[] entities;
    private MBackground background;

    public MBoard(MSound actualMusic, MEntity[] entities, MBackground background) {
        this.actualMusic = actualMusic;
        this.entities = entities;
        this.background = background;
    }

    public MBoard() {
        this.actualMusic = null;
        this.entities = new MEntity[10];
        this.background = new MBackground();
    }
}
