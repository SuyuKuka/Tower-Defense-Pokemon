package graphics;

import main.Game;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    public MainMenu(Game main) {
        System.out.println("Menu");
        setLayout(new BorderLayout());
        setOpaque(false);

        // Titre
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);
    
        JLabel heading = new JLabel();
        ImageIcon icon = new ImageIcon("ressources/title.png");
        icon.setImage(icon.getImage().getScaledInstance(812, 170, Image.SCALE_SMOOTH));
        heading.setIcon(icon);

        titlePanel.add(heading, new GridBagConstraints());

        // Boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JButton classiqueButton = new JButton(new ImageIcon("ressources/button/classique_button.png"));
        JButton marathonButton = new JButton(new ImageIcon("ressources/button/marathon_button.png"));
        JButton boutiqueButton = new JButton(new ImageIcon("ressources/button/boutique_button.png"));
        JButton optionsButton = new JButton(new ImageIcon("ressources/button/options_button.png"));

        main.buttonPressed(classiqueButton, "classique");
        main.buttonPressed(marathonButton, "marathon");
        main.buttonPressed(boutiqueButton, "boutique");
        main.buttonPressed(optionsButton, "options");

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

        JPanel profilPanel = new JPanel();
        profilPanel.setOpaque(false);
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

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(profilPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
 

}
