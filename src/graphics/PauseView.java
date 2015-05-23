package graphics;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ResourceBundle;

public class PauseView extends Background {

    private Font font;

    private MainMenuButton continue_;
    private MainMenuButton menu;
    private MainMenuButton options;
    private MainMenuButton quit;


    public PauseView() {
        super("pictures/cake.jpg");

        try {
            File file = new File("fonts/newyorkescape.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, file)
                    .deriveFont(Font.PLAIN, 32);
        } catch (Exception e) {
            font = null;
        }

        title();
        buttons();
    }

    public void title() {
        ResourceBundle bundle = MainFrame.getCurrentInstance().getBundle();

        JLabel title = new JLabel(bundle.getString("pauseMenuTitle")); /* FIXME */
        if (font != null) {
            title.setFont(font.deriveFont(Font.PLAIN, 64));
        }

        title.setForeground(Color.RED);
        this.add(title, BorderLayout.NORTH);
    }

    public void buttons() {
        ResourceBundle bundle = MainFrame.getCurrentInstance().getBundle();

        continue_ = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("pauseMenuContinue"),
                font);
        menu = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("pauseMenuMenu"),
                font);
        options = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("pauseMenuOptions"),
                font);
        quit = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("pauseMenuQuit"),
                font);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.setOpaque(false);
        buttonPanel.add(continue_);
        buttonPanel.add(menu);
        buttonPanel.add(options);
        buttonPanel.add(quit);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public MainMenuButton getContinue() {
        return continue_;
    }

    public MainMenuButton getMenu() {
        return menu;
    }

    public MainMenuButton getOptions() {
        return options;
    }

    public MainMenuButton getQuit() {
        return quit;
    }

}
