package graphics;

import model.Hero;
import model.Player;
import model.Tower;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class TowerPresent extends JPanel {
    private GraphicsMaker maker = new GraphicsMaker();
    private TowerManagement selectedPokemon;
    private JPanel bg = maker.imagePanel("ressources/team/towerside.png");
    private Tower[] team = Player.INSTANCE.getTeam();

    public TowerPresent() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 0)); // taille parfaite
        setOpaque(false);
        bg.add(initiateTitle());

        GridLayout normalLayout = new GridLayout(8, 1);
        normalLayout.setHgap(10);
        normalLayout.setVgap(10);

        for (int i = 0; i < team.length; i = i + 1) {
            if (!team[i].getSprite().contains("ressources/sprite/nothing.png")) {
                initiateTeam(team[i], i + 1);
            }
        }

        bg.setOpaque(false);
        bg.setLayout(normalLayout);
        add(bg, BorderLayout.CENTER);
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

    public void initiateTeam(Tower tower, int numero)  {
        TowerManagement pokemon = new TowerManagement(tower, numero, this);
        bg.add(pokemon);
    }

    public void setSelectedPokemon(TowerManagement selectedPokemon) {
        this.selectedPokemon = selectedPokemon;
    }
    public void updatePresent() {
        bg.removeAll();
        bg.add(initiateTitle());
        for (int i = 0; i < Player.INSTANCE.getTeam().length; i++) {
            if (!Player.INSTANCE.getTeam()[i].getSprite().contains("ressources/sprite/nothing.png")) {
                initiateTeam(Player.INSTANCE.getTeam()[i], i + 1);
            }
        }
    }

    public void setHero(Hero hero) {
        JLabel name = maker.oneLineTextMaker();
        name.setText(hero.getName());

        JLabel desc = maker.oneLineTextMaker();
        desc.setText(hero.getDesc());

        JPanel heroInfoPanel = new JPanel();
        heroInfoPanel.setLayout(new GridLayout(2, 1));
        heroInfoPanel.setBackground(new Color(20, 20, 20, 150));
        heroInfoPanel.add(name);
        heroInfoPanel.add(desc);

        JPanel heroPanel = maker.imagePanel(hero.getImage());
        heroPanel.setLayout(new BorderLayout());
        heroPanel.setBackground(Color.BLACK);
        heroPanel.setBorder(new LineBorder(Color.CYAN));
        heroPanel.add(heroInfoPanel, BorderLayout.CENTER);

        bg.add(heroPanel);
    }

    public Tower getTower() {
        return selectedPokemon.getPokemon();
    }

    public TowerManagement getSelectedPokemon() {
        return selectedPokemon;
    }
}
