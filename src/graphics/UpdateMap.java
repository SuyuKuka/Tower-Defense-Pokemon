package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Ennemi;
import model.MapState;
import model.Player;
import model.Tower;
import control.Control;

import java.util.ArrayList;

public  class UpdateMap extends JLayeredPane {
    private MapState state;
    private TowerPresent tPresent;
    private GraphicsMaker maker = new GraphicsMaker();
    private Control control = Control.INSTANCE;
    private UpdateMap uMap = this;

    private JPanel gameMap;

    private int cellSize = 32;
    private int mapWidth;
    private int mapHeight;

    public UpdateMap(MapState state, TowerPresent tPresent) {


        this.state = state;
        this.tPresent = tPresent;
        int[][] map = state.getMap().getConfig();

        setBounds(0, 0, 764, 531);
        setVisible(true);

        GridBagConstraints constraints = new GridBagConstraints();
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
                    tiles = new Tiles(new int[] {i, j});
                    drawPathTiles(tiles, cellSize);

                } else if (map[i][j] == 4) {
                    tiles = new Tiles(new int[] {i, j});
                    tiles.setBackground(Color.CYAN);

                } else if (map[i][j] == 5) {
                    tiles = new Tiles(new int[] {i, j});
                    tiles.setBackground(Color.PINK);

                } else if (map[i][j] == 9) {
                    JLabel bg = maker.resizeJLabel("ressources/tiles/" + Options.selectedMap +"/obstacle.png", cellSize);
                    GridBagConstraints constraints2 = new GridBagConstraints();

                    tiles = new Tiles(new int[] {i, j});
                    tiles.setOccupied(true);
                    tiles.addSpriteOnTiles(bg, constraints2);
                    tiles.setOpaque(false);

                } else {
                    tiles = new Tiles(new int[] {i, j});
                    tiles.setOpaque(false);
                }

                tiles.settPresent(tPresent);
                tiles.setState(state);
                tiles.setuMap(this);

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

        add(mapLayer, DEFAULT_LAYER);
    }

    public void setSpriteEntry(Ennemi e) {
        JLabel sprite = new JLabel(new ImageIcon(e.getSprite()));
        e.setJLabelSprite(sprite);
        sprite.setBounds(0, 0, cellSize, cellSize);
        sprite.setLocation(new Point(state.getMap().getEntry()[1] * cellSize, state.getMap().getEntry()[0] * cellSize));
        sprite.setVisible(false);
        add(sprite, PALETTE_LAYER);
    }

    public Tiles getTiles(int[] pos) {
        for (Tiles tiles : Tiles.getTilesList()) {
            if (tiles.getPos()[0] == pos[0] && tiles.getPos()[1] == pos[1]) {
                return tiles;
            }
        }
        return null;
    }

    private void drawPathTiles(Tiles tiles, int size) {
        model.Map.DirectionTiles[][] tilesPathMap = state.getMap().getDirMapWithCorner();
        JLabel bg;
        switch (tilesPathMap[tiles.getPos()[0]][tiles.getPos()[1]].getType()) {
            case X:
                bg = maker.resizeJLabel("ressources/tiles/" + Options.selectedMap + "/xTiles.png", size);
                break;
            case H:
                bg = maker.resizeJLabel("ressources/tiles/" + Options.selectedMap + "/hTiles.png", size);
                break;
            case V:
                bg = maker.resizeJLabel("ressources/tiles/" + Options.selectedMap + "/vTiles.png", size);
                break;
            case A:
                bg = maker.resizeJLabel("ressources/tiles/" + Options.selectedMap + "/aTiles.png", size);
                break;
            case Z:
                bg = maker.resizeJLabel("ressources/tiles/" + Options.selectedMap + "/zTiles.png", size);
                break;
            case Q:
                bg = maker.resizeJLabel("ressources/tiles/" + Options.selectedMap + "/qTiles.png", size);
                break;
            case S:
                bg = maker.resizeJLabel("ressources/tiles/" + Options.selectedMap + "/sTiles.png", size);
                break;
            default:
                bg = maker.resizeJLabel("ressources/tiles/" + Options.selectedMap + "/xTiles.png", size);
                break;
        }

        GridBagConstraints constraints2 = new GridBagConstraints();
        tiles.addSpriteOnTiles(bg, constraints2);
        tiles.setOpaque(false);
    }

    public int getCellSize() {
        return cellSize;
    }


    public JPanel getGameMap() {
        return gameMap;
    }

    public TowerPresent gettPresent() {
        return tPresent;
    }

    public JPanel makeTowerRange(Tower pkm, int row, int col) {
        JPanel towerRange = new JPanel() {
            public void paintComponent(Graphics g) {
                int rayon = pkm.getDiametreValue(pkm.getRayon()) * cellSize;
                int x = (col * cellSize) - rayon / 2 + 16;
                int y = (row * cellSize) - rayon / 2 + 16;
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(20, 20, 20, 180));
                g2.drawOval(x, y, rayon, rayon);
                g2.setColor(new Color(20, 20, 20, 100));
                g2.fillOval(x, y, rayon, rayon);
            }
        };
        towerRange.setOpaque(false);
        towerRange.setBounds(0, 0, mapWidth * cellSize, mapHeight * cellSize);

        return towerRange;
    }

}
