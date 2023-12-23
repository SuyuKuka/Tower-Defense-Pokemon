package main;

import graphics.Boutique;
import graphics.Classique;
import graphics.GameScreen;
import graphics.MainMenu;
import graphics.Marathon;
import graphics.Options;
import graphics.GameScreen2;
import graphics.UpdateMap;
import model.MapState;
import model.Player;
import model.Pokemon;
import model.Tower;
import model.Pokemon.State;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private GameScreen gameScreen = new GameScreen(this);
    private MapState state = new MapState();
    private GameScreen2 test = new GameScreen2(state);
    // private UpdateMap updateMap = new UpdateMap(state);
    private BufferedImage backgroundImage;
    boolean fullscreen = false; // AJUSTABLE

    private int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public Game(Player p) {

        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ressources/icon.png").getImage(),
                new Point(0, 0), "POKEMONGOOOOO"));

        if (fullscreen) {
            setAlwaysOnTop(true);
            setUndecorated(true);
            getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setResizable(false);
        } else {
            setSize(screenWidth - screenHeight / 3, screenHeight - screenHeight / 3);
            setLocationRelativeTo(null);
        }
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
        mainPanel.add(test, "gameScreen");

        add(mainPanel);
        setVisible(true);
    }

    public void changePanel(String buttonName) {
        Timer timer = new Timer(1, e1 -> {
            if (buttonName.equals("retour")) {
                cardLayout.show(mainPanel, "menu");
            } else {
                if (buttonName.length() >= 10 && buttonName.substring(0, 10).equals("gameScreen")) {

                    cardLayout.show(mainPanel, buttonName.substring(0, 10));
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {

                            state.startGraphic(test, buttonName.substring(13, buttonName.length()));
                            return null;
                        }
                    };
                    worker.execute();
                } else {
                    cardLayout.show(mainPanel, buttonName);
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 0 && args[0].equals("[terminal]")) {
            MapState terminal = new MapState();
            terminal.startTerminal();
        } else {
            javax.swing.SwingUtilities.invokeLater(
                    new Runnable() {
                        public void run() {
                            Player p = new Player();
                            Tower t = new Tower("charmander", ALLBITS, ABORT, null, null,
                                    "ressources/sprite/charmander.png", State.LOCKED);
                            Tower t1 = new Tower("charmeleon", ALLBITS, ABORT, null, null,
                                    "ressources/sprite/charmeleon.png", State.LOCKED);
                            Tower t2 = new Tower("charizard", ALLBITS, ABORT, null, null,
                                    "ressources/sprite/charizard.png", State.LOCKED);
                            Tower t3 = new Tower("squirtle", ALLBITS, ABORT, null, null,
                                    "ressources/sprite/squirtle.png", State.LOCKED);
                            Tower t4 = new Tower("venusaur", ALLBITS, ABORT, null, null,
                                    "ressources/sprite/venusaur.png", State.LOCKED);
                            Tower t5 = new Tower("ivysaur", ALLBITS, ABORT, null, null,
                                    "ressources/sprite/ivysaur.png", State.LOCKED);

                            p.getObtainedPokemon().add(t);
                            p.getObtainedPokemon().add(t1);
                            p.getObtainedPokemon().add(t2);
                            p.getObtainedPokemon().add(t3);
                            p.getObtainedPokemon().add(t4);
                            p.getObtainedPokemon().add(t5);
                            for (Tower pkm : p.getObtainedPokemon()) {
                                pkm.setState(State.PC);
                            }

                            p.addteam(t);
                            p.addteam(t1);
                            p.addteam(t2);
                            p.addteam(t3);

                            p.getPc().add(t4);
                            p.getPc().add(t5);

                            Game game = new Game(p);
                            game.setVisible(true);
                        }
                    });
        }
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

}
