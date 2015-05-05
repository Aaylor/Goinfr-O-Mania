package engine.weapons;

import engine.weapons.AbstractWeapon;
import sound.MSound;

import java.util.List;

public class RangedWeapon extends AbstractWeapon {

    private double range;

    public RangedWeapon(double damage, List<MSound> sounds) {
        super(damage, sounds);
    }

    @Override
    public void attack() {

    }

    @Override
    public void playSound() {

    }
}
