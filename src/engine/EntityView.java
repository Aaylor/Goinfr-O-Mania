package engine;

import log.IGLog;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class EntityView {

    private BufferedImage defaultSkin;


    /* Constructors */

    public EntityView(String defaultSkinPath) {
        try {
            defaultSkin = ImageIO.read(new File(defaultSkinPath));
        } catch (IOException e) {
            int grey = new Color(170, 170, 170).getRGB();
            IGLog.error("The file " + defaultSkinPath + " was not found.");
            defaultSkin = new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    defaultSkin.setRGB(i, j, grey);
                }
            }
        }
    }


    /* Getters */

    public BufferedImage getDefaultSkin() {
        return defaultSkin;
    }
}
