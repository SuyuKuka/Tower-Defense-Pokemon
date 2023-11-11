package java.main;

import javax.swing.JFrame;

import java.characters.Ennemi;
import java.characters.Pokemon;
import java.characters.Tower;
import java.config.Map;
import java.graphics.GameScreen;
import java.model.MapState;
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


    public static void main(String[] args) throws Exception {
        Map m = new Map(20, 20);
        m.mapBuilding();
        MapState a = new MapState(m);
        m.affiche();

        System.out.println(
                "Pour commencer à jouer, il faut placer une tours aux endroits qui contiennent des + sous la forme de coordonées");
        System.out.println("Exemple : F4 ou F04");

        Scanner sc = new Scanner(System.in);

        Tower towerTerminal = new Tower("towerTerminal", 1, java.characters.Type.NONE, java.characters.Type.NONE);
        String st = sc.nextLine();
        int[] posTower = towerTerminal.putTower(st);
        a.setTower(posTower,towerTerminal);

        System.out.println("Pose un ennemi");
        Ennemi ennemiTerminal = new Ennemi("enemmiTerminal", 12, java.characters.Type.NONE, java.characters.Type.NONE);
        String test = sc.nextLine();
        int[] posEnnemi = ennemiTerminal.putEnnemi(test);
        a.setEnnemi(posEnnemi,ennemiTerminal);

        m.affiche();

        while (gameStart) {
            towerTerminal = new Tower("towerTerminal", 1, java.characters.Type.NONE, java.characters.Type.NONE);
            st = sc.nextLine();
            posTower = towerTerminal.putTower(st);
            a.setTower(posTower,towerTerminal);
            towerTerminal.attaque(a.getTowerPresent() ,a.getEnnemiPresent(), a);
            
            m.affiche();
        }
        sc.close();
    }
}
