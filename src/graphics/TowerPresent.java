package graphics;

import model.Player;
import model.Tower;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.io.IOException;

import javax.swing.border.EmptyBorder;

public class TowerPresent extends JPanel {
    private Tower[] team = Player.INSTANCE.getTeam();
    private GraphicsMaker maker = new GraphicsMaker();
    private TowerManagement selectedPokemon;
    private JPanel bg = maker.imagePanel("ressources/team/towerside.png");

    public TowerPresent() {

        setLayout(new BorderLayout());
        bg.setOpaque(false);
        add(bg, BorderLayout.CENTER);

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



        bg.add(heroPanel);

        for (int i = 0; i < team.length; i = i + 1) {
            try {
                initiateTeam(team[i], i + 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public JPanel initiateTitle() {
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        JLabel logoPokeball = new JLabel(new ImageIcon("ressources/team/pokeball.png"));
        JLabel titleLabel = new JLabel("|   Votre equipe ");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(maker.getCustomFont().deriveFont(Font.PLAIN, 24f));

        JPanel title = new JPanel();
        title.setOpaque(false);
        title.add(logoPokeball);
        title.add(titleLabel);
        title.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));

        titlePanel.add(title);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        return titlePanel;
    }

    public void initiateTeam(Tower tower, int numero) throws IOException {
        TowerManagement pokemon = new TowerManagement(tower, numero);
        bg.add(pokemon);
        pokemon.setEnabled(true);
        pokemon.setBorder(new EmptyBorder(2, 2, 2, 0));

        pokemon.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!pokemon.isPlaced() && pokemon != null) {
                    if (pokemon.isSelected()) {
                        if (selectedPokemon != null) {
                            selectedPokemon.setBorder(new EmptyBorder(2, 2, 2, 0));
                        }
                        pokemon.setBorder(new MatteBorder(2, 2, 2, 0, Color.RED));
                        selectedPokemon = pokemon;
                    } else {
                        pokemon.setBorder(new EmptyBorder(2, 2, 2, 0));
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
