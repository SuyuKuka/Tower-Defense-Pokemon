package model;

import java.util.ArrayList;
import model.Hero;
import model.Pokemon.State;

public class Player {

    private String pseudo;
    private int money;
    private double exp;
    private int lvl;

    private ArrayList<Tower> obtainedPokemon;

    private ArrayList<Tower> pc;
    private Tower[] team = new Tower[6];

    // private ArrayList<Hero> heroContract = new ArrayList<>();

    public Player() {
        this.pseudo = "Joueur";
        money = 1000;
        exp = 0;
        lvl = 1;
        obtainedPokemon = new ArrayList<>();
        pc = new ArrayList<>();
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

    public Tower[] getTeam() {
        return team;
    }

    public ArrayList<Tower> getObtainedPokemon() {
        return obtainedPokemon;
    }

    public ArrayList<Tower> getPc() {
        return pc;
    }

    public void addteam(Tower t) { // temporaire ptet
        int lastPos = -1;
        for (int i = 0; i < team.length; i++) {
            if (team[i] == null) {
                lastPos = i;
                break;
            }
        }
        if (lastPos == -1) {
            System.out.println("invalide");
        } else {
            t.setState(State.TEAM);
            team[lastPos] = t;
        }
    }

    public void removeTeam(Tower t) {
        for (int i = 0; i < team.length; i++) {
            if (team[i] == t) {
                team[i] = null;
                break;
            }
        }
        t.setState(State.PC);
    }

}
