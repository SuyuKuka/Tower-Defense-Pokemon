package graphics;

import javax.swing.*;

import control.TowerManagement;
import model.Player;
import model.Tower;

import java.awt.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TowerPresent extends JPanel{
    private Tower[] team = model.Player.INSTANCE.getTeam();
    private Tower[] hero = model.Player.INSTANCE.getHero();
    private GraphicsMaker maker = new GraphicsMaker();
    public TowerPresent(){
        setPreferredSize(new Dimension(354, 1000)); //taille parfaite
        setOpaque(false);

        add(initiateTitle());

        JPanel heroPanel = new JPanel(new GridLayout(1, 2));
        heroPanel.setOpaque(false);
        TowerManagement hero1 = new TowerManagement(hero[0], 7);
        TowerManagement hero2 = new TowerManagement(hero[1], 8);
        heroPanel.add(hero1);
        heroPanel.add(hero2);

        add(heroPanel);

        setLayout(new GridLayout(8,1));
        TowerManagement pokemon1 = new TowerManagement(team[0], 1);
        TowerManagement pokemon2 = new TowerManagement(team[1], 2);
        TowerManagement pokemon3 = new TowerManagement(team[2], 3);
        TowerManagement pokemon4 = new TowerManagement(team[3], 4);
        TowerManagement pokemon5 = new TowerManagement(team[4], 5);
        TowerManagement pokemon6 = new TowerManagement(team[5], 6);
        add(pokemon1);
        add(pokemon2);
        add(pokemon3);
        add(pokemon4);
        add(pokemon5);
        add(pokemon6);

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("ressources/team/towerside.png")), 0, 0, getWidth(), getHeight(), this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public JPanel initiateTitle(){
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        JLabel logoPokeball = new JLabel(new ImageIcon("ressources/team/pokeball.png"));
        JLabel titleLabel = new JLabel("|   Votre equipe ");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(maker.getCustomFont().deriveFont(Font.PLAIN, 24f));

        titlePanel.add(logoPokeball);
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        return titlePanel;
    }
}
