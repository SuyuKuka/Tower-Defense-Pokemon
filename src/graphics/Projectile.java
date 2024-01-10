package graphics;

import model.Ennemi;
import model.Tower;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Projectile extends JPanel {
    private Ennemi target;
    private Tower shooter;
    private String image;


    public Projectile(Ennemi target, Tower shooter, String image) {
        this.target = target;
        this.shooter = shooter;
        this.image = image;

        int[][] map=target.getMap().getConfig();
        int mapWidth = map[0].length;
        int mapHeight = map.length;
        int cellSize=32;

    }

    public void drawLine(){

    }
@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("LINEEEEE");
        drawProjectiles( g);
    }

    public  void drawProjectiles(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawLine(target.getPos()[0],target.getPos()[1],shooter.getPos()[0],shooter.getPos()[1]);
    }
}
