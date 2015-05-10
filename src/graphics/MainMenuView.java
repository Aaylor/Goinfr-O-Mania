package graphics;

import sound.MSound;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Aaylor, Tratost, Pixelman on 15/04/15.
 */
public class MainMenuView extends Background implements Observer {

    private MainFrame framep;
    private MainMenuButton play;
    private MainMenuButton load;
    private MainMenuButton options;
    private GridBagConstraints gbc;


    //CONSTRUCTOR

    public MainMenuView(MainFrame framep) {
        super("pictures/cake.jpg");
        this.framep = framep;
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
        try {
            File file = new File("font/newyorkescape.ttf");
            Font fontTitle = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(Font.PLAIN, 60);
            JLabel title = new JLabel("Main Menu");
            title.setFont(fontTitle);
            this.add(title);
        }
        catch(Exception e){
            JLabel title = new JLabel("Main Menu");
            this.add(title);
        }
    }

    private void instantiateButtons() {
        play = new MainMenuButton("pictures/En/buttonSleepNewGame2.png", "pictures/En/medButtonNewGameActif2.png", "pictures/En/medButtonNewGame.png");
        load = new MainMenuButton("pictures/En/buttonSleepLoad2.png", "pictures/En/medButtonLoadActif.png", "pictures/En/medButtonLoad.png");
        options = new MainMenuButton("pictures/En/buttonSleepOptions2.png", "pictures/En/medButtonOptionsenActif.png", "pictures/En/medButtonOptions.png");
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
