package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class MainMenu extends JPanel {

    private BufferedImage backgroundImage;

    public MainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        //Titre
        JLabel heading = new JLabel("PokeRevolute");
        heading.setFont(new Font("Arial", Font.BOLD, 86));
        heading.setForeground(Color.WHITE);
        panel.add(heading, BorderLayout.NORTH);
        
        //Background
        try {
            Random rand = new Random();
            int background_random = rand.nextInt(3);
            System.out.println(background_random);
            backgroundImage = ImageIO
                    .read(getClass().getResource("ressources/background_image" + background_random + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Bouttons
        JButton classiqueButton = new JButton(new ImageIcon("src/graphics/ressources/classique.png"));
        JButton marathonButton = new JButton(new ImageIcon("src/graphics/ressources/marathon.png"));
        JButton boutiqueButton = new JButton(new ImageIcon("src/graphics/ressources/boutique.png"));
        JButton optionsButton = new JButton(new ImageIcon("src/graphics/ressources/options.png"));

        classiqueButton.setBorderPainted(false);
        classiqueButton.setContentAreaFilled(false);
        marathonButton.setBorderPainted(false);
        marathonButton.setContentAreaFilled(false);
        boutiqueButton.setBorderPainted(false);
        boutiqueButton.setContentAreaFilled(false);
        optionsButton.setBorderPainted(false);
        optionsButton.setContentAreaFilled(false);

        classiqueButton.addActionListener(e -> System.out.println("Classique"));
        marathonButton.addActionListener(e -> System.out.println("Marathon"));
        boutiqueButton.addActionListener(e -> System.out.println("Boutique"));
        optionsButton.addActionListener(e -> System.out.println("Options?"));

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1)); // Change number of columns to 1
        buttonPanel.setOpaque(false);
        buttonPanel.add(classiqueButton);
        buttonPanel.add(marathonButton);
        buttonPanel.add(boutiqueButton);
        buttonPanel.add(optionsButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        // Profile
        JPanel profilPanel = new JPanel();
        profilPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel profilPhoto = new JLabel(new ImageIcon("src/graphics/ressources/profil_photo.png"));
        profilPhoto.setPreferredSize(new Dimension(50, 50));
        profilPanel.add(profilPhoto);

        JLabel profilInfo = new JLabel("Level: 1 | Username: Player1");
        profilPanel.add(profilInfo);

        panel.add(profilPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}