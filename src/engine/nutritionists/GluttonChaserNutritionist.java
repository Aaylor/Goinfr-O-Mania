package engine.nutritionists;


import engine.Entity;
import engine.weapons.Weapon;

import java.awt.*;


public class GluttonChaserNutritionist extends AbstractNutritionist {
    public GluttonChaserNutritionist(int nbLifes) {
        super(nbLifes);
        w();
    }

    public GluttonChaserNutritionist(float speed, float direction, int nbLifes) {
        super(speed, direction, nbLifes);
        w();
    }

    public GluttonChaserNutritionist(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction, nbLifes);
        w();
    }

    private void w() {
        Weapon w = Weapon.make("punch");
        w.setOwner(this);
        setWeapon(w);
    }

    @Override
    public void nextStep() {
        attackIfPossible(getManager().getGlutton());
        moveToEntity(getManager().getGlutton());
    }

    @Override
    public boolean effect(Entity e) {
        return getLife() <= 0;
    }

    @Override
    public int scoreValue() {
        return 15;
    }
}
