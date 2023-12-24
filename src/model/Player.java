package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import characters.Starter;
import characters.Starter.Salameche;
import model.Hero;
import model.Pokemon.State;

public class Player {
    private String pseudo;
    private int money;
    private int lvl;
    private double exp;

    private ArrayList<Tower> obtainedPokemon; //utilité?
   
    private ArrayList<Hero> heroContract = new ArrayList<>();
    private Hero[] hero = new Hero[2];
    private Tower[] team = new Tower[6];
    private ArrayList<Tower> pc;

    public static final Player INSTANCE = new Player();
    private File file = new File("ressources/config/data.csv");

    public Player() {
        if(createData()){
            this.pseudo = "Joueur";
            money = 1000;
            exp = 0;
            lvl = 1;
            obtainedPokemon = new ArrayList<>();
            pc = new ArrayList<>();
            Starter starterInstance = new Starter();
            Salameche sala = starterInstance.new Salameche("Salameche", 20, 40, Types.FEU, Types.NONE); //il doit choisir
            hero[0] = new Hero("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
            hero[1] = new Hero("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
            team[0] = sala;
            team[1] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
            team[2] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
            team[3] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
            team[4] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
            team[5] = new Tower("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
            hero[0] = new Hero("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
            hero[1] = new Hero("", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
        }else{
            loadData();
        }
        saveData();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Saving player data before shutting down...");

            saveData();
        }));
    }

    private boolean createData(){
        boolean result;  
        try {  
            result = file.createNewFile();
            if(result){
                //Nouveau Joueur
                System.out.println("file created "+file.getCanonicalPath());
                FileWriter writer = new FileWriter(file, true);
                writer.append("Pseudo,Money,Level,Exp,Contrat_Hero,Team,Pc");
                writer.append("\n");
                writer.close();

                System.out.println("Data written to file successfully.");
            }else{
                //Ancien Joueur
                System.out.println("File already exist at location: " + file.getCanonicalPath());  
            }
            return result;
        } catch (IOException e){  
            e.printStackTrace();
        }
        return false;
    }

    public void saveData(){
        try (FileWriter writer = new FileWriter(file, false)) {
            // Écrit l'en-tête du fichier
            writer.append("Pseudo,Money,Level,Exp,Contrat_Hero,Pokemon,Pc");
            writer.append("\n");

            // Écrit les données du joueur
            String data = pseudo + "," + money + "," + lvl + "," + exp + "," + heroContract.toString() + "," +
                    arrayToString(team) + "," + pc.toString() + "\n";
            writer.append(data);

            System.out.println("Player data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String arrayToString(Tower[] array) {
        StringBuilder result = new StringBuilder();
        for (Tower tower : array) {
            if (tower != null) {
                result.append(tower.toString()).append(";");
            } else {
                result.append("null,");
            }
        }
        return result.toString();
    }

    private void loadData(){

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
