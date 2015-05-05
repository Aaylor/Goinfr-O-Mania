package graphics;

import engine.*;
import org.hamcrest.Matchers;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class BoardView extends JPanel implements Observer {

    private Board board;

    public BoardView(Board board) {
        super();
        this.board = board;

        setDoubleBuffered(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    private void drawEntity(Graphics2D g2d, EntityView ev) {
        Entity entity = ev.getEntity();

        AffineTransform t = new AffineTransform();
        t.translate(entity.getX(), entity.getY());
        t.scale(1, 1);

        g2d.drawImage(ev.getCurrentDrawing(), t, null);

        if (entity instanceof AbstractMovableEntity) {

            AbstractMovableEntity ame = (AbstractMovableEntity) entity;
            Circle c = ame.getBoundsCircle();

            double dx = entity.getCenterX() +
                    (c.getRadius() * Math.cos(ame.getDirectionRadian()));
            double dy = entity.getCenterY() +
                    (c.getRadius() * Math.sin(ame.getDirectionRadian()));

            g2d.draw(new Line2D.Double(entity.getCenter(), new Point2D.Double(dx, dy)));

        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;

        EntityManager manager = board.getLevel().getEntityManager();

        /* Draw the glutton */
        drawEntity(graphics2D, manager.getGluttonView());

        /* Draw nutritionists */
        for (EntityView e : manager.getNutritionistsView())
            drawEntity(graphics2D, e);

        /* Draw other entities */
        for (EntityView e : manager.getOthersView())
            drawEntity(graphics2D, e);
    }
}
