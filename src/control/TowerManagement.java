package control;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import graphics.GraphicsMaker;
import model.Tower;

public class TowerManagement extends JPanel {
    private GraphicsMaker maker = new GraphicsMaker();
    private Tower pokemon;
    private int numero;
    private int arcWidth = 30;
    private int arcHeight = 30;

    public TowerManagement(Tower pokemon, int numero) {
        this.pokemon = pokemon;
        this.numero = numero;
    
        setLayout(new GridBagLayout());
    
        if (!pokemon.getSprite().contains("ressources/sprite/nothing.png")) {
            try {
                BufferedImage spriteImage = ImageIO.read(new File(pokemon.getSprite()));
                Image spriteResized = spriteImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                JLabel spriteLabel = new JLabel(new ImageIcon(spriteResized));
    
                GridBagConstraints spriteConstraints = new GridBagConstraints();
                spriteConstraints.gridx = 0;
                spriteConstraints.gridy = 0;
                spriteConstraints.anchor = GridBagConstraints.NORTHWEST;
                add(spriteLabel, spriteConstraints);
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            JLabel levelLabel = new JLabel("Nv : " + String.valueOf(pokemon.getLevel()));
            levelLabel.setForeground(Color.WHITE);
            levelLabel.setFont(getFont().deriveFont(Font.PLAIN, 24f));
    
            GridBagConstraints levelConstraints = new GridBagConstraints();
            levelConstraints.gridx = 1;
            levelConstraints.gridy = 0;
            levelConstraints.anchor = GridBagConstraints.CENTER;
            add(levelLabel, levelConstraints);
    
            JLabel priceLabel = new JLabel("Cout : " + String.valueOf(pokemon.getPrice()) + " $");
            priceLabel.setForeground(Color.WHITE);
            priceLabel.setFont(getFont().deriveFont(Font.PLAIN, 20f));
    
            // Changer la colonne pour placer le prix tout à gauche
            GridBagConstraints priceConstraints = new GridBagConstraints();
            priceConstraints.gridx = -1;  // Changer de 1 à 0
            priceConstraints.gridy = 1;
            priceConstraints.anchor = GridBagConstraints.WEST;
            add(priceLabel, priceConstraints);
    
            JLabel nameLabel = new JLabel(pokemon.getName());
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(getFont().deriveFont(Font.PLAIN, 24f));
    
            GridBagConstraints nameConstraints = new GridBagConstraints();
            nameConstraints.gridx = 1;
            nameConstraints.gridy = 1;
            nameConstraints.anchor = GridBagConstraints.CENTER;
            add(nameLabel, nameConstraints);
        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (numero > 6) {
            if (!pokemon.getSprite().contains("ressources/sprite/nothing.png")) {
                g.drawImage(new ImageIcon("ressources/team/rectanglehero.png").getImage(), 0, 0, getWidth(), getHeight(), this);
            } else {
                g.drawImage(new ImageIcon("ressources/team/rectanglehero_locked.png").getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        } else {
            if (!pokemon.getSprite().contains("ressources/sprite/nothing.png")) {
                g.drawImage(new ImageIcon("ressources/team/rectanglepkm.png").getImage(), 0, 0, getWidth(), getHeight(), this);
            } else {
                g.drawImage(new ImageIcon("ressources/team/rectanglepkm.png").getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
