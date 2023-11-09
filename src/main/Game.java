package main;

import javax.swing.JFrame;

import config.Map;
import graphics.GameScreen;

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
        if (args[0].length() == 0) {
            System.out.println("Graphics");
        } else if (args[0].equals("[terminal]")) {
            Map m = new Map(20, 20);
            m.mapBuilding();

            m.affiche();
        }
    }
}
