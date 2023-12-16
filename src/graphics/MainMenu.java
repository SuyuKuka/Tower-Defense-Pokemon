package graphics;

import main.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MainMenu extends JPanel {
    private BufferedImage backgroundImage;
    private JPanel mainMenuPanel;

    public MainMenu() {
        setLayout(new BorderLayout());
        setOpaque(false);
        
        System.out.println("menu");
        mainMenuPanel = new JPanel();
        initMainMenu();
    
        setVisible(true);
    }


    public void initMainMenu(){
        mainMenuPanel.setOpaque(false);
        mainMenuPanel.setLayout(new BorderLayout());

        //Background
        Random rand = new Random();
        int background_random = rand.nextInt(3);
        File file = new File("ressources/background/background" + background_random + ".png");
        try {
            backgroundImage = ImageIO.read(file);
            if(backgroundImage==null){
                backgroundImage = ImageIO.read(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        buttonPressed(classiqueButton, "classique");
        buttonPressed(marathonButton, "marathon");
        buttonPressed(boutiqueButton, "boutique");
        buttonPressed(optionsButton, "options");

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

        mainMenuPanel.add(titlePanel, BorderLayout.NORTH);
        mainMenuPanel.add(buttonPanel, BorderLayout.CENTER);
        mainMenuPanel.add(profilPanel, BorderLayout.SOUTH);

        add(mainMenuPanel);
    }

    protected void buttonPressed(JButton button, String st) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> {
            button.setIcon(new ImageIcon("ressources/button/" + st + "_button_pressed.png"));

            Timer timer = new Timer(500, e1 -> {
                button.setIcon(new ImageIcon("ressources/button/" + st + "_button.png"));
                start(st);
            });
            timer.setRepeats(false);
            timer.start();
        });
    }

    public void start(String st){
        switch (st) {
            case "classique":
                showClassiqueScreen();
                break;
            case "marathon":

                break;
            case "boutique":

                break;
            case "options":

                break;
            case "facile":

                break;
            case "moyen":

                break;
            case "difficile":

                break;
            case "retour":
                showMainMenuScreen();
                break;
            default:
                break;
        }
    }

    private void showMainMenuScreen() {
        MainMenu mainMenu = new MainMenu();
        switchToScreen(mainMenu.getMainMenuPanel());
    }

    private void switchToScreen(JPanel screen) {
        setVisible(false);
        removeAll();
        add(screen);
        setVisible(true);
    }    

    private void showClassiqueScreen() {
        Classique classique = new Classique();
        switchToScreen(classique.getClassiquePanel());
    }

    public JPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
    }

}
