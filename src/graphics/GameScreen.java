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

public class GameScreen extends JPanel {
    private int[][] map;
    private MapState state = new MapState();
    private JPanel ennemiPanel = new JPanel();
    private JPanel mapPanel = new JPanel();
    private JPanel towerPanel;
    private ArrayList<Ennemi> ennemiPresent;

    public GameScreen() {
        setLayout(new BorderLayout());
        this.ennemiPresent = state.getEnnemiPresent();
        this.map = state.getMap().getMap();
        initiateMapPanel();

        towerPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("ressources/team_background/team.png")), 0, 0, getWidth() , getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        ennemiPanel.setBackground(Color.BLACK);
        ennemiPanel.setLayout(new BoxLayout(ennemiPanel, BoxLayout.Y_AXIS));
        ennemiPanel.setPreferredSize(new Dimension(254, 0));
        mapPanel.setPreferredSize(new Dimension(630, 0));
        towerPanel.setOpaque(false);
        towerPanel.setPreferredSize(new Dimension(340, 0));

        JLabel ennemiLabel = new JLabel("Ennemie sur le terrain");
        ennemiLabel.setForeground(Color.WHITE);
        ennemiLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        ennemiPanel.add(ennemiLabel);
        JLabel test = new JLabel("test");
        test.setForeground(Color.WHITE);
        ennemiPanel.add(test);
        ennemiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        test.setAlignmentX(Component.CENTER_ALIGNMENT);


        add(ennemiPanel, BorderLayout.WEST);
        add(mapPanel, BorderLayout.CENTER);
        add(towerPanel, BorderLayout.EAST);
    }

    public void initiateMapPanel(){
        this.mapPanel = new JPanel() {
           
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {
                        g.fillRect(j * 30, i * 30, 45, 45);
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
        };
    }

    public MapState getState(){
        return state;
    }

    public void setEnnemiPresent(ArrayList<Ennemi> ennemiPresent) {
        for(Ennemi e : ennemiPresent){
            JLabel ennemiName = new JLabel(e.getName());
            ennemiPanel.add(ennemiName);
        }
    }
}