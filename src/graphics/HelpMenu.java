package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class HelpMenu extends JDialog {

    final ResourceBundle bundle;

    private String replaceByBr(String text) {
        return text.replace("\n", "<br />");
    }

    private JLabel createLabel(String path) {
        try {
            BufferedImage bufferedImage =
                    ImageIO.read(new File(path));
            return new JLabel(new ImageIcon(bufferedImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void make(JPanel panel, GridBagConstraints gbc, int y,
                      String path, String bundleLabel) {
        JLabel icon = createLabel(path);

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(icon, gbc);

        gbc.gridx = 1;
        JLabel text = new JLabel(
                "<html>" +
                        "<p>" + replaceByBr(bundle.getString(bundleLabel)) + "</p>" +
                        "</html>"
        );
        text.setOpaque(false);
        panel.add(text, gbc);
    }

    private class GluttonPanel extends JPanel {
        GridBagConstraints gbc;

        public GluttonPanel() {
            setLayout(new GridLayout(1, 1));
            gbc = new GridBagConstraints();
            make(this, gbc, 0, "pictures/Characters/goinfre/an3.png", "gluttonText");
        }
    }

    private class NutritionistPanel extends JPanel {
        GridBagConstraints gbc;

        public NutritionistPanel() {
            setLayout(new GridLayout(3, 3));
            gbc = new GridBagConstraints();

            make(this, gbc, 0, "pictures/Characters/Nutritioniste1/an3.png",
                    "cakeChaserText");
            make(this, gbc, 1, "pictures/Characters/Nutritioniste2/an3.png",
                    "gluttonChaserText");
            make(this, gbc, 2, "pictures/Characters/Nutritioniste3/an3.png",
                    "trapShooterText");
        }
    }

    private class CakePanel extends JPanel {
        GridBagConstraints gbc;

        public CakePanel() {
            setLayout(new GridLayout(5, 5));
            gbc = new GridBagConstraints();

            make(this, gbc, 0, "pictures/cake/cake_cream.png", "lifeCakeText");
            make(this, gbc, 1, "pictures/cake/cake_citron.png", "superLifeCakeText");
            make(this, gbc, 2, "pictures/cake/cake_choco.png", "hyperLifeCakeText");
            make(this, gbc, 3, "pictures/cake/cake_fraise.png", "speedCakeText");
            make(this, gbc, 4, "pictures/cake/cake_citrus.png", "invulCakeText");
        }
    }

    public HelpMenu(ResourceBundle bundle) {
        setPreferredSize(new Dimension(400, 700));
        setSize(new Dimension(400, 700));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setModal(true);

        this.bundle = bundle;

        JTabbedPane p = new JTabbedPane();
        p.addTab("Test1", new GluttonPanel());
        p.addTab("Test2", new NutritionistPanel());
        p.addTab("Test3", new CakePanel());
        p.addTab("Test4", new NutritionistPanel());
        add(p);

        setResizable(false);
        setVisible(true);
    }

}
