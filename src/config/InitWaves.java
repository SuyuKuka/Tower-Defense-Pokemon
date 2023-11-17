package config;

import characters.Ennemi;

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
        for (int i = 0; i < nbTotalEnnemi; i++) {

            int randBoss = random.nextInt(Ennemi.LIST.getBossList().size());
            int randChar = random.nextInt(i + 1);

            while (randChar >= 22 || Ennemi.LIST.getList().get(randChar).isBoss()) { // 22: TAILLE DE LA LISTE ENNEMI
                randChar = random.nextInt(i + 1);
            }
            ennemisTerminal.add(Ennemi.LIST.getList().get(randChar));

            if (i == limite) { // AJUSTABLE

                if (i > 6) {
                    ennemisTerminal.add(Ennemi.LIST.getBossList().get(randBoss));
                }
                vague.add(ennemisTerminal);

                limite = limite + limite * 1 / 2 + 2;
                ennemisTerminal = new ArrayList<>();
            }
        }
        // SPOILER
        int somme = 0;
        for (int i = 0; i < vague.size(); i++) {
            System.out.println(vague.get(i));
            somme = somme + vague.get(i).size();
            System.out.println();
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
        for (Ennemi e : Ennemi.LIST.getList()) {
            e.setValue(e.getValue() * multiplier);
        }
        for (Ennemi e : Ennemi.LIST.getBossList()) {
            e.setValue(e.getValue() * multiplier);
        }
    }

    public ArrayList<ArrayList<Ennemi>> getEnnemisListStandby() {
        return vague;
    }
}
