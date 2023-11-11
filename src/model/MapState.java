package model;

import config.InitPokemon;
import java.util.ArrayList;
import characters.Ennemi;
import characters.Tower;
import config.Map;

public class MapState {

    private double lastUpdate;
    private int[][] config;
    private int money;
    private ArrayList<Tower> towerPresent = new ArrayList<Tower>();
    private ArrayList<Ennemi> ennemiPresent = new ArrayList<Ennemi>();

    public MapState(Map map) {
        config = map.getConfig();

        // timer();
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
                        for(Ennemi e: ennemiPresent){
                            if(i==e.getPos()[0] && j==e.getPos()[1]){
                                plateau[i][j]=Character.forDigit(e.getValue(), 10);
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
        for(int j=1; j < config[0].length+1; j=j+1){
            if(j<10){
                System.out.print("0" + j + " ");
            }else{
                System.out.print(j + " ");
            }
        }
        System.out.println();
        System.out.print("   ");
        for(int j=1; j < config[0].length+1; j=j+1){
            if(j<10){
                System.out.print("==");
            }else{
                System.out.print("==");
            }
            if(j!=config.length){
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

    public void move(Ennemi e) {
        config[e.getPos()[0]][e.getPos()[1]] = 3;

        if (config[e.getPos()[0]][e.getPos()[1] + 1] == 3 &&
                config[e.getPos()[0]][e.getPos()[1] - 1] == 3) {

            if (e.wasRight()) {
                int[] pos = { e.getPos()[0], e.getPos()[1] + 1 };
                e.setPos(pos);
                config[pos[0]][pos[1]] = 1;
            } else {
                int[] pos = { e.getPos()[0], e.getPos()[1] - 1 };
                e.setPos(pos);
                config[pos[0]][pos[1]] = 1;
            }
        } else if (config[e.getPos()[0] + 1][e.getPos()[1]] == 3) { // BAS
            int[] pos = { e.getPos()[0] + 1, e.getPos()[1] };
            e.setPos(pos);
            config[pos[0]][pos[1]] = 1;

        } else if (config[e.getPos()[0]][e.getPos()[1] + 1] == 3) { // DROITE
            int[] pos = { e.getPos()[0], e.getPos()[1] + 1 };
            e.setPos(pos);
            config[pos[0]][pos[1]] = 1;
            e.setWasRight(true);

        } else if (config[e.getPos()[0]][e.getPos()[1] - 1] == 3) { // GAUCHE
            int[] pos = { e.getPos()[0], e.getPos()[1] - 1 };
            e.setPos(pos);
            config[pos[0]][pos[1]] = 1;
            e.setWasRight(false);
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

            if (config[posX][posY] == 3) {
                config[posX][posY] = 1;
                ennemiPresent.add(ennemi);
                ennemi.setPos(posEnnemi);
            } else {
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
