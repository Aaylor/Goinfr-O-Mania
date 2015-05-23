package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.io.File;

/**
 * Created by PixelMan on 23/05/15.
 */
public class OptionsTabs extends JTabbedPane {

    public class OptionTabsUI extends BasicTabbedPaneUI {

        JPanel parent;

        Image selected;
        Image[] unselected;
        Image[] tabBorder;

        public OptionTabsUI(JPanel parent){
            super();
            try {
                this.parent = parent;

                this.selected = ImageIO.read(new File("pictures/OptionsMenuSelected.png"));

                this.unselected = new Image[2];
                this.unselected[0] = ImageIO.read(new File("pictures/OptionsMenuUnselected0.png"));
                this.unselected[1] = ImageIO.read(new File("pictures/OptionsMenuUnselected1.png"));

                this.tabBorder = new Image[2];
                this.tabBorder[0] = ImageIO.read(new File("pictures/OptionsTabBorder0.png"));
                this.tabBorder[1] = ImageIO.read(new File("pictures/OptionsTabBorder1.png"));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected void paintTabBackground(
                Graphics g, int tabPlacement,
                int tabIndex, int x, int y,
                int w, int h, boolean isSelected) {
            if(isSelected)
                g.drawImage(selected, x, y, parent);
            else
                g.drawImage(unselected[tabIndex], x, y, parent);
        }

        @Override
        protected void paintTabBorder(
                Graphics g, int tabPlacement, int tabIndex,
                int x, int y, int w, int h, boolean isSelected) {
            int xdec;
            if(tabIndex == 0)
                xdec = x - 8;
            else
                xdec = x;
            g.drawImage(tabBorder[tabIndex], xdec, y, parent);

        }

        @Override
        protected void paintContentBorderTopEdge(
                Graphics g, int tabPlacement,
                int selectedIndex, int x, int y,
                int w, int h) {
            int margin = parent.getWidth()/2 - 181;
            g.setColor(new Color(130, 131, 121));
            g.fillRect(x, y - 49, margin, 2);
            g.fillRect(x + margin + 2*181 - 10, y - 2, margin + 10, 2);
            //g.fillRect(x, y, w, h);
        }

        @Override
        protected void paintContentBorderLeftEdge(
                Graphics g, int tabPlacement,
                int selectedIndex, int x, int y,
                int w, int h) {}

        @Override
        protected void paintContentBorderBottomEdge(
                Graphics g, int tabPlacement,
                int selectedIndex, int x, int y,
                int w, int h) {}

        @Override
        protected void paintContentBorderRightEdge(
                Graphics g, int tabPlacement,
                int selectedIndex, int x, int y,
                int w, int h) {}

        @Override
        protected void paintFocusIndicator(
                Graphics g, int tabPlacement,
                Rectangle[] rects, int tabIndex,
                Rectangle iconRect, Rectangle textRect,
                boolean isSelected) {}

        @Override
        protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
            return 50;
        }

        @Override
        protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics){
            return 165;
        }

        @Override
        protected Insets getTabAreaInsets(int tabPlacement) {
            int margin = parent.getWidth()/2 - 181;
            return new Insets(30, margin, 0, 0);
        }
    }

    public OptionsTabs(JPanel parent, JPanel general, JPanel commands) {
        super();

        this.setUI(new OptionTabsUI(parent));

        this.addTab("General", general);
        this.addTab("Commands", commands);

    }
}
