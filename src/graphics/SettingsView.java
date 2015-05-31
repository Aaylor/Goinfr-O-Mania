package graphics;

import engine.KeyConfiguration;
import engine.Settings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ResourceBundle;

/**
 * Created by PixelMan on 22/05/15.
 */
public class SettingsView extends Background {

    private Settings currentSettings;

    private JTabbedPane tabs;

    private JPanel generalPanel;

    private VolumeView volumePanel;
    private JLabel volumeLabel;

    private VolumeView soundEffectPanel;
    private JLabel soundEffectLabel;

    private JLabel difficultyLabel;
    private JComboBox<String> difficultyComboBox;

    private JLabel languageLabel;
    private JComboBox<String> languageComboBox;

    private JPanel commandsPanel;

    private JLabel up;
    private JLabel down;
    private JLabel left;
    private JLabel right;
    private JLabel attack;
    //private JLabel quit;
    private JLabel pause;
    private JLabel menu;

    private JButton upBut;
    private JButton downBut;
    private JButton leftBut;
    private JButton rightBut;
    private JButton attackBut;
    private JButton pauseBut;
    private JButton menuBut;

    private JPanel buttonPanel;
    private MainMenuButton back;
    private MainMenuButton ok;

    private ResourceBundle bundle;
    private Font font;
    private Font fontKey;

    private JMenuItem rDC;


    /* Constructors */

