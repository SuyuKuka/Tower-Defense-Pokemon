package model;

import java.util.ArrayList;

import characters.Starter;
import characters.Starter.Salameche;
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

    private ArrayList<Hero> heroContract = new ArrayList<>();
    private Tower[] hero = new Tower[2];

    public static final Player INSTANCE = new Player();

    public Player() {
        this.pseudo = "Joueur";
        money = 1000;
        exp = 0;
        lvl = 1;
        obtainedPokemon = new ArrayList<>();
        pc = new ArrayList<>();
        Starter starterInstance = new Starter();
        Salameche sala = starterInstance.new Salameche("Salameche", 10, 40, Types.FEU, Types.NONE);
        team[0] = sala;
        team[1] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
        team[2] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
        team[3] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
        team[4] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
        team[5] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
        hero[0] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
        hero[1] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
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
