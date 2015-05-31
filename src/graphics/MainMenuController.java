package graphics;

import engine.*;
import sound.MMusic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {

    /* Attributes */

    /**
     * The game main menu that must be controlled
     */
    private MainMenu mainMenu;
    private MainMenuView mainMenuView;
    private MMusic mainMusic;
    private MainFrame parent;

    /**
     * The controller of the actual level
     */
    private Level level;


    /* Constructors */

    public MainMenuController(MainFrame parent, MainMenu mainMenu, Level level) {
        this.parent = parent;
        this.mainMenuView = new MainMenuView(parent);
        mainMenuView.setController(this);
        parent.addPanel(mainMenuView);
        mainMusic = new MMusic("erza","music/erza.wav");
        parent.setMainMusic(mainMusic);

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

    public MMusic getMainMusic() {
        return mainMusic;
    }

    /* Main Menu Controller functionalities */

    /**
     * Runs the main menu.
     */
    public void run(){
        mainMusic.stop();
        parent.setPreferredSize(parent.getSize());

        Level level = new Level(new Score(),
                MainFrame.getCurrentInstance().getSettings().getDifficulty());


        Board board = new Board(new Player("Test"), level);
        BoardView view = new BoardViewGenerator(board).viewGeneration();
        view.setSize(parent.getSize());
        parent.addPanel(view);
        BoardController controller = new BoardController(board, view);
        controller.start();

        view.setFocusable(true);
    }


    /**
     * Launche the option menu
     */
    public void options(){
        new SettingsController(this.mainMenuView);
    }

    /**
     * Shows the scores of the several games.
     */
    public void showScores(){
        new ScoresController(this.mainMenuView);
    }

    private void addControllers(){
        mainMenuView.getPlay().addActionListener(this);
        mainMenuView.getLoad().addActionListener(this);
        mainMenuView.getOptions().addActionListener(this);
        mainMenuView.getScores().addActionListener(this);
        mainMenuView.getQuit().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==mainMenuView.getPlay()){
            run();
        }
        else if(e.getSource()==mainMenuView.getLoad()){
            JOptionPane.showMessageDialog(
                    null,
                    MainFrame.getCurrentInstance().getBundle().getString("LoadingDialogMessage"),
                    "",
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("pictures/SaveIcon.png"));
        }
        else if(e.getSource()==mainMenuView.getOptions()){
            options();
        }
        else if(e.getSource()==mainMenuView.getScores()){
            showScores();
        }
        else if(e.getSource()==mainMenuView.getQuit()){
            MainFrame.getCurrentInstance().dispose();
            MainFrame.getCurrentInstance().getMainMusic().stop();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
        else{
            System.out.println("Erreur !!");
        }
    }


}