    public SettingsView(JPanel previousView, Settings currentSettings){
        super("pictures/simpleBackground.png");

        this.currentSettings = currentSettings;

        this.setLayout(new BorderLayout());

        try {
            File file = new File("fonts/newyorkescape.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, file)
                    .deriveFont(Font.PLAIN, 18);
        } catch (Exception e) {
            font = null;
        }

        fontKey = new Font(Font.SANS_SERIF, Font.BOLD, 18);

        this.setPreferredSize(previousView.getSize());

        this.bundle = MainFrame.getCurrentInstance().getBundle();

        JMenuBar bar = MainFrame.getCurrentInstance().getJMenuBar();
        rDC = new JMenuItem(bundle.getString("defaultControl"));
        bar.add(rDC);

        instantiateTitle();
        initCommandPanel();
        initGeneralPanel();
        initTabs();
        initButtonPanel();

    }

    public JMenuItem getDefaultControls(){
        return rDC;
    }

    /* Accessors */

    public MainMenuButton getOk() {
        return ok;
    }

    public MainMenuButton getBack() {
        return back;
    }

    public JButton getMenuBut() {
        return menuBut;
    }

    public JButton getPauseBut() {
        return pauseBut;
    }

    public JButton getAttackBut() {
        return attackBut;
    }

    public JButton getRightBut() {
        return rightBut;
    }

    public JButton getLeftBut() {
        return leftBut;
    }

    public JButton getDownBut() {
        return downBut;
    }

    public JButton getUpBut() {
        return upBut;
    }

    public JComboBox<String> getDifficultyComboBox() {
        return difficultyComboBox;
    }

    public JComboBox<String> getLanguageComboBox() {
        return languageComboBox;
    }

    /* Initialisation functionalities */

    private void instantiateTitle(){
        JLabel title = new JLabel(bundle.getString("optionsTitle"), SwingConstants.CENTER);
        if(this.font != null)
            title.setFont(font.deriveFont(Font.PLAIN, 60));
        title.setForeground(new Color(0x3B3B3B));
        this.add(title, BorderLayout.NORTH);
    }

    private void initGeneralPanel(){
        GridLayout generalLayout = new GridLayout(4, 2);
        generalLayout.setHgap(40);
        generalLayout.setVgap(10);

        generalPanel = new JPanel(generalLayout);
        generalPanel.setBorder(new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {}

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(0, 60, 0, 60);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        volumeLabel = new JLabel(bundle.getString("optionsVolumeLabel"), SwingConstants.CENTER);
        volumeLabel.setForeground(Color.WHITE);
        volumeLabel.setFont(font);
        volumePanel = new VolumeController(currentSettings).getView();

        soundEffectLabel = new JLabel(bundle.getString("optionsSoundEffectLabel"), SwingConstants.CENTER);
        soundEffectLabel.setForeground(Color.WHITE);
        soundEffectLabel.setFont(font);
        soundEffectPanel = new SoundEffectController(currentSettings).getView();

        difficultyLabel = new JLabel(bundle.getString("optionsDifficultyLabel"), SwingConstants.CENTER);
        difficultyLabel.setForeground(Color.WHITE);
        difficultyLabel.setFont(font);

        String[] difficultyChoices = {
                bundle.getString("optionsComboDiffEasy"),
                bundle.getString("optionsComboDiffNormal"),
                bundle.getString("optionsComboDiffHard")
        };
        difficultyComboBox = new JComboBox<>(difficultyChoices);
        difficultyComboBox.setSelectedIndex(currentSettings.getDifficulty());

        languageLabel = new JLabel(bundle.getString("optionsLanguageLabel"), SwingConstants.CENTER);
        languageLabel.setForeground(Color.WHITE);
        languageLabel.setFont(font);

        String[] langChoices = {
                bundle.getString("optionsComboLangFr"),
                bundle.getString("optionsComboLangEn")
        };
        languageComboBox = new JComboBox<>(langChoices);
        languageComboBox.setSelectedIndex(currentSettings.getLang());

        generalPanel.add(volumeLabel);
        generalPanel.add(volumePanel);
        generalPanel.add(soundEffectLabel);
        generalPanel.add(soundEffectPanel);
        generalPanel.add(difficultyLabel);
        generalPanel.add(difficultyComboBox);
        generalPanel.add(languageLabel);
        generalPanel.add(languageComboBox);

        generalPanel.setOpaque(false);
    }

    private void initCommandPanel(){
        commandsPanel = new JPanel();
        JLabel label = new JLabel("Command Panel");
        label.setForeground(Color.white);

        GridLayout commandLayout = new GridLayout(7, 2);
        commandLayout.setHgap(150);
        commandLayout.setVgap(10);
        commandsPanel.setLayout(commandLayout);

        up = initKeyLabel("upControl");
        down = initKeyLabel("downControl");
        left = initKeyLabel("leftControl");
        right = initKeyLabel("rightControl");
        attack = initKeyLabel("attackControl");
        menu = initKeyLabel("menuControl");
        pause = initKeyLabel("pauseControl");

        KeyConfiguration kg = currentSettings.getKeyConfiguration();

        upBut = initKeyButton(kg.getUp());
        downBut = initKeyButton(kg.getDown());
        leftBut = initKeyButton(kg.getLeft());
        rightBut = initKeyButton(kg.getRight());
        attackBut = initKeyButton(kg.getAttack());
        pauseBut = initKeyButton(kg.getPause());
        menuBut = initKeyButton(kg.getMenu());

        commandsPanel.add(up);
        commandsPanel.add(upBut);
        commandsPanel.add(down);
        commandsPanel.add(downBut);
        commandsPanel.add(left);
        commandsPanel.add(leftBut);
        commandsPanel.add(right);
        commandsPanel.add(rightBut);
        commandsPanel.add(attack);
        commandsPanel.add(attackBut);
        commandsPanel.add(pause);
        commandsPanel.add(pauseBut);
        commandsPanel.add(menu);
        commandsPanel.add(menuBut);

        commandsPanel.setOpaque(false);
    }

    private void initTabs(){
        UIManager.put("TabbedPane.contentOpaque", false);
        tabs = new SettingsTabs(this, generalPanel, commandsPanel);
        this.add(tabs, BorderLayout.CENTER);
    }

    private JButton initKeyButton(int keyCode){
        JButton button = new JButton(KeyEvent.getKeyText(keyCode));
        button.setFont(fontKey);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.white);
        return button;
    }

    private JLabel initKeyLabel(String bundleKey){
        JLabel label = new JLabel(bundle.getString(bundleKey), SwingConstants.RIGHT);
        label.setFont(font);
        label.setForeground(Color.white);
        return label;
    }

    private void initButtonPanel(){
        this.buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);

        back = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("optionsBackButton"),
                font);

        ok = new MainMenuButton(
                "pictures/GameMenuSleepButton.png",
                "pictures/GameMenuPressedButton.png",
                "pictures/GameMenuFocusButton.png",
                bundle.getString("optionsOkButton"),
                font);

        buttonPanel.add(back, BorderLayout.WEST);
        buttonPanel.add(ok, BorderLayout.EAST);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

}
