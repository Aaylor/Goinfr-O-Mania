package engine;

import graphics.Circle;
import log.IGLog;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *  The skin class is to handle every buffered image of a given skin.
 */
public class Skin implements Cloneable {

    private BufferedImage[] perso;
    private int animation;
    private int movement;
    private int movementmax;

    private boolean oneTime;
    private boolean started;

    public Skin (BufferedImage[] perso, int movementmax){
        this.perso = perso;
        this.animation = 0;
        this.movement = -1;
        if(movementmax > 0)
            this.movementmax = movementmax;
        else
            this.movementmax = 0;
        started = false;
        oneTime = false;
    }

    public Skin(int width, int height) {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(r, g, b));
        g2d.fill(new Circle(0, 0, width / 2).getShape());

        perso =
                new BufferedImage[] { image };
    }

    /**
     * Move an image to show the next animation
     */
    public BufferedImage move(){
        if (started) {
            movement++;
            if (movement == movementmax) {
                animation++;
                movement = 0;
            }
            if (animation == perso.length) {
                animation = 0;
                if (oneTime) {
                    started = false;
                    return null;
                }
            }
            return this.perso[animation];
        }

        return null;
    }



    public boolean isStarted() {
        return started;
    }

    public void start() {
        started = true;
    }

    public void start(boolean oneTime) {
        this.oneTime = oneTime;
        start();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Skin s = (Skin) super.clone();

        s.perso         = perso.clone();
        s.animation     = 0;
        s.movement      = -1;
        s.movementmax   = movementmax;

        return s;
    }
}
