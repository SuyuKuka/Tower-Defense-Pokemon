package config;

import characters.Ennemi;

import java.util.ArrayList;
import java.util.Random;

public class InitWaves {

    private static int count;
    private final int wave;

    Random random = new Random();

    private ArrayList<Ennemi> ennemisTerminal = new ArrayList<>();

    public InitWaves(int numberE) { // numberE: NOMBRE D'ENNEMI DE DEPART
        count++;
        wave = count;
        int floor = 0; // de + en + d'ennemi fort
        for (int j = 0; j < numberE; j++) {
            int rand = random.nextInt(numberE);
            ennemisTerminal.add(Ennemi.LIST.getList().get(rand + floor));
        }
    }
}
