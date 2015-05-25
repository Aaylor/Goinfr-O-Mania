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
    int sleepGap;
    int focusGap;
    private Font fontSleep = null;
    private Font fontFocus = null;
    private Color fontColorSleep;
    private Color fontColorFocus;
    private Color fontColorPressed;




    public MainMenuButton(String imgOnSleep, String imgOnClick, String imgFocus, String text, Font font) {
        super(text, new ImageIcon(imgOnSleep));
        this.imgFocus = new ImageIcon(imgFocus);
        this.imgOnClick = new ImageIcon(imgOnClick);
        this.imgOnSleep = new ImageIcon(imgOnSleep);
        this.transition = new MSound("menu1", "music/menu001.mp3");
        this.selection = new MSound("menu2", "music/menu011.mp3");
        this.fontColorSleep = new Color(0xBDB39C);
        this.fontColorFocus = new Color(0xF5F5F5);
        this.fontColorPressed = new Color(0xCDCDCD);

        if(font != null) {
            this.fontSleep = font.deriveFont(Font.PLAIN, 20);
            this.fontFocus = font.deriveFont(Font.PLAIN, 25);
            if (this.fontSleep != null)
                this.setFont(this.fontSleep);
        }
        updateGap();

        this.setIconTextGap(sleepGap);
        this.setForeground(fontColorSleep);
        this.setBackground(null);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.addMouseListener(new RolloverMainButtonListener(this));
    }

    public void reinit() {
        setIcon(imgOnSleep);
        setBackground(null);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
        if(fontSleep != null)
            setFont(fontSleep);
        setIconTextGap(sleepGap);
        setForeground(fontColorSleep);
    }

    public void updateGap(){
        int rightPaddingSleep = 70;
        int rightPaddingFocus = 110;

        if(fontSleep != null && fontFocus != null) {
            FontMetrics metrics = getFontMetrics(fontSleep);
            this.sleepGap = -(this.getFontMetrics(fontSleep).stringWidth(this.getText()) + rightPaddingSleep);
            this.focusGap = -(this.getFontMetrics(fontFocus).stringWidth(this.getText()) + rightPaddingFocus);
        }
        this.setIconTextGap(sleepGap);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        updateGap();
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
            mb.reinit();
        }

        @Override
        public void mouseReleased(MouseEvent event) { mb.reinit();}

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
