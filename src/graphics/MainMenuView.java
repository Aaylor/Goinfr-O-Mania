package graphics;

import log.IGLog;
import sound.MSound;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MainMenuView extends Background implements Observer, Resumable, LanguageSwapable {

    private MainFrame framep;
    private MainMenuController controller;

    private JLabel title;
    private MainMenuButton play;
    private MainMenuButton load;
    private MainMenuButton options;
    private MainMenuButton scores;
    private MainMenuButton quit;
    private GridBagConstraints gbc;
    private Font font;


    //CONSTRUCTOR

    public MainMenuView(MainFrame framep) {
        super("pictures/cake.jpg");
        this.framep = framep;
        try {
            File file = new File("fonts/newyorkescape.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(Font.PLAIN, 60);
        }
        catch(Exception e){
            font = null;
        }
        instantiateGridBagConstraints();
        instantiateTitle();
        instantiateButtons();
        sizeOfPictures();
    }

    public void setController(MainMenuController controller) {
        this.controller = controller;
    }

    private void instantiateGridBagConstraints(){
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(0, 10, 0, 10);
    }

    private void instantiateTitle(){
        ResourceBundle bundle = framep.getBundle();
        title = new JLabel(bundle.getString("gameMenuTitle"));
        if(this.font != null)
            title.setFont(font);
        title.setForeground(new Color(0x3B3B3B));
        this.add(title);
    }

    private void instantiateButtons() {
        ResourceBundle bundle = framep.getBundle();
        play = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("gameMenuPlayButton"),
                font);
        load = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("gameMenuLoadButton"),
                font);
        options = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("gameMenuOptionsButton"),
                font);
        scores = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("gameMenuScoresButton"),
                font);
        quit = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("gameMenuQuitButton"),
                font);
        this.add(play, gbc);
        this.add(load, gbc);
        this.add(options, gbc);
        this.add(scores, gbc);
        this.add(quit, gbc);
    }

    public void reinitializeButton() {
        play.reinit();
        load.reinit();
        options.reinit();
        scores.reinit();
        quit.reinit();
    }

    //GETTER

    public MainMenuButton getPlay() {
        return play;
    }

    public MainMenuButton getLoad() {
        return load;
    }

    public MainMenuButton getOptions() {
        return options;
    }

    public MainMenuButton getScores() {
        return  scores;
    }

    public MainMenuButton getQuit() {
        return quit;
    }

    //UPDATE

    @Override
    public void update(Observable o, Object arg) {
        //TODO
    }

    @Override
    public void resumeIt() {
        IGLog.write("MainMenuController::resumeIt()");
        if (!controller.getMainMusic().isPlaying()) {
            controller.getMainMusic().play();
        }

        reinitializeButton();
    }

    /**
     * Change all the language of all the texts visible on a component
     *
     * @param bundle The new bundle to use to get texts
     */
    @Override
    public void swapLang(ResourceBundle bundle) {
        title.setText(bundle.getString("gameMenuTitle"));
        play.setText(bundle.getString("gameMenuPlayButton"));
        load.setText(bundle.getString("gameMenuLoadButton"));
        options.setText(bundle.getString("gameMenuOptionsButton"));
        scores.setText(bundle.getString("gameMenuScoresButton"));
        quit.setText(bundle.getString("gameMenuQuitButton"));
    }
}
