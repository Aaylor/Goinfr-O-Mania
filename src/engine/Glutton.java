package engine;

import log.IGLog;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by tratost on 13/04/15.
 */
public class Glutton extends AbstractMovableEntity {

    int nbLifes;

    //CONSTRUCTORS

    public Glutton(int nbLifes) {
        super();
        this.nbLifes = nbLifes;
    }

    public Glutton(float speed, float direction, int nbLifes) {
        super(speed, direction);
        this.nbLifes = nbLifes;
    }

    public Glutton(Point startPosition, Dimension size, float speed, float direction, int nbLifes) {
        super(startPosition, size, speed, direction);
        this.nbLifes = nbLifes;
    }

    @Override
    public void move(Direction d) {
        double nextX, nextY;
        Point2D position = getPoint();
        double angle = getDirectionRadian();
        switch (d) {
            case FRONT:
                nextX = position.getX() + (getSpeed() * Math.cos(angle));
                nextY = position.getY() + (getSpeed() * Math.sin(angle));
                setPoint(new Point2D.Double(nextX, nextY));
                break;

            case BELOW:
                nextX = position.getX() - (getSpeed() * Math.cos(angle));
                nextY = position.getY() - (getSpeed() * Math.sin(angle));
                setPoint(new Point2D.Double(nextX, nextY));
                break;

            case LEFT:
                System.out.println("direction: " + getDirection());
                System.out.println("new direction: " + (getDirection() - 1f));
                addDirection(-1f);
                break;

            case RIGHT:
                System.out.println("direction: " + getDirection());
                System.out.println("new direction: " + (getDirection() + 1f));
                addDirection(1f);
                break;

            default:
                IGLog.error("Glutton::move -> Unknown direction.");
                break;
        }
    }

    @Override
    public void effect(Entity e) {

    }

}
