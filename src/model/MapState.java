package model;

import model.InitWaves.Difficulty;
import characters.Starter;
import characters.Starter.Salameche;

import graphics.GameScreen;
import graphics.UpdateMap;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.TimerTask;
import java.util.*;

public class MapState {

    private Map m;

    private int[][] config;
    private GameScreen screen;

    private ArrayList<Tower> towerPresent = new ArrayList<Tower>();
    private ArrayList<Ennemi> ennemiPresent = new ArrayList<Ennemi>();

    private ArrayList<ArrayList<Ennemi>> allEnnemis = new ArrayList<>();
    private ArrayList<Ennemi> wave = new ArrayList<>();

    private int pv = 3;
    private int limiteDeTemps = 30;
    private int gameSpeed = 20; // GRAPHIC ONLY
    private boolean pause = true;
    private double money = 400;
    private double moneyIncrement = 0;
    private int nbTotalWave;
    private int nextWaveIn = 0;
    private Random rand = new Random();

    private boolean endless;
    private InitWaves w;

    public MapState(Difficulty difficulty, int mode) {

        Map map = new Map(18, 24, difficulty);
        try {
            InitPokemon.loadingChar();
        } catch (FileNotFoundException e) {

        }
        InitWaves w = new InitWaves(difficulty);
        endless = mode == 1;
        if (!endless) {
            allEnnemis = w.getAllEnnemis();
        }

        this.w = w;

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

    public void startTerminal() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);

        int nbTour = 6; // AJUSTABLE

        infoTerminal();

        // A METTRE DANS /control
        // Tower terminal = new Tower("Terminal", 1,1, null, null);
        Starter starterInstance = new Starter();
        Salameche sala = starterInstance.new Salameche("Salameche", 10, 0, Types.FEU, Types.NONE);

        int step = 0;
        int increment = 1;
        if (endless) {
            allEnnemis.add(w.initEndless(increment++));
        }

