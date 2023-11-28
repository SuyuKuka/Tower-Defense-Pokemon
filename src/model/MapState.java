package model;

import config.InitPokemon;
import config.InitWaves;

import java.util.ArrayList;
import model.Ennemi;
import model.Tower;
import config.Map;
import java.util.Scanner;
import java.util.Random;

public class MapState {
    private boolean terminal;
    private Map m;
    private Random rand = new Random();
    private double lastUpdate;
    private int[][] config;
    private int money;
    private ArrayList<Tower> towerPresent = new ArrayList<Tower>();
    private ArrayList<Ennemi> ennemiPresent = new ArrayList<Ennemi>();

    public MapState(boolean terminal) {
        Map map = new Map(20, 20);
        m = map;
        config = m.getConfig();
    }

    private void infoTerminal() {
        System.out.println(
                "===============================================================================================================");
        System.out.println(
                "Pour commencer à jouer, il faut placer une tours aux endroits qui contiennent des + sous la forme de coordonées");
        System.out.println(
                " - Exemple : F4 ou F04");
        System.out.println(
                "Le jeu se jouera tout seul après avoir placer les 4 tourelles");
        System.out.println(
                "Vous avez 3 vies, il y a 3 vagues");
        System.out.println(
                "Si un ennemie passe sur la sortis S, vous perdez une vie, a 0 vie la partie est perdu");
        System.out.println(
                "===============================================================================================================");
    }

    public void startTerminal() {

        InitPokemon.loadingCharTerminal();
        InitWaves w = new InitWaves();
        Scanner sc = new Scanner(System.in);

        int nbTour = 4; // AJUSTABLE
        int pv = 3; // AJUSTABLE

        int pas = 0;
        boolean leaked = false;
        int frequenceApparition = 0;

        int[] pasi = { 4, 11 };

        infoTerminal();
        while (pv != 0) {
            System.out.println("\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println("Ennemis en attente: " + w.getEnnemisListStandby().size());
            System.out.println("Ennemis sur le terrain: " + ennemiPresent.size());
            System.out.println("FREQ: " + frequenceApparition);
            System.out.println("PV: " + pv);
            System.out.println();
            affiche(m);
            System.out.println();

            for (Ennemi e : ennemiPresent) { // DEPLACE LES ENNEMIS
                if (e.move()) { // SI LA PROCHAINE CASE EST LA SORTIS
                    leaked = true;
                }
            }
            if (leaked && !ennemiPresent.isEmpty()) { // SI UN ENNEMI PASSE
                ennemiPresent.remove(0);
                leaked = false;
                pv--;
            }
            if (pas == frequenceApparition) { // VARIE LA FREQUENCE D'APPARITION DES ENNEMIS
                if (!w.getEnnemisListStandby().isEmpty()) {
                    frequenceApparition = rand.nextInt(8) + 1; // PLUS LE BOUND EST PETIT + C FAST
                    setEnnemi(m.getEntry(), w.getEnnemisListStandby().get(0));
                    w.getEnnemisListStandby().remove(0);
                    pas = 0;
                }
            }
            pas++;
            Tower terminal = new Tower("Terminal", 1,1, null, null);
            if (nbTour != 0) { // SI IL RESTE DES TOURS A PLACER
                System.out.print("Ajouter une tour  (" + nbTour + "): ");
                String st = sc.nextLine();
                if (st.length() != 0) {
                    if (setTower(terminal.putTower(st), terminal)) {
                        nbTour--;
                    }
                }
            } else {
                System.out.println("Aucune tours restantes");
                try {
                    Thread.sleep(250); // VITESSE DE JEU AUTO
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            terminal.attack(towerPresent, ennemiPresent, this);

            if (w.getEnnemisListStandby().size() == 0 && ennemiPresent.size() == 0) { // SI IL N'Y A PLUS D'ENNEMIS DANS
                                                                                      // LA PARTIE
                System.out.println("VOUS AVEZ GAGNEE");
                break;
            }
        }
        if (pv == 0) {
            System.out.println("VOUS AVEZ PERDU");
        }
        sc.close();
    }

    private char[][] intToChar(int[][] map) {
        char[][] plateau = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case 0:
                        plateau[i][j] = '.';
                        break;
                    case 1:
                        plateau[i][j] = ' ';
                        for (Ennemi e : ennemiPresent) {
                            if (i == e.getPos()[0] && j == e.getPos()[1]) {
                                plateau[i][j] = (char) (e.getPv() + 48);
                            }
                        }
                        // plateau[i][j] = (char) 4;//PV CACHEE COMMENT THIS LINE TO REVEAL

                        break;
                    case 2:
                        plateau[i][j] = 'T';
                        break;
                    case 3:
                        plateau[i][j] = ' ';
                        break;
                    case 4:
                        plateau[i][j] = 'E';
                        break;
                    case 5:
                        plateau[i][j] = 'S';
                        break;
                    case 6:
                        plateau[i][j] = '/';
                        break;
                    case 9:
                        plateau[i][j] = (char) 5;
                        break;
                    default:
                        break;
                }
            }
        }
        return plateau;
    }

    public void affiche(Map map) {
        System.out.print("   ");
        for (int j = 1; j < config[0].length + 1; j = j + 1) {
            if (j < 10) {
                System.out.print("0" + j + " ");
            } else {
                System.out.print(j + " ");
            }
        }
        System.out.println();
        System.out.print("   ");
        for (int j = 1; j < config[0].length + 1; j = j + 1) {
            if (j < 10) {
                System.out.print("==");
            } else {
                System.out.print("==");
            }
            if (j != config.length) {
                System.out.print("=");
            }
        }
        System.out.println();
        for (int i = 0; i < config.length; i++) {
            System.out.print((char) ('A' + i) + "|| ");
            for (int j = 0; j < config[0].length; j++) {
                System.out.print(intToChar(config)[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public boolean setTower(int[] posTower, Tower tower) { // MANUELLEMENT
        if (posTower[0] == -1 || posTower[1] == -1) {
            return false;
        } else {
            int posX = posTower[0];
            int posY = posTower[1];

            if (config[posX][posY] == 0) {
                config[posX][posY] = 2;
                towerPresent.add(tower);
                tower.setPos(posTower);
                return true;
            }
            return false;
        }
    }

    public void setEnnemi(int[] posEnnemi, Ennemi ennemi) { // MANUELLEMENT
        if (posEnnemi[0] == -1 || posEnnemi[1] == -1) {

            return;
        } else {
            int posX = posEnnemi[0];
            int posY = posEnnemi[1];

            if (config[posX][posY] == 3 || config[posX][posY] == 4 || config[posX][posY] == 1) {
                config[posX][posY] = 1;
                ennemiPresent.add(ennemi);
                ennemi.setPos(posEnnemi);
            } else {
                return;
            }
        }
    }

    public void start() {
        // GRAPHIC
    }

    public void isDead(Ennemi e) {
        ennemiPresent.remove(e);
        int[] pos = e.getPos();
        config[pos[0]][pos[1]] = 3;
    }

    public ArrayList<Ennemi> getEnnemiPresent() {
        return ennemiPresent;
    }

    public ArrayList<Tower> getTowerPresent() {
        return towerPresent;
    }
}
