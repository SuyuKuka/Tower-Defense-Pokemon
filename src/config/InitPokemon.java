package config;

import java.io.InputStream;
import java.util.Scanner;

import characters.Ennemi;
import characters.Pokemon;
import characters.Tower;
import characters.Type;

public class InitPokemon {

    public static void loadingChar() {
        InputStream inputStream = InitPokemon.class.getResourceAsStream("/resources/config/PokemonLoader.txt");
        Scanner sc = new Scanner(inputStream);
        while (sc.hasNext()) {
            String[] info = new String[7];
            info = sc.next().split(",");
            pokemonMaker(info);
        }
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
        for (int i = 1; i < 10; i++) {
            Ennemi ennemiTerminal = new Ennemi("ennemiTerminal", i, Type.NONE, Type.NONE);
        }
        for (int i = 1; i < 5; i++) {
            Tower towerTerminal = new Tower("towerTerminal", i, Type.NONE, Type.NONE);
        }

    }
}
