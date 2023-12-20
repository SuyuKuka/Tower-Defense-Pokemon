package graphics;

import java.util.*;

import java.awt.*;
import javax.swing.*;

import control.ButtonManager;
import control.TowerManagement;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import model.Ennemi;
import model.MapState;
import main.Game;

public class GameScreen extends JPanel {

    private ButtonManager manager = new ButtonManager();
    private GraphicsMaker maker = new GraphicsMaker();

    private int[][] map;
    private MapState state = new MapState();
    private ArrayList<Ennemi> ennemiPresent;

    // Création des 3 catégories de panels
    private JPanel ennemiPanel;
    private JPanel mapPanel = new JPanel();
    private JPanel towerPanel;

    public GameScreen(Game main) {
        // setLayout(new BorderLayout());
        // this.ennemiPresent = state.getEnnemiPresent();

        // towerPanel = new JPanel() {
        //     @Override
        //     protected void paintComponent(Graphics g) {
        //         super.paintComponent(g);
        //         try {
        //             g.drawImage(ImageIO.read(new File("ressources/template/team1.png")), 0, 0, 800, 800, this);
        //         } catch (IOException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // };

        // // Initialisation des panels
        // ennemiPanel = new JPanel() {
        //     @Override
        //     protected void paintComponent(Graphics g) {
        //         super.paintComponent(g);
        //         try {
        //             g.drawImage(ImageIO.read(new File("ressources/template/ennemi.png")), 0, 0, 600, 720, this);
        //         } catch (IOException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // };
        // ennemiPanel.setLayout(new BoxLayout(ennemiPanel, BoxLayout.Y_AXIS));
        // ennemiPanel.setPreferredSize(new Dimension(254, 0));
        // mapPanel.setPreferredSize(new Dimension(630, 0));
        // towerPanel.setOpaque(false);
        // towerPanel.setPreferredSize(new Dimension(340, 0));
        // towerPanel.setLayout(new BorderLayout());

        // // Ennemi
        // JPanel container = new JPanel() {
        //     @Override
        //     protected void paintComponent(Graphics g) {
        //         super.paintComponent(g);
        //         try {
        //             g.drawImage(ImageIO.read(new File("ressources/template/ennemi.png")), 0, 0, 600, 1000, this);

        //         } catch (IOException e) {
        //             e.printStackTrace();
        //         }

        //     }
        // };

        // container.setOpaque(false);

        // BoxLayout boxLayout = new BoxLayout(container, BoxLayout.Y_AXIS);
        // container.setLayout(boxLayout);

        // JLabel ennemiLabel = new JLabel("Ennemi sur le terrain");
        // ennemiLabel.setOpaque(false);
        // ennemiLabel.setForeground(Color.WHITE);
        // ennemiLabel.setFont(new Font(Font.SERIF, Font.BOLD, 22));
        // ennemiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // ennemiPanel.add(ennemiLabel);

        // JScrollPane ennemiList = new JScrollPane(container);
        // ennemiList.setOpaque(false);
        // ennemiList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // ennemiList.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // TROP PUISSANT
        // ennemiPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // TROP PUISSANT
        // ennemiPanel.add(ennemiList);
        // // Map
        // mapPanel = initiateMapPanel(mapPanel);

        // // Tour

        // JLabel team = new JLabel("Ton équipe");
        // team.setForeground(Color.WHITE);
        // team.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        // team.setAlignmentX(Component.CENTER_ALIGNMENT);

        // // Rectangle pokemon1 = new Rectangle(1);
        // // Rectangle pokemon2 = new Rectangle(2);
        // // Rectangle pokemon3 = new Rectangle(3);
        // // Rectangle pokemon4 = new Rectangle(4);
        // // Rectangle pokemon5 = new Rectangle(5);
        // // Rectangle pokemon6 = new Rectangle(6);
        // // Rectangle hero1 = new Rectangle(7);
        // // Rectangle hero2 = new Rectangle(8);

        // JPanel towerTeamPanel = new JPanel();
        // towerTeamPanel.setOpaque(false);

        // FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 10, 10);
        // JPanel row1 = new JPanel(flowLayout);
        // row1.setOpaque(false);
        // row1.add(pokemon1);
        // row1.add(pokemon2);

        // JPanel row2 = new JPanel(flowLayout);
        // row2.setOpaque(false);
        // row2.add(pokemon3);
        // row2.add(pokemon4);

        // JPanel row3 = new JPanel(flowLayout);
        // row3.setOpaque(false);
        // row3.add(pokemon5);
        // row3.add(pokemon6);

        // JPanel row4 = new JPanel(flowLayout);
        // row4.setOpaque(false);
        // row4.add(hero1);
        // row4.add(hero2);

        // for (Component rectangle : Arrays.asList(row1, row2, row3, row4)) {
        //     for (Component component : ((Container) rectangle).getComponents()) {
        //         component.setPreferredSize(new Dimension(156, 62));
        //     }
        // }

        // JButton pcButton = new JButton(new ImageIcon("ressources/button/pc_button.png"));
        // manager.buttonPressed(pcButton, "pc", main);

        // towerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // TROP PUISSANT
        // towerTeamPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0)); // TROP PUISSANT

        // towerPanel.add(team, BorderLayout.NORTH);
        // towerTeamPanel.add(row1);
        // towerTeamPanel.add(row2);
        // towerTeamPanel.add(row3);
        // towerTeamPanel.add(row4);
        // towerPanel.add(pcButton, BorderLayout.SOUTH);
        // towerPanel.add(towerTeamPanel);

        // // mapPanel.setBorder(BorderFactory.createLineBorder(new Color(165,42,42), 5));
        // mapPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 15, Color.BLACK));
        // add(ennemiPanel, BorderLayout.WEST);
        // add(mapPanel, BorderLayout.CENTER);
        // add(towerPanel, BorderLayout.EAST);
    }

