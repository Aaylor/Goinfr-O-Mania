package engine;

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
        this.leftCpt = 0;
        this.right = right;
        this.rightCpt = 0;
        this.top = top;
        this.topCpt = 0;
        this.down = down;
        this.downCpt = 0;
        this.onDestruct = onDestruct;
        this.onDestructCpt = 0;
        this.onAppears = onAppears;
        this.onAppearsCpt = 0;
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
        this.leftCpt = this.rightCpt = this.topCpt = this.downCpt = 0;
        this.onDestruct = onDestruct;
        this.onDestructCpt = 0;
        this.onAppears = onAppears;
        this.onAppearsCpt = 0;
        fix = true;
    }


    /* TODO : add function to get the correct buffered image of a skin */
}
