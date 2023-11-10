package main;

import javax.swing.JFrame;
import characters.Ennemi;
import characters.Pokemon;
import characters.Tower;
import config.InitPokemon;
import config.InitWaves;
import config.Map;
import graphique.GameScreen;
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
        System.out.println(args[0]);
        Map m = new Map(20, 20);
        m.mapBuilding();

        // System.out.println(m);
        // m.affiche();

        // MapState a = new MapState(m);
        // System.out.println(M);
        // System.out.println(pika);

        // System.out.println(M.getList());

        InitPokemon.loadingChar();
        InitWaves.showlist();

    }
}
