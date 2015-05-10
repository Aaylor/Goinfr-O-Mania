package graphics;

import sound.MSound;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Aaylor, Tratost, Pixelman on 16/04/15.
 */
public class MainMenuButton extends JButton {

    private ImageIcon imgOnClick;
    private ImageIcon imgFocus;
    private ImageIcon imgOnSleep;
    private MSound transition;
    private MSound selection;

    public MainMenuButton(String imgOnSleep, String imgOnClick, String imgFocus) {
        super();
        this.imgFocus = new ImageIcon(imgFocus);
        this.imgOnClick = new ImageIcon(imgOnClick);
        this.imgOnSleep = new ImageIcon(imgOnSleep);
        this.setIcon(this.imgOnSleep);
        this.transition = new MSound("menu1", "music/menu001.mp3");
        this.selection = new MSound("menu2", "music/menu011.mp3");
        this.setBackground(null);
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
            transition.play();
            mb.setBackground(null);
            mb.setFocusPainted(false);
            mb.setBorderPainted(false);
            mb.setOpaque(false);
            mb.setContentAreaFilled(false);
        }

        @Override
        public void mouseExited(MouseEvent event){
            mb.setIcon(imgOnSleep);
            mb.setBackground(null);
            mb.setFocusPainted(false);
            mb.setBorderPainted(false);
            mb.setOpaque(false);
            mb.setContentAreaFilled(false);
        }

        @Override
        public void mousePressed(MouseEvent event){
            mb.setIcon(imgOnClick);
            selection.play();
            mb.setBackground(null);
            mb.setFocusPainted(false);
            mb.setBorderPainted(false);
            mb.setOpaque(false);
            mb.setContentAreaFilled(false);
        }

        public void mouseClicked(MouseEvent event){
            mb.setIcon(imgFocus);
            mb.setBackground(null);
            mb.setFocusPainted(false);
            mb.setBorderPainted(false);
            mb.setOpaque(false);
            mb.setContentAreaFilled(false);
        }
    }
}
