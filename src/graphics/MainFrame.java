package graphics;

import engine.*;
import engine.weapons.Weapon;
import log.IGLog;
import sound.MSound;
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

    private MSound mainMusic;

    private Options options;
    private Scores scores;

    public MainFrame(ResourceBundle bundle) throws HeadlessException {
        super(bundle.getString("title"));
        this.bundle = bundle;
        this.mainMusic = null;
        this.options = new Options(100, Options.NORMAL);
        IGLog.write("Main frame creation.");
        stackPanel = new Stack<>();
        defaultFrameConfiguration(bundle);

        /* Scores setting */
        scores = new Scores("scores.xml");
        Runtime.getRuntime().addShutdownHook(
                new Thread() {
                    @Override
                    public void run() {
                        scores.save();
                    }
                }
        );

        instance = this;
    }



    /* Getters */

    public static MainFrame getCurrentInstance() {
        return instance;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public Scores getScores() {
        return scores;
    }

    public Options getOptions() {
        return options;
    }

    public MSound getMainMusic() {
        return mainMusic;
    }

    public void setMainMusic(MSound mainMusic) {
        if(this.mainMusic!=null && this.mainMusic.isPlaying())
            this.mainMusic.stop();
        this.mainMusic = mainMusic;
        this.mainMusic.play(options.getVolume());
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    /* Frame Configuration */

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


    /* Panels Management */

    /**
     * Add the panel j to the stack in this frame and set him as the actual panel to show.
     * @param j The panel that we wants to add
     */
    public void addPanel(JPanel j){
        stackPanel.add(j);
        changePanel(j, false);
    }

    /**
     * Pop the first panel of the stack and show the precedent.
     */
    public void popPanel(){
        popPanel(true);
    }

    public void popPanel(boolean resume) {
        stackPanel.pop();
        changePanel(headPanel(), resume);
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
        while (stackPanel.size() > 0) {
            if (headPanel() == j) {
                changePanel(headPanel(), true);
                return;
            } else {
                stackPanel.pop();
            }
        }

        IGLog.error("MainFrame::returnToPanel(Jpanel) -> panel not found...");
    }

    /**
     * Pop all the Panel until we arrive to the ith panel and then, show him
     * @param i the number of panel that we want to pop
     */
    public void returnToPanel(int i){
        while(i != 0){
            stackPanel.pop();
            i--;
        }

        changePanel(headPanel(), true);
    }

    public void backToFirstPanel() {
        while (stackPanel.size() != 1) {
            stackPanel.pop();
        }

        changePanel(headPanel(), true);
    }

    private void changePanel(JPanel p, boolean resume) {
        setContentPane(p);
        p.requestFocus();
        if (resume && p instanceof Resumable) {
            ((Resumable) p).resumeIt();
        }
        pack();
    }



    /* Main */

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
