package main;

import javax.swing.JFrame;

import config.Map;
import graphique.GameScreen;

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
        Map m = new Map(20, 20);
        m.mapBuilding();

        m.affiche();
    }
}
