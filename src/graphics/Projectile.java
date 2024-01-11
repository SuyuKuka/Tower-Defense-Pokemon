package graphics;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Ennemi;
import model.Tower;

public class Projectile extends JPanel {
    private Ennemi target;
    private Timer eraser;

    private int cellSize = 32;

    public Projectile(Ennemi target, Tower shooter, String image) {
        this.target = target;
        eraser = new Timer(500, event -> {
            eraseProj();
            eraser.stop();
        });

        setVisible(false);
        setOpaque(false);
        setBounds(0, 0, cellSize, cellSize);

        JLabel img = new JLabel(new ImageIcon(image));
        add(img);
    }

    public void updateProj() {
        if (isVisible() && !eraser.isRunning()) {
            eraser.start();
        }
        setLocation(new Point(target.getJLabelSprite().getX(), target.getJLabelSprite().getY()));
    }

    public void eraseProj() {
        setVisible(false);
    }
}
