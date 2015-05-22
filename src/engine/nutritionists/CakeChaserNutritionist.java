package engine.nutritionists;

import engine.cake.AbstractCake;
import engine.Entity;
import log.IGLog;

import java.awt.*;
import java.util.List;

public class CakeChaserNutritionist extends AbstractNutritionist {
    public CakeChaserNutritionist(int nbLifes) {
        super(nbLifes);
    }

    public CakeChaserNutritionist(float speed, float direction, int nbLifes) {
        super(speed, direction, nbLifes);
    }

    public CakeChaserNutritionist(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction, nbLifes);
    }

    @Override
    public void nextStep() {
        List<Entity> cakes = getManager().getCakes();

        if (cakes.isEmpty()) {
            /* no cake, do nothing ?? */
        } else {
            Entity closestCakes = getClosestEntity(cakes);
            moveToEntity(closestCakes);
        }
    }

    @Override
    public boolean effect(Entity e) {
        if (e instanceof AbstractCake) {
            IGLog.info("CakeChaser, effect: cake.");
            return false;
        }

        return false;
    }

    @Override
    public int scoreValue() {
        return 5;
    }
}