    public JPanel initiateMapPanel(JPanel mapPanel) {
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

    public MapState getState() {
        return state;
    }

    public void setEnnemiPresent(ArrayList<Ennemi> ennemiPresent) {
        for (Ennemi e : ennemiPresent) {
            createEnnemiLabel(e, ennemiPanel);
            System.out.println(ennemiPresent.size());
        }
    }

    public void createEnnemiLabel(Ennemi e, JPanel container) {
        // 1. Create a new JPanel to hold all the elements.
        JPanel ennemiInfo = new JPanel(new GridBagLayout());
        ennemiInfo.setOpaque(false);
        // ennemiInfo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        // 2. Create a JLabel for the profile photo and set its size.
        JLabel profilPhoto = new JLabel(new ImageIcon("ressources/profile/profil_photo.png"));
        profilPhoto.setOpaque(false);
        profilPhoto.setPreferredSize(new Dimension(50, 50));

        // 3. Create a JLabel for the name and set its properties (font, color, etc.).
        JLabel nameLabel = new JLabel(e.getName());
        nameLabel.setOpaque(false);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);

        // 5. Create a JProgressBar for the health bar and set its properties.
        JProgressBar healthBar = new JProgressBar();
        healthBar.setOpaque(false);
        healthBar.setMinimum(0);
        healthBar.setMaximum((int) e.getTotalPv());
        healthBar.setValue((int) e.getPv());
        healthBar.setForeground(Color.RED);
        healthBar.setStringPainted(true);

        // 6. Add all the components to the new JPanel in the desired layout.
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.ipadx = 10; // Add horizontal padding to the profile photo.
        ennemiInfo.add(profilPhoto, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST; // This will align the name to the left.
        gbc.insets = new Insets(0, 8, 0, 0); // Add space around the name label.
        ennemiInfo.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        ennemiInfo.add(healthBar, gbc);

        // 7. Add the JPanel to the container.
        container.add(ennemiInfo);
    }
}