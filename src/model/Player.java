package model;

import java.util.ArrayList;

public class Player {

    private String name;
    private int money;
    private double exp;
    private int lvl;
    private ArrayList<Pokemon> obtainedPokemon;
    private Pokemon[] team = new Pokemon[6];

    private ArrayList<Hero> heroContract = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        money = 1000;
        exp = 0;
        lvl = 1;
        obtainedPokemon = new ArrayList<>();
    }

}
