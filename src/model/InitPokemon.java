package model;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class InitPokemon {
    public static void loadingChar() throws FileNotFoundException {
        File ennemiLoader = new File("ressources/config/PokemonLoader.csv");
        Scanner sc = new Scanner(ennemiLoader);
        while (sc.hasNext()) {
            String[] info = new String[9];
            info = sc.next().split(",");
            pokemonMaker(info);
        }
        sc.close();
    }

    private static void pokemonMaker(String[] info) {
        Ennemi e = new Ennemi(info[0], // NOM
                Integer.parseInt(info[1]), // PV
                Integer.parseInt(info[2]), // DEFPHY
                Integer.parseInt(info[3]), // DEFSPE
                Types.stringToType(info[4]), // TYPE1
                Types.stringToType(info[5]), // TYPE2
                info[6].equals("VRAI"), // BOSS
                info[8]); // sprite
        e.setSpeed(Pokemon.stringToSpeed(info[7])); // SPEED
    }
}
