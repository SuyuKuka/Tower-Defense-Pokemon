package model;

import java.io.InputStream;
import java.util.Scanner;

public class InitPokemon {

    public static void loadingChar() {
        // TROUVER SI POSSIBLE UNE MEILLEUR ALTERNATIVE
        InputStream inputStream = InitPokemon.class.getResourceAsStream("/resources/config/PokemonLoader2.txt");
        Scanner sc = new Scanner(inputStream);
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
                info[6].equals("true")); // BOSS
        e.setSpeed(Pokemon.stringToSpeed(info[7])); // SPEED

    }

}
