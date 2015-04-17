package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by tratost on 15/04/15.
 */
public class MainMenuView extends JPanel implements Observer {

    private MainMenuButton play;
    private Background background;

    //CONSTRUCTOR

    public MainMenuView() {
        super();
        instantiateButtons();
        this.add(play);
    }

    public MainMenuView(Background bg) {
        this();
        background = bg;
    }

    private void instantiateButtons(){
        play = new MainMenuButton("Play Game");
        play.setVisible(true);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            background.paintComponent(g);
        }
    }
}
