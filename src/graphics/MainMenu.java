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
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        panel.add(heading, BorderLayout.NORTH);
        try {
            Random rand = new Random();
            int background_random = rand.nextInt(3);
            System.out.println(background_random);
            backgroundImage = ImageIO
                    .read(getClass().getResource("ressources/background_image" + background_random + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load button images
        ImageIcon classiqueIcon = new ImageIcon("src/graphics/ressources/classique.png");
        ImageIcon marathonIcon = new ImageIcon("src/graphics/ressources/marathon.png");
        ImageIcon boutiqueIcon = new ImageIcon("src/graphics/ressources/boutique.png");
        ImageIcon optionsIcon = new ImageIcon("src/graphics/ressources/options.png");

        // Menu buttons with custom icons
        JButton classique = new JButton(classiqueIcon);
        JButton marathon = new JButton(marathonIcon);
        JButton boutique = new JButton(boutiqueIcon);
        JButton options = new JButton(optionsIcon);

        classique.setContentAreaFilled(false);
        marathon.setContentAreaFilled(false);
        boutique.setContentAreaFilled(false);
        options.setContentAreaFilled(false);

        classique.setOpaque(false);
        marathon.setOpaque(false);
        boutique.setOpaque(false);
        options.setOpaque(false);

        // New panel with GridLayout
        JPanel newPanel = new JPanel(new GridLayout(0, 1)); // Change number of columns to 1
        newPanel.add(classique);
        newPanel.add(marathon);
        newPanel.add(boutique);
        newPanel.add(options);

        // Replace old panel with new panel
        panel.add(newPanel, BorderLayout.CENTER);

        // Profil panel
        JPanel profilPanel = new JPanel();
        profilPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel profilPhoto = new JLabel(new ImageIcon("src/graphics/ressources/profil_photo.png"));
        profilPhoto.setPreferredSize(new Dimension(50, 50));
        profilPanel.add(profilPhoto);

        JLabel profilInfo = new JLabel("Level: 1 | Username: Player1", SwingConstants.RIGHT);
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