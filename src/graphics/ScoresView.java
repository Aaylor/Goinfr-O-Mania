package graphics;

import engine.Score;
import engine.Scores;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Created by PixelMan on 22/05/15.
 */
public class ScoresView extends Background {

    private MainMenuButton back;
    private JPanel scoreTable;
    private JLabel[][] scoreLabels;

    private Font font;

    public ScoresView(JPanel previousView){
        super("pictures/cake.jpg");

        this.setLayout(new BorderLayout());

        try {
            File file = new File("fonts/newyorkescape.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, file)
                    .deriveFont(Font.PLAIN, 20);
        } catch (Exception e) {
            font = null;
        }

        this.setPreferredSize(previousView.getSize());

        initScoreLabels();
        initScoreTable();
        initButton();
    }

    public MainMenuButton getBack() {
        return back;
    }

    public void initScoreLabels(){

        Scores s = MainFrame.getCurrentInstance().getScores();
        Collections.sort(s.getScores(), new Score());

        scoreLabels = new JLabel[10][2];

        for(int i = 0; i<10; i++){
            String player = s.getScores().get(i).getWho();
            String score = Integer.toString(s.getScores().get(i).getValue());

            scoreLabels[i][0] = new JLabel(player);
            scoreLabels[i][0].setHorizontalAlignment(SwingConstants.RIGHT);
            scoreLabels[i][0].setFont(font);
            scoreLabels[i][0].setForeground(Color.WHITE);

            scoreLabels[i][1] = new JLabel(score);
            scoreLabels[i][1].setHorizontalAlignment(SwingConstants.LEFT);
            scoreLabels[i][1].setFont(font);
            scoreLabels[i][1].setForeground(Color.WHITE);
        }
    }

    public void initScoreTable(){
        GridLayout scoreLayout = new GridLayout(10, 2);
        scoreLayout.setHgap(50);

        scoreTable = new JPanel(scoreLayout);
        scoreTable.setOpaque(false);

        for (int i = 0; i < 10; i++) {
            scoreTable.add(scoreLabels[i][0]);
            scoreTable.add(scoreLabels[i][1]);
        }

        this.add(scoreTable, BorderLayout.CENTER);
    }

    private void initButton() {
        ResourceBundle bundle = MainFrame.getCurrentInstance().getBundle();
        this.back = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("scoresBackButton"),
                font);

        this.add(back, BorderLayout.SOUTH);
    }

}
