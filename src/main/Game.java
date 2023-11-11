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

    private static double lastUpdate;
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
        MapState a = new MapState(m);
        a.affiche(m);

        System.out.println(
                "Pour commencer à jouer, il faut placer une tours aux endroits qui contiennent des + sous la forme de coordonées");
        System.out.println("Exemple : F4 ou F04");

        Scanner sc = new Scanner(System.in);

        Tower towerTerminal = new Tower("towerTerminal", 1, characters.Type.NONE, characters.Type.NONE);
        String st = sc.nextLine();
        int[] posTower = towerTerminal.putTower(st);
        a.setTower(posTower, towerTerminal);

        System.out.println("Pose un ennemi");
        Ennemi ennemiTerminal = new Ennemi("enemmiTerminal", 6, characters.Type.NONE, characters.Type.NONE);
        String test = sc.nextLine();
        int[] posEnnemi = ennemiTerminal.putEnnemi(test);
        a.setEnnemi(posEnnemi, ennemiTerminal);
        System.out.println("--------------");
        System.out.println(Character.forDigit(ennemiTerminal.getValue(), 10));
        System.out.println("--------------");

        System.out.println(m);
        a.affiche(m);

        while (gameStart) {
            if (System.currentTimeMillis() - lastUpdate >= 1000) { // 1000 = 1s a changer si nécessaire
                lastUpdate = System.currentTimeMillis();

                System.out.println("Pose une tour");
                towerTerminal = new Tower("towerTerminal", 1, characters.Type.NONE, characters.Type.NONE);
                st = sc.nextLine();
                posTower = towerTerminal.putTower(st);
                a.setTower(posTower, towerTerminal);
                towerTerminal.attaque(a.getTowerPresent(), a.getEnnemiPresent(), a);

                System.out.println("Pose un ennemi");
                ennemiTerminal = new Ennemi("enemmiTerminal", 9, characters.Type.NONE, characters.Type.NONE);
                test = sc.nextLine();
                posEnnemi = ennemiTerminal.putEnnemi(st);
                a.setEnnemi(posEnnemi, ennemiTerminal);
                for (int i = 0; i < a.getEnnemiPresent().size(); i++) {

                    a.move(a.getEnnemiPresent().get(i));
                }
                System.out.println("IL Y A :" + a.getEnnemiPresent().size());
                System.out.println(m);
                a.affiche(m);

            }

        }
        sc.close();
    }
}
