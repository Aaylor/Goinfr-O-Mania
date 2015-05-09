package graphics;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by tratost on 16/04/15.
 */
public class MainMenuButton extends JButton {

    private String text;
    private ImageIcon imgOnClick;
    private ImageIcon imgFocus;
    private ImageIcon imgOnSleep;

    public MainMenuButton() {
    }

    public MainMenuButton(String text) {
        super();
        this.text = text;
        this.imgFocus = new ImageIcon("pictures/index2.jpeg");
        this.imgOnClick = new ImageIcon("pictures/index.jpeg");
        this.imgOnSleep = new ImageIcon( "pictures/images.png");
        this.setIcon(imgOnSleep);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.addMouseListener(new RolloverMainButtonListener(this));
    }

    public MainMenuButton(String text, Icon icon) {
        super(text, icon);
    }

    private final class RolloverMainButtonListener extends MouseAdapter {

        MainMenuButton mb;

        public RolloverMainButtonListener(MainMenuButton mb){
            this.mb = mb;
        }

        @Override
        public void mouseEntered(MouseEvent event){
            mb.setIcon(imgFocus);
        }

        @Override
        public void mouseExited(MouseEvent event){
            mb.setIcon(imgOnSleep);
        }

        @Override
        public void mousePressed(MouseEvent event){
            mb.setIcon(imgOnClick);
        }
    }
}
