package graphics;

import main.Game;

import model.Player;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.midi.Track;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainGraphics extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel;
    private BufferedImage backgroundImage;

    private int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public MainGraphics(Player p, Game main) {

        // setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new
        // ImageIcon("ressources/icon.png").getImage(),new Point(0, 0),
        // "POKEMONGOOOOO"));

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

            }
        });

        setMinimumSize(new Dimension(screenWidth - screenWidth / 3, screenHeight - screenHeight / 3));
        setResizable(false);

        setLocationRelativeTo(null);
        setTitle("PokeRevolute");
        setIconImage(new ImageIcon("ressources/icon.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setResizable(false);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        // setUndecorated(true);

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
