package model;

import java.util.ArrayList;
import java.util.Random;

public class InitWaves {

    public enum Difficulty {
        EASY, NORMAL, HARD
    }

    private int nbTotalEnnemi;
    private int multiplier;

    Random random = new Random();

    private ArrayList<ArrayList<Ennemi>> vague = new ArrayList<>();

    public InitWaves(Difficulty diff) { // CREER UNE WAVE ET LA RANDOMISE

        difficultyAjust(diff);

        int limite = 3;
        ArrayList<Ennemi> ennemisTerminal = new ArrayList<>();
        System.out.println(nbTotalEnnemi);
        for (int i = 0; i < nbTotalEnnemi; i++) {

            int randBoss = random.nextInt(Ennemi.LIST.getBossList().size());
            int randChar = random.nextInt(i + 1);

            while (randChar >= 22 || Ennemi.LIST.getList().get(randChar).isBoss()) { // 22: TAILLE DE LA LISTE ENNEMI
                randChar = random.nextInt(i + 1);
            }
            ennemisTerminal.add(new Ennemi(Ennemi.LIST.getList().get(randChar)));

            if (i == limite) { // AJUSTABLE

                if (i > 6) {
                    ennemisTerminal.add(new Ennemi(Ennemi.LIST.getBossList().get(randBoss)));
                }
                vague.add(ennemisTerminal);
                limite = limite + limite * 1 / 2 + 2;
                ennemisTerminal = new ArrayList<>();
            }
        }

    }

    private void difficultyAjust(Difficulty diff) { // AJUSTABLE
        if (diff == Difficulty.EASY) {
            nbTotalEnnemi = 32;
            multiplier = 1;
        }
        if (diff == Difficulty.NORMAL) {
            nbTotalEnnemi = 69;
            multiplier = 2;
        }
        if (diff == Difficulty.HARD) {
            nbTotalEnnemi = 96;
            multiplier = 3;
        }

    }

    public ArrayList<ArrayList<Ennemi>> getAllEnnemis() {
        return vague;
    }

    public ArrayList<Ennemi> getiWave(int i) {
        return vague.get(i);
    }
}
