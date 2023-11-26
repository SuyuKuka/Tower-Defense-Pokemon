package config;

import model.Ennemi;

import java.util.ArrayList;
import java.util.Random;

public class InitWaves {

    Random random = new Random();

    private ArrayList<Ennemi> ennemisTerminal = new ArrayList<>();

    private ArrayList<ArrayList<Ennemi>> Vague = new ArrayList<>();

    public InitWaves() { // CREER UNE WAVE ET LA RANDOMISE

        int floor = 1; // de + en + d'ennemi fort
        for (int j = 0; j < 6; j++) {
            int rand = random.nextInt(3);
            ennemisTerminal.add(Ennemi.LIST.getList().get(j));
            ennemisTerminal.get(j).setValue(rand + floor);
        }
        for (int j = 6; j < 25; j++) {
            int rand = random.nextInt(6);
            ennemisTerminal.add(Ennemi.LIST.getList().get(j));
            ennemisTerminal.get(j).setValue(rand + floor);
        }
        for (int j = 25; j < 56; j++) {
            int rand = random.nextInt(9);
            ennemisTerminal.add(Ennemi.LIST.getList().get(j));
            ennemisTerminal.get(j).setValue(rand + floor);
        }
    }

    public ArrayList<Ennemi> getEnnemisListStandby() {
        return ennemisTerminal;
    }
}
