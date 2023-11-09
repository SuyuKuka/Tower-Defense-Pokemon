package model;

import config.Map;

public class MapState {
    private double lastUpdate;
    private int[][] config;
    private int money;

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

    public void setTower(int[] posTower) {
        if (posTower[0] == -1 || posTower[1] == -1) {
            return;
        } else {
            int posX = posTower[0];
            int posY = posTower[1];

            if (config[posX][posY] == 0) {
                config[posX][posY] = 2;
            }
        }
    }
}
