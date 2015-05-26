package engine;

import helpers.Chrono;

public class Level {

    private Score actualScore;
    private EntityManager em;
    private Chrono chrono;

    public Level(Score actualScore) {
        this.actualScore = actualScore;
        this.em = new EntityManager(this);
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
