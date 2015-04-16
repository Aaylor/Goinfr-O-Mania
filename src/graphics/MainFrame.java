package graphics;

import log.IGLog;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created by tratost on 15/04/15.
 */
public class MainFrame extends JFrame {

    public MainFrame(ResourceBundle bundle) throws HeadlessException {
        super(bundle.getString("title"));
        IGLog.write("Main frame creation.");
        defaultFrameConfiguration(bundle);
    }

    private void defaultFrameConfiguration(ResourceBundle bundle) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 5000));
        setLocationByPlatform(true);

        ToolbarView bar = new ToolbarView(bundle);
        setJMenuBar(bar);

        setVisible(true);
        pack();
    }

    private static void setSystemProperties() {
        final String os = System.getProperty("os.name");

        if (os.indexOf("Mac") >= 0) {

            IGLog.info("OSX detected. Changed some properties.");

            /* Menu bar setting for OSX */
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty(
                    "com.apple.mrj.application.apple.menu.about.name", "Name");

        } else {

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                IGLog.write("Launching the interface.");
                IGLog.info("Searching for bundle.");
                ResourceBundle bundle =
                        ResourceBundle.getBundle("lang/bundle");
                IGLog.info("Bundle loaded.");
                setSystemProperties();
                new MainFrame(bundle);
            }
        });
    }
}
