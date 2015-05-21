package engine;

import graphics.Board;
import sound.MSound;
import sound.SoundManager;

public class Level {

    private Score actualScore;
    private Goal goal;
    private EntityManager em;
    private SoundManager sm;

    public Level(Score actualScore, Goal goal, SoundManager sm) {
        this.actualScore = actualScore;
        this.goal = goal;
        this.em = new EntityManager(this);
        this.sm = sm;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void gameLoop() {
        em.entityLoop();

    }

    public Score getScore() {
        return actualScore;
    }
}
