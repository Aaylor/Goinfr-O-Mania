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

    private class GluttonPanel extends JPanel {
        JLabel icon;

        public GluttonPanel() {
            setLayout(new GridLayout(1, 1));
            try {
                BufferedImage bufferedImage =
                        ImageIO.read(new File("pictures/Characters/goinfre/an3.png"));
                icon = new JLabel(new ImageIcon(bufferedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }

            make();
        }

        private void make() {
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            add(icon, gbc);

            gbc.gridx = 1;
            JLabel text = new JLabel(
                    "<html>" +
                    "<p>" + replaceByBr(bundle.getString("gluttonText")) + "</p>" +
                    "</html>"
            );
            text.setOpaque(false);
            add(text, gbc);
        }

    }

    public HelpMenu(ResourceBundle bundle) {
        setPreferredSize(new Dimension(400, 600));
        setSize(new Dimension(400, 600));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setModal(true);

        this.bundle = bundle;

        JTabbedPane p = new JTabbedPane();
        p.addTab("Test1", new GluttonPanel());
        p.addTab("Test2", new JPanel());
        add(p);

        setResizable(false);
        setVisible(true);
    }

}
