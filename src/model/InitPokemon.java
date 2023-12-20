package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class InitPokemon {

    public static void loadingChar() {
        File ennemiLoader = new File("ressources/config/PokemonLoader2.txt");
        Scanner sc;
        try {
            sc = new Scanner(ennemiLoader);
            while (sc.hasNext()) {
                String[] info = new String[8];
                info = sc.next().split(",");
                pokemonMaker(info);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void pokemonMaker(String[] info) {
        Ennemi e = new Ennemi(info[0], // NOM
                Integer.valueOf(info[1]), // PV
                Integer.valueOf(info[2]), // DEFPHY
                Integer.valueOf(info[3]), // DEFSPE
                Types.valueOf(info[4]), // TYPE1
                Types.stringToType(info[5]), // TYPE2
                info[6].equals("VRAI"),
                info[7]); // BOSS
        e.setSpeed(Pokemon.stringToSpeed(info[7])); // SPEED

    }

}
