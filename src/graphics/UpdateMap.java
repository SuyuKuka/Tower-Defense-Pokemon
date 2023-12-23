package graphics;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import model.Ennemi;
import model.MapState;

public class UpdateMap extends JPanel {
    private MapState state;
    private TowerPresent tPresent;
    private int cellSize = 45;

    public UpdateMap(MapState state, TowerPresent tPresent) {
        this.state = state;
        this.tPresent = tPresent;
        setLayout(new BorderLayout());

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                System.out.println("clicked");
                int row = evt.getY() / cellSize; // Ajuste le décalage
                int col = evt.getX() / cellSize; // Ajuste le décalage
                System.out.println(row);
                System.out.println(col);
                int rawX = evt.getX();
                int rawY = evt.getY();
                System.out.println("Raw X: " + rawX + ", Raw Y: " + rawY);

                if (tPresent.getSelectedPokemon() != null) {
                    // Assure-toi que les coordonnées sont valides
                    if (row >= 0 && row < state.getMap().getConfig().length && col >= 0
                            && col < state.getMap().getConfig()[0].length) {
                        state.setTower(new int[] { row, col }, tPresent.getSelectedPokemon().getPokemon());
                        // Actualise l'affichage
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
        g.setColor(getCellColor(cellValue));
        g.fillRect(j * scale, i * scale, scale, scale);
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
