package graphics;

import java.awt.*;

import javax.swing.*;

import model.Tower;

public class TowerManagement extends JPanel {
    private Tower pokemon;
    private int numero;
    private boolean isSelected;
    private JPanel spritePanel = new JPanel();
    private JPanel infoPanel = new JPanel();
    private boolean isPlaced = false;
    private GraphicsMaker maker = new GraphicsMaker();

    private JLabel nameLabel;
    private JLabel spriteLabel;
    private JLabel priceLabel;

    public TowerManagement(Tower pokemon, int numero) {
        this.pokemon = pokemon;
        this.numero = numero;

        setOpaque(false);

        setLayout(new BorderLayout());

        if (pokemon != null) {
            add(addSpriteLabel(), BorderLayout.WEST);
            add(addInfoPanel(), BorderLayout.CENTER);
        }

    }

    private JPanel addInfoPanel() {
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setOpaque(false);

        infoPanel.add(addNameLabel(), BorderLayout.NORTH);

        // Ajoutez une verification pour afficher le niveau ou le prix
        if (isPlaced) {
            infoPanel.add(addLevelLabel(), BorderLayout.SOUTH);
        } else {
            infoPanel.add(addPriceLabel(), BorderLayout.SOUTH);
        }

        return infoPanel;
    }

    private JPanel addSpriteLabel() {
        spritePanel.setOpaque(false);
        spriteLabel = new JLabel(new ImageIcon(pokemon.getSprite()));
        pokemon.setManager(this);

        spritePanel.add(spriteLabel, BorderLayout.SOUTH);
        spritePanel.setBorder(BorderFactory.createEmptyBorder(5, 28, 0, 0));
        return spritePanel;
    }

    private JPanel addNameLabel() {
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);

        nameLabel = new JLabel(pokemon.getName());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(maker.getCustomFont().deriveFont(24f));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        namePanel.add(nameLabel);
        return namePanel;
    }

    private JPanel addPriceLabel() {
        JPanel pricePanel = new JPanel();
        pricePanel.setOpaque(false);
        priceLabel = new JLabel("Prix : " + String.valueOf(pokemon.getPrice()) + "$");
        priceLabel.setFont(maker.getCustomFont().deriveFont(20f));
        priceLabel.setForeground(Color.WHITE);

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
            if (pokemon != null) {
                g.drawImage(new ImageIcon("ressources/team/rectanglehero.png").getImage(), 0, 0, getWidth(),
                        getHeight(), this);
            } else {
                g.drawImage(new ImageIcon("ressources/team/rectanglehero_locked.png").getImage(), 0, 0, getWidth(),
                        getHeight(), this);
            }
        } else {
            if (pokemon != null) {
                g.drawImage(new ImageIcon("ressources/team/rectanglepkm.png").getImage(), 0, 0, getWidth(),
                        getHeight(), this);
            } else {
                g.drawImage(new ImageIcon("ressources/team/rectanglepkm.png").getImage(), 0, 0, getWidth(),
                        getHeight(), this);
            }
        }
    }

    public String getSprite() {
        return pokemon.getSprite();
    }

    public int getNumero() {
        return numero;
    }

    public boolean isSelected() {
        if (isSelected) {
            isSelected = false;
            return isSelected;
        } else {
            isSelected = true;
            return isSelected;
        }
    }

    public JPanel getSpritePanel() {
        return spritePanel;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public Tower getPokemon() {
        return pokemon;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public void updateManager(Tower tower) {
        spriteLabel.setIcon(new ImageIcon(tower.getSprite()));
        nameLabel.setText(tower.getName());
        priceLabel.setText("Prix : " + pokemon.getPrice() + "$");
        revalidate();
        repaint();
    }

}
