package model;

import config.Map;

public class MapState {

    private static double lastUpdate;
    private int[][] config;

    public MapState(Map map) {
        config = map.getConfig();
        timer();
    }

    public static void timer() {
        while (true) {
            if (System.currentTimeMillis() - lastUpdate >= 1000) {
                lastUpdate = System.currentTimeMillis();
                System.out.println(lastUpdate);
            }
        }
        // LE FONCTIONNEMENT DU JEU
    }

}
