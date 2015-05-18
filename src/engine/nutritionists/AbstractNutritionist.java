package engine.nutritionists;

import engine.AbstractMovableEntity;
import engine.Entity;
import engine.Movable;
import engine.weapons.Attackable;
import engine.weapons.Weapon;
import graphics.Circle;
import helpers.ExtMath;
import log.IGLog;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 *  The nutritionist entity.
 */
public abstract class AbstractNutritionist extends AbstractMovableEntity
        implements Attackable, ArtificialIntelligence {

    /**
     *  The number of life.
     */
    int nbLifes;

    public AbstractNutritionist(int nbLifes) {
        super();
        this.nbLifes = nbLifes;
        setCrossable(false);
    }

    public AbstractNutritionist(float speed, float direction, int nbLifes) {
        super(speed, direction);
        this.nbLifes = nbLifes;
        setCrossable(false);
    }

    public AbstractNutritionist(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction);
        this.nbLifes = nbLifes;
        setCrossable(false);
    }

    @Override
    public void effect(Entity e) {

    }

    @Override
    public void takeDamage(int damage) {
        nbLifes -= damage;

        IGLog.info(this + " a pris " + damage + " dommages. " + nbLifes + " pv restants.");

        if (nbLifes <= 0) {
            getManager().removeNutritionist(this);
        }
    }



    /* Default algorithm */
    public double calculateNextAngle(Entity entity) {
        Point2D entityPosition = entity.getCenter();
        Circle  entityCircle   = entity.getBoundsCircle();

        double opposite_angle = ExtMath.addToAngle(getDirection(), 180);

        double dx = entity.getCenterX() - getCenterX();
        double dy = entity.getCenterY() - getCenterY();

        return ExtMath.addToAngle(Math.toDegrees(Math.atan2(dy, dx)), 360);
    }

    public void moveToEntity(Entity entity) {
        double angle         = getDirection();
        double oppositeAngle = ExtMath.addToAngle(angle, 180);
        double nextAngle     = calculateNextAngle(entity);

        if (nextAngle <= angle - 2 || nextAngle >= angle + 2) {
            if (angle >= 180) {
                if (nextAngle <= angle && nextAngle >= oppositeAngle) {
                    this.move(Movable.Direction.LEFT);
                } else {
                    this.move(Movable.Direction.RIGHT);
                }
            } else {
                if (nextAngle >= angle && nextAngle <= oppositeAngle) {
                    this.move(Movable.Direction.RIGHT);
                } else {
                    this.move(Movable.Direction.LEFT);
                }
            }
        }

        if (nextAngle >= angle - 30 && nextAngle <= angle + 30) {
            this.move(Movable.Direction.FRONT);
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
}
