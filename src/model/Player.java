package model;

import java.util.ArrayList;
import model.Hero;

public class Player {

    private String pseudo;
    private int money;
    private double exp;
    private int lvl;
    private ArrayList<Pokemon> obtainedPokemon;
    private Tower[] team = new Tower[6];

    private ArrayList<Hero> heroContract = new ArrayList<>();
    private Tower[] hero = new Tower[2];

    public static final Player INSTANCE = new Player();

    public Player() {
        this.pseudo = "Joueur";
        money = 1000;
        exp = 0;
        lvl = 1;
        obtainedPokemon = new ArrayList<>();
        team[0] = new Tower("Salameche", 100000, 0, Types.FEU, Types.NONE,
                "ressources/sprite/charmander.png");
        team[1] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png");
        team[2] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png");
        team[3] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png");
        team[4] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png");
        team[5] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png");
        hero[0] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png");
        hero[1] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png");
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

    public ArrayList<Pokemon> getObtainedPokemon() {
        return obtainedPokemon;
    }

    public Tower[] getHero() {
        return hero;
    }

    public ArrayList<Hero> getHeroContract() {
        return heroContract;
    }

    // TODO
    public void setPseudo(String pseudo) throws Exception {

    }
}
