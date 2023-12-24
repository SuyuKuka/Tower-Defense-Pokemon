package graphics;

import model.Player;
import model.Tower;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

public class TowerPresent extends JPanel {
    private Tower[] team = Player.INSTANCE.getTeam();
    private Tower[] hero = Player.INSTANCE.getHero();
    private GraphicsMaker maker = new GraphicsMaker();
    private TowerManagement selectedPokemon;

    public TowerPresent() {
        setPreferredSize(new Dimension(354, 1000)); // taille parfaite
        setOpaque(false);

        add(initiateTitle());

        GridLayout heroLayout = new GridLayout(1, 2);
        heroLayout.setHgap(10);
        heroLayout.setVgap(10);
        JPanel heroPanel = new JPanel(heroLayout);
        heroPanel.setOpaque(false);
        TowerManagement hero1 = new TowerManagement(hero[0], 7);
        TowerManagement hero2 = new TowerManagement(hero[1], 8);
        heroPanel.add(hero1);
        heroPanel.add(hero2);

        add(heroPanel);

        GridLayout normalLayout = new GridLayout(8, 1);
        normalLayout.setHgap(10);
        normalLayout.setVgap(10);
        setLayout(normalLayout);
        for (int i = 0; i < team.length; i = i + 1) {
            try {
                initiateTeam(team[i], i + 1);
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

    public JPanel initiateTitle() {
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

    public void initiateTeam(Tower tower, int numero) throws IOException {
        TowerManagement pokemon = new TowerManagement(tower, numero);
        add(pokemon);
        pokemon.setEnabled(true);
        pokemon.setBorder(new EmptyBorder(2, 2, 2, 2));

        pokemon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!pokemon.isPlaced() && !pokemon.getSprite().equals("ressources/sprite/nothing.png")) {
                    if (pokemon.isSelected()) {
                        if (selectedPokemon != null) {
                            selectedPokemon.setBorder(new EmptyBorder(2, 2, 2, 2));
                        }
                        pokemon.setBorder(new MatteBorder(2, 2, 2, 2, Color.RED));
                        selectedPokemon = pokemon;
                    } else {
                        pokemon.setBorder(new EmptyBorder(2, 2, 2, 2));
                        selectedPokemon = null;
                    }
                }
            }
        });
    }

    public Tower getTower() {
        return selectedPokemon.getPokemon();
    }

    public TowerManagement getSelectedPokemon() {
        return selectedPokemon;
    }
}
