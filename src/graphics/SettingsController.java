package graphics;

import com.sun.javaws.exceptions.InvalidArgumentException;
import engine.Settings;
import log.IGLog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    }


    private void registerListening(){
        view.getBack().addActionListener(this);
        view.getOk().addActionListener(this);
        view.getDifficultyComboBox().addActionListener(this);
        view.getLanguageComboBox().addActionListener(this);
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
        else if(e.getSource() == view.getLanguageComboBox()){
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


}
