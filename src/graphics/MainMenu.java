package graphics;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu() {
        // Panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        // Heading
        JLabel heading = new JLabel("PokeRevolute", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        panel.add(heading, BorderLayout.NORTH);

        // Load button images
        ImageIcon classiqueIcon = new ImageIcon("src\\graphics\\ressources\\classique_icon.png");
        ImageIcon marathonIcon = new ImageIcon("src\\graphics\\ressources\\marathon_icon.png");
        ImageIcon boutiqueIcon = new ImageIcon("src\\graphics\\ressources\\boutique_icon.png");
        ImageIcon optionsIcon = new ImageIcon(
                "C:\\Users\\miche\\pooproject\\src\\graphics\\ressources\\options_icon.png");

        // Menu buttons with custom icons
        JButton classique = new JButton(classiqueIcon);
        JButton marathon = new JButton(marathonIcon);
        JButton boutique = new JButton(boutiqueIcon);
        JButton options = new JButton(optionsIcon);

        // Modify button appearance
        classique.setBorderPainted(false);
        marathon.setBorderPainted(false);
        boutique.setBorderPainted(false);
        options.setBorderPainted(false);

        classique.setFocusPainted(false);
        marathon.setFocusPainted(false);
        boutique.setFocusPainted(false);
        options.setFocusPainted(false);

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

        JLabel profilPhoto = new JLabel(new ImageIcon("profil_photo.jpg"));
        profilPhoto.setPreferredSize(new Dimension(50, 50));
        profilPanel.add(profilPhoto);

        JLabel profilInfo = new JLabel("Level: 1 | Username: Player1", SwingConstants.RIGHT);
        profilPanel.add(profilInfo);

        panel.add(profilPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}