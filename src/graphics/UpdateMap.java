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

public class UpdateMap extends JLayeredPane {
    private MapState state;
    private TowerPresent tPresent;

    private GraphicsMaker maker = new GraphicsMaker();

    private JPanel gameMap;

    private int cellSize = 32;

    public UpdateMap(MapState state, TowerPresent tPresent) {
        JPanel mapLayer = new JPanel();
        mapLayer.setLayout(new BorderLayout());
        mapLayer.setOpaque(false);
        setBounds(0, 0, 764, 531);
        gameMap = new JPanel();

        GridBagLayout gridConfiguration = new GridBagLayout();
        gameMap.setLayout(gridConfiguration);
        gameMap.setOpaque(false);
        GridBagConstraints constraints = new GridBagConstraints();

        this.state = state;
        this.tPresent = tPresent;

        int[][] map = state.getMap().getConfig();
        int mapWidth = map[0].length;
        int mapHeight = map.length;

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
                    JLabel bg = maker.resizeJLabel("ressources/tiles/obstacle.png", cellSize);
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

                mapLayer.setBounds(0, 0, mapWidth * cellSize, mapHeight * cellSize);
            }
        }

        mapLayer.add(gameMap, BorderLayout.CENTER);
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
                bg = maker.resizeJLabel("ressources/tiles/xTiles.png", size);
                break;
            case H:
                bg = maker.resizeJLabel("ressources/tiles/hTiles.png", size);
                break;
            case V:
                bg = maker.resizeJLabel("ressources/tiles/vTiles.png", size);
                break;
            case A:
                bg = maker.resizeJLabel("ressources/tiles/aTiles.png", size);
                break;
            case Z:
                bg = maker.resizeJLabel("ressources/tiles/zTiles.png", size);
                break;
            case Q:
                bg = maker.resizeJLabel("ressources/tiles/qTiles.png", size);
                break;
            case S:
                bg = maker.resizeJLabel("ressources/tiles/sTiles.png", size);
                break;
            default:
                bg = maker.resizeJLabel("ressources/tiles/xTiles.png", size);
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
        JLabel empty = new JLabel(new ImageIcon("ressources/sprite/nothing.png"));
        GridBagConstraints constraints = new GridBagConstraints();

        public Tiles(int[] pos) {
            this.pos = pos;
            tilesList.add(this);
            setLayout(new GridBagLayout());
            setMinimumSize(new Dimension(cellSize, cellSize));
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
                    Tower[] team = Player.INSTANCE.getTeam();

                    int row = pos[0];
                    int col = pos[1];

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
