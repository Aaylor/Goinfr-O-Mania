package graphics;

import engine.Player;
import log.IGLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseController implements ActionListener {

    private JPanel parent;
    private BoardController parentController;
    private Player player;

    private PauseView view;

    public PauseController(JPanel parent, BoardController boardController, Player player) {
        this.parent = parent;
        this.parentController = boardController;
        this.player = player;

        view = new PauseView();
        registerListening();
        MainFrame.getCurrentInstance().addPanel(view);
    }

    private void registerListening() {
        view.getContinue().addActionListener(this);
        view.getMenu().addActionListener(this);
        view.getQuit().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getContinue()) {
            IGLog.write("PauseController::actionPerformed -> getContinue()");
            MainFrame.getCurrentInstance().returnToPanel(parent);
            parentController.resumeGame();
        } else if (e.getSource() == view.getMenu()) {
            IGLog.write("PauseController::actionPerformed -> getMenu()");
            /* FIXME : kill the game before jumping to main menu */
            MainFrame.getCurrentInstance().backToFirstPanel();
        } else if (e.getSource() == view.getQuit()) {
            IGLog.write("PauseController::actionPerformed -> getQuit()");
            System.exit(0);
        } else {
            IGLog.error("PauseController::actionPerformed -> "
                    + "unknown action (" + e + ").");
        }
    }
}
