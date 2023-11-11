package model;

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

    public void timer() {
        while (true) {
            if (System.currentTimeMillis() - lastUpdate >= 1000) { // 1000 = 1s a changer si nécessaire
                lastUpdate = System.currentTimeMillis();
                money += 1;
                // System.out.println(money);
            }
            // LE FONCTIONNEMENT DU JEU

        }
    }

    public void setTower(int[] posTower, Tower tower) {
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

    public void setEnnemi(int[] posEnnemi, Ennemi ennemi) {
        if (posEnnemi[0] == -1 || posEnnemi[1] == -1) {
            return;
        } else {
            int posX = posEnnemi[0];
            int posY = posEnnemi[1];

            if (config[posX][posY] == 3) {
                config[posX][posY] = 1;
                ennemiPresent.add(ennemi);
                ennemi.setPos(posEnnemi);
            }
        }
    }

    public void isDead(Ennemi e){
        ennemiPresent.remove(e);
        int[] pos = e.getPos();
        config[pos[0]][pos[1]] = 3;
    }

    public ArrayList<Ennemi> getEnnemiPresent(){
        return ennemiPresent;
    }

    public ArrayList<Tower> getTowerPresent() {
        return towerPresent;
    }
}
