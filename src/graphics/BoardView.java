package graphics;

import engine.Entity;
import engine.EntityManager;
import engine.EntityView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class BoardView extends JPanel implements Observer {

    private Board board;

    public BoardView(Board board) {
        super();
        this.board = board;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;

        EntityManager manager = board.getLevel().getEntityManager();

        EntityView gluttonView = manager.getGluttonView();
        Entity glutton = gluttonView.getEntity();

        AffineTransform t = new AffineTransform();
        t.translate(glutton.getX(), glutton.getY());
        t.scale(1, 1);

        graphics2D.drawImage(gluttonView.getCurrentDrawing(), t, null);

        Collection<EntityView> nutritionists = manager.getNutritionistsView();
        for (EntityView e : nutritionists) {
            Entity entity = e.getEntity();

            AffineTransform nt = new AffineTransform();
            nt.translate(entity.getX(), entity.getY());
            nt.scale(1, 1);

            graphics2D.drawImage(e.getCurrentDrawing(), nt, null);
        }

        Collection<EntityView> others = manager.getOthersView();
        for (EntityView e : others) {
            Entity entity = e.getEntity();

            AffineTransform ot = new AffineTransform();
            ot.translate(entity.getX(), entity.getY());
            ot.scale(1, 1);

            graphics2D.drawImage(e.getCurrentDrawing(), ot, null);
        }
    }
}
