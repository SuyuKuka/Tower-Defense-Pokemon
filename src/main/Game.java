package main;

import javax.swing.JFrame;
import java.util.Scanner;

import characters.Ennemi;
import characters.Pokemon;
import characters.Tower;
import config.Map;
import graphics.GameScreen;
import model.MapState;

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


    public static void main(String[] args) throws Exception {
        Map m = new Map(20, 20);
        m.mapBuilding();

        m.affiche();

        System.out.println(
                "Pour commencer à jouer, il faut placer une tours aux endroits qui contiennent des + sous la forme de coordonées");
        System.out.println("Exemple : F4 ou F04");

        Scanner sc = new Scanner(System.in);

        Tower tower = new Tower("terminal", ABORT, characters.Type.NONE, characters.Type.NONE);
        String st = sc.nextLine();
        tower.putTower(st);

        while (gameStart) {
            st = sc.nextLine();
            tower.putTower(st);

        }
        // MapState a = new MapState(m);

        // Ennemi M = new Ennemi("Miaouss", 29, characters.Type.NORMAL,
        // characters.Type.NONE);
        // Tower pika = new Tower("Pikachu", 256, characters.Type.ELECTIK,
        // characters.Type.NONE);

        // pika.setSpeed(Pokemon.Speed.FAST);
        // System.out.println(M);
        // System.out.println(pika.toString());

    }
}
