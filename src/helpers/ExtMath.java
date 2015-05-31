package helpers;

import graphics.Circle;

import java.awt.geom.Point2D;
import java.util.Random;

public class ExtMath {

    private ExtMath(){}

    private static final Random r = new Random();

    public static double addToAngle(double angle, double add) {
        double r = angle + add;
        while (r < 0) r += 360;
        while (r >= 360) r -= 360;

        return r;
    }

    public static double distance(double xa, double ya, double xb, double yb) {
        return Math.sqrt(Math.pow(xb - xa, 2) + Math.pow(yb - ya, 2));
    }

    public static double distance(Point2D p1, Point2D p2) {
        return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public static double circleDistance(Circle c1, Circle c2) {
        return distance(c1.getCenter(), c2.getCenter()) -
                c1.getRadius() - c2.getRadius();
    }

    public static int getRandomBewteen(int x, int y) {
        return r.nextInt(y - x) + x;
    }

}
