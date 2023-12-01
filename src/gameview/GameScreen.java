package gameview;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Ennemi;
import model.MapState;

public class GameScreen extends JPanel {

    private MapState state;

    public GameScreen(MapState state) {
        this.state = state;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] map = state.getMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                g.fillRect(j * 45, i * 45, 45, 45);
                switch (map[i][j]) {
                    case 2:
                        g.setColor(Color.BLUE);
                        break;
                    case 3:
                        g.setColor(new Color(165, 125, 49));
                        break;
                    case 4:
                        g.setColor(new Color(165, 125, 49));
                        break;
                    case 5:
                        g.setColor(new Color(165, 125, 49));
                        break;
                    case 9:
                        g.setColor(new Color(138, 216, 93));
                        break;
                    default:
                        g.setColor(new Color(111, 160, 83));
                        break;
                }
                for (Ennemi e : state.getEnnemiPresent()) {
                    if (i == e.getPos()[0] && j == e.getPos()[1]) {
                        // plateau[i][j] = (char) 4;
                        if (e.isBoss()) {
                            g.setColor(new Color(225, 0, 0));
                        } else {
                            g.setColor(new Color(225, 0, 0));
                        }
                    }
                }
                g.fillRect(0, 0, 45, 45);

            }
        }
    }

}
