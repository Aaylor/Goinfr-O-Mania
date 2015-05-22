package engine.nutritionists;


import engine.Entity;

import java.awt.*;


public class GluttonChaserNutritionist extends AbstractNutritionist {
    public GluttonChaserNutritionist(int nbLifes) {
        super(nbLifes);
    }

    public GluttonChaserNutritionist(float speed, float direction, int nbLifes) {
        super(speed, direction, nbLifes);
    }

    public GluttonChaserNutritionist(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction, nbLifes);
    }

    @Override
    public void nextStep() {
        attackIfPossible(getManager().getGlutton());
        moveToEntity(getManager().getGlutton());
    }

    @Override
    public boolean effect(Entity e) {
        return false;
    }

    @Override
    public int scoreValue() {
        return 15;
    }
}
