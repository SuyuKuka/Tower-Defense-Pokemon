package config;

import java.util.Scanner;

import characters.Ennemi;
import characters.Pokemon;
import characters.Tower;
import characters.Type;

public class InitPokemon {

    private static Scanner sc;

    public static void loadingChar() {

        InitPokemon.sc = new Scanner(InitPokemon.class.getResourceAsStream("PokemonLoader.txt"));
        while (sc.hasNext()) {
            String[] info = new String[7];
            info = sc.next().split(",");
            pokemonMaker(info);
        }
    }

    public static void pokemonMaker(String[] info) {
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
}
