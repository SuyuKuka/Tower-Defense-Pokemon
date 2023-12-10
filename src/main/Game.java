package main;

import javax.swing.JFrame;

import graphics.GameScreen;
import model.MapState;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Game extends JFrame {

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
            setSize(1000, 1000);
            setLocationRelativeTo(null);
        }
        setTitle("PokéRevolute");
        setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png")).getImage());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        add(gameScreen);
        setVisible(true);

    }

    public static void main(String[] args) throws Exception {
        if (args.length != 0 && args[0].equals("[terminal]")) {

            MapState terminal = new MapState();
            terminal.startTerminal();

        } else {
            Game game = new Game();

        }
    }
}
