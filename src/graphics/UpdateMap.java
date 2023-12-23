package graphics;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.*;

import model.Ennemi;
import model.MapState;

public class UpdateMap extends JPanel {
    private MapState state;
    private TowerPresent tPresent;
    private int cellSize = 30;

    public UpdateMap(MapState state, TowerPresent tPresent) {
        setBackground(new Color(111, 160, 83));
        this.state = state;
        this.tPresent = tPresent;
        setLayout(new BorderLayout());

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = evt.getY() / cellSize;
                int col = evt.getX() / cellSize;
                if (tPresent.getSelectedPokemon() != null) {
                    if (row >= 0 && row < state.getMap().getConfig().length && col >= 0
                            && col < state.getMap().getConfig()[0].length) {
                        state.setTower(new int[] { row, col }, tPresent.getSelectedPokemon().getPokemon());
                        repaint();
                    }
                }
            }
        });
    }

    private void drawEnnemi(Graphics g, int i, int j, int scale) {
        for (Ennemi e : state.getEnnemiPresent()) {
            if (i == e.getPos()[0] && j == e.getPos()[1]) {
                g.setColor(getEnnemiColor(e));
                g.fillRect(j * scale, i * scale, scale, scale);
            }
        }
    }

    public void paintComponent(Graphics g) {
        int scale = cellSize;
        super.paintComponent(g);
        int[][] map = state.getMap().getConfig();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                drawCell(g, i, j, map[i][j], scale);
                drawEnnemi(g, i, j, scale);
            }
        }
    }

    private void drawCell(Graphics g, int i, int j, int cellValue, int scale) {
        Color color = getCellColor(cellValue);
        if (color == Color.BLUE) {
            if (tPresent.getSelectedPokemon() != null) {
                try {
                    BufferedImage image = ImageIO.read(new File(tPresent.getSelectedPokemon().getSprite()));
                    g.drawImage(image, j * scale, i * scale, scale, scale, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            g.setColor(color);
            g.fillRect(j * scale, i * scale, scale, scale);
        }
    }  

    private Color getCellColor(int cellValue) {
        switch (cellValue) {
            case 2:
                return Color.BLUE;
            case 3:
            case 4:
            case 5:
                return new Color(165, 125, 49);
            case 9:
                return new Color(138, 216, 93);
            default:
                return new Color(111, 160, 83);
        }
    }

    private Color getEnnemiColor(Ennemi e) {
        if (e.isBoss()) {
            return new Color(225, 225, 225); // Couleur boss
        } else {
            return new Color(225, 0, 0); // Couleur normal
        }
    }

}
