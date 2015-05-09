package graphics;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {

    private String path;
    private Image image;
    private JComponent parentComponent;

    private int width;
    private int height;

    public Background(String path) {
        ImageIcon icon = new ImageIcon(path);

        this.path  = path;
        image = icon.getImage();
        parentComponent = null;

        width  = icon.getIconWidth();
        height = icon.getIconHeight();

        this.setVisible(true);
        this.setOpaque(true);
    }

    //Still utils ?
    public Background(String path, JComponent parentComponent) {
        this.path = path;
        image = new ImageIcon(path).getImage();
        this.parentComponent = parentComponent;

        width  = parentComponent.getWidth();
        height = parentComponent.getHeight();
    }

    protected void sizeOfPictures(){
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setSize(size);
    }

    private void updateParentComponent() {
        if (parentComponent != null) {
            width  = parentComponent.getWidth();
            height = parentComponent.getHeight();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateParentComponent();
        g.drawImage(image, 0, 0, width, height, null);
    }
}
