package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import model.Tower.Rayon;
import model.Tower.State;

public class Player {
    public static final Player INSTANCE = new Player();
    private String pseudo;
    private int lvl;
    private double exp;
    private double expLvlUp;

    private Tower[] team = new Tower[6];
    private Pc pc = Pc.INSTANCE;

    private File file = new File("ressources/config/data.csv");

    private Player() {
        if (createData()) {
            this.pseudo = "Joueur";
            exp = 0;
            lvl = 1;
        } else {
            loadData();
        }
        //Initialisation de la team
        team[1] = new Chartor();
        team[0] = new Anchwatt();
        team[2] = new Tylton();
        team[3] = new Gringolem();
        //team[4] = new Lilia();
        team[5] = new Starter().new Arcko();

        for (int i = 0; i < team.length; i = i + 1) {
            if (team[i] != null) {
                team[i].setState(State.TEAM);
            } else {
                team[i] = new Tower("nothing", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM, -1, Rayon.NONE, -1);
            }
        }

        resetTeam();
        pc.setTeam(team);

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
                writer.append("Pseudo,Level,Exp");
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
            writer.append("Pseudo,Level,Exp");
            writer.append("\n");

            // Ecris les donnees
            String data = pseudo + "," + lvl + "," + exp + "\n";
            writer.append(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                lvl = Integer.parseInt(fields[1]);
                exp = Double.parseDouble(fields[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double expCap() {
        expLvlUp = lvl * 100;
        return expLvlUp;
    }

    public void levelUp() {
        double cap = expCap();
        if (cap <= exp) {
            lvl++;
            exp -= cap;
        }
    }

    public String getPseudo() {
        return pseudo;
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

    public Pc getPc() {
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
                team[i] = new Tower("nothing", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.TEAM, -1, Rayon.NONE, -1);
                break;
            }
        }
    }

    public void resetTeam() {
        for (Tower t : team) {
            if (t instanceof Starter.Salameche) {
                ((Starter.Salameche) t).reset();
            }
            if (t instanceof Starter.Arcko) {
                ((Starter.Arcko) t).reset();
            }
            if (t instanceof Starter.Moustillon) {
                ((Starter.Moustillon) t).reset();
            }
            if (t instanceof Anchwatt) {
                ((Anchwatt) t).reset();
            }
            if (t instanceof Obalie) {
                ((Obalie) t).reset();
            }
            if (t instanceof Tylton) {
                ((Tylton) t).reset();
            }
            if (t instanceof Terhal) {

                ((Terhal) t).reset();
            }
            if (t instanceof Tarsal) {

                ((Tarsal) t).reset();
            }
            if (t instanceof Rozbouton) {

                ((Rozbouton) t).reset();
            }
            if (t instanceof Ningale) {
                ((Ningale) t).reset();
            }
            if (t instanceof Nodulithe) {
                ((Nodulithe) t).reset();
            }
            if (t instanceof Mysdibule) {
                ((Mysdibule) t).reset();
            }
            if (t instanceof MotismaFroid) {
                ((MotismaFroid) t).reset();
            }
            if (t instanceof Makuhita) {
                ((Makuhita) t).reset();
            }
            if (t instanceof Lilia) {
                ((Lilia) t).reset();
            }
            if (t instanceof Gringolem) {
                ((Gringolem) t).reset();
            }
            if (t instanceof Escargaume) {
                ((Escargaume) t).reset();
            }
            if (t instanceof Ecrapince) {
                ((Ecrapince) t).reset();
            }
            if (t instanceof Chartor) {
                ((Chartor) t).reset();
            }

        }

    }

    public void setExp(double exp) {
        this.exp = exp;
    }
}
