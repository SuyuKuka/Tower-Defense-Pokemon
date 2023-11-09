package main;

import javax.swing.JFrame;

import characters.Ennemi;
import characters.Pokemon;
import characters.Tower;
import characters.Tower;
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
        Ennemi M = new Ennemi("Miaouss", 29, characters.Type.NORMAL, characters.Type.NONE);
        Tower pika = new Tower("Pikachu", 256, characters.Type.ELECTIK, characters.Type.NONE);

        pika.setSpeed(Pokemon.Speed.FAST);
        System.out.println(M);
        System.out.println(pika);
    }
}
