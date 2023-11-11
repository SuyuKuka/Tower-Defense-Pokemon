package main;

import javax.swing.JFrame;

import characters.Ennemi;
import characters.Pokemon;
import characters.Tower;
import config.InitPokemon;
import config.InitWaves;
import config.Map;
import graphics.GameScreen;
import model.MapState;

import java.util.Scanner;

public class Game extends JFrame {

    private GameScreen gameScreen;
    private static boolean gameStart = true;

    public Game() {
        setSize(1000, 1000);
        setVisible(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameScreen = new GameScreen();
        add(gameScreen);
    }

}
