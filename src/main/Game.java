package main;

import javax.swing.JFrame;

import characters.Characters;
import characters.Tour;
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
        Tour pika = new Tour("Pikachu", 29, Characters.Type.ELECTIK, Characters.Type.DRAGON);
        System.out.println(pika);
        pika.setSpeed(Characters.Speed.FAST);
        System.out.println(pika);
    }
}
