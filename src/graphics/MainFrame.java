package graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tratost on 15/04/15.
 */
public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        super("All'a bataille !!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
