package helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ExtGraphics {

    public static Image resizeInitialImage(String path, double newValue, boolean what) {
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

}
