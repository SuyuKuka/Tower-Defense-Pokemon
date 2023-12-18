package graphics;

import java.util.*;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import model.Ennemi;
import model.MapState;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameScreen extends JPanel {
    private int[][] map;
    private MapState state = new MapState();

    private JPanel mapPanel = new JPanel();
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int cellsize = 40;

    public GameScreen() {
        setLayout(new BorderLayout());
        this.map = state.getMap().getMap();
        initiateMapPanel();

        JLabel ennemiLabel = new JLabel("Ennemie sur le terrain");
        ennemiLabel.setForeground(Color.WHITE);
        ennemiLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));

        JLabel test = new JLabel("test");
        test.setForeground(Color.WHITE);

        test.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(mapPanel, BorderLayout.CENTER);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ajustement de la taille du JPanel lorsque la fenêtre est redimensionnée
                cellsize = cellsize++;
                System.out.println(screenHeight);
                System.out.println(screenWidth);

            }
        });

    }

    public void initiateMapPanel() {

        this.mapPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {
                        g.fillRect(j * cellsize + screenWidth / 2 - map.length * cellsize / 2, i * cellsize, cellsize,
                                cellsize);
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

                    }
                }
            }
        };
    }

    public MapState getState() {
        return state;
    }

    public void setEnnemiPresent(ArrayList<Ennemi> ennemiPresent) {
        for (Ennemi e : ennemiPresent) {
            JLabel ennemiName = new JLabel(e.getName());

        }
    }

}