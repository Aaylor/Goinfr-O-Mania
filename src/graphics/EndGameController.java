package graphics;

import engine.Difficulties;
import engine.Level;
import engine.Score;
import log.IGLog;
import sound.MMusic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class EndGameController implements ActionListener {

    private EndGameView endGameView;
    private MMusic endGameMusic;
    private Board currentState;
    private ResourceBundle bundle;
    private boolean scoreSaved;

    public EndGameController(Board currentState) {
        endGameView  = new EndGameView(currentState.getLevel().getScore());
        endGameMusic = new MMusic("endGame", "music/endgame00.wav");

        bundle = MainFrame.getCurrentInstance().getBundle();
        scoreSaved = false;

        MainFrame.getCurrentInstance().addPanel(endGameView);
        registerListening();
        endGameMusic.play();
        this.currentState = currentState;
    }

    private void registerListening() {
        endGameView.getReplay().addActionListener(this);
        endGameView.getScore().addActionListener(this);
        endGameView.getMenu().addActionListener(this);
        endGameView.getQuit().addActionListener(this);
    }

    private void saveScore(String name){
        if(!scoreSaved && !name.equals(bundle.getString("playerName")) && !name.equals("")) {
            MainFrame.getCurrentInstance().getScores().addScore(
                    name,
                    currentState.getLevel().getScore()
            );
            scoreSaved = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        endGameMusic.stop();
        if (e.getSource() == endGameView.getReplay()) {
            IGLog.write("EndGameController::actionPerformed -> getReplay()");
            saveScore(endGameView.getPlayerName());
            Board b = new Board(currentState.getPlayer(), new Level(new Score(),
                    currentState.getLevel().getDifficulty()));
            BoardView boardView = new BoardViewGenerator(b).viewGeneration();
            boardView.setSize(MainFrame.getCurrentInstance().getSize());
            BoardController bc = new BoardController(b, boardView);
            MainFrame.getCurrentInstance().popPanel(false);
            MainFrame.getCurrentInstance().addPanel(boardView);
            bc.start();
        } else if (e.getSource() == endGameView.getScore()) {
            IGLog.write("EndGameController::actionPerformed -> getScore()");
            saveScore(endGameView.getPlayerName());
            new ScoresController(this.endGameView);
        } else if (e.getSource() == endGameView.getMenu()) {
            IGLog.write("EndGameController::actionPerformed -> getMenu()");
            saveScore(endGameView.getPlayerName());
            MainFrame.getCurrentInstance().popPanel();
        } else if (e.getSource() == endGameView.getQuit()) {
            IGLog.write("EndGameController::actionPerformed -> getQuit()");
            saveScore(endGameView.getPlayerName());
            System.exit(0);
        }
    }
}
