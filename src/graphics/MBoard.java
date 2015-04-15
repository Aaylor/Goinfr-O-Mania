package graphics;

import engine.MEntity;
import sound.MMusic;

import java.util.Observable;

/**
 * Created by tratost on 13/04/15.
 */
public class MBoard extends Observable {

    private MMusic actualMusic;
    private MEntity[] entities;
    private MBackground background;

    public MBoard(MMusic actualMusic, MEntity[] entities, MBackground background) {
        this.actualMusic = actualMusic;
        this.entities = entities;
        this.background = background;
    }

    public MBoard() {
        this.actualMusic = new MMusic();
        this.entities = new MEntity[10];
        this.background = new MBackground();
    }
}
