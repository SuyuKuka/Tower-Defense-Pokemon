package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;
import javax.swing.border.*;

public class GraphicsMaker {

    private Font customFont;

    public GraphicsMaker() {
        try {

            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("ressources/munro.ttf"));
            customFont = customFont.deriveFont(Font.ITALIC, 60f);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();

        }
    }

    public Font getCustomFont() {
        return customFont;
    }

    public JPanel imagePanel(String path) {
        BufferedImage backgroundImage = loadImage(path);
        JPanel IPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            }
        };

        return IPanel;
    }

    public BufferedImage loadImage(String path) {
        BufferedImage backgroundImage = null;
        if (backgroundImage == null) {
            File file = new File(path);
            try {
                backgroundImage = ImageIO.read(file);
                if (backgroundImage == null) {
                    backgroundImage = ImageIO.read(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return backgroundImage;
    }

    public JLabel resizeJLabel(String path, int size) {
        BufferedImage resize = loadImage(path);
        Image resized = resize.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        JLabel res = new JLabel(new ImageIcon(resized));
        res.setOpaque(false);
        return res;
    }

    public JLabel oneLineTextMaker() { // TOWER GUI.JAVA
        JLabel panel = new JLabel();
        panel.setFont(getCustomFont().deriveFont(20f));//
        panel.setForeground(Color.WHITE);

        return panel;
    }

}
