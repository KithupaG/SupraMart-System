package lk.supramart.component;

import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import lk.supramart.util.LoggerUtil;

public class profileImageRounded extends JPanel {

    private Image bg;

    public profileImageRounded(String imagePath) {
        try {
            bg = new ImageIcon(getClass().getResource(imagePath)).getImage();
        } catch (Exception e) {
            LoggerUtil.Log.severe(profileImageRounded.class, "Exception: " + e.getMessage());
        }
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (bg != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            int size = Math.min(getWidth(), getHeight());
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Shape circle = new java.awt.geom.Ellipse2D.Float(0, 0, size, size);
            g2.setClip(circle);

            g2.drawImage(bg, 0, 0, size, size, this);

            g2.dispose();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // You can return fixed size if needed
        return new Dimension(100, 100); // Example: 100x100 circle
    }
}

