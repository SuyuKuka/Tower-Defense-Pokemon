package model;

import java.util.ArrayList;
import model.Hero;

public class Player {

    private String pseudo;
    private int money;
    private double exp;
    private int lvl;
    private ArrayList<Pokemon> obtainedPokemon;
    private Pokemon[] team = new Pokemon[6];

    // private ArrayList<Hero> heroContract = new ArrayList<>();

    public Player() {
        this.pseudo = "Joueur";
        money = 1000;
        exp = 0;
        lvl = 1;
        obtainedPokemon = new ArrayList<>();
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getMoney() {
        return money;
    }

    public int getLvl() {
        return lvl;
    }

    public double getExp() {
        return exp;
    }

    public Pokemon[] getTeam() {
        return team;
    }

    public ArrayList<Pokemon> getObtainedPokemon() {
        return obtainedPokemon;
    }

    public void addteam(Tower t, int pos) { // temporaire ptet
        if (pos > 6) {
            System.out.println("invalide");
        } else {
            team[pos] = t;
        }
    }
}
