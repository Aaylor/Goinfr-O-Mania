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
public class OptionsView extends Background {

    private JTabbedPane tabs;

    private JPanel generalPanel;
    private JPanel commandsPanel;

    private ResourceBundle bundle;
    private Font font;

    public OptionsView(JPanel previousView){
        super("pictures/simpleBackground.png");

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

    }

    public JTabbedPane getTabs() {
        return tabs;
    }

    private void instantiateTitle(){
        JLabel title = new JLabel(bundle.getString("optionsTitle"), SwingConstants.CENTER);
        if(this.font != null)
            title.setFont(font.deriveFont(Font.PLAIN, 60));
        title.setForeground(new Color(0x3B3B3B));
        this.add(title, BorderLayout.NORTH);
    }

    public void initGeneralPanel(){
        generalPanel = new JPanel();
        JLabel label = new JLabel("General Panel");
        label.setForeground(Color.white);
        generalPanel.add(label);
        generalPanel.setOpaque(false);
    }

    public void initCommandPanel(){
        commandsPanel = new JPanel();
        JLabel label = new JLabel("Command Panel");
        label.setForeground(Color.white);
        commandsPanel.add(label);
        commandsPanel.setOpaque(false);
    }

    public void initTabs(){
        UIManager.put("TabbedPane.contentOpaque", false);
        tabs = new OptionsTabs(this, generalPanel, commandsPanel);
        this.add(tabs, BorderLayout.CENTER);
    }

}
