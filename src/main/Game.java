package main;

import javax.swing.JFrame;

import graphics.GameScreen;
import graphics.MainMenu;
import model.MapState;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Game extends JFrame {

    private GameScreen gameScreen;
    public int width;
    public int height;
    boolean fullscreen = false; // AJUSTABLE

    public Game() {
        if (fullscreen) {

            setAlwaysOnTop(true);
            setUndecorated(true);
            getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setResizable(false);
        } else {
            this.width = 1224;
            this.height = 642;
            setSize(width, height);
            setLocationRelativeTo(null);
        }
        setTitle("PokeRevolute");
        setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png")).getImage());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // gameScreen = new GameScreen();
        // add(gameScreen);
        MainMenu menu = new MainMenu();
        add(menu);
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
