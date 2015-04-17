package graphics;

import log.IGLog;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;
import java.util.Stack;

/**
 * Created by tratost on 15/04/15.
 */
public class MainFrame extends JFrame {

    Stack<JPanel> stackPanel;

    public MainFrame(ResourceBundle bundle) throws HeadlessException {
        super(bundle.getString("title"));
        IGLog.write("Main frame creation.");
        stackPanel = new Stack<>();
        defaultFrameConfiguration(bundle);
    }

    private void defaultFrameConfiguration(ResourceBundle bundle) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setLocationByPlatform(true);

        ToolbarView bar = new ToolbarView(bundle);
        setJMenuBar(bar);

        MainMenuView menuView = new MainMenuView();
        addPanel(menuView);

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

    /**
     * Add the panel j to the stack in this frame and set him as the actual panel to show.
     * @param j The panel that we wants to add
     */
    public void addPanel(JPanel j){
        this.setVisible(false);
        stackPanel.add(j);
        this.setContentPane(j);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Pop the first panel of the stack and show the precedent.
     */
    public void popPanel(){
        this.setVisible(false);
        stackPanel.pop();
        this.setContentPane(stackPanel.peek());
        this.pack();
        this.setVisible(true);
    }

    /**
     * Pop all the Panel until we arrive to the Panel j
     * show the Panel j
     * @param j the panel on which we want to return
     */
    public void returnToPanel(JPanel j){
        this.setVisible(false);
        int pos = stackPanel.search(j);
        while(pos != 0){
            stackPanel.pop();
            pos--;
        }
        this.setContentPane(stackPanel.peek());
        this.pack();
        this.setVisible(true);
    }

    /**
     * Pop all the Panel until we arrive to the ith panel and then, show him
     * @param i the number of panel that we want to pop
     */
    public void returnToPanel(int i){
        this.setVisible(false);
        while(i != 0){
            stackPanel.pop();
            i--;
        }
        this.setContentPane(stackPanel.peek());
        this.pack();
        this.setVisible(true);
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
