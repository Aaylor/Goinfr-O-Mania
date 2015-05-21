package graphics;

import engine.*;
import engine.nutritionists.AbstractNutritionist;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class BoardView extends JPanel implements Observer {

    private static final int    UI_HEIGHT    = 50;
    private static final double FRISE_HEIGHT = 10;

    private Image gameUi;
    private Board board;
    private boolean paused;
    private Font font;

    public BoardView(Board board) {
        super();
        this.board = board;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/LearningCurve.ttf")).deriveFont(Font.PLAIN, 60);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameUi = resizeInitialImage("pictures/frise00.png");
        setDoubleBuffered(true);
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    private Image resizeInitialImage(String path) {
        ImageIcon gameUi = new ImageIcon(path);
        double alpha  = FRISE_HEIGHT / gameUi.getIconHeight();
        int widthp = (int) (gameUi.getIconWidth() * alpha);

        BufferedImage image =
                new BufferedImage(widthp, (int)FRISE_HEIGHT,
                        BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = image.createGraphics();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(
                gameUi.getImage(),
                0, 0,
                widthp, (int) FRISE_HEIGHT,
                null
        );

        return image;
    }

    private int getRealHeight() {
        return getHeight() - UI_HEIGHT;
    }

    @Override
    public Dimension getSize() {
        return new Dimension(getWidth(), getHeight() - UI_HEIGHT);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    private void drawUI(Graphics2D g2d) {
        EntityManager em = board.getLevel().getEntityManager();

        Color initialColor = g2d.getColor();

        /* Rectangle */
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, getRealHeight(), getWidth(), UI_HEIGHT);

        /* Frise */
        int start  = 0;
        do {
            g2d.drawImage(
                    gameUi,
                    start, getRealHeight(),
                    gameUi.getWidth(null), gameUi.getHeight(null),
                    null
            );
            start += gameUi.getWidth(null);
        } while(start <= getWidth());

        /* Glutton informations */
        /* FIXME : real ui for those informations. */
        if (em.getGlutton() != null) {
            g2d.setColor(Color.white);

            // life
            g2d.drawString(em.getGlutton().getLife() + "/" + em.getGlutton().getMaxLife(),
                    10, getHeight() - 15);

            // score
            /* Fixme : use bundle here. */
            g2d.drawString(
                    "Score : " + board.getLevel().getScore(), 200, getHeight() - 15);

            // weapon
            g2d.drawString(
                    "Weapon : Fistiropoing", 400, getHeight() - 15); /* TODO */

            // chrono
            g2d.drawString(
                    "Chrono : " + board.getChrono().toString(), 600, getHeight() - 15);
        }

        g2d.setColor(initialColor);

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

        drawUI(graphics2D);

        /* Draw the glutton */
        if (manager.getGlutton() != null)
            drawEntity(graphics2D, manager.getGluttonView());

        /* Draw nutritionists */

        for (EntityView e : manager.getNutritionistsView())
            drawEntity(graphics2D, e);

        /* Draw other entities */
        for (EntityView e : manager.getOthersView())
            drawEntity(graphics2D, e);

        if (isPaused()) {
            String pause = "Pause";
            graphics2D.setFont(font);
            graphics2D.setColor(new Color(200, 14, 32));

            Rectangle2D rect = graphics2D.getFontMetrics(font)
                    .getStringBounds(pause, null);

            double x = getWidth()  - rect.getWidth() - 20;
            double y = getHeight() - rect.getHeight();

            graphics2D.drawString(pause, (int) x, (int) y);
        }
    }
}
