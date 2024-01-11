package graphics;

import model.Ennemi;
import model.Tower;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.JLayeredPane.POPUP_LAYER;

public class Projectile extends JPanel {
    private UpdateMap updateMap;
    int cellSize=32;
    //
    private Ennemi target;
    private ArrayList<Ennemi> listTarget;
    private Tower shooter;
    private String image;
    //
    private Timer eraser;
    public Projectile(Ennemi target, Tower shooter, String image) {
        this.target = target;
        this.shooter = shooter;
        this.image = image;

        if (target != null) {
            int[][] map = target.getMap().getConfig();
            int mapWidth = map[0].length;
            int mapHeight = map.length;
        }
        setVisible(false);
        setOpaque(false);
         eraser= new Timer(500, event -> { // TIMEEEEEEEEEER
                 eraseProj();
                 eraser.stop();
        });


        JLabel img = new JLabel(new ImageIcon(image));
        add(img);
        setBounds(0, 0, cellSize, cellSize);
    }


    public void updateProj() {
        if(isVisible()&&!eraser.isRunning()){
            eraser.start();
        }
        setLocation(new Point(target.getJLabelSprite().getX(), target.getJLabelSprite().getY()));
    }

    public void eraseProj(){
        setVisible(false);
    }
}
