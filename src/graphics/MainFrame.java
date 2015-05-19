package graphics;

import engine.*;
import engine.weapons.Weapon;
import log.IGLog;
import sound.SoundManager;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private Stack<JPanel> stackPanel;
    private ResourceBundle bundle;

    public MainFrame(ResourceBundle bundle) throws HeadlessException {
        super(bundle.getString("title"));
        this.bundle = bundle;
        IGLog.write("Main frame creation.");
        stackPanel = new Stack<>();
        defaultFrameConfiguration(bundle);
        instance = this;
    }

    public static MainFrame getCurrentInstance() {
        return instance;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    private void defaultFrameConfiguration(ResourceBundle bundle) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        ToolbarView bar = new ToolbarView(bundle);
        setJMenuBar(bar);
        //Modification Later ?
        setResizable(false);


        new MainMenuController(this, null, null);
        setVisible(true);

        pack();
    }

    private static void setSystemProperties() {
        final String os = System.getProperty("os.name");

        if (os.contains("Mac")) {

            IGLog.info("OSX detected. Changed some properties.");

            /* Menu bar setting for OSX */
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty(
                    "com.apple.mrj.application.apple.menu.about.name", "Name");

        } else {

        }
    }

    private static void loadInternalResources() {
        EntityAssociation.defaults();
        Weapon.defaults();
    }

    private static void loadExternalResources() {

    }

    /**
     * Add the panel j to the stack in this frame and set him as the actual panel to show.
     * @param j The panel that we wants to add
     */
    public void addPanel(JPanel j){
        stackPanel.add(j);
        this.setContentPane(j);
        j.requestFocus();
        this.pack();
    }

    /**
     * Pop the first panel of the stack and show the precedent.
     */
    public void popPanel(){
        stackPanel.pop();
        this.setContentPane(stackPanel.peek());
        stackPanel.peek().requestFocus();
        this.pack();
    }

    public JPanel headPanel() {
        return stackPanel.peek();
    }

    /**
     * Pop all the Panel until we arrive to the Panel j
     * show the Panel j
     * @param j the panel on which we want to return
     */
    public void returnToPanel(JPanel j){
        int pos = stackPanel.search(j);
        while(pos != 0){
            stackPanel.pop();
            pos--;
        }
        this.setContentPane(stackPanel.peek());
        this.pack();
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
                loadInternalResources();
                loadExternalResources();
                new MainFrame(bundle);
            }
        });
    }
}
