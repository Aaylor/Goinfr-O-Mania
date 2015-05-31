package graphics;

import log.IGLog;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class ToolbarView extends JMenuBar {

    public ToolbarView(ResourceBundle bundle) {
        super();

        IGLog.write("Creating toolbar.");

        JMenu file = new JMenu(bundle.getString("file"));

        JMenu help = new JMenu(bundle.getString("help"));
        JMenuItem rules   = new JMenuItem(bundle.getString("rules"));
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpMenu(bundle);
            }
        });

        JMenuItem apropos = new JMenuItem(bundle.getString("apropos"));
        apropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        bundle.getString("project") + "\n" +
                        "Tortrat-Gentilhomme Nicolas\nRaymond Nicolas\nRunarvot Loïc",
                        bundle.getString("apropos"),
                        JOptionPane.NO_OPTION
                );
            }
        });

        addItems(help, rules, apropos);

        addJmenus(file, help);
    }

    private void addJmenus(JMenu ... menus) {
        for (JMenu menu : menus) {
            add(menu);
        }
    }

    private void addItems(JMenu menu, JMenuItem... items) {
        for (JMenuItem i : items) {
            menu.add(i);
        }
    }


}
