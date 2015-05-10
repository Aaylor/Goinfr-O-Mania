package graphics;

import engine.*;
import engine.weapons.Weapon;
import sound.MSound;
import sound.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by Aaylor, Tratost, PixelMan on 15/04/15.
 */
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
        parent.addPanel(mainMenuView);

        mainMusic = new MSound("erza","music/erza.mp3");
        mainMusic.play(0.35);

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




    /* Main Menu Controller functionalities */

    /**
     * Runs the main menu.
     */
    public void run(){
        mainMusic.stop();
        parent.setPreferredSize(new Dimension(500, 500));
        HashMap<String, String> punchSounds = new HashMap<>();
        punchSounds.put("left-punch", "sounds/left-punch.mp3");
        punchSounds.put("right-punch", "sounds/right-punch.mp3");
        Weapon.register("punch", Weapon.MELEE, punchSounds, 10, 1, 2, 1000);

        Glutton glutton = new Glutton(new Point(30, 30),
                new Dimension(30, 30), 3f, 0f, 5);


        glutton.setWeapon(Weapon.make("punch"));


        EntityManager manager = new EntityManager(
                glutton,
                new EntityView(new Skin(30, 30))
        );

        Level level = new Level(
                new Score(),
                new Goal(),
                manager,
                new SoundManager()
        );

        manager.addEntity(
                new Cake(new Point(130, 70), new Dimension(20, 20), null),
                new EntityView(new Skin(20, 20))
        );

        Nutritionist n = new Nutritionist(new Point(300, 234), new Dimension(30, 30), 2f, 0f, 5);
        n.setWeapon(Weapon.make("punch"));


        manager.addNutritionist(
                n,
                new EntityView(new Skin(30, 30))
        );

        Board board = new Board(new Player("Test"), level);

        BoardView view = new BoardView(board);
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
