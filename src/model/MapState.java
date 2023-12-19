package model;

import java.util.ArrayList;

import model.InitWaves.Difficulty;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import characters.Starter;
import characters.Starter.Salameche;
import graphics.GameScreen;

public class MapState {

    private Map m;

    private int[][] config;

    private ArrayList<Tower> towerPresent = new ArrayList<Tower>();
    private ArrayList<Ennemi> ennemiPresent = new ArrayList<Ennemi>();

    private ArrayList<ArrayList<Ennemi>> allEnnemis = new ArrayList<>();
    private ArrayList<Ennemi> wave = new ArrayList<>();

    private int pv = 3;

    private int limiteDeTemps = 30;

    public MapState() {
        Map map = new Map(20, 20);
        m = map;
        config = m.getConfig();
    }

    private void infoTerminal() {
        System.out.println(
                "===============================================================================================================");
        System.out.println(
                "Pour commencer à jouer, il faut placer une tours aux endroits qui contiennent des . sous la forme de coordonées");
        System.out.println(
                " - Exemple : F4 ou F04");
        System.out.println(
                "Vous avez 3 vies, il y a 6 vagues");
        System.out.println(
                "Si un ennemie passe sur la sortis S, vous perdez une vie, a 0 vie la partie est perdu");
        System.out.println(
                "===============================================================================================================");
    }

    public void startTerminal() {

        InitPokemon.loadingChar();
        InitWaves w = new InitWaves(Difficulty.HARD);
        Scanner sc = new Scanner(System.in);

        int nbTour = 6; // AJUSTABLE

        infoTerminal();

        allEnnemis = w.getAllEnnemis();
        for (ArrayList<Ennemi> eList : allEnnemis) {
            for (Ennemi e : eList) {
                e.setMap(m);
            }
        }

        int step = 0;
        while (gameContinue()) {

            moveOnMap();
            loadNextWave();
            m.affiche(ennemiPresent);
            System.out.println("Vagues restantes: " + (allEnnemis.size()));
            System.out.println("Ennemie en attente:" + wave.size());
            System.out.println("Ennemis sur le terrain: " + ennemiPresent.size());
            System.out.println("PV: " + pv);
            System.out.println("Temps avant la prochaine vague: " + (limiteDeTemps + 1 - step));

            step = ennemiComing(step);
            step++;

            // A METTRE DANS /control
            // Tower terminal = new Tower("Terminal", 1,1, null, null);
            Starter starterInstance = new Starter();
            Salameche sala = starterInstance.new Salameche("Salameche", 1, 0, Type.FEU, Type.NONE);

            if (nbTour != 0) { // SI IL RESTE DES TOURS A PLACER
                System.out.print("Ajouter une tour  (" + nbTour + "): ");
                String st = sc.nextLine();
                if (st.length() != 0) {
                    if (setTower(sala.putTower(st), sala)) {
                        nbTour--;
                    }
                }
            } else {
                String st = sc.nextLine();
            }
            sala.attack(towerPresent, ennemiPresent, this);

        }
        sc.close();
    }

    public void startGraphic(GameScreen screen) {

        InitPokemon.loadingChar();
        InitWaves w = new InitWaves(Difficulty.HARD);
        Scanner sc = new Scanner(System.in);

        int nbTour = 6; // AJUSTABLE

        infoTerminal();

        allEnnemis = w.getAllEnnemis();
        for (ArrayList<Ennemi> eList : allEnnemis) {
            for (Ennemi e : eList) {
                e.setMap(m);
            }
        }

        int step = 0;
        Timer timer = new Timer();
        while (gameContinue()) {

            screen.repaint();

            moveOnMap();
            loadNextWave();
            m.affiche(ennemiPresent);
            System.out.println("Vagues restantes: " + (allEnnemis.size()));
            System.out.println("Ennemie en attente:" + wave.size());
            System.out.println("Ennemis sur le terrain: " + ennemiPresent.size());
            System.out.println("PV: " + pv);
            System.out.println("Temps avant la prochaine vague: " + (limiteDeTemps + 1 - step));

            step = ennemiComing(step);
            step++;

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
            };
            task.run();
        }
        sc.close();
    }

    private boolean gameContinue() {
        if (allEnnemis.isEmpty()) {
            System.out.println("VOUS AVEZ GAGNEE");
            return false;
        }
        if (pv == 0) {
            System.out.println("VOUS AVEZ PERDU");
            return false;
        }
        return true;
    }

    private int ennemiComing(int pas) {
        if (pas == 2 && !wave.isEmpty() || pas > limiteDeTemps && ennemiPresent.isEmpty()) {
            System.out.println(wave);
            setEnnemi(m.getFrontEntry(), wave.get(0));
            wave.remove(0);
            return 0;
        }
        return pas;
    }

    private void moveOnMap() {
        Ennemi escaped = null;
        boolean leaked = false;
        for (Ennemi e : ennemiPresent) { // DEPLACE LES ENNEMIS
            if (e.move2()) { // SI LA PROCHAINE CASE EST LA SORTIS
                leaked = true;
                escaped = e;
            }
        }
        if (leaked && !ennemiPresent.isEmpty()) { // SI UN ENNEMI PASSE
            ennemiPresent.remove(escaped);
            leaked = false;
            pv--;
        }

    }

    private void loadNextWave() {
        if (wave.isEmpty() && ennemiPresent.isEmpty()) {
            allEnnemis.remove(wave);
        }
        if (!allEnnemis.isEmpty()) {
            wave = allEnnemis.get(0);
        }
    }

    private boolean setTower(int[] posTower, Tower tower) { // MANUELLEMENT
        if (posTower[0] == -1 || posTower[1] == -1) {
            return false;
        } else {
            // LA CONCEPTION DU TABLEAU EST DIFFERENTE, INVERSER I J
            int posX = posTower[1];
            int posY = posTower[0];

            if (config[posX][posY] == 0) {
                config[posX][posY] = 2;
                towerPresent.add(tower);
                tower.setPos(posTower);
                return true;
            }
            return false;
        }
    }

    private void setEnnemi(int[] posEnnemi, Ennemi ennemi) { // MANUELLEMENT
        int posX = posEnnemi[0];
        int posY = posEnnemi[1];

        if (config[posX][posY] == 3 || config[posX][posY] == 4) {
            ennemiPresent.add(ennemi);
            ennemi.setPos(posEnnemi);
        } else {
            return;
        }
    }

    public void isDead(Ennemi e) {
        System.out.println("DEAD:" + e);
        ennemiPresent.remove(e);
    }

    public ArrayList<Ennemi> getEnnemiPresent() {
        return ennemiPresent;
    }

    public ArrayList<Tower> getTowerPresent() {
        return towerPresent;
    }

    public Map getMap() {
        return m;
    }

    public Difficulty stringToDifficulty(String difficulty){
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
