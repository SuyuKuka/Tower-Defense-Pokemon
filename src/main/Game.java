package main;

import javax.swing.JFrame;
import characters.Ennemi;
import characters.Pokemon;
import characters.Tower;
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

        Map m = new Map(20, 20);
        m.mapBuilding();

        System.out.println(m);
        m.affiche();

        // MapState a = new MapState(m);

        Ennemi M = new Ennemi("Miaouss", 29, characters.Type.NORMAL, characters.Type.NONE);
        Ennemi P = new Ennemi("sqdqsdqsvqss", 29, characters.Type.NORMAL, characters.Type.NONE);
        Ennemi D = new Ennemi("8855858ss", 29, characters.Type.NORMAL, characters.Type.NONE);

        Tower pika = new Tower("Pikachu", 256, characters.Type.ELECTIK, characters.Type.NONE);

        pika.setSpeed(Pokemon.Speed.FAST);
        System.out.println(M);
        System.out.println(pika);

        // System.out.println(M.getList());

        System.out.println();

        InitWaves.showlist();

    }
}
