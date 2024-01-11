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



    public Projectile(Ennemi target, Tower shooter, String image) {
        this.target = target;
        this.shooter = shooter;
        this.image = image;

        if (target != null) {
            int[][] map = target.getMap().getConfig();
            int mapWidth = map[0].length;
            int mapHeight = map.length;
        }

        setOpaque(false);

        JLabel img=new JLabel(new ImageIcon(image));
        add(img);
        setBounds(0, 0,  cellSize, cellSize);
    }

    public Projectile(ArrayList<Ennemi> listTarget, Tower shooter, String image) {
       this.listTarget=listTarget;
        this.shooter = shooter;
        this.image = image;

        for (Ennemi e : listTarget){
            int[][] map = e.getMap().getConfig();
            int mapWidth = map[0].length;
            int mapHeight = map.length;
        }

        setOpaque(false);

        JLabel img=new JLabel(new ImageIcon(image));
        add(img);
        setBounds(0, 0,  cellSize, cellSize);
    }


    public void updateProj() {
        setLocation(new Point(target.getJLabelSprite().getX(), target.getJLabelSprite().getY()));
        Timer timer = new Timer(1, e1 -> {
            setVisible(false);
        });
        timer.setRepeats(false);
        timer.start();
    }
}
