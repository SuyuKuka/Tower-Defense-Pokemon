package main;

import javax.swing.JFrame;

import graphics.GameScreen;
import model.MapState;

public class Game extends JFrame {

    private GameScreen gameScreen;

    public Game() {
        setSize(1000, 1000);
        setVisible(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameScreen = new GameScreen();
        add(gameScreen);
    }

    public static void main(String[] args) throws Exception {
        if (args[0].equals("[terminal]")) {
            MapState terminal = new MapState(true);
            terminal.startTerminal();
        }
    }
}
