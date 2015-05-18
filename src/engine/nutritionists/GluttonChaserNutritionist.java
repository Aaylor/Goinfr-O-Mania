package engine.nutritionists;

import engine.EntityManager;
import engine.Movable;
import engine.weapons.Weapon;
import graphics.Circle;
import helpers.ExtMath;

import java.awt.*;
import java.awt.geom.Point2D;

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
    public void nextStep(EntityManager manager) {
        Point2D playerPosition = manager.getGlutton().getCenter();
        Circle playerCircle    = manager.getGlutton().getBoundsCircle();

        Point2D nutritionistPosition = this.getCenter();
        double  nutritionistAngle    = this.getDirection();
        Circle  nutritionistCircle   = this.getBoundsCircle();

            /* Third step : calculate the opposite angle */
        double  oppositeAngle = ExtMath.addToAngle(nutritionistAngle, 180);

            /* Fourth step : calculate the new needed angle to face the player. */
        double dx = playerPosition.getX() - nutritionistPosition.getX();
        double dy = playerPosition.getY() - nutritionistPosition.getY();
        double nextAngle = ExtMath.addToAngle(Math.toDegrees(Math.atan2(dy, dx)), 360);

            /* If the nutritionist can attack the player, then he does. */
        if (this.getWeapon() != null) {
            Weapon w = this.getWeapon();

            double weaponRadius = w.getRange() + nutritionistCircle.getRadius();
            Circle weaponCircle = new Circle(
                    this.getCenterX() - weaponRadius,
                    this.getCenterY() - weaponRadius,
                    weaponRadius
            );

            if (weaponCircle.intersects(playerCircle)) {
                manager.attack(this);
                return;
            }
        }

            /* Fifth step : if the nutritionist doesn't face the glutton,
             * then turn to the right side
             */
        if (nextAngle <= nutritionistAngle - 2 || nextAngle >= nutritionistAngle + 2) {
            if (nutritionistAngle >= 180) {
                if (nextAngle <= nutritionistAngle && nextAngle >= oppositeAngle) {
                    this.move(Movable.Direction.LEFT);
                } else {
                    this.move(Movable.Direction.RIGHT);
                }
            } else {
                if (nextAngle >= nutritionistAngle && nextAngle <= oppositeAngle) {
                    this.move(Movable.Direction.RIGHT);
                } else {
                    this.move(Movable.Direction.LEFT);
                }
            }
        }

            /* Sixth step : if the nutritionist face quite correctly the player,
             * then he moves.
             */
        if (nextAngle >= nutritionistAngle - 20 && nextAngle <= nutritionistAngle + 20) {
            this.move(Movable.Direction.FRONT);
        }

    }
}
