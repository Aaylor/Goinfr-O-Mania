package engine;

import graphics.Board;
import helpers.Chrono;
import sound.MSound;
import sound.SoundManager;

public class Level {

    private Score actualScore;
    private Goal goal;
    private EntityManager em;
    private SoundManager sm;
    private Chrono chrono;

    public Level(Score actualScore, Goal goal, SoundManager sm) {
        this.actualScore = actualScore;
        this.goal = goal;
        this.em = new EntityManager(this);
        this.sm = sm;
        chrono = new Chrono();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public Chrono getChrono() {
        return chrono;
    }

    public void setChrono(Chrono chrono) {
        this.chrono = chrono;
    }

    public void gameLoop() {
        em.entityLoop();

    }

    public Score getScore() {
        return actualScore;
    }
}
