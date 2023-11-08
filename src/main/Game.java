package main;

import javax.swing.JFrame;

import characters.Bad;
import characters.Pokemon;
import characters.Tour;

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
        Bad M = new Bad("Miaouss", 29, characters.Type.NORMAL, characters.Type.NONE);
        Tour pika = new Tour("Pikachu", 256, characters.Type.ELECTIK, characters.Type.NONE);

        pika.setSpeed(Pokemon.Speed.FAST);
        System.out.println(M);
        System.out.println(pika);
    }
}
