package graphics;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Circle {

    private Point2D position;

    private double radius;
    private double diameter;

    public Circle(double x, double y, double radius) {
        position = new Point2D.Double(x, y);
        this.radius = radius;
        this.diameter = radius * 2;
    }

    public Circle(Point2D position, double radius) {
        this.position = position;
        this.radius = radius;
        this.diameter = radius * 2;
    }

    public Point2D getPosition() {
        return position;
    }

    public double getX() {
        return getPosition().getX();
    }

    public double getY() {
        return getPosition().getY();
    }

    public Point2D getCenter() {
        return new Point2D.Double(getCenterX(), getCenterY());
    }

    public double getCenterX() {
        return getX() + radius;
    }

    public double getCenterY() {
        return getY() + radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public boolean intersects(Circle circle) {
        double dx = getCenter().getX() - circle.getCenter().getX();
        double dy = getCenter().getY() - circle.getCenter().getY();

        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance <= getRadius() + circle.getRadius();
    }

    public Ellipse2D getShape() {
        return new Ellipse2D.Double(getX(), getY(), getDiameter(), getDiameter());
    }
}
