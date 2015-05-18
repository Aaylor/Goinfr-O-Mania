package graphics;

import sound.MSound;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MainMenuView extends Background implements Observer {

    private MainFrame framep;
    private MainMenuButton play;
    private MainMenuButton load;
    private MainMenuButton options;
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

    public MainMenuView(String img) {
        super(img);
        instantiateButtons();
        this.add(play);
        this.sizeOfPictures();
    }

    public void instantiateGridBagConstraints(){
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(10, 10, 10, 10);
    }

    private void instantiateTitle(){
        ResourceBundle bundle = framep.getBundle();
        JLabel title = new JLabel(bundle.getString("gameMenuTitle"));
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
        this.add(play, gbc);
        this.add(load, gbc);
        this.add(options, gbc);
        //play.setVisible(true);
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

    //UPDATE

    @Override
    public void update(Observable o, Object arg) {
        //TODO
    }
/*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            background.paintComponent(g);
        }
    }*/
}
