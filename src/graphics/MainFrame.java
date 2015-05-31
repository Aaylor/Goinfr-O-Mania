package graphics;

import engine.*;
import engine.weapons.Weapon;
import log.IGLog;
import sound.MMusic;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private Stack<JPanel> stackPanel;

    private ClassLoaderUTF8 classLoaderUTF8;
    private ResourceBundle bundle;

    private MMusic mainMusic;

    private Settings settings;
    private Scores scores;

    public MainFrame() throws HeadlessException {
        super();
        this.bundle = bundle;
        this.mainMusic = null;
        this.settings = new Settings();
        classLoaderUTF8 = new ClassLoaderUTF8(getClass().getClassLoader());
        this.bundle = ResourceBundle.getBundle("lang/bundle", settings.getLocale(), classLoaderUTF8);

        this.setTitle(bundle.getString("title"));

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

    public Settings getSettings() {
        return settings;
    }

    public MMusic getMainMusic() {
        return mainMusic;
    }

    public ClassLoaderUTF8 getClassLoaderUTF8() {
        return classLoaderUTF8;
    }

    public void setMainMusic(MMusic mainMusic) {
        if(this.mainMusic!=null && this.mainMusic.isPlaying())
            this.mainMusic.stop();
        this.mainMusic = mainMusic;
        this.mainMusic.setVolume(settings.getVolume());
        this.mainMusic.playInfinite();
    }

    /* Setters */

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    /* Frame Configuration */

    private void defaultFrameConfiguration(ResourceBundle bundle) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        ToolbarView bar = new ToolbarView(bundle);
        setJMenuBar(bar);
        //Modification Later ?
        setResizable(false);


        new MainMenuController(this, null, null);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
        Weapon.defaults();
        EntityAssociation.defaults();
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

    public void updateLangOnAllPanels(){
        for(JPanel p: stackPanel){
            if(p instanceof LanguageSwapable)
                ((LanguageSwapable) p).swapLang(this.bundle);
        }
    }


    /* Main */

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.setProperty( "file.encoding", "UTF-8" );
                IGLog.write("Launching the interface.");
                IGLog.info("Searching for bundle.");
                IGLog.info("Bundle loaded.");
                setSystemProperties();
                loadInternalResources();
                loadExternalResources();
                new MainFrame();
            }
        });
    }
}
