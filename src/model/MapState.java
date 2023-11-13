package model;

import config.InitPokemon;
import config.InitWaves;

import java.util.ArrayList;
import characters.Ennemi;
import characters.Tower;
import config.Map;
import java.util.Scanner;

import java.util.Timer;
import java.util.TimerTask;

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

        // timer();
    }

    public void startTerminal() {

        InitPokemon.loadingCharTerminal();
        InitWaves w = new InitWaves(3);

        System.out.println(
                "Pour commencer à jouer, il faut placer une tours aux endroits qui contiennent des + sous la forme de coordonées");
        System.out.println("Exemple : F4 ou F04");

        Scanner sc = new Scanner(System.in);

        affiche(m);

        Timer timer = new Timer();
        TimerTask update = new TimerTask() {
            public void run() {
                for (Ennemi e : ennemiPresent) {
                    e.move();
                }
                System.out.println("IL Y A :" + getEnnemiPresent().size());
                affiche(m);
            }
        };
        timer.scheduleAtFixedRate(update, 0, 1000);
        Timer timerE = new Timer();

        TimerTask ennemies = new TimerTask() {
            int i = 0;

            public void run() {
                if (i != Ennemi.LIST.getList().size()) {
                    setEnnemi(m.getEntry(), Ennemi.LIST.getList().get(i));
                    i++;
                }

            }

        };
        timerE.scheduleAtFixedRate(ennemies, 0, 5000);

        sc.close();
    }

    public void start() {

    }

    public char[][] intToChar(int[][] map) {
        char[][] plateau = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case 0:
                        plateau[i][j] = '+';
                        break;
                    case 1:
                        for (Ennemi e : ennemiPresent) {
                            if (i == e.getPos()[0] && j == e.getPos()[1]) {
                                plateau[i][j] = Character.forDigit(e.getValue(), 10);
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
                        plateau[i][j] = '/';
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

    public void timer() {
        while (true) { // TODO: A CHANGER
            if (System.currentTimeMillis() - lastUpdate >= 1000) { // 1000 = 1s a changer si nécessaire
                lastUpdate = System.currentTimeMillis();
                money += 1;
                // System.out.println(money);
            }
            // LE FONCTIONNEMENT DU JEU

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
                System.out.println('e');
                return;
            }
        }
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