        while (gameContinue() || endless) {
            if (endless && allEnnemis.size() == 1) {
                allEnnemis.add(w.initEndless(increment++));

            }

            for (ArrayList<Ennemi> eList : allEnnemis) {
                for (Ennemi e : eList) {
                    e.setMap(m);
                }
            }

            moveOnMap();
            loadNextWave();
            m.affiche(ennemiPresent);

            info(step);

            step = ennemiComing(step);
            step++;

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

    private void info(int step) {
        System.out.println("Vagues restantes: " + (allEnnemis.size()));
        System.out.println("Ennemie en attente:" + wave.size());
        System.out.println("Ennemis sur le terrain: " + ennemiPresent.size());
        System.out.println("PV: " + pv);
        System.out.println("Temps avant la prochaine vague: " + (limiteDeTemps + 1 - step));
    }

    public void startGraphic(GameScreen screen) throws FileNotFoundException {

        this.screen = screen;

        Scanner sc = new Scanner(System.in);

        allEnnemis = w.getAllEnnemis();
        for (ArrayList<Ennemi> eList : allEnnemis) {
            for (Ennemi e : eList) {
                e.setMap(m);
                e.setTilesMap(m.getdirMap());
                screen.getuMap().setSpriteEntry(e);
                e.setPos(m.getEntry());
            }
        }

        int step = 0;

        nbTotalWave = allEnnemis.size();
        // m.affiche(ennemiPresent);

        while (gameContinue() || endless) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(gameSpeed);
                    } catch (InterruptedException e) {
                    }
                }
            };
            task.run();

            money = money + 0.01 * 1 + moneyIncrement;
            // System.out.println(moneyIncrement);
            UpdateGraphic(nextWaveIn);

            moveOnMapGraphic(screen.getuMap());
            loadNextWave();

            step = ennemiComing(step);
            step++;
            nextWaveIn++;
            Player.INSTANCE.getTeam()[0].attack(towerPresent, ennemiPresent, this);

            for (Ennemi e : screen.getePresent().getEnnemiGone()) {
                screen.getuMap().remove(e.getJLabelSprite());
            }

            while (pause) {
                task.run();
            }
        }
        sc.close();
        UpdateGraphic(0);
    }

    private boolean gameContinue() {
        if (allEnnemis.isEmpty() && !endless && ennemiPresent.isEmpty()) {
            System.out.println("VOUS AVEZ GAGNEE");
            return false;
        }
        if (pv == 0) {
            System.out.println("VOUS AVEZ PERDU");
            endless = false;
            return false;
        }
        return true;
    }

    private int ennemiComing(int pas) {
        if (!wave.isEmpty()) {

            if (pas / 10 > rand.nextInt(600)) {

                setEnnemi(m.getEntry(), wave.get(0));
                wave.remove(0);
                return 0;
            }
        }
        return pas;
    }

    private void moveOnMap() {
        Ennemi escaped = null;
        boolean leaked = false;
        for (Ennemi e : ennemiPresent) { // DEPLACE LES ENNEMIS

            if (e.move()) { // SI LA PROCHAINE CASE EST LA SORTIS
                leaked = true;
                escaped = e;
            }
        }
        if (leaked && !ennemiPresent.isEmpty()) { // SI UN ENNEMI PASSE
            ennemiPresent.remove(escaped);
            if (screen != null) {
                screen.getePresent().getEnnemiGone().add(escaped);
            }
            leaked = false;
            pv--;
        }

    }

    private void moveOnMapGraphic(UpdateMap uMap) {
        Ennemi escaped = null;
        boolean leaked = false;
        for (Ennemi e : ennemiPresent) { // DEPLACE LES ENNEMIS
            e.getJLabelSprite().setVisible(true);
            if (e.moveGraphic(e.getJLabelSprite(), uMap)) { // SI LA PROCHAINE CASE EST LA SORTIS
                leaked = true;
                escaped = e;
            }
        }

        if (leaked && !ennemiPresent.isEmpty()) { // SI UN ENNEMI PASSE
            ennemiPresent.remove(escaped);
            if (screen != null) {
                screen.getePresent().getEnnemiGone().add(escaped);
            }
            leaked = false;
            pv--;
            screen.getuMap().repaint();
        }
    }

    private void loadNextWave() {
        if (nextWaveIn > limiteDeTemps * 100) {
            nextWaveIn = 0;
            for (Ennemi e : wave) {
                setEnnemi(m.getEntry(), e);
            }
            wave.clear();
            allEnnemis.remove(wave);
            moneyIncrement = moneyIncrement + 0.01;

        }

        if (!allEnnemis.isEmpty()) {
            wave = allEnnemis.get(0);
        }
    }

    public boolean setTower(int[] posTower, Tower tower) { // MANUELLEMENT
        if (posTower[0] == -1 || posTower[1] == -1) {
            return false;
        } else {
            // LA CONCEPTION DU TABLEAU EST DIFFERENTE, INVERSER I J
            int posX = posTower[0];
            int posY = posTower[1];

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
        // System.out.println("DEAD:" + e);
        screen.getuMap().repaint();
        if (e.isBoss()) {
            money = money + 100;
        } else {
            money = money + 10;
        }
        ennemiPresent.remove(e);
        if (screen != null) {
            screen.getePresent().getEnnemiGone().add(e);
        }
    }

    public void UpdateGraphic(int pas) {
        screen.getePresent().setEnnemiPresent(ennemiPresent);
        screen.getInGameInfo().UpdateInfo();
        screen.getInGameButton().updateButton(pas, limiteDeTemps);

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

    public ArrayList<ArrayList<Ennemi>> getAllEnnemis() {
        return allEnnemis;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean getPause() {
        return pause;
    }

    public double getMoney() {
        return money;
    }

    public int getPv() {
        return pv;
    }

    public int getNbTotalWave() {
        return nbTotalWave;
    }

}
