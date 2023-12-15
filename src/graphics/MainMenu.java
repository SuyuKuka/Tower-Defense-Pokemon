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
    private JPanel titlePanel = new JPanel(new GridBagLayout());
    private JPanel profilPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    public MainMenu() {
        setLayout(new BorderLayout());
        setOpaque(false);

        // Titre
        JLabel heading = new JLabel();
        ImageIcon icon = new ImageIcon("ressources/title.png");
        icon.setImage(icon.getImage().getScaledInstance(812, 170, Image.SCALE_SMOOTH));
        heading.setIcon(icon);

        titlePanel.setOpaque(false);
        titlePanel.add(heading, new GridBagConstraints());
        add(titlePanel, BorderLayout.NORTH);

        //Background
        Random rand = new Random();
        int background_random = rand.nextInt(3);
        File file = new File("ressources/background/background" + background_random + ".png");
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

        buttonPressed(classiqueButton, "classique");
        buttonPressed(marathonButton, "marathon");
        buttonPressed(boutiqueButton, "boutique");
        buttonPressed(optionsButton, "options");

        // Panel pour les boutons
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

        // Panel pour le profil
        profilPanel.setOpaque(false);
        profilPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        // Panel pour la photo de profil et les informations de profil
        JPanel cadrePanel = new JPanel();
        cadrePanel.setBackground(Color.BLACK);
        cadrePanel.setLayout(new BorderLayout());
        cadrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));
        cadrePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel profilPhoto = new JLabel(new ImageIcon("ressources/profile/profil_photo.png"));
        profilPhoto.setPreferredSize(new Dimension(50, 50));
        cadrePanel.add(profilPhoto, BorderLayout.WEST);

        // Création d'un sous-panel pour contenir le pseudo et le niveau du joueur
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setOpaque(false);

        JLabel pseudo = new JLabel("  Joueur1 ");
        pseudo.setForeground(Color.WHITE);
        pseudo.setFont(new Font(pseudo.getFont().getName(), Font.BOLD, 16));
        infoPanel.add(pseudo, BorderLayout.NORTH);

        JLabel levelAccount = new JLabel("  Nv du compte : 1 ");
        levelAccount.setForeground(Color.WHITE);
        levelAccount.setFont(new Font(levelAccount.getFont().getName(), Font.BOLD, 16));
        infoPanel.add(levelAccount, BorderLayout.CENTER);

        // Ajout du sous-panel au cadrePanel
        cadrePanel.add(infoPanel, BorderLayout.CENTER);
        profilPanel.add(cadrePanel);

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
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> {
            button.setIcon(new ImageIcon("ressources/button/" + st + "_button_pressed.png"));

            Timer timer = new Timer(500, e1 -> {
                button.setIcon(new ImageIcon("ressources/button/" + st + "_button.png"));
                if (st.equals("classique")) {
                    setVisible(false);
                    Classique classique = new Classique();
                    remove(titlePanel);
                    remove(buttonPanel);
                    remove(profilPanel);
                    add(classique);
                    setVisible(true);
                } else if (st.equals("marathon")) {

                } else if (st.equals("boutique")) {

                } else if (st.equals("options")) {

                } else {
                    // erreur?
                }
            });
            timer.setRepeats(false);
            timer.start();
        });
    }

    public BufferedImage getBackgroundImage() {
        return this.backgroundImage;
    }
}
