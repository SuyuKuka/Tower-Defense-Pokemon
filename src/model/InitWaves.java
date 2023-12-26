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

    private int nbWave;
    private int multiplier; // TODO

    private ArrayList<Ennemi> catalogue = Ennemi.LIST.getList();
    private ArrayList<Ennemi> catalogueBoss = Ennemi.LIST.getBossList();

    private Random random = new Random();

    private ArrayList<ArrayList<Ennemi>> AllWaves = new ArrayList<>();

    public InitWaves(Difficulty diff) { // CREER UNE WAVE ET LA RANDOMISE

        difficultyAjust(diff);

        ArrayList<Ennemi> wave = new ArrayList<>();
        for (int i = 1; i < nbWave + 1; i++) {

            if (i % 50 == 0) { // AUGM LES STATS A TOUT LES 50 WAVE
                for (Ennemi e : catalogue) {
                    e.buffAllStat(2);
                }
            }

            if (!haveBoss(wave)) { // AJOUTE UN BOSS SUPPLEMENTAIRE TOUT LES 10 WAVE
                for (int j = 0; j < i / 10; j++) {
                    wave.add(getBoss());
                }
            }

            while (wave.size() < 2 * (i + 2)) { // TANT QUE LA WAVE N'EST PAS PLEINE, TAILLE CROISSANTE DES WAVE
                int randChar = random.nextInt(i);
                while (randChar >= catalogue.size()) { // RECUP ALEATOIRE UN PKM DANS LE CATALOGUE
                    randChar = random.nextInt(i);
                }

                wave.add(new Ennemi(catalogue.get(randChar)));
            }
            AllWaves.add(wave); // AJOUTE A LA LISTE DE WAVE
            wave = new ArrayList<>();
        }
        // debug();

        // test(wave);

    }

    public ArrayList<Ennemi> initEndless(int i) {

        ArrayList<Ennemi> wave = new ArrayList<>();
        if (i % 50 == 0) { // AUGM LES STATS A TOUT LES 50 WAVE
            for (Ennemi e : catalogue) {
                e.buffAllStat(2);
            }
        }
        if (!haveBoss(wave)) { // AJOUTE UN BOSS SUPPLEMENTAIRE TOUT LES 10 WAVE
            for (int j = 0; j < i / 10; j++) {
                wave.add(getBoss());
            }
        }
        while (wave.size() < 2 * (i + 2)) { // TANT QUE LA WAVE N'EST PAS PLEINE, TAILLE CROISSANTE DES WAVE
            int randChar = random.nextInt(i);
            while (randChar >= catalogue.size()) { // RECUP ALEATOIRE UN PKM DANS LE CATALOGUE
                randChar = random.nextInt(i);
            }

            wave.add(new Ennemi(catalogue.get(randChar)));
        }
        System.out.println(wave);
        System.out.println();
        return wave;
    }

    private void test(ArrayList<Ennemi> wave) {
        AllWaves.clear();
        wave.clear();
        wave.add(Ennemi.LIST.getList().get(0));
        AllWaves.add(wave);
    }

    private void debug() {
        int count = 0;
        for (ArrayList<Ennemi> arrayList : AllWaves) {
            System.out.println("Wave " + count++ + ": " + arrayList.size());
            System.out.println(arrayList);
        }
    }

    private Ennemi getBoss() {
        int randBoss = random.nextInt(catalogueBoss.size());
        return new Ennemi(catalogueBoss.get(randBoss));
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
            nbWave = 20;

        }
        if (diff == Difficulty.NORMAL) {
            nbWave = 40;

        }
        if (diff == Difficulty.HARD) {
            nbWave = 60;

        }

    }

    public ArrayList<ArrayList<Ennemi>> getAllEnnemis() {

        return AllWaves;
    }

    public ArrayList<Ennemi> getiWave(int i) {
        return AllWaves.get(i);
    }
}
