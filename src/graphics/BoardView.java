package graphics;

import engine.Entity;
import engine.EntityManager;
import engine.EntityView;
import engine.Level;

import javax.swing.*;
import java.awt.*;
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
        System.out.println("Board view: update()");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("repaint");

        Graphics2D graphics2D = (Graphics2D)g;

        EntityManager manager = board.getLevel().getEntityManager();

        EntityView gluttonView = manager.getGluttonView();
        Entity glutton = gluttonView.getEntity();
        graphics2D.drawImage(gluttonView.getCurrentDrawing(), null,
                glutton.getX(), glutton.getY());

        Collection<EntityView> nutritionists = manager.getNutritionistsView();
        for (EntityView e : nutritionists) {
            Entity entity = e.getEntity();
            graphics2D.drawImage(e.getCurrentDrawing(), null,
                    entity.getX(), entity.getY());
        }

        Collection<EntityView> others = manager.getOthersView();
        for (EntityView e : others) {
            Entity entity = e.getEntity();
            graphics2D.drawImage(e.getCurrentDrawing(), null,
                    entity.getX(), entity.getY());
        }
    }
}
