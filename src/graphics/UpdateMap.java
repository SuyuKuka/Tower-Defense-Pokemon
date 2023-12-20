package graphics;

import java.awt.*;
import javax.swing.*;

import model.Ennemi;
import model.MapState;

public class UpdateMap extends JPanel {

    private MapState state;

    public UpdateMap(MapState state) {
        this.state = state;
        setLayout(new BorderLayout());
    }

    private void drawEnnemi(Graphics g, int i, int j) {
        for (Ennemi e : state.getEnnemiPresent()) {
            if (i == e.getPos()[0] && j == e.getPos()[1]) {
                g.setColor(getEnnemiColor(e));
                g.fillRect(j * 30, i * 30, 45, 45);
            }
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] map = state.getMap().getConfig();
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                drawCell(g, i, j, map[i][j]);
                drawEnnemi(g, i, j);
            }
        }
    }

    private void drawCell(Graphics g, int i, int j, int cellValue) {
        g.setColor(getCellColor(cellValue));
        g.fillRect(j * 30, i * 30, 45, 45);
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
            return new Color(225, 225, 225); //Couleur boss
        } else {
            return new Color(225, 0, 0); //Couleur normal
        }
    }
    
}
