package engine;

import log.IGLog;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *  The skin class is to handle every buffered image of a given skin.
 */
public class Skin {

    private BufferedImage[] left;
    private int leftCpt;

    private BufferedImage[] right;
    private int rightCpt;

    private BufferedImage[] top;
    private int topCpt;

    private BufferedImage[] down;
    private int downCpt;
    
    private BufferedImage[] onDestruct;
    private int onDestructCpt;

    private BufferedImage[] onAppears;
    private int onAppearsCpt;

    private boolean fix = false;

    /**
     *  Create a skin with given buffered images arrays.
     *  @param left handling the left position
     *  @param right handling the right position
     *  @param top handling the top position
     *  @param down handling the down position
     *  @param onDestruct skin on destruction
     *  @param onAppears skin on appearance
     */
    public Skin(BufferedImage[] left, BufferedImage[] right, BufferedImage[] top,
                BufferedImage[] down, BufferedImage[] onDestruct,
                BufferedImage[] onAppears) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.down = down;
        this.onDestruct = onDestruct;
        this.onAppears = onAppears;
        reinit();
    }

    /**
     *  Create a skin with given buffered images arrays.
     *  The skin is considered as fixed (ie. even if it moves, it doesn't
     *  have differences on the current skin).
     *  @param fixed fixed skin
     *  @param onDestruct skin on destruction
     *  @param onAppears skin on appearance
     */
    public Skin(BufferedImage[] fixed, BufferedImage[] onDestruct,
                BufferedImage[] onAppears) {
        this.left = this.right = this.top = this.down = fixed;
        this.onDestruct = onDestruct;
        this.onAppears = onAppears;
        fix = true;
        reinit();
    }

    public Skin(String folder) {
        /* TODO: handle skin folder *
           It should be like:
            left00.jpg
            left01.jpg
            ...
            right00.jpg
            ...
            ...

            Every others files will be ignored (will be printing on the log).
            If fix.jpg is found, the Skin is considered as fixed.
         */
    }

    public Skin(int width, int height) {
        int grey = new Color(170, 170, 170).getRGB();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, grey);
            }
        }

        right = top = down = onDestruct = onAppears = left =
                new BufferedImage[] { image };
    }


    /* TODO : add function to get the correct buffered image of a skin */
    public void reinit() {
        leftCpt = rightCpt = topCpt = downCpt = onDestructCpt = onAppearsCpt = 0;
    }
}
