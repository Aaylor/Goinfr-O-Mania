package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by tratost on 15/04/15.
 */
public class MainMenuView extends Background implements Observer {

    private MainMenuButton play;
    private GridBagConstraints gbc;

    //CONSTRUCTOR

    public MainMenuView() {
        super("pictures/cake.jpg");
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
        JLabel title = new JLabel("Main Menu");
        title.setSize(20,20);
        this.add(title, gbc);
    }

    private void instantiateButtons() {
        play = new MainMenuButton("Play Game");
        play.setOpaque(true);
        this.add(play, gbc);
        //play.setVisible(true);
    }

    //GETTER

    public MainMenuButton getPlay() {
        return play;
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
