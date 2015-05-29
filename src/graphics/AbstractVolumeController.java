package graphics;

import com.sun.javaws.exceptions.InvalidArgumentException;
import engine.Settings;
import log.IGLog;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractVolumeController implements ActionListener, ChangeListener {

    protected VolumeView view;
    protected boolean muting;
    protected Settings currentSettings;

    public AbstractVolumeController(Settings currentSettings, double initialVolume) {
        this.currentSettings = currentSettings;

        view = new VolumeView(initialVolume);
        registerListening();

    }

    public VolumeView getView() {
        return view;
    }

    private void registerListening(){
        view.getMuteButton().addActionListener(this);
        view.getVolumeSlider().addChangeListener(this);
    }

    public abstract void updateSonorVolume(int volume);


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getMuteButton()){
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
                updateSonorVolume(vSlider.getValue());
            }
        }
    }
}
