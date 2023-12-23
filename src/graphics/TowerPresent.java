package graphics;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import control.TowerManagement;
import model.Player;
import model.Tower;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TowerPresent extends JPanel{
    private Tower[] team = Player.INSTANCE.getTeam();
    private Tower[] hero = Player.INSTANCE.getHero();
    private GraphicsMaker maker = new GraphicsMaker();
    private TowerManagement selectedPokemon;

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
        for(int i=0; i<team.length; i=i+1){
            try {
                initiateTeam(team[i], i+1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

    public void initiateTeam(Tower tower, int numero) throws IOException{
        TowerManagement pokemon = new TowerManagement(tower, numero);
        add(pokemon);
        pokemon.setEnabled(true);
    
        pokemon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!pokemon.isPlaced() && !pokemon.getSprite().equals("ressources/sprite/nothing.png")){
                    if(pokemon.isSelected()) {
                        if(selectedPokemon!=null){
                        selectedPokemon.setBorder(BorderFactory.createEmptyBorder());
                        }
                        pokemon.setBorder(new MatteBorder(2, 2, 2, 0, Color.RED));
                        selectedPokemon=pokemon;
                    } else {
                        pokemon.setBorder(BorderFactory.createEmptyBorder());
                        selectedPokemon=null;
                    }
                }
            }
        });
    }
   
    public Tower getTower(){
        return selectedPokemon.getPokemon();
    }

    public TowerManagement getSelectedPokemon() {
        return selectedPokemon;
    }
}
