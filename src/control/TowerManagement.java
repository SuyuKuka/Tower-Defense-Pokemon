package control;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import graphics.GraphicsMaker;
import model.Tower;

public class TowerManagement extends JPanel implements MouseListener, MouseMotionListener{
    private Tower pokemon;
    private int numero;
    private boolean hasMove;
    private double xClick;
    private double yClick;

    public TowerManagement(Tower pokemon, int numero) {
        this.pokemon = pokemon;
        this.numero = numero;

        setOpaque(false);

        setPreferredSize(new Dimension(20, 10));

        setLayout(new BorderLayout());

        if (!pokemon.getSprite().contains("ressources/sprite/nothing.png")) {
            
            add(addSpriteLabel(), BorderLayout.WEST);
            add(addInfoPanel(), BorderLayout.CENTER);

            addMouseListener(this);
            addMouseMotionListener(this);
        }

    }

    private JPanel addInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setOpaque(false);

        infoPanel.add(addNameLabel(), BorderLayout.NORTH);
        infoPanel.add(addPriceLabel(), BorderLayout.SOUTH);

        return infoPanel;
    }

    private JPanel addSpriteLabel() {
        JPanel spritePanel = new JPanel();
        spritePanel.setOpaque(false);
        try {
            BufferedImage spriteImage = ImageIO.read(new File(pokemon.getSprite()));
            Image spriteResized = spriteImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            JLabel spriteLabel = new JLabel(new ImageIcon(spriteResized));
            spritePanel.add(spriteLabel, BorderLayout.SOUTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spritePanel.setBorder(BorderFactory.createEmptyBorder(5, 28, 0, 0));
        return spritePanel;
    }

    private JPanel addNameLabel() {
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        JLabel nameLabel = new JLabel(pokemon.getName());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(getFont().deriveFont(Font.PLAIN, 24f));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        namePanel.add(nameLabel);
        return namePanel;
    }

    private JPanel addPriceLabel() {
        JPanel pricePanel = new JPanel();
        pricePanel.setOpaque(false);
        JLabel priceLabel = new JLabel("Prix : " + String.valueOf(pokemon.getPrice()) + "$");
        priceLabel.setForeground(Color.WHITE);
        priceLabel.setFont(getFont().deriveFont(Font.PLAIN, 20f));
        pricePanel.add(priceLabel);
        return pricePanel;
    }

    private JLabel addLevelLabel() {
        JLabel levelLabel = new JLabel("Nv : " + String.valueOf(pokemon.getLevel()));
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(getFont().deriveFont(Font.PLAIN, 24f));
        return levelLabel;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (numero > 6) {
            if (!pokemon.getSprite().contains("ressources/sprite/nothing.png")) {
                g.drawImage(new ImageIcon("ressources/team/rectanglehero.png").getImage(), 10, 10, getWidth(), getHeight(), this);
            } else {
                g.drawImage(new ImageIcon("ressources/team/rectanglehero_locked.png").getImage(), 10, 10, getWidth(), getHeight(), this);
            }
        } else {
            if (!pokemon.getSprite().contains("ressources/sprite/nothing.png")) {
                g.drawImage(new ImageIcon("ressources/team/rectanglepkm.png").getImage(), 10, 10, getWidth(), getHeight(), this);
            } else {
                g.drawImage(new ImageIcon("ressources/team/rectanglepkm.png").getImage(), 10, 10, getWidth(), getHeight(), this);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!hasMove) {
            // Store the initial click position relative to the center of the spritePanel
            xClick = e.getX() - (addSpriteLabel().getWidth() / 2);
            yClick = e.getY() - (addSpriteLabel().getHeight() / 2);
            hasMove = true;
        } else {
            // Calculate the new position based on the drag
            int newX = (int) (e.getX() - xClick);
            int newY = (int) (e.getY() - yClick);

            // Set the new position for the spritePanel (the panel containing the sprite)
            Component[] components = getComponents();
            for (Component component : components) {
                if (component instanceof JPanel && ((JPanel) component).getComponentCount() > 0) {
                    component.setLocation(newX, newY);
                    setComponentZOrder((JPanel) component, 0);
                    break; // Assuming there is only one spritePanel, you can modify this accordingly
                }
            }

            // Repaint the panel
            repaint();
        }
    }





    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Affiche les infos de la tour
        if(!hasMove) {
            System.out.println("Rectangle " + numero + " - entrée mouvement");
            xClick=e.getX();
            yClick=e.getY();
            hasMove = true;
        }else {
            System.out.println("Rectangle " + numero + " - sortie mouvement");
            hasMove = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
