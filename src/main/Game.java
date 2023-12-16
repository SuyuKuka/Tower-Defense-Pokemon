package main;

import graphics.Boutique;
import graphics.Classique;
import graphics.GameScreen;
import graphics.MainMenu;
import graphics.Marathon;
import graphics.Options;
import model.MapState;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private BufferedImage backgroundImage;
    private GameScreen gameScreen;
    boolean fullscreen = false; // AJUSTABLE

    public Game() {
        if (fullscreen) {
            setAlwaysOnTop(true);
            setUndecorated(true);
            getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setResizable(false);
        } else {
            setSize(1224, 642);
            setLocationRelativeTo(null);
        }
        setTitle("PokeRevolute");
        setIconImage(new ImageIcon("ressources/icon.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Background
        if(backgroundImage == null){
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
        }
        mainPanel = new JPanel(cardLayout) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setOpaque(false);

        MainMenu menu = new MainMenu(this);
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

    public static void main(String[] args) throws Exception {
        if (args.length != 0 && args[0].equals("[terminal]")) {
            MapState terminal = new MapState();
            terminal.startTerminal();
        } else {
            javax.swing.SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						Game game = new Game();
						game.setVisible(true);
					}
				}
			);
        }
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public void buttonPressed(JButton button, String st) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
        button.addActionListener(e -> {
            button.setIcon(new ImageIcon("ressources/button/" + st + "_button_pressed.png"));

            Timer timer = new Timer(500, e1 -> {
                button.setIcon(new ImageIcon("ressources/button/" + st + "_button.png"));
                handleButtonClick(st);
            });
            timer.setRepeats(false);
            timer.start();
        });
    }
    
    private void handleButtonClick(String buttonName) {
        if(buttonName.equals("retour")){
            cardLayout.show(mainPanel, "menu");
        }else{
            cardLayout.show(mainPanel, buttonName);
        }
    }
}
