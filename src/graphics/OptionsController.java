package graphics;

import log.IGLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by PixelMan on 23/05/15.
 */
public class OptionsController implements ActionListener{

    MainFrame parent;
    OptionsView view;

    public OptionsController(JPanel previousView) {
        parent = MainFrame.getCurrentInstance();

        view = new OptionsView(previousView);
        parent.addPanel(view);

        registerListening();
    }

    private void registerListening(){
        view.getBack().addActionListener(this);
        view.getOk().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBack()) {
            IGLog.write("ScoreController::actionPerformed -> getBack()");
            parent.popPanel();
        }
        else if (e.getSource() == view.getOk()){
            IGLog.write("ScoreController::actionPerformed -> getOk()");
            /* TODO */
        }
        else {
            IGLog.error("ScoreController::actionPerformed -> "
                    + "unknown action (" + e + ").");
        }
    }
}
