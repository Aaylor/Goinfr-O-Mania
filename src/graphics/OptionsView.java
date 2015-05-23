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

    private ResourceBundle bundle;
    private Font font;

    public OptionsView(JPanel previousView){
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

        this.bundle = MainFrame.getCurrentInstance().getBundle();

        instantiateTitle();

    }

    private void instantiateTitle(){
        JLabel title = new JLabel(bundle.getString("optionsTitle"), SwingConstants.CENTER);
        if(this.font != null)
            title.setFont(font.deriveFont(Font.PLAIN, 60));
        title.setForeground(new Color(0x3B3B3B));
        this.add(title, BorderLayout.NORTH);
    }

}
