package engine.nutritionists;

import engine.Cake;
import engine.Entity;
import log.IGLog;

import java.awt.*;
import java.util.*;
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
    public void effect(Entity e) {
        if (e instanceof Cake) {
            IGLog.info("CakeChaser, effect: cake.");
            getManager().removeEntity(e);
        }
    }
}
