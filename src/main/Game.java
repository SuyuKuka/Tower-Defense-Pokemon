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
    private GameScreen gameScreen = new GameScreen(this);
    private BufferedImage backgroundImage;
    boolean fullscreen = false; // AJUSTABLE

    public Game() {
        if (fullscreen) {
            setAlwaysOnTop(true);
            setUndecorated(true);
            getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setResizable(false);
        } else {
            setSize(1224, 654);
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

        // mainPanel.add(menu, "menu");
        // mainPanel.add(classique, "classique");
        // mainPanel.add(marathon, "marathon");
        // mainPanel.add(boutique, "boutique");
        // mainPanel.add(options, "options");
        mainPanel.add(gameScreen, "gameScreen");
        //gameScreen.startLevel("facile");
        
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        if (args.length != 0 && args[0].equals("[terminal]")) {
            MapState terminal = new MapState();
            terminal.startTerminal();
        } else {
            Game game = new Game();
            game.setVisible(true);
            GameScreen gameScreen = new GameScreen(game);
            gameScreen.startLevel("facile");
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
            if(st.length()>=10 && st.substring(0, 10).equals("gameScreen")){
                button.setIcon(new ImageIcon("ressources/button/" + st.substring(13, st.length()) + "_button_pressed.png"));
            }else{
                button.setIcon(new ImageIcon("ressources/button/" + st + "_button_pressed.png"));
            }

            Timer timer = new Timer(200, e1 -> {
                if(st.length()>=10 && st.substring(0, 10).equals("gameScreen")){
                    button.setIcon(new ImageIcon("ressources/button/" + st.substring(13, st.length()) + "_button.png"));
                }else{
                    button.setIcon(new ImageIcon("ressources/button/" + st + "_button.png"));
                }
                changePanel(st);
            });
            timer.setRepeats(false);
            timer.start();
        });
    }
    
    private void changePanel(String buttonName) {
        Timer timer = new Timer(1, e1 -> {
            if(buttonName.equals("retour")){
                cardLayout.show(mainPanel, "menu");
            }else{
                if(buttonName.length()>=10 && buttonName.substring(0, 10).equals("gameScreen")){
                    cardLayout.show(mainPanel, buttonName.substring(0, 10));
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            gameScreen.startLevel(buttonName.substring(13, buttonName.length()));
                        }
                    });
                }else{
                    cardLayout.show(mainPanel, buttonName);       
                }
            }
        });
        timer.setRepeats(false);
        timer.start();   
    }
}
