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
        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Heading
        JLabel heading = new JLabel("PokeRevolute", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 86));
        heading.setForeground(Color.WHITE);
        JPanel headingPanel = new JPanel();
        headingPanel.add(heading);
        panel.add(headingPanel, BorderLayout.NORTH);
        try {
            Random rand = new Random();
            int background_random = rand.nextInt(3);
            System.out.println(background_random);
            backgroundImage = ImageIO
                    .read(getClass().getResource("ressources/background_image" + background_random + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Menu buttons with custom icons
        JButton classiqueButton = new JButton(new ImageIcon("src/graphics/ressources/classique.png"));
        JButton marathonButton = new JButton(new ImageIcon("src/graphics/ressources/marathon.png"));
        JButton boutiqueButton = new JButton(new ImageIcon("src/graphics/ressources/boutique.png"));
        JButton optionsButton = new JButton(new ImageIcon("src/graphics/ressources/options.png"));

        classiqueButton.addActionListener(e -> System.out.println("Classique"));

        marathonButton.addActionListener(e -> System.out.println("Marathon"));

        boutiqueButton.addActionListener(e -> System.out.println("Boutique"));

        optionsButton.addActionListener(e -> System.out.println("Options?"));
        // New panel with GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1)); // Change number of columns to 1
        buttonPanel.add(classiqueButton);
        buttonPanel.add(marathonButton);
        buttonPanel.add(boutiqueButton);
        buttonPanel.add(optionsButton);

        // Replace old panel with new panel
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Profil panel
        JPanel profilPanel = new JPanel();
        profilPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel profilPhoto = new JLabel(new ImageIcon("src/graphics/ressources/profil_photo.png"));
        profilPhoto.setOpaque(false);
        profilPhoto.setPreferredSize(new Dimension(50, 50));
        profilPanel.add(profilPhoto);

        JLabel profilInfo = new JLabel("Level: 1 | Username: Player1", SwingConstants.RIGHT);
        profilInfo.setOpaque(false);
        profilPanel.add(profilInfo);

        panel.add(profilPanel, BorderLayout.SOUTH);

        add(panel);
        setOpaque(false);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}