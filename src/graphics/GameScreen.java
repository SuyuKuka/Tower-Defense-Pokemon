package graphics;

import java.util.*;

import java.awt.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import model.Ennemi;
import model.MapState;
import main.Game;
import geometry.Rectangle;

public class GameScreen extends JPanel {
    private int[][] map;
    private MapState state = new MapState();
    private ArrayList<Ennemi> ennemiPresent;

    //Création des 3 catégories de panels
    private JPanel ennemiPanel = new JPanel();
    private JPanel mapPanel = new JPanel();
    private JPanel towerPanel;

    public GameScreen(Game main) {
        setLayout(new BorderLayout());
        this.ennemiPresent = state.getEnnemiPresent();
        this.map = state.getMap().getMap();

        towerPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("ressources/team_background/team1.png")), 0, 0,  600, 720, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        
        //Initialisation des panels
        ennemiPanel.setBackground(Color.BLACK);
        ennemiPanel.setLayout(new BoxLayout(ennemiPanel, BoxLayout.Y_AXIS));
        ennemiPanel.setPreferredSize(new Dimension(254, 0));
        mapPanel.setPreferredSize(new Dimension(630, 0));
        towerPanel.setOpaque(false);
        towerPanel.setPreferredSize(new Dimension(340, 0));
        towerPanel.setLayout(new BorderLayout());

        //Ennemi
        JLabel ennemiLabel = new JLabel("Ennemie sur le terrain");
        ennemiLabel.setForeground(Color.WHITE);
        ennemiLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        ennemiPanel.add(ennemiLabel);
        JLabel test = new JLabel("Carapuce");
        test.setForeground(Color.WHITE);
        ennemiPanel.add(test);
        ennemiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        test.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Map
        mapPanel = initiateMapPanel(mapPanel);

        //Tour
        JButton pcButton = new JButton(new ImageIcon("ressources/button/pc_button.png"));
        main.buttonPressed(pcButton, "pc");

        JPanel towerTeamPanel = new JPanel();
        //towerTeamPanel.setLayout(new BoxLayout(towerTeamPanel, BoxLayout.Y_AXIS));
        towerTeamPanel.setOpaque(false);

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        row1.setOpaque(false);
        row1.add(new Rectangle(1));
        row1.add(new Rectangle(2));

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        row2.setOpaque(false);
        row2.add(new Rectangle(3));
        row2.add(new Rectangle(4));

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        row3.setOpaque(false);
        row3.add(new Rectangle(5));
        row3.add(new Rectangle(6));

        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        row4.setOpaque(false);
        row4.add(new Rectangle(7));
        row4.add(new Rectangle(8));

        for (Component rectangle : Arrays.asList(row1, row2, row3, row4)) {
            for (Component component : ((Container) rectangle).getComponents()) {
                component.setPreferredSize(new Dimension(156, 62));
            }
        }

        towerPanel.add(pcButton, BorderLayout.NORTH);
        towerTeamPanel.add(row1);
        towerTeamPanel.add(row2);
        towerTeamPanel.add(row3);
        towerTeamPanel.add(row4);

        towerPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0)); //TROP PUISSANT
        towerTeamPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); //TROP PUISSANT
        towerPanel.add(towerTeamPanel); 
        

        add(ennemiPanel, BorderLayout.WEST);
        add(mapPanel, BorderLayout.CENTER);
        add(towerPanel, BorderLayout.EAST);
    }

    public JPanel initiateMapPanel(JPanel mapPanel){
        mapPanel = new JPanel() { 
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

        return mapPanel;
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