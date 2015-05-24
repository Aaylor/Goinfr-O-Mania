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

public class BoardView extends Background implements Observer {

    private static final int    UI_HEIGHT    = 50;
    private static final double FRISE_HEIGHT = 10;

    private final int HEART_PADDING = 5;

    private String lifeLabel;
    private String scoreLabel;
    private String weaponLabel;
    private String timeLabel;

    private Image gameUI;
    private Image barUI;

    private Image emptyHeart;
    private Image filledHeart;

    private Font  fontUI;
    private Board board;
    private boolean paused;
    private Font font;

    public BoardView(Board board, String background) {
        super(background);
        this.board = board;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/LearningCurve.ttf")).deriveFont(Font.PLAIN, 60);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameUI = resizeInitialImage("pictures/frise00.png", FRISE_HEIGHT, true);
        barUI  = resizeInitialImage("pictures/frise01.png", 6, false);

        try {
            File file = new File("fonts/Viking_n.ttf");
            fontUI = Font.createFont(Font.TRUETYPE_FONT, file)
                    .deriveFont(Font.PLAIN, 12);
        } catch (Exception e) {
            fontUI = null;
        }

        emptyHeart  = resizeInitialImage("pictures/heart00.png", 20, true);
        filledHeart = resizeInitialImage("pictures/heart01.png", 20, true);

        ResourceBundle bundle = MainFrame.getCurrentInstance().getBundle();
        lifeLabel   = bundle.getString("lifeUI");
        scoreLabel  = bundle.getString("scoreUI");
        weaponLabel = bundle.getString("weaponUI");
        timeLabel   = bundle.getString("chronoUI");

        setDoubleBuffered(true);
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    private Image resizeInitialImage(String path, double newValue, boolean what) {
        ImageIcon icon = new ImageIcon(path);

        double alpha;
        int nWidth;
        int nHeight;

        if (what) {
            alpha   = newValue / icon.getIconHeight();
            nWidth  = (int) (icon.getIconWidth() * alpha);
            nHeight = (int) newValue;
        } else {
            alpha   = newValue / icon.getIconWidth();
            nWidth  = (int) newValue;
            nHeight = (int) (icon.getIconHeight() * alpha);
        }

        BufferedImage image =
                new BufferedImage(nWidth, nHeight,
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
                icon.getImage(),
                0, 0, nWidth, nHeight, null
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
                    gameUI,
                    start, getRealHeight(),
                    gameUI.getWidth(null), gameUI.getHeight(null),
                    null
            );
            start += gameUI.getWidth(null);
        } while(start <= getWidth());

        /* Bar */
        int distance  = getWidth() / 4;
        int xPosition = distance;
        while (xPosition < getWidth()) {
            g2d.drawImage(barUI, xPosition, (int)(getRealHeight() + FRISE_HEIGHT),
                    barUI.getWidth(null), barUI.getHeight(null), null);
            xPosition += distance;
        }


        /* Glutton informations */
        if (em.getGlutton() != null) {
            Font initialFont = g2d.getFont();

            if (fontUI != null) {
                g2d.setFont(fontUI);
            }

            g2d.setColor(Color.white);

            FontMetrics fm = g2d.getFontMetrics(fontUI);

            int current_distance = 0;

            int middle =
                    getRealHeight() + ((int)FRISE_HEIGHT) +
                    ((getHeight() - getRealHeight() + ((int)FRISE_HEIGHT)) / 2) -
                            (fm.getHeight() / 2);

            // life
            g2d.drawString(lifeLabel, current_distance + 5, middle);
            int curX = current_distance + fm.stringWidth(lifeLabel) + 30;
            for (int i = 0; i < 6; i++) {
                if (i < board.getLevel().getEntityManager().getGlutton().getLife())
                    g2d.drawImage(filledHeart, curX, (middle - (emptyHeart.getHeight(null) / 2) - 5), null);
                else
                    g2d.drawImage(emptyHeart, curX, (middle - (emptyHeart.getHeight(null) / 2) - 5), null);
                curX += emptyHeart.getWidth(null) + HEART_PADDING;
            }
            current_distance += distance + barUI.getWidth(null);

            // score
            g2d.drawString(scoreLabel, current_distance + 5, middle);
            g2d.drawString(
                    String.format("%05d", board.getLevel().getScore().getValue()),
                    current_distance + fm.stringWidth(scoreLabel) + 50, middle
            );
            current_distance += distance + barUI.getWidth(null);

            // weapon
            g2d.drawString(weaponLabel, current_distance + 5, middle);
            g2d.drawString(
                    "*TODO*",
                    current_distance + fm.stringWidth(weaponLabel) + 50, middle
            );
            current_distance += distance + barUI.getWidth(null);

            // chrono
            g2d.drawString(timeLabel, current_distance + 5, middle);
            g2d.drawString(board.getLevel().getChrono().toString(),
                    current_distance + fm.stringWidth(timeLabel) + 50, middle);

            g2d.setFont(initialFont);
        }

        g2d.setColor(initialColor);

    }

    private void drawEntity(Graphics2D g2d, EntityView ev) {
        Entity entity = ev.getEntity();

        AffineTransform t = new AffineTransform();
        t.translate(entity.getX(), entity.getY());
        t.scale(1, 1);

        if (entity instanceof AbstractMovableEntity) {
            AbstractMovableEntity ame = (AbstractMovableEntity) entity;
            Circle c = ame.getBoundsCircle();

            double dx = entity.getCenterX() +
                    (c.getRadius() * Math.cos(ame.getDirectionRadian()));
            double dy = entity.getCenterY() +
                    (c.getRadius() * Math.sin(ame.getDirectionRadian()));

            double angle = ame.getDirectionRadian();

            g2d.rotate(angle, entity.getCenterX(), entity.getCenterY());
            g2d.drawImage(ev.getCurrentDrawing(), t, null);
            g2d.rotate(-angle, entity.getCenterX(), entity.getCenterY());
        }
        else {
            g2d.drawImage(ev.getCurrentDrawing(), t, null);
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
