package model;

import model.InitWaves.Difficulty;

import characters.Starter;

import characters.Starter.Salameche;

import graphics.GameScreen;
import graphics.GameoverScreen;
import graphics.UpdateMap;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.*;

import java.util.ArrayList;

import javax.swing.*;

import model.Ennemi;

import model.MapState;
import model.Player;
import model.Tower;
import java.awt.*;
import javax.swing.Timer;

import model.MapState;

import model.MapState;
import model.Player;
import model.Tower;

import javax.swing.border.*;
import javax.swing.border.*;

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
    private int gameSpeed = 50; // GRAPHIC ONLY
    private boolean pause = false;
    private double money;
    private double moneyIncrement = 0;
    private int nbTotalWave;
    private int nextWaveIn = 0;
    private Random rand = new Random();
    private Timer timer;
    private boolean endless;
    private int count = 0;
    private InitWaves w;

    private Difficulty diff;

    int step = 0;
    int inc = 1;

    // STATS
    private double countMoney;
    private ArrayList<Ennemi> ennemiEscaped = new ArrayList<>();

    //
    public MapState(Difficulty difficulty, int mode) {
        Map map = new Map(18, 24, difficulty);
        try {
            InitPokemon.loadingChar();
        } catch (FileNotFoundException e) {

        }
        diff = difficulty;
        InitWaves w = new InitWaves(difficulty);
        if (difficulty == Difficulty.EASY) {
            limiteDeTemps = 40;
            money = 8000;
        } else if (difficulty == Difficulty.NORMAL) {
            limiteDeTemps = 25;
            money = 400;
        } else if (difficulty == Difficulty.HARD) {
            limiteDeTemps = 10;
            money = 200;
        }
        countMoney = money; // STATS
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
                "Pour commencer à jouer, il faut placer une tours aux endroits qui contiennent des . sous la forme de coordonees");
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
        Salameche sala = starterInstance.new Salameche();

        int step = 0;
        int increment = 1;
        if (endless) {
            allEnnemis.add(w.initEndless(increment++));
        }

        while (!endGame() || endless) {
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

        // MODE ENDLESS
        if (endless) {
            allEnnemis.add(w.initEndless(inc++));
            allEnnemis.add(w.initEndless(inc++));
            allEnnemis.add(w.initEndless(inc++));
            allEnnemis.add(w.initEndless(inc++));
        }
        //
        for (ArrayList<Ennemi> eList : allEnnemis) {
            for (Ennemi e : eList) {
                e.setMap(m);
                e.setTilesMap(m.getdirMap());
                screen.getuMap().setSpriteEntry(e);
                e.setPos(m.getEntry());
            }
        }
        nbTotalWave = allEnnemis.size();

        timer = new Timer(gameSpeed, event -> { // TIMEEEEEEEEEER
            // ENDLESS
            if (endless && allEnnemis.size() == 3) {
                allEnnemis.add(w.initEndless(inc++));
                for (ArrayList<Ennemi> eList : allEnnemis) {
                    for (Ennemi e : eList) {
                        e.setMap(m);
                        e.setTilesMap(m.getdirMap());
                        screen.getuMap().setSpriteEntry(e);
                        e.setPos(m.getEntry());
                    }
                }
            }
            //

            money = money + 0.01 * 1 + moneyIncrement;
            countMoney = countMoney + 0.01 * 1 + moneyIncrement;
            UpdateGraphic(nextWaveIn, false);
            moveOnMapGraphic(screen.getuMap());
            loadNextWave();
            step = ennemiComing(step);
            step++;
            nextWaveIn++;

            for (Tower t : towerPresent) {
                t.attack2(ennemiPresent, this);

            }
            // Player.INSTANCE.getTeam()[0].attack(towerPresent, ennemiPresent, this);

            for (Ennemi e : screen.getePresent().getEnnemiGone()) {
                screen.getuMap().remove(e.getJLabelSprite());
            }

            if (endGame()) {
                allEnnemis.clear();
                ennemiPresent.clear();
                wave.clear();
                if (pv == 0) {
                    System.out.println("VOUS AVEZ PERDU");
                    gameOverScreen(false);
                } else {
                    System.out.println("VOUS AVEZ GAGNEE");
                    gameOverScreen(true);
                }
                timer.stop();
            }
        });

        timer.start();

    }

    private boolean endGame() {

        if (allEnnemis.isEmpty() && !endless && ennemiPresent.isEmpty()) {
            return true;
        }
        if (pv == 0) {
            return true;
        }
        return false;
    }

    private void gameOverScreen(boolean win) {
        UpdateGraphic(0, true);
        GameoverScreen fini = new GameoverScreen(this, screen, win);
        fini.revalidate();
        fini.repaint();
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
                ennemiEscaped.add(e);
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
        if (nextWaveIn > limiteDeTemps * 100 || ennemiPresent.isEmpty() && wave.isEmpty()) {

            count++;
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
            countMoney = countMoney + 100;
        } else {
            money = money + 10;
            countMoney = countMoney + 10;
        }

        ennemiPresent.remove(e);
        if (screen != null) {
            screen.getePresent().getEnnemiGone().add(e);
        }
    }

    public void UpdateGraphic(int pas, boolean lose) {
        screen.getePresent().setEnnemiPresent(ennemiPresent);
        screen.getInGameInfo().UpdateInfo();
        screen.getInGameButton().updateButton(pas, limiteDeTemps, count, lose);

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
        if (timer != null) {

            timer.setDelay(gameSpeed);
            timer.restart();
        }
        this.gameSpeed = gameSpeed;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setPause(boolean pause) {
        if (pause) {
            timer.stop();
        } else {
            timer.start();
        }
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

    public boolean getEndless() {
        return endless;
    }

    public int getCount() {
        return count;
    }

    public double getCountMoney() {
        return countMoney;
    }

    public ArrayList<Ennemi> getEnnemiEscaped() {
        return ennemiEscaped;
    }

    public Difficulty getDiff() {
        return diff;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setEndless(boolean endless) {
        this.endless = endless;
    }

    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
