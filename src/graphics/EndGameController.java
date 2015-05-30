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

    public EndGameController(Board currentState) {
        endGameView  = new EndGameView(currentState.getLevel().getScore());
        endGameMusic = new MMusic("endGame", "music/endgame00.mp3");

        bundle = MainFrame.getCurrentInstance().getBundle();

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
        if(!name.equals(bundle.getString("playerName"))) {
            MainFrame.getCurrentInstance().getScores().addScore(
                    name,
                    currentState.getLevel().getScore()
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        endGameMusic.stop();
        if (e.getSource() == endGameView.getReplay()) {
            IGLog.write("EndGameController::actionPerformed -> getReplay()");
            saveScore(endGameView.getPlayerName());
            MainFrame.getCurrentInstance().popPanel(false);
            Board b = new Board(currentState.getPlayer(), new Level(new Score(), Difficulties.MEDIUM));
            BoardView boardView = new BoardViewGenerator(b).viewGeneration();
            boardView.setSize(MainFrame.getCurrentInstance().getSize());
            BoardController bc = new BoardController(b, boardView);
            MainFrame.getCurrentInstance().addPanel(boardView);
            bc.start();
        } else if (e.getSource() == endGameView.getScore()) {
            IGLog.write("EndGameController::actionPerformed -> getScore()");
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
