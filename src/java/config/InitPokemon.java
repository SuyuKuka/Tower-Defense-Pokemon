package config;

import java.util.Scanner;

public class InitPokemon {

    private static Scanner sc;

    public static void loadingChar() {
        System.out.println("dqsd");
        System.out.println(InitPokemon.class.getResourceAsStream("PokemonLoader.txt"));
        InitPokemon.sc = new Scanner(InitPokemon.class.getResourceAsStream("PokemonLoader.txt"));
        while (sc.hasNext()) {
            String[] tab = new String[7];

            tab = sc.next().split(",");
        }
    }
}
