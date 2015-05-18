package graphics;

import log.IGLog;
import sound.MSound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameController implements ActionListener {

    private EndGameView endGameView;
    private MSound mainMusic;

    public EndGameController(Board currentState) {
        endGameView = new EndGameView();

        MainFrame.getCurrentInstance().addPanel(endGameView);
        registerListening();
    }

    private void registerListening() {
        endGameView.getReplay().addActionListener(this);
        endGameView.getScore().addActionListener(this);
        endGameView.getMenu().addActionListener(this);
        endGameView.getQuit().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == endGameView.getReplay()) {
            IGLog.write("EndGameController::actionPerformed -> getReplay()");
        } else if (e.getSource() == endGameView.getScore()) {
            IGLog.write("EndGameController::actionPerformed -> getScore()");
        } else if (e.getSource() == endGameView.getMenu()) {
            IGLog.write("EndGameController::actionPerformed -> getMenu()");
            MainFrame.getCurrentInstance().popPanel();
        } else if (e.getSource() == endGameView.getQuit()) {
            IGLog.write("EndGameController::actionPerformed -> getQuit()");
            System.exit(0);
        }
    }
}
