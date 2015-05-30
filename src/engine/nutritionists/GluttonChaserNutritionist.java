package engine.nutritionists;


import engine.Entity;
import engine.weapons.Weapon;

import java.awt.*;


public class GluttonChaserNutritionist extends AbstractNutritionist {
    public GluttonChaserNutritionist(int nbLifes) {
        super(nbLifes);
        setWeapon(Weapon.make("punch"));
    }

    public GluttonChaserNutritionist(float speed, float direction, int nbLifes) {
        super(speed, direction, nbLifes);
        setWeapon(Weapon.make("punch"));
    }

    public GluttonChaserNutritionist(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction, nbLifes);
        setWeapon(Weapon.make("punch"));
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
