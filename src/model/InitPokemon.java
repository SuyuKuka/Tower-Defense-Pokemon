package model;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class InitPokemon {

    public static void loadingChar() throws FileNotFoundException {
        // TROUVER SI POSSIBLE UNE MEILLEUR ALTERNATIVE
        File file = new File("ressources/config/PokemonLoader.csv");
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String[] info = new String[8];
            info = sc.next().split(",");
            pokemonMaker(info);
        }
        sc.close();
    }

    private static void pokemonMaker(String[] info) {
        Ennemi e = new Ennemi(info[0], // NOM
                Integer.valueOf(info[1]), // PV
                Integer.valueOf(info[2]), // DEFPHY
                Integer.valueOf(info[3]), // DEFSPE
                Type.valueOf(info[4]), // TYPE1
                Type.stringToType(info[5]), // TYPE2
                info[6].equals("VRAI"),
                info[7]); // BOSS
        e.setSpeed(Pokemon.stringToSpeed(info[7])); // SPEED

    }

}
