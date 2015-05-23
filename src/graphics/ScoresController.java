package graphics;

import log.IGLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by PixelMan on 23/05/15.
 */
public class ScoresController implements ActionListener{

    MainFrame parent;
    ScoresView view;

    public ScoresController(JPanel previousView) {
        parent = MainFrame.getCurrentInstance();

        view = new ScoresView(previousView);
        parent.addPanel(view);

        registerListening();
    }

    private void registerListening() {
        view.getBack().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBack()) {
            IGLog.write("ScoreController::actionPerformed -> getBack()");
            parent.popPanel();
        } else {
            IGLog.error("ScoreController::actionPerformed -> "
                    + "unknown action (" + e + ").");
        }
    }
}
