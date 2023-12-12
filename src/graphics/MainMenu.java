package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MainMenu extends JPanel {

    private BufferedImage backgroundImage;

    public MainMenu() {
        setLayout(new BorderLayout());
        setOpaque(false);

        // Titre
        JLabel heading = new JLabel();
        heading.setFont(new Font("Arial", Font.BOLD, 86));
        heading.setForeground(Color.WHITE);

        ImageIcon icon = new ImageIcon("ressources/title.png");
        icon.setImage(icon.getImage().getScaledInstance(812, 180, Image.SCALE_SMOOTH));
        heading.setIcon(icon);

        // Utiliser GridBagLayout pour centrer le titre
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        titlePanel.add(heading, gbc);

        add(titlePanel, BorderLayout.NORTH);
        //Titre
        // JLabel heading = new JLabel("PokeRevolute", SwingConstants.CENTER);
        // heading.setFont(new Font("Arial", Font.BOLD, 86));
        // heading.setForeground(Color.WHITE);
        // add(heading, BorderLayout.NORTH);

        //Background
        Random rand = new Random();
        int background_random = rand.nextInt(3);
        File file = new File("ressources/background/background_image" + background_random + ".png");
        try {
            backgroundImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Boutons
        JButton classiqueButton = new JButton(new ImageIcon("ressources/button/classique_button.png"));
        JButton marathonButton = new JButton(new ImageIcon("ressources/button/marathon_button.png"));
        JButton boutiqueButton = new JButton(new ImageIcon("ressources/button/boutique_button.png"));
        JButton optionsButton = new JButton(new ImageIcon("ressources/button/options_button.png"));

        buttonPressed(classiqueButton, "ressources/button/classique_button");
        buttonPressed(marathonButton, "ressources/button/marathon_button");
        buttonPressed(boutiqueButton, "ressources/button/boutique_button");
        buttonPressed(optionsButton, "ressources/button/options_button");

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        // Centrer les boutons verticalement
        classiqueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        marathonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boutiqueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ajouter des boutons avec des rigid areas (espace fixe) entre eux
        buttonPanel.add(classiqueButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacement vertical de 5 pixels
        buttonPanel.add(marathonButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(boutiqueButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(optionsButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Profile
        JPanel profilPanel = new JPanel();
        profilPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        profilPanel.setPreferredSize(new Dimension(0, 70));
        //profilPanel.setBackground(Color.BLUE);

        JLabel profilPhoto = new JLabel(new ImageIcon("ressources/profile/profil_photo.png"));
        profilPhoto.setPreferredSize(new Dimension(50, 50));
        profilPanel.add(profilPhoto);

        JLabel profilInfo = new JLabel("Level: 1 | Username: Player1");
        profilPanel.add(profilInfo);

        add(profilPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void buttonPressed(JButton button, String st) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            button.setIcon(new ImageIcon(st + "_pressed.png"));

            Timer timer = new Timer(500, e1 -> {
                button.setIcon(new ImageIcon(st + ".png"));
            });
            timer.setRepeats(false);
            timer.start();
        });
    }
}
