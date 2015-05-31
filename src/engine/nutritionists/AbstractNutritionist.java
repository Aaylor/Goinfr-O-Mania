package engine.nutritionists;

import engine.AbstractMovableEntity;
import engine.Entity;
import engine.Movable;
import engine.Valuable;
import engine.weapons.Attackable;
import engine.weapons.Weapon;
import graphics.Circle;
import helpers.ExtMath;
import log.IGLog;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

/**
 *  The nutritionist entity.
 */
public abstract class AbstractNutritionist extends AbstractMovableEntity
        implements Attackable, ArtificialIntelligence, Valuable {

    public AbstractNutritionist(int nbLifes) {
        super();
        setLife(nbLifes);
        setCrossable(false);
    }

    public AbstractNutritionist(float speed, float direction, int nbLifes) {
        super(speed, direction);
        setLife(nbLifes);
        setCrossable(false);
    }

    public AbstractNutritionist(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction);
        setLife(nbLifes);
        setCrossable(false);
    }

    @Override
    public boolean takeDamage(int damage) {
        if (!getInvulnerable()) {
            setLife(getLife() - damage);
            IGLog.info(this + " a pris " + damage + " dommages. " + getLife() + " pv restants.");
            return getLife() <= 0;
        } else {
            IGLog.info(this + " a pris des dommages, mais est invulnérable. Dommage !");
            return false;
        }
    }


    /* Default algorithm */
    public double calculateNextAngle(Entity entity) {
        double dx = entity.getCenterX() - getCenterX();
        double dy = entity.getCenterY() - getCenterY();

        return ExtMath.addToAngle(Math.toDegrees(Math.atan2(dy, dx)), 360);
    }

    public void moveToEntity(Entity entity) {
        double angle = getDirection();
        double oppositeAngle = ExtMath.addToAngle(angle, 180);
        double nextAngle = calculateNextAngle(entity);

        boolean frontMove = nextAngle >= angle - 30 && nextAngle <= angle + 3;
        boolean angleMove = nextAngle <= angle - 2 || nextAngle >= angle + 2;
        boolean b = frontMove && angleMove;

        if (angleMove) {
            if (angle >= 180) {
                if (nextAngle <= angle && nextAngle >= oppositeAngle) {
                    this.move(Movable.Direction.LEFT, b);
                } else {
                    this.move(Movable.Direction.RIGHT, b);
                }
            } else {
                if (nextAngle >= angle && nextAngle <= oppositeAngle) {
                    this.move(Movable.Direction.RIGHT, b);
                } else {
                    this.move(Movable.Direction.LEFT, b);
                }
            }
        }

        if (frontMove) {
            this.move(Movable.Direction.FRONT, b);
        }
    }

    public void attackIfPossible(Entity who) {
        if (this.getWeapon() != null) {
            Weapon w = this.getWeapon();

            double weaponRadius = w.getRange() + getBoundsCircle().getRadius();
            Circle weaponCircle = new Circle(
                    this.getCenterX() - weaponRadius,
                    this.getCenterY() - weaponRadius,
                    weaponRadius
            );

            if (weaponCircle.intersects(who.getBoundsCircle())) {
                getManager().attack(this);
                return;
            }
        }
    }

    public Entity getClosestEntity(List<Entity> entities) {
        double minDistance = 0;
        Entity min = null;

        Circle curCircle = getBoundsCircle();

        for (Entity e : entities) {
            double distance = ExtMath.circleDistance(curCircle, e.getBoundsCircle());
            if (min == null || distance < minDistance) {
                min = e;
                minDistance = distance;
            }
        }

        return min;
    }

}
