package graphics;

import com.sun.javaws.exceptions.InvalidArgumentException;
import engine.KeyConfiguration;
import engine.Settings;
import log.IGLog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.ResourceBundle;

/**
 * Created by PixelMan on 23/05/15.
 */
@SuppressWarnings("DefaultFileTemplate")
public class SettingsController implements ActionListener{

    MainFrame parent;
    SettingsView view;
    KeyListener ka = null;
    KeyListener kp = null;
    KeyListener kl = null;
    KeyListener kr = null;
    KeyListener ku = null;
    KeyListener kd = null;
    KeyListener km = null;

    Settings currentSettings;

    public SettingsController(JPanel previousView) {
        parent = MainFrame.getCurrentInstance();

        this.currentSettings = new Settings(MainFrame.getCurrentInstance().getSettings());

        view = new SettingsView(previousView, currentSettings);
        parent.addPanel(view);

        registerListening();
    }

    public void updateOptions(){
        MainFrame frame = MainFrame.getCurrentInstance();
        frame.setSettings(currentSettings);
        frame.setBundle(ResourceBundle.getBundle(
                "lang/bundle",
                currentSettings.getLocale(),
                frame.getClassLoaderUTF8()
        ));
        frame.updateLangOnAllPanels();
        currentSettings.savePreferences();
    }


    private void registerListening(){
        view.getBack().addActionListener(this);
        view.getOk().addActionListener(this);
        view.getDifficultyComboBox().addActionListener(this);
        view.getLanguageComboBox().addActionListener(this);
        view.getAttackBut().addActionListener(react(view.getAttackBut(), "attack"));
        view.getDownBut().addActionListener(react(view.getDownBut(), "down"));
        view.getLeftBut().addActionListener(react(view.getLeftBut(), "left"));
        view.getMenuBut().addActionListener(react(view.getMenuBut(), "menu"));
        view.getRightBut().addActionListener(react(view.getRightBut(), "right"));
        view.getUpBut().addActionListener(react(view.getUpBut(), "up"));
        view.getPauseBut().addActionListener(react(view.getPauseBut(), "pause"));
    }

    private void reloadKeys(){
        KeyConfiguration key = currentSettings.getKeyConfiguration();
        view.getAttackBut().setText(KeyEvent.getKeyText(key.getAttack()));
        view.getDownBut().setText(KeyEvent.getKeyText(key.getDown()));
        view.getLeftBut().setText(KeyEvent.getKeyText(key.getLeft()));
        view.getMenuBut().setText(KeyEvent.getKeyText(key.getMenu()));
        view.getRightBut().setText(KeyEvent.getKeyText(key.getRight()));
        view.getUpBut().setText(KeyEvent.getKeyText(key.getUp()));
        view.getPauseBut().setText(KeyEvent.getKeyText(key.getPause()));
    }

    private void removesListener(){
        if(ka != null){
            view.getAttackBut().removeKeyListener(ka);
            ka = null;
        }
        if(km != null){
            view.getMenuBut().removeKeyListener(km);
            km = null;
        }
        if(kp != null){
            view.getPauseBut().removeKeyListener(kp);
            kp = null;
        }
        if(ku != null){
            view.getUpBut().removeKeyListener(ku);
            ku = null;
        }
        if(kd != null){
            view.getDownBut().removeKeyListener(kd);
            kd = null;
        }
        if(kl != null){
            view.getLeftBut().removeKeyListener(kl);
            kl = null;
        }
        if(kr != null){
            view.getRightBut().removeKeyListener(kr);
            kr = null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBack()) {
            IGLog.write("ScoreController::actionPerformed -> getBack()");
            MainFrame frame = MainFrame.getCurrentInstance();
            frame.getMainMusic().setVolume(frame.getSettings().getVolume());
            parent.popPanel();
        }
        else if (e.getSource() == view.getOk()){
            IGLog.write("ScoreController::actionPerformed -> getOk()");
            updateOptions();
            parent.popPanel();
        }

        else if(e.getSource() == view.getDifficultyComboBox()){
            IGLog.write("ScoreController::actionPerformed -> getDifficultyComboBox()");
            try {
                currentSettings.setDifficulty(view.getDifficultyComboBox().getSelectedIndex());
            } catch (InvalidArgumentException e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == view.getLanguageComboBox()) {
            IGLog.write("ScoreController::actionPerformed -> getLanguageComboBox()");
            try {
                currentSettings.setLang(view.getLanguageComboBox().getSelectedIndex());
            } catch (InvalidArgumentException e1) {
                e1.printStackTrace();
            }
        }
        else {
            IGLog.error("ScoreController::actionPerformed -> "
                    + "unknown action (" + e + ").");
        }
    }


    private ActionListener react(JButton j, String t){
        return (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getModifiers() != 0) {
                    j.requestFocus();
                    removesListener();
                    reloadKeys();
                    j.setText("?");
                    switch(t) {
                        case "attack":
                            ka = hearListener(j, t);
                            j.addKeyListener(ka);
                            break;
                        case "pause":
                            kp = hearListener(j, t);
                            j.addKeyListener(kp);
                            break;
                        case "menu":
                            km = hearListener(j, t);
                            j.addKeyListener(km);
                            break;
                        case "up":
                            ku = hearListener(j, t);
                            j.addKeyListener(ku);
                            break;
                        case "down":
                            kd = hearListener(j, t);
                            j.addKeyListener(kd);
                            break;
                        case "left":
                            kl = hearListener(j, t);
                            j.addKeyListener(kl);
                            break;
                        case "right":
                            kr = hearListener(j, t);
                            j.addKeyListener(kr);
                            break;
                        default:
                            System.err.println("Ne devrait pas entrer dans cette configuration de clef");
                    }
                }
            }
        });
    }

    private KeyListener hearListener(JButton j, String t){
        return (new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                switch(t) {
                    case "attack":
                        currentSettings.getKeyConfiguration().setAttack(code);
                        break;
                    case "pause":
                        currentSettings.getKeyConfiguration().setPause(code);
                        break;
                    case "menu":
                        currentSettings.getKeyConfiguration().setMenu(code);
                        break;
                    case "up":
                        currentSettings.getKeyConfiguration().setUp(code);
                        break;
                    case "down":
                        currentSettings.getKeyConfiguration().setDown(code);
                        break;
                    case "left":
                        currentSettings.getKeyConfiguration().setLeft(code);
                        break;
                    case "right":
                        currentSettings.getKeyConfiguration().setRight(code);
                        break;
                    default:
                        System.err.println("Ne devrait pas entrer dans cette configuration de clef");
                }
                j.setText(KeyEvent.getKeyText(code));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE)
                    j.setText(KeyEvent.getKeyText(KeyEvent.VK_SPACE));
                j.removeKeyListener(this);
                j.requestFocus(false);
            }
        });
    }


}
