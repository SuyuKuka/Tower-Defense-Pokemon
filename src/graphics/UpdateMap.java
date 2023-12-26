package graphics;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Ennemi;
import model.Map;
import model.MapState;
import model.Player;
import model.Tower;

public class UpdateMap extends JPanel {
    private MapState state;
    private TowerPresent tPresent;
    private int cellSize = 50;
    GraphicsMaker maker = new GraphicsMaker();
    CardLayout card = new CardLayout();
    JPanel gameMap;

    public UpdateMap(MapState state, TowerPresent tPresent) {

        gameMap = new JPanel();

        this.state = state;
        this.tPresent = tPresent;

        int[][] map = state.getMap().getConfig();
        int mapWidth = state.getMap().getConfig()[0].length;
        int mapHeight = state.getMap().getConfig().length;

        GridBagLayout gridConfiguration = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        gameMap.setLayout(gridConfiguration);
        gameMap.setBackground(new Color(165, 125, 49));

        for (ArrayList<Ennemi> eList : state.getAllEnnemis()) {
            System.out.println(eList);
            for (Ennemi e : eList) {
                initSprite(e);
            }
        }

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {

                Tiles tiles;
                if (map[i][j] == 3) {
                    tiles = new Tiles(new int[] { i, j });
                } else if (map[i][j] == 4) {
                    tiles = new Tiles(new int[] { i, j });
                    tiles.setBackground(Color.CYAN);

                } else if (map[i][j] == 5) {
                    tiles = new Tiles(new int[] { i, j });
                    tiles.setBackground(Color.PINK);

                } else {
                    tiles = new Tiles(new int[] { i, j });
                    tiles.setBackground(new Color(111, 160, 83));
                }
                constraints.gridx = j;
                constraints.gridy = i;
                constraints.weightx = 1;
                constraints.weighty = 1;
                constraints.fill = GridBagConstraints.BOTH;

                tiles.setOpaque(false);
                gameMap.add(tiles, constraints);
                // gameMap.add(new JLabel(new ImageIcon("ressources/sprite/magikarp.gif")),
                // constraints);
            }
        }
        add(gameMap);
        setVisible(true);

    }

    private void drawEnnemi(Graphics g, int i, int j, int scale) {

        for (Ennemi e : state.getEnnemiPresent()) {

            if (i == e.getPos()[0] && j == e.getPos()[1]) {

                Tiles tiles = getTiles(new int[] { i, j });

                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(-20, -30, -20, -30);

                tiles.revalidate();
                g.setColor(getEnnemiColor(e));
                // g.fillRect(j * scale, i * scale, scale, scale);
            }
        }
    }

    private Color getEnnemiColor(Ennemi e) {
        if (e.isBoss()) {
            return new Color(225, 225, 225); // Couleur boss
        } else {
            return new Color(225, 0, 0); // Couleur normal
        }
    }

    public void initSprite(Ennemi e) {

        JLabel sprite = new JLabel(new ImageIcon(e.getSprite()));
        e.setJLabelSprite(sprite);

        GridBagConstraints constraints = new GridBagConstraints();

        gameMap.add(sprite, constraints);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        moveSprite();

    }

    private void moveSprite() {
        System.out.println("moving");
        for (Ennemi e : state.getEnnemiPresent()) {
            e.moveGraphic(e.getJLabelSprite());
        }
    }

    public Tiles getTiles(int[] pos) {
        for (Tiles tiles : Tiles.tilesList) {
            if (tiles.getPos()[0] == pos[0] && tiles.getPos()[1] == pos[1]) {
                return tiles;
            }
        }
        return null;
    }

    public class Tiles extends JPanel {
        private int[] pos;
        public static ArrayList<Tiles> tilesList = new ArrayList<>();
        private JLabel spriteOnIt;

        public Tiles(int[] pos) {
            this.pos = pos;
            tilesList.add(this);
            setLayout(new GridBagLayout());

            setBorder(new LineBorder(Color.RED));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(5, 5, 5, 5);
            JLabel empty = new JLabel(new ImageIcon("ressources/sprite/blank2.png"));

            spriteOnIt = empty;
            add(empty, constraints);
            add(empty);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    int row = pos[0];
                    int col = pos[1];
                    System.out.println(row);
                    System.out.println(col);
                    Tower[] team = Player.INSTANCE.getTeam();
                    if (tPresent.getSelectedPokemon() != null) {
                        if (!tPresent.getSelectedPokemon().isPlaced()) {
                            //
                            state.setTower(new int[] { row, col }, team[tPresent.getSelectedPokemon().getNumero() - 1]);
                            Tower pkm = team[tPresent.getSelectedPokemon().getNumero() - 1];
                            JLabel sprite = new JLabel(new ImageIcon(pkm.getSprite()));
                            addSprite(sprite, constraints);

                            if (state.getTowerPresent().contains(tPresent.getSelectedPokemon().getPokemon())) {
                                tPresent.getSelectedPokemon().setPlaced(true);
                                tPresent.getSelectedPokemon().setBorder(BorderFactory.createEmptyBorder());
                            }

                        }
                    }
                }
            });
        }

        public void addSprite(JLabel jlabel, GridBagConstraints constraints) {

            remove(spriteOnIt);
            spriteOnIt = jlabel;
            this.add(jlabel, constraints);
            jlabel.getParent().revalidate();
            state.getMap().affiche(state.getEnnemiPresent());
        }

        public int[] getPos() {
            return pos;
        }

        public JLabel getSpriteOnIt() {
            return spriteOnIt;
        }
    }

}
