package graphics;

import main.Game;

import javax.imageio.ImageIO;
import javax.swing.*;

import control.ButtonManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MainMenu extends JPanel {

    private ButtonManager manager = new ButtonManager();
    private GraphicsMaker maker = new GraphicsMaker();

    public MainMenu(Game main) {

        setLayout(new BorderLayout());
        setOpaque(false);

        // Titre
        JPanel titlePanel = titlePanel();

        // Boutons
        JPanel buttonPanel = buttonPanel(main);
        buttonPanel.setAlignmentY(BOTTOM_ALIGNMENT);

        // Profil
        JPanel profilPanel = profilPanel();
        JPanel bot = new JPanel();

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.EAST);
        add(profilPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel titlePanel() {
        JPanel titlePanel = new JPanel();
        JLabel heading = new JLabel("POKEREVOLUTE");
        heading.setFont(maker.getCustomFont());
        heading.setForeground(Color.WHITE);

        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBackground(Color.BLACK);

        return titlePanel;
    }

    private JPanel profilPanel() {

        loadBgImage("ressources/background/background0.jpg");
        JPanel profilPanel = addBg("ressources/background/background0.jpg");

        profilPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setOpaque(false);

        JPanel cadrePanel = new JPanel();
        cadrePanel.setBackground(Color.BLACK);
        cadrePanel.setLayout(new BorderLayout());
        cadrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));
        cadrePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel profilPhoto = new JLabel(new ImageIcon("ressources/profile/profil_photo.png"));
        profilPhoto.setPreferredSize(new Dimension(50, 50));

        JLabel pseudo = new JLabel("  Joueur1 ");
        pseudo.setForeground(Color.WHITE);
        pseudo.setFont(new Font(pseudo.getFont().getName(), Font.BOLD, 16));

        JLabel levelAccount = new JLabel("  Nv du compte : 1 ");
        levelAccount.setForeground(Color.WHITE);
        levelAccount.setFont(new Font(levelAccount.getFont().getName(), Font.BOLD, 16));

        cadrePanel.add(profilPhoto, BorderLayout.WEST);
        infoPanel.add(pseudo, BorderLayout.NORTH);
        infoPanel.add(levelAccount, BorderLayout.CENTER);

        cadrePanel.add(infoPanel, BorderLayout.CENTER);
        profilPanel.add(cadrePanel);

        return profilPanel;
    }

    private BufferedImage loadBgImage(String path) {
        BufferedImage backgroundImage = null;
        if (backgroundImage == null) {
            File file = new File(path);
            try {
                backgroundImage = ImageIO.read(file);
                if (backgroundImage == null) {
                    backgroundImage = ImageIO.read(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return backgroundImage;
    }

    private JPanel addBg(String path) {
        BufferedImage backgroundImage = loadBgImage(path);
        JPanel IPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        return IPanel;
    }

    private JPanel buttonPanel(Game main) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JButton classiqueButton = new JButton(new ImageIcon("ressources/button/classique_button.png"));
        JButton marathonButton = new JButton(new ImageIcon("ressources/button/marathon_button.png"));
        JButton boutiqueButton = new JButton(new ImageIcon("ressources/button/boutique_button.png"));
        JButton optionsButton = new JButton(new ImageIcon("ressources/button/options_button.png"));

        manager.buttonPressed(classiqueButton, "classique", main);
        manager.buttonPressed(marathonButton, "marathon", main);
        manager.buttonPressed(boutiqueButton, "boutique", main);
        manager.buttonPressed(optionsButton, "options", main);

        // Centrer les boutons verticalement
        classiqueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        marathonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boutiqueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ajouter des boutons avec des rigid areas (espace fixe) entre eux
        buttonPanel.add(classiqueButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(marathonButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(boutiqueButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(optionsButton);

        return buttonPanel;

    }

}
