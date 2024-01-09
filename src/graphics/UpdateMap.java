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

import control.Control;

public class UpdateMap extends JLayeredPane {
    private MapState state;
    private TowerPresent tPresent;

    private GraphicsMaker maker = new GraphicsMaker();
    private Control control = Control.INSTANCE;
    private JPanel gameMap;
    private UpdateMap uMap = this;

    private int cellSize = 32;
    private int mapWidth;
    private int mapHeight;

    public UpdateMap(MapState state, TowerPresent tPresent) {
        this.state = state;
        this.tPresent = tPresent;

        GridBagConstraints constraints = new GridBagConstraints();
        int[][] map = state.getMap().getConfig();
        mapWidth = map[0].length;
        mapHeight = map.length;

        GridBagLayout gridConfiguration = new GridBagLayout();
        gameMap = new JPanel();
        gameMap.setLayout(gridConfiguration);
        gameMap.setOpaque(false);

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                Tiles tiles;
                if (map[i][j] == 3) {

                    tiles = new Tiles(new int[] { i, j });
                    drawPathTiles(tiles, cellSize);

                } else if (map[i][j] == 4) {
                    tiles = new Tiles(new int[] { i, j });
                    tiles.setBackground(Color.CYAN);

                } else if (map[i][j] == 5) {
                    tiles = new Tiles(new int[] { i, j });
                    tiles.setBackground(Color.PINK);

                } else if (map[i][j] == 9) {
                    tiles = new Tiles(new int[] { i, j });
                    JLabel bg = maker.resizeJLabel("ressources/tiles/automne/obstacle.png", cellSize);
                    GridBagConstraints constraints2 = new GridBagConstraints();
                    tiles.addSprite(bg, constraints2);
                    tiles.setOpaque(false);

                } else {
                    tiles = new Tiles(new int[] { i, j });
                    tiles.setOpaque(false);
                }

                constraints.gridx = j;
                constraints.gridy = i;
                constraints.anchor = GridBagConstraints.FIRST_LINE_START;

                gameMap.add(tiles, constraints);

            }
        }

        JPanel mapLayer = new JPanel();
        mapLayer.setBounds(0, 0, mapWidth * cellSize, mapHeight * cellSize);
        mapLayer.setLayout(new BorderLayout());
        mapLayer.setOpaque(false);
        mapLayer.add(gameMap, BorderLayout.CENTER);

        setBounds(0, 0, 764, 531);
        setVisible(true);
        add(mapLayer, DEFAULT_LAYER);

    }

    public void setSpriteEntry(Ennemi e) {

        JLabel sprite = new JLabel(new ImageIcon(e.getSprite()));
        e.setJLabelSprite(sprite);
        sprite.setBounds(0, 0, cellSize, cellSize);
        sprite.setLocation(
                new Point(state.getMap().getEntry()[1] * cellSize, state.getMap().getEntry()[0] * cellSize));
        sprite.setVisible(false);
        add(sprite, PALETTE_LAYER);

    }

    public Tiles getTiles(int[] pos) {
        for (Tiles tiles : Tiles.tilesList) {
            if (tiles.getPos()[0] == pos[0] && tiles.getPos()[1] == pos[1]) {
                return tiles;
            }
        }
        return null;
    }

    private void drawPathTiles(Tiles tiles, int size) {
        JLabel bg;
        model.Map.DirectionTiles[][] tilesPathMap = state.getMap().getDirMapWithCorner();

        switch (tilesPathMap[tiles.getPos()[0]][tiles.getPos()[1]].getType()) {
            case X:
                bg = maker.resizeJLabel("ressources/tiles/automne/xTiles.png", size);
                break;
            case H:
                bg = maker.resizeJLabel("ressources/tiles/automne/hTiles.png", size);
                break;
            case V:
                bg = maker.resizeJLabel("ressources/tiles/automne/vTiles.png", size);
                break;
            case A:
                bg = maker.resizeJLabel("ressources/tiles/automne/aTiles.png", size);
                break;
            case Z:
                bg = maker.resizeJLabel("ressources/tiles/automne/zTiles.png", size);
                break;
            case Q:
                bg = maker.resizeJLabel("ressources/tiles/automne/qTiles.png", size);
                break;
            case S:
                bg = maker.resizeJLabel("ressources/tiles/automne/sTiles.png", size);
                break;
            default:
                bg = maker.resizeJLabel("ressources/tiles/automne/xTiles.png", size);
                break;
        }

        GridBagConstraints constraints2 = new GridBagConstraints();
        tiles.addSprite(bg, constraints2);
        tiles.setOpaque(false);
    }

    public int getCellSize() {
        return cellSize;
    }

    public class Tiles extends JPanel {
        private int[] pos;
        public static ArrayList<Tiles> tilesList = new ArrayList<>();
        private JLabel spriteOnIt;
        private JLabel empty = new JLabel(new ImageIcon("ressources/sprite/nothing.png"));
        private GridBagConstraints constraints = new GridBagConstraints();

        private Tower pokemon;

        public Tiles(int[] pos) {

            this.pos = pos;
            tilesList.add(this);
            spriteOnIt = empty;

            setLayout(new GridBagLayout());
            setMinimumSize(new Dimension(cellSize, cellSize));
            setBorder(new EmptyBorder(1, 1, 1, 1));

            add(empty, constraints);

            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent evt) {
                    setBorder(new LineBorder(Color.RED, 1));
                }

                @Override
                public void mouseExited(MouseEvent evt) {
                    setBorder(new EmptyBorder(1, 1, 1, 1));
                }

                @Override
                public void mouseClicked(MouseEvent evt) {
                    Tower[] team = Player.INSTANCE.getTeam();

                    int row = pos[0];
                    int col = pos[1];

                    if (tPresent.getSelectedPokemon() != null) {
                        Tower pkm = team[tPresent.getSelectedPokemon().getNumero() - 1];
                        if (!tPresent.getSelectedPokemon().isPlaced() && pkm.getPrice() < state.getMoney()) {

                            control.setTowerOnMap(new int[] { row, col }, pkm, state);

                            JLabel sprite = new JLabel(new ImageIcon(pkm.getSprite()));
                            addSprite(sprite, constraints);
                            giveTiles(pkm);
                            tPresent.getSelectedPokemon().updateManager(pkm);

                            int rayon = pkm.getRayonValue(pkm.getRayon()) * cellSize;
                            int x = (col * cellSize) - rayon / 2 + 16;
                            int y = (row * cellSize) - rayon / 2 + 16;
                            JPanel towerRange = new JPanel() {
                                public void paint(Graphics gh) {
                                    super.paint(gh);
                                    gh.drawOval(x, y, rayon, rayon);
                                }
                            };
                            towerRange.setOpaque(false);
                            towerRange.setBounds(0, 0, mapWidth * cellSize, mapHeight * cellSize);
                            pkm.setRangePanel(towerRange);
                            towerRange.setVisible(false);
                            pkm.getGUI().setVisible(false);
                            uMap.add(towerRange, POPUP_LAYER);

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

        private void giveTiles(Tower pkm) {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) { // FRAGILE

                    if (SwingUtilities.isLeftMouseButton(evt)) {
                        if (tPresent.getComponentCount() < 2) {

                            tPresent.add(pkm.getGUI(), BorderLayout.SOUTH);
                            pkm.getGUI().setVisible(true);
                            tPresent.getComponent(0).setVisible(false);
                            pkm.getRangePanel().setVisible(true);
                            setBackground(Color.BLUE);
                            setOpaque(true);
                        }
                    }
                    if (SwingUtilities.isRightMouseButton(evt)) {
                        pkm.getGUI().setVisible(false);
                        tPresent.remove(pkm.getGUI());
                        tPresent.getComponent(0).setVisible(true);
                        pkm.getRangePanel().setVisible(false);
                        setOpaque(false);
                    }

                }
            });
            pkm.setTiles(this);
        }

        public void replaceSprite(String spritePath) {

            JLabel jlabel = new JLabel(new ImageIcon(spritePath));
            remove(spriteOnIt);
            spriteOnIt = jlabel;
            this.add(jlabel, new GridBagConstraints());
            jlabel.getParent().revalidate();
        }

        public int[] getPos() {
            return pos;
        }

        public JLabel getSpriteOnIt() {
            return spriteOnIt;
        }

    }

    public TowerPresent gettPresent() {
        return tPresent;
    }

}
