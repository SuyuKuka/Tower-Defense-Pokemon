package graphics;

import model.Player;
import model.Tower;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.io.IOException;

public class TowerPresent extends JPanel {
    private Tower[] team = Player.INSTANCE.getTeam();
    private Tower[] hero = Player.INSTANCE.getHero();
    private GraphicsMaker maker = new GraphicsMaker();
    private TowerManagement selectedPokemon;
    private JPanel bg = maker.imagePanel("ressources/team/towerside.png");
    private JPanel infoPanel;

    private CardLayout cardLayout = new CardLayout();

    public TowerPresent() {
        setLayout(cardLayout);

        bg.setOpaque(false);

        setPreferredSize(new Dimension(250, 0)); // taille parfaite
        setOpaque(false);
        GridLayout normalLayout = new GridLayout(8, 1);
        normalLayout.setHgap(10);
        normalLayout.setVgap(10);
        bg.setLayout(normalLayout);

        bg.add(initiateTitle());

        GridLayout heroLayout = new GridLayout(1, 2);
        heroLayout.setHgap(10);
        heroLayout.setVgap(10);
        JPanel heroPanel = new JPanel(heroLayout);
        heroPanel.setOpaque(false);
        TowerManagement hero1 = new TowerManagement(hero[0], 7);
        TowerManagement hero2 = new TowerManagement(hero[1], 8);
        heroPanel.add(hero1);
        heroPanel.add(hero2);

        bg.add(heroPanel);

        for (int i = 0; i < team.length; i++) {
            try {
                initiateTeam(team[i], i + 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        infoPanel = new JPanel(cardLayout);
        infoPanel.setOpaque(false);
        infoPanel.add(bg, "main");

        add(infoPanel, BorderLayout.CENTER);
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
        bg.add(pokemon);
        pokemon.setEnabled(true);
        pokemon.setBorder(new EmptyBorder(2, 2, 2, 2));

        pokemon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pokemon.isPlaced() && !pokemon.getSprite().equals("ressources/sprite/nothing.png")) {
                    showPokemonInfo(pokemon.getPokemon());
                }
                if (!pokemon.isPlaced() && !pokemon.getSprite().equals("ressources/sprite/nothing.png")) {
                    if (pokemon.isSelected()) {
                        if (selectedPokemon != null) {
                            selectedPokemon.setBorder(new EmptyBorder(2, 2, 2, 2));
                        }
                        pokemon.setBorder(new MatteBorder(2, 2, 2, 0, Color.RED));
                        selectedPokemon = pokemon;
                    } else {
                        pokemon.setBorder(new EmptyBorder(2, 2, 2, 2));
                        selectedPokemon = null;
                    }
                }
            }
        });
    }

    private void showPokemonInfo(Tower tower) {
        cardLayout.show(infoPanel, "pokemonInfo");

        JPanel pokemonInfoPanel = maker.imagePanel("ressources/team/towerside.png");
        pokemonInfoPanel.setOpaque(false);
        
        pokemonInfoPanel.add(initiateInfo(tower));

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(infoPanel, "main"));
        pokemonInfoPanel.add(backButton);

        infoPanel.add(pokemonInfoPanel, "pokemonInfo"); 
        revalidate();
        repaint();
    }

    private JPanel initiateInfo(Tower tower){
        JPanel info = new JPanel();
        info.setOpaque(false);
        JLabel name = new JLabel(tower.getName());
        JLabel level = new JLabel(String.valueOf(tower.getLevel()));
        // titleLabel.setFont(maker.getCustomFont().deriveFont(Font.PLAIN, 24f));
        JLabel type = new JLabel(tower.getType()[0].toString());
        JLabel stat = new JLabel("ATK PHY : " + String.valueOf(tower.getPhysical() + " ATK SPE : " + String.valueOf(tower.getSpecial())));

        return null;
    }

    private JPanel initiateSkills(Tower tower){
        //Afficher sur un 2x2 avec des JButton? ou des JLabel?
        return null;
    }

    public Tower getTower() {
        return selectedPokemon.getPokemon();
    }

    public TowerManagement getSelectedPokemon() {
        return selectedPokemon;
    }
}
