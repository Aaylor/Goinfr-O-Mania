package graphics;

import sound.MSound;

import java.awt.*;
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
    String text;
    int sleepGap;
    int focusGap;
    private Font fontSleep = null;
    private Font fontFocus = null;
    Color fontColorSleep;
    Color fontColorFocus;
    Color fontColorPressed;



    public MainMenuButton(String imgOnSleep, String imgOnClick, String imgFocus, String text, Font font) {
        super(text, new ImageIcon(imgOnSleep));
        this.imgFocus = new ImageIcon(imgFocus);
        this.imgOnClick = new ImageIcon(imgOnClick);
        this.imgOnSleep = new ImageIcon(imgOnSleep);
        this.transition = new MSound("menu1", "music/menu001.mp3");
        this.selection = new MSound("menu2", "music/menu011.mp3");
        this.text = text;
        this.fontColorSleep = new Color(0xBDB39C);
        this.fontColorFocus = new Color(0xF5F5F5);
        this.fontColorPressed = new Color(0xCDCDCD);

        int rightPaddingSleep = 100;
        int rightPaddingFocus = 120;

        if(font != null) {
            this.fontSleep = font.deriveFont(Font.PLAIN, 20);
            this.fontFocus = font.deriveFont(Font.PLAIN, 25);
            FontMetrics metrics = getFontMetrics(fontSleep);
            this.sleepGap = -(this.getFontMetrics(fontSleep).stringWidth(this.text) + rightPaddingSleep);
            this.focusGap = -(this.getFontMetrics(fontFocus).stringWidth(this.text) + rightPaddingFocus);
            if (this.fontSleep != null)
                this.setFont(this.fontSleep);
        }

        this.setIconTextGap(sleepGap);
        this.setForeground(fontColorSleep);
        this.setBackground(null);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.addMouseListener(new RolloverMainButtonListener(this));
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
            if(mb.fontFocus != null)
                mb.setFont(mb.fontFocus);
            mb.setIconTextGap(mb.focusGap);
            mb.setForeground(fontColorFocus);
        }

        @Override
        public void mouseExited(MouseEvent event){
            mb.setIcon(imgOnSleep);
            mb.setBackground(null);
            mb.setFocusPainted(false);
            mb.setBorderPainted(false);
            mb.setOpaque(false);
            mb.setContentAreaFilled(false);
            if(mb.fontSleep != null)
                mb.setFont(mb.fontSleep);
            mb.setIconTextGap(mb.sleepGap);
            mb.setForeground(fontColorSleep);
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
            mb.setForeground(fontColorPressed);
        }

        public void mouseClicked(MouseEvent event){
            mb.setIcon(imgFocus);
            mb.setBackground(null);
            mb.setFocusPainted(false);
            mb.setBorderPainted(false);
            mb.setOpaque(false);
            mb.setContentAreaFilled(false);
            mb.setForeground(fontColorFocus);
        }
    }
}
