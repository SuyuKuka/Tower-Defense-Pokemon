package graphics;

import main.Game;

import model.Player;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGraphics extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel;
    private BufferedImage backgroundImage;

    private int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public MainGraphics(Player p, Game main) {

        setMinimumSize(new Dimension(screenWidth - screenWidth / 3, screenHeight - screenHeight / 3));
        setResizable(false);

        setLocationRelativeTo(null);
        setTitle("PokeRevolute");
        setIconImage(new ImageIcon("ressources/icon.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background
        if (backgroundImage == null) {
            Random rand = new Random();
            int background_random = rand.nextInt(3);
            File file = new File("ressources/background/background" + background_random + ".jpg");
            try {
                backgroundImage = ImageIO.read(file);
                if (backgroundImage == null) {
                    backgroundImage = ImageIO.read(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mainPanel = new JPanel(cardLayout) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setOpaque(false);

        MainMenu menu = new MainMenu(this, p);
        Classique classique = new Classique(this);
        Marathon marathon = new Marathon(this);
        Boutique boutique = new Boutique(this);
        Options options = new Options(this);

        mainPanel.add(menu, "menu");
        mainPanel.add(classique, "classique");
        mainPanel.add(marathon, "marathon");
        mainPanel.add(boutique, "boutique");
        mainPanel.add(options, "options");

        add(mainPanel);
        setVisible(true);
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
