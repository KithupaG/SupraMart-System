package lk.supramart.component;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import lk.supramart.util.LoggerUtil;


public class CustomPanel extends JPanel{
    private Image bg;

        public CustomPanel(String imagePath) {
            try {
                bg = new ImageIcon(getClass().getResource(imagePath)).getImage();
            } catch (Exception e) {
                LoggerUtil.Log.severe(CustomPanel.class, "Exception: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bg != null) {
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        }
}
