package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import characters.Anchwatt;
import characters.Chartor;
import characters.Ecrapince;
import characters.Escargaume;
import characters.Gringolem;
import characters.Lilia;
import characters.Makuhita;
import characters.MotismaFroid;
import characters.Mysdibule;
import characters.Ningale;
import characters.Nodulithe;
import characters.Obalie;
import characters.Rozbouton;
import characters.Starter;
import characters.Tarsal;
import characters.Terhal;
import characters.Tylton;
import characters.Starter.Salameche;
import graphics.UpdateMap.Tiles;
import model.Pokemon.State;

public class Player {
    public static final Player INSTANCE = new Player();
    private String pseudo;
    private int money;
    private int lvl;
    private double exp;

    private ArrayList<Tower> obtainedPokemon; // utilite?

    private ArrayList<Hero> heroContract = new ArrayList<>();
    private Hero[] hero = new Hero[2];
    private Tower[] team = new Tower[6];
    private ArrayList<Tower> pc = new ArrayList<Tower>();

    private File file = new File("ressources/config/data.csv");

    private Player() {
        if (true) {
            this.pseudo = "Joueur";
            money = 1000;
            exp = 0;
            lvl = 1;
            obtainedPokemon = new ArrayList<>();
            pc = new ArrayList<>();
            Starter starterInstance = new Starter();
            Salameche sala0 = starterInstance.new Salameche(); // il doit choisir

            Salameche sala1 = starterInstance.new Salameche(); // il doit choisir
            Salameche sala2 = starterInstance.new Salameche(); // il doit choisir
            Salameche sala3 = starterInstance.new Salameche(); // il doit choisir
            Salameche sala4 = starterInstance.new Salameche(); // il doit choisir
            Salameche sala5 = starterInstance.new Salameche(); // il doit choisir
            Tower nothingTower0 = new Tower(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png",
                    State.TEAM);
            Tower nothingTower1 = new Tower(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png",
                    State.TEAM);
            Tower nothingTower2 = new Tower(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png",
                    State.TEAM);
            Tower nothingTower3 = new Tower(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png",
                    State.TEAM);
            Tower nothingTower4 = new Tower(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png",
                    State.TEAM);
            Tower nothingTower5 = new Tower(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png",
                    State.TEAM);
            hero[0] = null;
            hero[1] = null;

            team[0] = sala0;
            team[1] = new Anchwatt();
            team[2] = null;
            team[3] = null;
            team[4] = null;
            team[5] = null;
            // nothing tower
            // new Tower(" ", -1, -1, Types.NONE, Types.NONE,
            // "ressources/sprite/nothing.png", State.TEAM);
            // NOTHING skill
            // new Skill(" ", -1, -1, Types.NONE, -1, false, " ");
        } else {
            loadData();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            saveData();
        }));
    }

    private boolean createData() {
        try {
            boolean result = file.createNewFile();
            if (result) {
                // Nouveau Joueur
                FileWriter writer = new FileWriter(file, true);
                writer.append("Pseudo,Money,Level,Exp,Contrat_Hero,Team,Pc");
                writer.append("\n");
                writer.close();
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void saveData() {
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.append("Pseudo,Money,Level,Exp,Contrat_Hero,Pokemon,Pc");
            writer.append("\n");

            // Ecris les donnees
            String data = pseudo + "," + money + "," + lvl + "," + exp + "," + heroContract.toString() + "," +
                    arrayToString(team) + "," + pc.toString() + "\n";
            writer.append(data);
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
                result.append(" ;1;0.0;-1.0;-1.0;NONE;NONE;null#null#null#null#;ressources/sprite/nothing.png;");
            }
        }
        return result.toString();
    }

    private void loadData() {
        try (Scanner scanner = new Scanner(file)) {
            // Premiere ligne
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("[,]");

                pseudo = fields[0];
                money = Integer.parseInt(fields[1]);
                lvl = Integer.parseInt(fields[2]);
                exp = Double.parseDouble(fields[3]);
                // parseHeroContract (fields[4]);
                hero[0] = new Hero(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
                hero[1] = new Hero(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM);
                parseTeam(fields[5]);
                parsePc(fields[6]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseTeam(String teamData) {
        String[] teamArrayData = teamData.split(";");
        int teamLimit = team.length;
        int towerArg = 9;
        int i = 0;

        for (int j = 0; j < teamLimit; j = j + 1) {
            if (i < teamLimit) {
                if (teamArrayData[j * towerArg + 8].contains("ressources/sprite/nothing.png")) {
                    team[i] = new Tower(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png",
                            State.TEAM);
                } else {
                    String nameTower = teamArrayData[j * towerArg + 0]; // 0 - 9 - 18 - 27 - 36 - 45
                    int levelTower = Integer.parseInt(teamArrayData[j * towerArg + 1]); // 1 - 10 - 19 - 28 - 37 - 46
                    double expTower = Double.parseDouble(teamArrayData[j * towerArg + 2]); // 2 - 11 - 20 - 29 - 38 - 47
                    double physicalTower = Double.parseDouble(teamArrayData[j * towerArg + 3]); // 3 - 12 - 21 - 30 - 39
                                                                                                // - 48
                    double specialTower = Double.parseDouble(teamArrayData[j * towerArg + 4]); // 4 - 13 - 22 - 31 - 40
                                                                                               // - 49
                    Types firstTypeTower = Types.stringToType(teamArrayData[j * towerArg + 5]); // 5 - 14 - 23 - 32 - 41
                                                                                                // - 50
                    Types secondTypeTower = Types.stringToType(teamArrayData[j * towerArg + 6]); // 6 - 15 - 24 - 33 -
                                                                                                 // 42 - 51
                    String spriteTower = teamArrayData[j * towerArg + 8]; // 8 - 17 - 26 - 35 - 44 - 53

                    team[i] = new Tower(nameTower, physicalTower, specialTower, firstTypeTower, secondTypeTower,
                            spriteTower, State.TEAM);
                    team[i].setLevel(levelTower);
                    team[i].setExp(expTower);
                    parseSkill(teamArrayData[j * towerArg + 7], team[i]); // 7 - 16 - 25 - 34 - 43 - 52
                }
                i++;
            }
        }
    }

    private void parsePc(String pcData) {
        String[] pcArrayData = pcData.split(";");
        int towerArg = 9;

        if (pcArrayData.length > 1) {
            for (int j = 0; j < pc.size(); j = j + 1) {
                if (pcArrayData[j * towerArg + 8].contains("ressources/sprite/nothing.png")) {
                    pc.add(new Tower(" ", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM));
                } else {
                    String nameTower = pcArrayData[j * towerArg + 0]; // 0 - 9 - 18 - 27 - 36 - 45
                    int levelTower = Integer.parseInt(pcArrayData[j * towerArg + 1]); // 1 - 10 - 19 - 28 - 37 - 46
                    double expTower = Double.parseDouble(pcArrayData[j * towerArg + 2]); // 2 - 11 - 20 - 29 - 38 - 47
                    double physicalTower = Double.parseDouble(pcArrayData[j * towerArg + 3]); // 3 - 12 - 21 - 30 - 39 -
                                                                                              // 48
                    double specialTower = Double.parseDouble(pcArrayData[j * towerArg + 4]); // 4 - 13 - 22 - 31 - 40 -
                                                                                             // 49
                    Types firstTypeTower = Types.stringToType(pcArrayData[j * towerArg + 5]); // 5 - 14 - 23 - 32 - 41 -
                                                                                              // 50
                    Types secondTypeTower = Types.stringToType(pcArrayData[j * towerArg + 6]); // 6 - 15 - 24 - 33 - 42
                                                                                               // - 51
                    String spriteTower = pcArrayData[j * towerArg + 8]; // 8 - 17 - 26 - 35 - 44 - 53

                    Tower tmp = new Tower(nameTower, physicalTower, specialTower, firstTypeTower, secondTypeTower,
                            spriteTower, State.TEAM);
                    tmp.setLevel(levelTower);
                    tmp.setExp(expTower);
                    parseSkill(pcArrayData[j * towerArg + 7], tmp); // 7 - 16 - 25 - 34 - 43 - 52
                    pc.add(tmp);
                }
            }
        }
    }

    private void parseSkill(String skillData, Tower tower) {
        String[] skillArrayData = skillData.split("#");
        int skillLimit = tower.getSkills().length;
        int skillArg = 7;
        int i = 0;

        for (int j = 0; j < skillArg; j = j + 1) {
            if (i < skillLimit) {
                if (skillArrayData[i] == null) {
                    tower.learnSkill(new Skill(" ", -1, -1, Types.NONE, -1, false, " "));
                } else {
                    String skillName = skillArrayData[j * skillArg + 0]; // 0 - 7 - 14 - 21
                    int skillPhysicalDamage = Integer.parseInt(skillArrayData[j * skillArg + 1]); // 1 - 8 - 15 - 22
                    int skillSpecialDamage = Integer.parseInt(skillArrayData[j * skillArg + 2]); // 2 - 9 - 16 - 23
                    Types skillType = Types.stringToType(skillArrayData[j * skillArg + 3]); // 3 - 10 - 17 - 24
                    double skillSpeedAtk = Double.parseDouble(skillArrayData[j * skillArg + 4]); // 4 - 11 - 18 - 25
                    boolean skillUnique;
                    if (skillArrayData[j * skillArg + 5].contains("true")) { // 5 - 12 - 19 - 26
                        skillUnique = true;
                    } else {
                        skillUnique = false;
                    }
                    String skillDescription = skillArrayData[j * skillArg + 6]; // 6 - 13 - 19 - 27
                    tower.learnSkill(new Skill(skillName, skillPhysicalDamage, skillSpecialDamage, skillType,
                            skillSpeedAtk, skillUnique, skillDescription));
                }
                i++;
            }
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

    public void moveToPC(Tower t) {
        for (int i = 0; i < team.length; i++) {
            if (team[i] == t) {
                team[i] = null;
                break;
            }
        }
        t.setState(State.PC);
    }

    public void removeTeam(Tower t) {
        for (int i = 0; i < team.length; i++) {
            if (team[i] == t) {
                team[i] = null;
                break;
            }
        }
        // t.setState(State.PC);
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

    public void resetTeam() {
        for (Tower t : team) {
            removeTeam(t);
            if (t instanceof Starter.Salameche) {
                addteam(((Starter.Salameche) t).reset());
            }
            if (t instanceof Starter.Arcko) {
                addteam(((Starter.Arcko) t).reset());
            }
            if (t instanceof Starter.Moustillon) {
                addteam(((Starter.Moustillon) t).reset());
            }
            if (t instanceof Anchwatt) {
                addteam(((Anchwatt) t).reset());
            }
            if (t instanceof Obalie) {
                addteam(((Obalie) t).reset());
            }
            if (t instanceof Tylton) {
                addteam(((Tylton) t).reset());
            }
            if (t instanceof Terhal) {
                addteam(((Terhal) t).reset());
            }
            if (t instanceof Tarsal) {
                addteam(((Tarsal) t).reset());
            }
            if (t instanceof Rozbouton) {
                addteam(((Rozbouton) t).reset());
            }
            if (t instanceof Ningale) {
                addteam(((Ningale) t).reset());
            }
            if (t instanceof Nodulithe) {
                addteam(((Nodulithe) t).reset());
            }
            if (t instanceof Mysdibule) {
                addteam(((Mysdibule) t).reset());
            }
            if (t instanceof MotismaFroid) {
                addteam(((MotismaFroid) t).reset());
            }
            if (t instanceof Makuhita) {
                addteam(((Makuhita) t).reset());
            }
            if (t instanceof Lilia) {
                addteam(((Lilia) t).reset());
            }
            if (t instanceof Gringolem) {
                addteam(((Gringolem) t).reset());
            }
            if (t instanceof Escargaume) {
                addteam(((Escargaume) t).reset());
            }
            if (t instanceof Ecrapince) {
                addteam(((Ecrapince) t).reset());
            }
            if (t instanceof Chartor) {
                addteam(((Chartor) t).reset());
            }
        }
    }
}
