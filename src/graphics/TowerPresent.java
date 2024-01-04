package graphics;

import model.Player;
import model.Skill;
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
    private static TowerManagement selectedPokemon;
    private JPanel bg = maker.imagePanel("ressources/team/towerside.png");
    private static JPanel infoPanel;

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
        
        pokemonInfoPanel.add(initiateInfo(tower), BorderLayout.NORTH);

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(infoPanel, "main"));
        pokemonInfoPanel.add(backButton, BorderLayout.SOUTH);

        infoPanel.add(pokemonInfoPanel, "pokemonInfo"); 
        revalidate();
        repaint();
    }

    private JPanel initiateInfo(Tower tower){
        JPanel info = new JPanel(new GridLayout(5, 1));
        info.setOpaque(false);

        JLabel name = new JLabel(tower.getName());
        name.setFont(maker.getCustomFont().deriveFont(Font.PLAIN, 26f));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel level = new JLabel("Nv : " + String.valueOf(tower.getLevel()));
        level.setFont(maker.getCustomFont().deriveFont(Font.PLAIN, 22f));
        level.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel types = new JPanel(new FlowLayout());
        types.setOpaque(false);
        JLabel type0 = new JLabel("FEU");
        type0.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel type1 = new JLabel("NONE");
        type1.setHorizontalAlignment(SwingConstants.CENTER);
        types.add(type0);
        types.add(type1);
        JLabel stat = new JLabel("ATK PHY : " + String.valueOf(tower.getPhysical() + " ATK SPE : " + String.valueOf(tower.getSpecial())));
        stat.setFont(maker.getCustomFont().deriveFont(Font.PLAIN, 20f));
        stat.setHorizontalAlignment(SwingConstants.CENTER);    

        info.add(name);
        info.add(level);
        info.add(types);
        info.add(stat);

        return info;
    }

    public static void updateInfo(Tower tower){//casser
        if(selectedPokemon != null && selectedPokemon.getPokemon() == tower){
            Container infoContainer = (Container) infoPanel.getComponent(1);
            
            JLabel name = (JLabel) infoContainer.getComponent(0);
            JLabel level = (JLabel) infoContainer.getComponent(1);
            JLabel type0 = (JLabel) infoContainer.getComponent(2);
            JLabel type1 = (JLabel) infoContainer.getComponent(3);
            JLabel stat = (JLabel) infoContainer.getComponent(4);

            name.setText(tower.getName());
            level.setText("Nv : " + String.valueOf(tower.getLevel()));
            type0.setText("FEU");
            type1.setText("VOL");
            stat.setText("ATK PHY : " + String.valueOf(tower.getPhysical() + " ATK SPE : " + String.valueOf(tower.getSpecial())));
        }
    }

    private JPanel initiateSkills(Tower tower){
        //Afficher sur un 2x2 avec des JButton? ou des JLabel?
        JPanel skill = new JPanel(new GridLayout(2, 2));
        skill.setOpaque(false);

        JPanel skill1 = new JPanel();
        Skill tmp1 = tower.getSkills()[0];
        JLabel name1 = new JLabel(tower.getSkills()[0].getName());



        JPanel skill2 = new JPanel();

        JPanel skill3 = new JPanel();

        JPanel skill4 = new JPanel();

        return null;
    }

    public Tower getTower() {
        return selectedPokemon.getPokemon();
    }

    public TowerManagement getSelectedPokemon() {
        return selectedPokemon;
    }
}
