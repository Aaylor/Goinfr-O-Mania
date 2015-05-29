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
public class SettingsController implements ActionListener{

    MainFrame parent;
    SettingsView view;

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
        currentSettings.getKeyConfiguration().savePreferences();
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
                    j.setText("?");
                    j.addKeyListener(hearListener(j, t));
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
                j.removeKeyListener(this);
            }
        });
    }


}
