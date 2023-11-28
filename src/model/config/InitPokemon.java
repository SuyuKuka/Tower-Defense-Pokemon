package model.config;

import java.io.InputStream;
import java.util.Scanner;

import model.characters.Ennemi;
import model.characters.Pokemon;
import model.characters.Tower;
import model.characters.Type;

public class InitPokemon {

    public static void loadingChar() {
        // TROUVER SI POSSIBLE UNE MEILLEUR ALTERNATIVE
        InputStream inputStream = InitPokemon.class.getResourceAsStream("/resources/config/PokemonLoader.txt");
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
                    Type.stringToType(info[3]), info[6].equals("true"));
            e.setSpeed(Pokemon.stringToSpeed(info[5]));
            e.setBoss(info[6]);
        }
        if (Integer.valueOf(info[0]) == 1) {
            Tower t = new Tower(info[1], Integer.valueOf(info[4]), Type.stringToType(info[2]),
                    Type.stringToType(info[3]));
            t.setSpeed(Pokemon.stringToSpeed(info[5]));
        }
    }

}
