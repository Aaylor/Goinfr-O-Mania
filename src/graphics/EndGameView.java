package graphics;

import engine.Player;
import engine.Score;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ResourceBundle;

public class EndGameView extends Background {

    private Font globalFont;
    private Font scoreFont;
    private Font nameFont;

    private MainMenuButton replay;
    private MainMenuButton score;
    private MainMenuButton menu;
    private MainMenuButton quit;

    private JTextField playerName;

    private Score gameScore;

    ResourceBundle bundle;


    public EndGameView(Score score) {
        super("pictures/simpleBackground.png");

        bundle = MainFrame.getCurrentInstance().getBundle();

        try {
            File file = new File("fonts/newyorkescape.ttf");
            globalFont = Font.createFont(Font.TRUETYPE_FONT, file)
                    .deriveFont(Font.PLAIN, 32);
        } catch (Exception e) {
            globalFont = null;
        }

        try {
            File file = new File("fonts/LearningCurve.ttf");
            scoreFont = Font.createFont(Font.TRUETYPE_FONT, file)
                    .deriveFont(Font.PLAIN, 64);
        } catch (Exception e) {
            scoreFont = null;
        }

        try {
            File file = new File("fonts/newyorkescape.ttf");
            nameFont = Font.createFont(Font.TRUETYPE_FONT, file)
                    .deriveFont(Font.PLAIN, 30);
        } catch (Exception e) {
            scoreFont = null;
        }

        this.gameScore = score;

        instantiateTitle();
        instantiateContent();
        sizeOfPictures();
    }

    private void instantiateTitle() {
        JLabel title = new JLabel(bundle.getString("endGameTitle"));
        if (globalFont != null) {
            title.setFont(globalFont.deriveFont(Font.PLAIN, 64));
        }

        title.setForeground(new Color(255, 0, 0));
        this.add(title, BorderLayout.NORTH);
    }

    private void instantiateContent() {
        JPanel content = new JPanel();

        content.setBorder(new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(50, 0, 0, 0);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        content.setOpaque(false);
        BorderLayout layout = new BorderLayout();
        content.setLayout(layout);
        content.add(instantiateScores(), BorderLayout.WEST);
        content.add(instantiateButtons(), BorderLayout.EAST);
        this.add(content, BorderLayout.CENTER);
    }

    private JPanel instantiateScores(){
        JPanel scorePanel = new JPanel();
        scorePanel.setPreferredSize(new Dimension(350, 120));
        scorePanel.setLayout(new BorderLayout());
        scorePanel.setOpaque(false);

        scorePanel.setBorder(new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {}
            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(80, 0, 110, 0);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        JLabel score = new JLabel(
                bundle.getString("endGameScoreLabel") +
                        " : " + gameScore.getValue()
        );
        score.setForeground(Color.RED);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        if (scoreFont != null)
            score.setFont(scoreFont);

        playerName = new JTextField(bundle.getString("playerName"));
        playerName.addMouseListener(new TextFieldMouseController(playerName));
        playerName.setForeground(Color.RED);
        playerName.setOpaque(false);
        playerName.setBackground(new Color(255, 255, 255, 20));
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        if (nameFont != null)
            playerName.setFont(nameFont);

        scorePanel.add(score, BorderLayout.CENTER);
        scorePanel.add(playerName, BorderLayout.SOUTH);
        return scorePanel;
    }

    private JPanel instantiateButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(501, 360));

        replay = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("endGameReplay"),
                globalFont);
        score = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("endGameScore"),
                globalFont);
        menu = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("endGameMenu"),
                globalFont);
        quit = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("endGameQuit"),
                globalFont);

        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.setOpaque(false);
        buttonPanel.add(replay);
        buttonPanel.add(score);
        buttonPanel.add(menu);
        buttonPanel.add(quit);
        return buttonPanel;
    }

    //GETTER

    public MainMenuButton getReplay() {
        return replay;
    }

    public MainMenuButton getScore() {
        return score;
    }

    public MainMenuButton getMenu() {
        return menu;
    }

    public MainMenuButton getQuit() {
        return quit;
    }

    public String getPlayerName(){
        return playerName.getText();
    }



    private class TextFieldMouseController extends MouseAdapter {

        JTextField textField;

        public TextFieldMouseController(JTextField textField) {
            super();
            this.textField = textField;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Textfield clicked");
            if(textField.getText().equals(bundle.getString("playerName"))) {
                playerName.setDocument(new JTextFieldLimit(8));
                textField.setText("");
            }
        }
    }

    private class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

}
