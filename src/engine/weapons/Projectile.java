package engine.weapons;

import engine.AbstractMovableEntity;
import engine.Entity;

import java.awt.*;

public class Projectile extends AbstractMovableEntity implements Cloneable {

    private int damage;
    private Weapon owner;

    public Projectile() {
    }

    public Projectile(float speed, float direction) {
        super(speed, direction);
    }

    public Projectile(Point startPosition, Dimension size, float speed, float direction) {
        super(startPosition, size, speed, direction);
    }

    @Override
    public void effect(Entity e) {

    }
}
