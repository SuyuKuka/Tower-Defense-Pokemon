package model;

import java.util.ArrayList;
import java.util.Random;

public class InitWaves {

    public enum Difficulty {
        EASY, NORMAL, HARD;

        public static Difficulty stringToDifficulty(String difficulty) {
            switch (difficulty) {
                case "facile":
                    return Difficulty.EASY;
                case "moyen":
                    return Difficulty.NORMAL;
                case "difficile":
                    return Difficulty.HARD;
                default:
                    return null;
            }
        }
    }

    private int nbTotalEnnemi;
    private int multiplier;

    Random random = new Random();

    private ArrayList<ArrayList<Ennemi>> AllWaves = new ArrayList<>();

    public InitWaves(Difficulty diff) { // CREER UNE WAVE ET LA RANDOMISE

        difficultyAjust(diff);

        int limite = 3;
        ArrayList<Ennemi> wave = new ArrayList<>();
        for (int i = 1; i < nbTotalEnnemi; i++) {

            if (!haveBoss(wave) && AllWaves.size() > 3) {
                wave.add(addBoss());
            }

            int randChar = random.nextInt(i);
            while (randChar >= Ennemi.LIST.getList().size()) {
                randChar = random.nextInt(i);
            }

            if (i == limite) { // AJUSTABLE

                AllWaves.add(wave);
                limite = limite + limite * 1 / 2 + 2;
                wave = new ArrayList<>();
            }
            wave.add(new Ennemi(Ennemi.LIST.getList().get(randChar)));
        }

        AllWaves.clear();
        wave.clear();
        wave.add(Ennemi.LIST.getList().get(0));
        AllWaves.add(wave);

    }

    private Ennemi addBoss() {
        int randBoss = random.nextInt(Ennemi.LIST.getBossList().size());
        return new Ennemi(Ennemi.LIST.getBossList().get(randBoss));
    }

    private boolean haveBoss(ArrayList<Ennemi> ennemisTerminal) {
        for (Ennemi ennemi : ennemisTerminal) {
            if (ennemi.isBoss()) {
                return true;
            }
        }
        return false;
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
        return AllWaves;
    }

    public ArrayList<Ennemi> getiWave(int i) {
        return AllWaves.get(i);
    }
}
