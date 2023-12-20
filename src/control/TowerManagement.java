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
    private Tower pokemon;
    private int numero;

    public TowerManagement(Tower pokemon, int numero) {
        this.pokemon = pokemon;
        this.numero = numero;

        setLayout(new BorderLayout()); // Use BorderLayout for better organization

        if (!pokemon.getSprite().contains("ressources/sprite/nothing.png")) {
            add(addSpriteLabel(), BorderLayout.WEST);
            add(addInfoPanel(), BorderLayout.CENTER);
        }
    }

    private JPanel addInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(addLevelLabel());
        infoPanel.add(addNameLabel());

        return infoPanel;
    }

    private JPanel addSpriteLabel() {
        JPanel spritePanel = new JPanel();
        spritePanel.setLayout(new GridLayout(2, 1));
        spritePanel.setOpaque(false);
        try {
            BufferedImage spriteImage = ImageIO.read(new File(pokemon.getSprite()));
            Image spriteResized = spriteImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            JLabel spriteLabel = new JLabel(new ImageIcon(spriteResized));
            spritePanel.add(spriteLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spritePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // TROP PUISSANT
        spritePanel.add(addPriceLabel(), BorderLayout.WEST);
        return spritePanel;
    }

    private JPanel addLevelLabel() {
        JPanel levelPanel = new JPanel();
        levelPanel.setOpaque(false);
        JLabel levelLabel = new JLabel("Nv : " + String.valueOf(pokemon.getLevel()));
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(getFont().deriveFont(Font.PLAIN, 24f));
        levelPanel.add(levelLabel);
        return levelPanel;
    }

    private JPanel addPriceLabel() {
        JPanel pricePanel = new JPanel();
        pricePanel.setOpaque(false);
        JLabel priceLabel = new JLabel("Cout : " + String.valueOf(pokemon.getPrice()) + "$");
        priceLabel.setForeground(Color.WHITE);
        priceLabel.setFont(getFont().deriveFont(Font.PLAIN, 20f));
        priceLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // TROP PUISSANT
        pricePanel.add(priceLabel);
        return pricePanel;
    }

    private JPanel addNameLabel() {
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        JLabel nameLabel = new JLabel(pokemon.getName());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(getFont().deriveFont(Font.PLAIN, 24f));
        namePanel.add(nameLabel);
        return namePanel;
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

