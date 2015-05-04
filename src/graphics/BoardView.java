package graphics;

import engine.AbstractWeapon;
import engine.Entity;
import engine.EntityManager;
import engine.EntityView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
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
        Entity glutton = ev.getEntity();

        AffineTransform t = new AffineTransform();
        t.translate(glutton.getX(), glutton.getY());
        t.scale(1, 1);

        g2d.drawImage(ev.getCurrentDrawing(), t, null);

        /*
        AbstractWeapon weapon = glutton.getWeapon();
        if (weapon != null) {
            AffineTransform wt = new AffineTransform();
            Dimension gSize = glutton.getSize();
            Dimension wSize = weapon.getDimension();
            wt.translate(
                    glutton.getX() + (gSize.getWidth() - wSize.getWidth()),
                    glutton.getY() + ((gSize.getHeight() / 2) - (wSize.getHeight() / 2))
            );
            wt.scale(1, 1);
            g2d.drawImage(weapon.getSkin().test(), wt, null);
        }
        */

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
