package main;

import javax.swing.JFrame;

import gameview.GameScreen;
import model.Map;
import model.MapState;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Game extends JFrame {

    boolean fullscreen = false; // AJUSTABLE

    public Game(MapState state) {

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
        setTitle("PokeRevolute");
        setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png")).getImage());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GameScreen gameScreen = new GameScreen(state);
        add(gameScreen);
        state.setScreen(gameScreen);
        setVisible(true);

    }

    public static void main(String[] args) throws Exception {

        Map m = new Map(20, 20);

        if (args.length != 0 && args[0].equals("[terminal]")) {

            MapState terminal = new MapState();
            terminal.startTerminal();
        } else {

            MapState graphic = new MapState();
            Game game = new Game(graphic);
            graphic.startGraphic();

        }
    }
}
