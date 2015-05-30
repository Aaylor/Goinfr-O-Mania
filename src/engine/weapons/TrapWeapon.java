package engine.weapons;

import engine.Entity;
import engine.Skin;
import sound.MSound;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class TrapWeapon extends Weapon{
    protected TrapWeapon(String name, List<MSound> sounds, double range, long cooldown, Skin weaponSkin, ImageIcon weaponIcon) {
        super(name, sounds, range, 0, 0, cooldown, weaponSkin, weaponIcon);
    }

    protected TrapWeapon(Weapon weapon) {
        super(weapon);
    }

    @Override
    public boolean attack(Entity entity) {
        playNextSound();
        getCooldown().start();
        getWeaponSkin().start(true);

        entity.getManager().addRandomTrapBehindEntity(entity);

        return false;
    }
}
