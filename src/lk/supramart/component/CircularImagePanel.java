package lk.supramart.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class CircularImagePanel extends JPanel {

    private BufferedImage image;

    public CircularImagePanel() {
        super();
        setOpaque(false);
        setPreferredSize(new Dimension(120, 120)); // square size
        setBackground(Color.LIGHT_GRAY); // default bg if no image
    }

    /**
     * Set the image to display inside the circle.
     *
     * @param image BufferedImage to display
     */
    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int diameter = Math.min(getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create circular clipping area
        Ellipse2D circle = new Ellipse2D.Float(0, 0, diameter, diameter);
        g2.setClip(circle);

        // Draw background circle if no image
        if (image == null) {
            g2.setColor(getBackground());
            g2.fillOval(0, 0, diameter, diameter);
        } else {
            // Draw the image scaled to fit the circle area
            Image scaledImage = image.getScaledInstance(diameter, diameter, Image.SCALE_SMOOTH);
            g2.drawImage(scaledImage, 0, 0, diameter, diameter, null);
        }

        g2.setClip(null); // reset clip
        g2.dispose();
    }
}
