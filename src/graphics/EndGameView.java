package graphics;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ResourceBundle;

public class EndGameView extends Background {

    private Font globalFont;
    private Font scoreFont;

    private MainMenuButton replay;
    private MainMenuButton score;
    private MainMenuButton menu;
    private MainMenuButton quit;


    public EndGameView() {
        super("pictures/cake.jpg");

        try {
            File file = new File("fonts/newyorkescape.ttf");
            globalFont = Font.createFont(Font.TRUETYPE_FONT, file)
                    .deriveFont(Font.PLAIN, 32);
        } catch (Exception e) {
            globalFont = null;
        }

        try {
            File file = new File("fonts/LearningCurve.ttf");
            scoreFont = Font.createFont(Font.TRUETYPE_FONT, file)
                    .deriveFont(Font.PLAIN, 64);
        } catch (Exception e) {
            scoreFont = null;
        }

        instantiateTitle();
        instantiateButtons();
        sizeOfPictures();
    }

    public void instantiateTitle() {
        ResourceBundle bundle = MainFrame.getCurrentInstance().getBundle();

        JLabel title = new JLabel(bundle.getString("endGameTitle"));
        if (globalFont != null) {
            title.setFont(globalFont.deriveFont(Font.PLAIN, 64));
        }

        title.setForeground(new Color(255, 0, 0));
        this.add(title, BorderLayout.NORTH);

        JLabel score = new JLabel("Score : " + 94);
        if (scoreFont != null)
            score.setFont(scoreFont);
        score.setForeground(new Color(255, 0, 0));
        this.add(score, BorderLayout.WEST);
    }

    private void instantiateButtons() {
        ResourceBundle bundle = MainFrame.getCurrentInstance().getBundle();

        JPanel buttonPanel = new JPanel();

        replay = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("endGameReplay"),
                globalFont);
        score = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("endGameScore"),
                globalFont);
        menu = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("endGameMenu"),
                globalFont);
        quit = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("endGameQuit"),
                globalFont);

        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.setOpaque(false);
        buttonPanel.add(replay);
        buttonPanel.add(score);
        buttonPanel.add(menu);
        buttonPanel.add(quit);
        this.add(buttonPanel, BorderLayout.EAST);
    }

    //GETTER

    public MainMenuButton getReplay() {
        return replay;
    }

    public MainMenuButton getScore() {
        return score;
    }

    public MainMenuButton getMenu() {
        return menu;
    }

    public MainMenuButton getQuit() {
        return quit;
    }
}
