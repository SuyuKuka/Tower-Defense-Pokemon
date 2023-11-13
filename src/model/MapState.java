package model;

import config.InitPokemon;
import config.InitWaves;

import java.util.ArrayList;
import characters.Ennemi;
import characters.Tower;
import config.Map;
import java.util.Scanner;

public class MapState {
    private boolean terminal;
    private Map m;

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

    public void startTerminal() {

        InitPokemon.loadingCharTerminal();
        InitWaves w = new InitWaves(9);
        System.out.println(
                "===============================================================================================================");
        System.out.println(
                "Pour commencer à jouer, il faut placer une tours aux endroits qui contiennent des + sous la forme de coordonées");
        System.out.println(
                " - Exemple : F4 ou F04");
        System.out.println(
                "Le jeu se jouera tout seul après avoir placer les 4 tourelles");
        System.out.println(
                "Vous avez 3 vies");
        System.out.println(
                "===============================================================================================================");

        System.out.println();

        Scanner sc = new Scanner(System.in);

        int pas = 0;

        int nbTour = 4;
        int i = 0;
        int pv = 3;
        boolean leaked = false;
        while (pv != 0) {
            affiche(m);
            for (Ennemi e : ennemiPresent) { // FAIT SE DEPLACER LES ENNEMIS
                if (e.move()) {
                    leaked = true;
                }
            }
            if (leaked && !ennemiPresent.isEmpty()) { // SI UN ENNEMI PASSE
                ennemiPresent.remove(i);
                leaked = false;
                pv--;
            }
            if (pas == 5) { // TOUT LES 5 ACTUALISATIONS UN NOUVEAU ENNEMIE ENTRE
                if (!w.getEnnemisListStandby().isEmpty()) {
                    setEnnemi(m.getEntry(), w.getEnnemisListStandby().get(0));
                    w.getEnnemisListStandby().remove(0);
                    pas = 0;
                }
            }
            pas++;
            Tower terminal = new Tower("Terminal", 1, null, null);

            if (nbTour != 0) { // SI IL RESTE DES TOURS A PLACER
                System.out.print("Ajouter une tour  (" + nbTour + "): ");
                String st = sc.nextLine();
                if (st.length() != 0) {

                    setTower(terminal.putTower(st), terminal);
                    nbTour--;
                }
            } else {
                System.out.println("Aucune tours restantes");
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            terminal.attaque(towerPresent, ennemiPresent, this);
            System.out.println("Ennemis en attente: " + w.getEnnemisListStandby().size());
            System.out.println("Ennemis sur le terrain: " + ennemiPresent.size());
            System.out.println("PV: " + pv);
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

    public char[][] intToChar(int[][] map) {
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
                                plateau[i][j] = (char) (e.getValue() + 48);
                            }
                        }
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

    public void setTower(int[] posTower, Tower tower) { // MANUELLEMENT
        if (posTower[0] == -1 || posTower[1] == -1) {
            return;
        } else {
            int posX = posTower[0];
            int posY = posTower[1];

            if (config[posX][posY] == 0) {
                config[posX][posY] = 2;
                towerPresent.add(tower);
                tower.setPos(posTower);
            }
        }
    }

    public void setEnnemi(int[] posEnnemi, Ennemi ennemi) { // MANUELLEMENT
        if (posEnnemi[0] == -1 || posEnnemi[1] == -1) {

            return;
        } else {
            int posX = posEnnemi[0];
            int posY = posEnnemi[1];

            if (config[posX][posY] == 3 || config[posX][posY] == 4) {
                config[posX][posY] = 1;
                ennemiPresent.add(ennemi);
                ennemi.setPos(posEnnemi);
            } else {
                return;
            }
        }
    }

    public void start() {

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
