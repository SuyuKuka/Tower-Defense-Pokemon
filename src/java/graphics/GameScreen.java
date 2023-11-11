package java.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GameScreen extends JPanel {

    private Random random;

    public GameScreen() {
        random = new Random();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                g.fillRect(i * 50, j * 50, 50, 50);
                g.setColor(randColor());
            }
        }
    }

    private Color randColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
}
