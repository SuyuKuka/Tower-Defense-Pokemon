package graphics;

import javax.swing.*;

import geometry.Rectangle;

import java.awt.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TowerPresent extends JPanel{
    public TowerPresent(){
        setPreferredSize(new Dimension(354, 1000)); //taille parfaite
        setOpaque(false);
        setLayout(new GridLayout(8,1));

        Rectangle pokemon1 = new Rectangle(1);
        Rectangle pokemon2 = new Rectangle(2);
        Rectangle pokemon3 = new Rectangle(3);
        Rectangle pokemon4 = new Rectangle(4);
        Rectangle pokemon5 = new Rectangle(5);
        Rectangle pokemon6 = new Rectangle(6);
        add(pokemon1);
        add(pokemon2);
        add(pokemon3);
        add(pokemon4);
        add(pokemon5);
        add(pokemon6);

        Rectangle hero1 = new Rectangle(7);
        Rectangle hero2 = new Rectangle(8);
        add(hero1);
        add(hero2);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("ressources/background/towerPanel.png")), 0, 0, getWidth(), getHeight(), this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
