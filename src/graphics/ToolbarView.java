package graphics;

import log.IGLog;

import javax.swing.*;
import java.util.ResourceBundle;

public class ToolbarView extends JMenuBar {

    public ToolbarView(ResourceBundle bundle) {
        super();

        IGLog.write("Creating toolbar.");

        JMenu file = new JMenu(bundle.getString("file"));
        JMenu help = new JMenu(bundle.getString("help"));

        addJmenus(file, help);
    }

    private void addJmenus(JMenu ... menus) {
        for (JMenu menu : menus) {
            add(menu);
        }
    }


}
