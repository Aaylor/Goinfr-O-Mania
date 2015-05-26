package engine;

import helpers.Chrono;

public class Level {

    private Score actualScore;
    private EntityManager em;
    private Chrono chrono;
    private Difficulties difficulty;

    public Level(Score actualScore, Difficulties difficulty) {
        this.actualScore = actualScore;
        this.difficulty = difficulty;
        this.em = new EntityManager(this);
        chrono = new Chrono();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public Difficulties getDifficulty() {
        return difficulty;
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
