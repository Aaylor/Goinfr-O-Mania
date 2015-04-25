package engine;

import log.IGLog;

import javax.swing.text.Position;
import java.awt.*;

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
        int nextX, nextY;
        Point position = getPoint();
        double angle = getDirectionRadian();
        System.out.println("angle: " +getDirection() + "\nradian: " + getDirectionRadian());
        switch (d) {
            case FRONT:
                nextX = (int) (position.getX() + (getSpeed() * Math.cos(angle)));
                nextY = (int) (position.getY() + (getSpeed() * Math.sin(angle)));
                setPoint(new Point(nextX, nextY));
                break;

            case BELOW:
                nextX = (int) (position.getX() - ((getSpeed() / 2) * Math.cos(angle)));
                nextY = (int) (position.getY() - ((getSpeed() / 2) * Math.sin(angle)));
                setPoint(new Point(nextX, nextY));
                break;

            case LEFT:
                setDirection(getDirection() + (1f * getSpeed()));
                break;

            case RIGHT:
                setDirection(getDirection() - (1f * getSpeed()));
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
