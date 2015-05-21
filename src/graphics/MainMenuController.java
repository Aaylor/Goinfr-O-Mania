package graphics;

import engine.*;
import engine.nutritionists.AbstractNutritionist;
import engine.nutritionists.CakeChaserNutritionist;
import engine.nutritionists.GluttonChaserNutritionist;
import engine.weapons.Weapon;
import log.IGLog;
import sound.MSound;
import sound.SoundManager;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {

    /* Attributes */

    /**
     * The game main menu that must be controlled
     */
    private MainMenu mainMenu;
    private MainMenuView mainMenuView;
    private MSound mainMusic;
    private MainFrame parent;

    /**
     * The controller of the actual level
     */
    private Level level;

    /* TODO : Add those attributes :
        - Difficulty controller
        - Scores controller
        - Save controller
        - Level edition controller
     */


    /* Constructors */

    public MainMenuController(MainFrame parent, MainMenu mainMenu, Level level) {
        this.parent = parent;
        this.mainMenuView = new MainMenuView(parent);
        mainMenuView.setController(this);
        parent.addPanel(mainMenuView);

        mainMusic = new MSound("erza","music/erza.mp3");
        mainMusic.playInfinite();

        addControllers();

        this.mainMenu = mainMenu;
        this.level = level;
    }



    /* Getters */

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Level getLevel() {
        return level;
    }

    public MSound getMainMusic() {
        return mainMusic;
    }

/* Main Menu Controller functionalities */

    /**
     * Runs the main menu.
     */
    public void run(){
        mainMusic.stop();
        parent.setPreferredSize(parent.getSize());

        Level level = new Level(
                new Score(),
                new Goal(),
                new SoundManager()
        );


        Board board = new Board(new Player("Test"), level);

        BoardView view = new BoardView(board);
        view.setSize(parent.getSize());
        parent.addPanel(view);
        BoardController controller = new BoardController(board, view);
        controller.start();

        view.setFocusable(true);
        /* TODO : run() */
    }



    /* /!\ Not implemented yet /!\ */

    /**
     * Changes the difficulty of the game
     */
    public void changeDifficulty(){
        /* TODO : changeDifficulty() */
    }

    /**
     * Load a game.
     */
    public void loadGame(){
        /* TODO : loadGame() */
    }

    /**
     * Save the current game.
     */
    public void saveGame(){
        /* TODO : saveGame() */
    }

    /**
     * TODO : options() documentation
     */
    public void options(){
        /* TODO : options() */
    }

    /**
     * Shows the scores of the several games.
     */
    public void showScores(){
        /* TODO : showScores () */
    }

    private void addControllers(){
        mainMenuView.getPlay().addActionListener(this);
        mainMenuView.getLoad().addActionListener(this);
        mainMenuView.getOptions().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==mainMenuView.getPlay()){
            run();
        }
        else if(e.getSource()==mainMenuView.getLoad()){
            System.out.println("load !");
        }
        else if(e.getSource()==mainMenuView.getOptions()){
            System.out.println("options");
        }
        else{
            System.out.println("Erreur !!");
        }
    }

     /* /!\ Not implemented yet /!\ */


}
