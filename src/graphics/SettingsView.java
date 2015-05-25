package graphics;

import engine.Settings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.util.ResourceBundle;

/**
 * Created by PixelMan on 22/05/15.
 */
public class SettingsView extends Background {

    private Settings currentSettings;

    private JTabbedPane tabs;

    private JPanel generalPanel;

    private JPanel volumePanel;
    private JLabel volumeLabel;
    private VolumeButton muteButton;
    private VolumeSlider volumeSlider;

    private JLabel difficultyLabel;
    private JComboBox<String> difficultyComboBox;

    private JLabel languageLabel;
    private JComboBox<String> languageComboBox;

    private JPanel commandsPanel;

    private JPanel buttonPanel;
    private MainMenuButton back;
    private MainMenuButton ok;

    private ResourceBundle bundle;
    private Font font;


    /* Constructors */

    public SettingsView(JPanel previousView, Settings currentSettings){
        super("pictures/simpleBackground.png");

        this.currentSettings = currentSettings;

        this.setLayout(new BorderLayout());

        try {
            File file = new File("fonts/newyorkescape.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, file)
                    .deriveFont(Font.PLAIN, 20);
        } catch (Exception e) {
            font = null;
        }

        this.setPreferredSize(previousView.getSize());

        this.bundle = MainFrame.getCurrentInstance().getBundle();

        instantiateTitle();
        initCommandPanel();
        initGeneralPanel();
        initTabs();
        initButtonPanel();

    }

    /* Accessors */

    public MainMenuButton getOk() {
        return ok;
    }

    public MainMenuButton getBack() {
        return back;
    }

    public VolumeButton getMuteButton() {
        return muteButton;
    }

    public VolumeSlider getVolumeSlider() {
        return volumeSlider;
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
        GridLayout generalLayout = new GridLayout(3, 2);
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

        volumePanel = new JPanel(new BorderLayout());
        volumePanel.setOpaque(false);
        muteButton = new VolumeButton(currentSettings.getVolume());
        volumeSlider = new VolumeSlider(currentSettings.getVolume());
        volumePanel.add(muteButton, BorderLayout.WEST);
        volumePanel.add(volumeSlider, BorderLayout.CENTER);

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

        generalPanel.add(volumeLabel);
        generalPanel.add(volumePanel);
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
        commandsPanel.add(label);
        commandsPanel.setOpaque(false);
    }

    private void initTabs(){
        UIManager.put("TabbedPane.contentOpaque", false);
        tabs = new SettingsTabs(this, generalPanel, commandsPanel);
        this.add(tabs, BorderLayout.CENTER);
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
