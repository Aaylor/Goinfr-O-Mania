package engine.nutritionists;

import engine.Entity;
import engine.weapons.TrapWeapon;
import engine.weapons.Weapon;
import log.IGLog;

import java.awt.*;

public class TrapShooterNutritionist extends AbstractNutritionist {
    public TrapShooterNutritionist(int nbLifes) {
        super(nbLifes);
        w();
    }

    public TrapShooterNutritionist(float speed, float direction, int nbLifes) {
        super(speed, direction, nbLifes);
        w();
    }

    public TrapShooterNutritionist(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction, nbLifes);
        w();
    }

    private void w() {
        Weapon weapon = Weapon.make("trap-weapon");
        weapon.setOwner(this);
        setWeapon(weapon);
    }

    @Override
    public void nextStep() {
        if (getWeapon() != null && getWeapon().ready()) {
            getWeapon().attack(this);
        }

        moveToEntity(getManager().getGlutton());
    }

    @Override
    public boolean effect(Entity e) {
        return getLife() <= 0;
    }

    @Override
    public int scoreValue() {
        return 20;
    }
}
