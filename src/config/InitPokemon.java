package config;

import java.io.InputStream;
import java.util.Scanner;

import model.Ennemi;
import model.Pokemon;
import model.Tower;
import model.Type;

public class InitPokemon {

    public static void loadingChar() {
        InputStream inputStream = InitPokemon.class.getResourceAsStream("/resources/config/PokemonLoader.txt"); // TROUVER
                                                                                                                // SI
                                                                                                                // POSSIBLE
                                                                                                                // UNE
                                                                                                                // MEILLEUR
                                                                                                                // ALTERNATIVE
        Scanner sc = new Scanner(inputStream);
        while (sc.hasNext()) {
            String[] info = new String[7];
            info = sc.next().split(",");
            pokemonMaker(info);
        }
        sc.close();
    }

    private static void pokemonMaker(String[] info) {
        if (Integer.valueOf(info[0]) == 0) {
            Ennemi e = new Ennemi(info[1], Integer.valueOf(info[4]), Type.stringToType(info[2]),
                    Type.stringToType(info[3]));
            e.setSpeed(Pokemon.stringToSpeed(info[5]));
        }
        if (Integer.valueOf(info[0]) == 1) {
            Tower t = new Tower(info[1], Integer.valueOf(info[4]), Type.stringToType(info[2]),
                    Type.stringToType(info[3]));
            t.setSpeed(Pokemon.stringToSpeed(info[5]));
        }
    }

    public static void loadingCharTerminal() {
        for (int i = 1; i < 57; i++) {
            Ennemi ennemiTerminal = new Ennemi("ennemiTerminal", i, Type.NONE, Type.NONE);
        }

    }
}
