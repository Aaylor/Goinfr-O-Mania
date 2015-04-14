package Model;

/**
 * Created by tratost on 13/04/15.
 */
public class MPan {

    private MMusic actualMusic;
    private MEntity[] entities;
    private MBackground background;

    public MPan(MMusic actualMusic, MEntity[] entities, MBackground background) {
        this.actualMusic = actualMusic;
        this.entities = entities;
        this.background = background;
    }

    public MPan() {
        this.actualMusic = new MMusic();
        this.entities = new MEntity[10];
        this.background = new MBackground();
    }
}
