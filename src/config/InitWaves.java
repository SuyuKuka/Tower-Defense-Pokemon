package config;

import characters.Ennemi;

import java.util.ArrayList;
import java.util.Random;

public class InitWaves {

    private static int count;
    private final int wave;

    Random random = new Random();

    private ArrayList<Ennemi> ennemisTerminal = new ArrayList<>();

    public InitWaves(int numberE) { // CREER UNE WAVE ET LA RANDOMISE
        count++;
        wave = count;
        int floor = 1; // de + en + d'ennemi fort
        for (int j = 0; j < numberE; j++) {
            int rand = random.nextInt(numberE);
            ennemisTerminal.add(Ennemi.LIST.getList().get(j));
            ennemisTerminal.get(j).setValue(rand + floor);
        }
    }

    public ArrayList<Ennemi> getEnnemisListStandby() {
        return ennemisTerminal;
    }
}
