package graphics;

import model.Ennemi;
import model.Tower;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static javax.swing.JLayeredPane.POPUP_LAYER;

public class Projectile extends JPanel {
    private Ennemi target;
    private Tower shooter;
    private String image;

    private UpdateMap updateMap;
    int cellSize=32;

    public Projectile(Ennemi target, Tower shooter, String image) {
        this.target = target;
        this.shooter = shooter;
        this.image = image;

        int[][] map=target.getMap().getConfig();
        int mapWidth = map[0].length;
        int mapHeight = map.length;

        setOpaque(false);

        JLabel img=new JLabel(new ImageIcon(image));
        add(img);


        setBounds(0, 0,  cellSize, cellSize);

//        System.out.println(target.getName());
//        System.out.println(shooter.getName());
//        System.out.println();

    }

    public void setUpdateMap(UpdateMap updateMap) {
        this.updateMap = updateMap;

        setVisible(false);

    }

    public void updateProj() {

        if (getLocation().getX() < target.getJLabelSprite().getLocation().getX()) {
            setLocation(new Point((int) (getLocation().getX() + 5), (int) getLocation().getY()));
        }
        if (getLocation().getX() > target.getJLabelSprite().getLocation().getX()) {
            setLocation(new Point((int) (getLocation().getX() - 5), (int) getLocation().getY()));
        }
        if (getLocation().getY() < target.getJLabelSprite().getLocation().getY()) {
            setLocation(new Point((int) (getLocation().getX()), (int) getLocation().getY() + 5));
        }
        if (getLocation().getY() > target.getJLabelSprite().getLocation().getY()) {
            setLocation(new Point((int) (getLocation().getX()), (int) getLocation().getY() - 5));
        }
//        if (getLocation().getX() == target.getJLabelSprite().getLocation().getX() && getLocation().getY() == target.getJLabelSprite().getLocation().getY()) {
//            setVisible(false);
//        }
        if(target.getPv()<=0){
            setVisible(false);
        }

    }
}
