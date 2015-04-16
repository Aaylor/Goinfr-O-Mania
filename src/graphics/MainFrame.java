package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created by tratost on 15/04/15.
 */
public class MainFrame extends JFrame {

    public MainFrame(ResourceBundle bundle) throws HeadlessException {
        super(bundle.getString("title"));
        defaultFrameConfiguration();
    }

    private void defaultFrameConfiguration() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ResourceBundle bundle =
                        ResourceBundle.getBundle("lang/bundle");
                new MainFrame(bundle);
            }
        });
    }
}
