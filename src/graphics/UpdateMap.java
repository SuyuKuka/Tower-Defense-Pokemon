package graphics;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.*;

import model.Ennemi;

import model.MapState;
import model.Player;
import model.Tower;
import javax.swing.border.*;

public class UpdateMap extends JPanel {
    private MapState state;
    private TowerPresent tPresent;

    GraphicsMaker maker = new GraphicsMaker();
    CardLayout card = new CardLayout();
    JPanel gameMap;

    public UpdateMap(MapState state, TowerPresent tPresent) {
        setLayout(new BorderLayout());

        setOpaque(false);
        gameMap = new JPanel();

        this.state = state;
        this.tPresent = tPresent;

        int[][] map = state.getMap().getConfig();
        int mapWidth = map[0].length;
        int mapHeight = map.length;

        GridBagLayout gridConfiguration = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        gameMap.setLayout(gridConfiguration);
        gameMap.setOpaque(false);

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                Tiles tiles;
                if (map[i][j] == 3) {
                    tiles = new Tiles(new int[] { i, j });

                    JLabel bg = maker.resizeJLabel("ressources/tiles/pathtiles.png", 60);
                    GridBagConstraints constraints2 = new GridBagConstraints();
                    tiles.addSprite(bg, constraints2);
                    tiles.setOpaque(false);

                } else if (map[i][j] == 4) {
                    tiles = new Tiles(new int[] { i, j });
                    tiles.setBackground(Color.CYAN);

                } else if (map[i][j] == 5) {
                    tiles = new Tiles(new int[] { i, j });
                    tiles.setBackground(Color.PINK);

                } else if (map[i][j] == 9) {
                    tiles = new Tiles(new int[] { i, j });
                    JLabel bg = maker.resizeJLabel("ressources/tiles/obstacle.png", 32);
                    GridBagConstraints constraints2 = new GridBagConstraints();
                    tiles.addSprite(bg, constraints2);

                    tiles.setOpaque(false);
                } else {
                    tiles = new Tiles(new int[] { i, j });
                    tiles.setOpaque(false);
                }

                constraints.gridx = j;
                constraints.gridy = i;
                constraints.weightx = 1;
                constraints.weighty = 1;
                constraints.fill = GridBagConstraints.BOTH;

                gameMap.add(tiles, constraints);
                // gameMap.add(new JLabel(new ImageIcon("ressources/sprite/magikarp.gif")),
                // constraints);
            }
        }
        add(new JLabel(new ImageIcon("ressources/sprite/rondoudou.gif")), BorderLayout.CENTER);
        add(gameMap, BorderLayout.CENTER);
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

    /*
     * 
     * public void paintComponent(Graphics g) {
     * super.paintComponent(g);
     * moveSprite();
     * 
     * }
     * 
     * private void moveSprite() {
     * 
     * for (Ennemi e : state.getEnnemiPresent()) {
     * e.moveGraphic(e.getJLabelSprite());
     * }
     * }
     */

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
        JLabel empty = new JLabel(new ImageIcon("ressources/sprite/blank2.png"));

        public Tiles(int[] pos) {
            this.pos = pos;
            tilesList.add(this);
            setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            setBorder(new EmptyBorder(1, 1, 1, 1));

            spriteOnIt = empty;
            add(empty, constraints);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent evt) {
                    if (spriteOnIt == empty) {
                        setBorder(new LineBorder(Color.RED, 1));
                    }
                }

                @Override
                public void mouseExited(MouseEvent evt) {
                    setBorder(new EmptyBorder(1, 1, 1, 1));
                }

                @Override
                public void mouseClicked(MouseEvent evt) {
                    int row = pos[0];
                    int col = pos[1];

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

            if (spriteOnIt == empty) {
                remove(spriteOnIt);
                spriteOnIt = jlabel;
                this.add(jlabel, constraints);
                jlabel.getParent().revalidate();
            }
        }

        public int[] getPos() {
            return pos;
        }

        public JLabel getSpriteOnIt() {
            return spriteOnIt;
        }
    }

}
