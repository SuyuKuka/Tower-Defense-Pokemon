package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import characters.Starter;
import characters.Starter.Salameche;
import model.Hero;
import model.Pokemon.State;

public class Player {
    private String pseudo;
    private int money;
    private int lvl;
    private double exp;

    private ArrayList<Tower> obtainedPokemon; //utilite?
   
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
            Salameche sala0 = starterInstance.new Salameche("arazf", 20, 40, Types.FEU, Types.NONE); //il doit choisir
            Salameche sala1 = starterInstance.new Salameche("kaka", 20, 40, Types.FEU, Types.NONE); //il doit choisir
            Salameche sala2 = starterInstance.new Salameche("zara", 20, 40, Types.FEU, Types.NONE); //il doit choisir
            Salameche sala3 = starterInstance.new Salameche("fzaq", 20, 40, Types.FEU, Types.NONE); //il doit choisir
            Salameche sala4 = starterInstance.new Salameche("vdqvs", 20, 40, Types.FEU, Types.NONE); //il doit choisir
            Salameche sala5 = starterInstance.new Salameche("gaqgj", 20, 40, Types.FEU, Types.NONE); //il doit choisir
            hero[0] = new Hero(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
            hero[1] = new Hero(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
            team[0] = sala0;
            team[1] = sala1;
            team[2] = sala2;
            team[3] = sala3;
            team[4] = sala4;
            team[5] = sala5;
            //new Tower(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
            for(Tower tower : team){
                System.out.println(tower.toString());
            }
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
            writer.append("Pseudo,Money,Level,Exp,Contrat_Hero,Pokemon,Pc");
            writer.append("\n");

            //ecris les donnees
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

   private void loadData() {
        try (Scanner scanner = new Scanner(file)) {
            // Ignore la premiere ligne (en-tête)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Si la ligne suivante existe, analyse les donnees
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("[,]");

                // Assurez-vous que le nombre de champs est correct
                if (fields.length == 7) {
                    pseudo = fields[0];
                    money = Integer.parseInt(fields[1]);
                    lvl = Integer.parseInt(fields[2]);
                    exp = Double.parseDouble(fields[3]);

                    // Contrat_Hero (champ 4) peut necessiter un traitement special
                    // Vous devrez implementer la logique en fonction de la structure reelle des donnees
                    // Dans cet exemple, nous laissons Contrat_Hero tel quel

                    parseTeam(fields[5]);
                    parsePc(fields[6]);
                } else {
                    System.out.println("Invalid data format in the file.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseTeam(String teamData) {
        String[] pokemonDataArray = teamData.split(";");
        
        int teamLimit = 6; // Limite du nombre de Pokemon dans l'equipe
        for (int i = 0; i < pokemonDataArray.length; i += 9) {
            if (i / 9 < teamLimit) {
                // Ajoute à l'equipe
                addTeamFromData(pokemonDataArray, i);
            } else {
                // Ajoute au PC
                addPokemonToPC(pokemonDataArray);
            }
        }
    }
    
    private void addTeamFromData(String[] pokemonDataArray, int i) {
        String nameTower = pokemonDataArray[0];
        int levelTower = Integer.parseInt(pokemonDataArray[1]);
        double expTower = Double.parseDouble(pokemonDataArray[2]);
        double physicalTower = Double.parseDouble(pokemonDataArray[3]);
        double specialTower = Double.parseDouble(pokemonDataArray[4]);
        Types firstTypeTower = Types.stringToType(pokemonDataArray[5]);
        Types secondTypeTower = Types.stringToType(pokemonDataArray[6]);
        parseSkill(pokemonDataArray[7]);
        String spriteTower = pokemonDataArray[8];

        team[i] = new Tower(nameTower, physicalTower, specialTower, firstTypeTower, secondTypeTower, spriteTower, State.TEAM);
        team[i].setLevel(levelTower);
        team[i].setExp(expTower);
    }
    
    private void addPokemonToPC(String[] pokemonDataArray) {
        String nameTower = pokemonDataArray[0];
        int levelTower = Integer.parseInt(pokemonDataArray[1]);
        double expTower = Double.parseDouble(pokemonDataArray[2]);
        double physicalTower = Double.parseDouble(pokemonDataArray[3]);
        double specialTower = Double.parseDouble(pokemonDataArray[4]);
        Types firstTypeTower = Types.stringToType(pokemonDataArray[5]);
        Types secondTypeTower = Types.stringToType(pokemonDataArray[6]);
        parseSkill(pokemonDataArray[7]);
        String spriteTower = pokemonDataArray[8];
        Tower tower = new Tower(nameTower, levelTower, expTower, firstTypeTower, secondTypeTower, spriteTower, State.PC);
        tower.setLevel(levelTower);
        tower.setExp(expTower);

        pc.add(tower);
    }

    private void parsePc(String pcData) {

    }

    private void parseSkill(String skillData, int i){
        String[] towerSkillData = skillData.split("#");

        String skillName = towerSkillData[0];
        int skillPhysicalDamage = Integer.parseInt(towerSkillData[1]);
        int skillSpecialDamage = Integer.parseInt(towerSkillData[2]);
        Types skillType = Types.stringToType(towerSkillData[3]);
        double skillSpeedAtk = Double.parseDouble(towerSkillData[4]);
        Effect skillEffect = null;
        boolean skillUnique = 
        
        public String toString(){
            return this.getName()
            +"#"+this.getType()
            +"#"+this.getPhysicalDamage() 
            +"#"+this.getSpecialDamage() 
            +"#"+this.getSpeedAtk()
            +"#"+this.getUnique()
            +"#"+this.getDescription();
        }

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
