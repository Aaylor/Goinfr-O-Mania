package graphics;

import com.sun.javaws.exceptions.InvalidArgumentException;
import engine.Options;
import log.IGLog;
import sound.MSound;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by PixelMan on 23/05/15.
 */
public class OptionsController implements ActionListener, ChangeListener{

    MainFrame parent;
    OptionsView view;

    Options currentSettings;

    boolean muting;

    public OptionsController(JPanel previousView) {
        parent = MainFrame.getCurrentInstance();

        this.currentSettings = new Options(MainFrame.getCurrentInstance().getOptions());

        view = new OptionsView(previousView);
        parent.addPanel(view);

        registerListening();
    }


    /**
     * update the mainFrame options and operate the immediate effects
     * of the settings modifications.
     */
    public void updateOptions(){
        MainFrame.getCurrentInstance().setOptions(currentSettings);
        MainFrame.getCurrentInstance().getMainMusic().setVolume(currentSettings.getVolume());
    }

    public void updateSonorVolume(int volume){
        try {
            double optionVolume = new Integer(volume).doubleValue() / 100;
            IGLog.info("Options updates <volume> : " + optionVolume);
            currentSettings.setVolume(optionVolume);
        }
        catch (InvalidArgumentException e){
            IGLog.error("Invalide Sonor Volume");
        }
    }

    private void registerListening(){
        view.getBack().addActionListener(this);
        view.getOk().addActionListener(this);
        view.getMuteButton().addActionListener(this);
        view.getVolumeSlider().addChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBack()) {
            IGLog.write("ScoreController::actionPerformed -> getBack()");
            parent.popPanel();
        }
        else if (e.getSource() == view.getOk()){
            IGLog.write("ScoreController::actionPerformed -> getOk()");
            updateOptions();
        }
        else if (e.getSource() == view.getMuteButton()){
            IGLog.write("ScoreController::actionPerformed -> getMuteButton()");
            VolumeButton vButton = view.getMuteButton();
            vButton.setMuted(!vButton.isMuted());
            if(vButton.isMuted()){
                muting = true;
                view.getVolumeSlider().setValue(0);
                muting = false;
                updateSonorVolume(0);
            }
            else{
                int volumeUpdate = view.getVolumeSlider().getVolume();
                view.getVolumeSlider().setValue(volumeUpdate);
                updateSonorVolume(volumeUpdate);
            }
        }
        else {
            IGLog.error("ScoreController::actionPerformed -> "
                    + "unknown action (" + e + ").");
        }
    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param e a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        VolumeSlider vSlider = view.getVolumeSlider();
        if (e.getSource() == vSlider){
            if(!vSlider.getValueIsAdjusting()){
                view.getMuteButton().setMuted(vSlider.getValue()==0);
                if(!muting)
                    vSlider.setVolume(vSlider.getValue());
            }
            updateSonorVolume(vSlider.getValue());
        }
    }
}
