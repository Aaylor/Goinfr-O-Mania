package engine.nutritionists;

import engine.Entity;
import engine.weapons.Weapon;

import java.awt.*;

public class TrapShooterNutritionist extends AbstractNutritionist {
    public TrapShooterNutritionist(int nbLifes) {
        super(nbLifes);
        setWeapon(Weapon.make("trap-weapon"));
    }

    public TrapShooterNutritionist(float speed, float direction, int nbLifes) {
        super(speed, direction, nbLifes);
        setWeapon(Weapon.make("trap-weapon"));
    }

    public TrapShooterNutritionist(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction, nbLifes);
        setWeapon(Weapon.make("trap-weapon"));
    }

    @Override
    public void nextStep() {
        if (getWeapon().ready())
            getWeapon().attack(this);

        moveToEntity(getManager().getGlutton());
    }

    @Override
    public boolean effect(Entity e) {
        return false;
    }

    @Override
    public int scoreValue() {
        return 20;
    }
}
