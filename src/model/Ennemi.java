package model;

import java.util.ArrayList;

import javax.swing.*;

import characters.Tylton;
import graphics.UpdateMap;
import graphics.UpdateMap.Tiles;
import model.Map.DirectionTiles;
import model.Map.TilesType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ennemi extends Pokemon {

    private static ArrayList<Ennemi> EList = new ArrayList<>();
    private static ArrayList<Ennemi> EBossList = new ArrayList<>();
    public static final Ennemi LIST = new Ennemi();
    public boolean boss;
    private JLabel sprite;
    // DEPLACEMENT
    private Map map;
    private boolean exit;
    private int[] pos;
    private int[][] roadMap = Map.roadMap.readableMap();
    // DEPL. GRAPHIC
    private Map.Corner corner;
    private boolean switcher = false;
    private int[] Xpos = new int[2];
    private double incrementX = 0;
    private double incrementY = 2;
    private boolean inLoop = false;
    private DirectionTiles[][] tilesMap;
    private int cellSize;
    private int numericSpeed;
    // DEPL. TERMINAL
    private int pas = 2;
    private char prevDirection;
    private boolean onCorner = false;

    public Ennemi(
            String name,
            double pv,
            double defPhysical,
            double defSpecial,
            Types type1,
            Types type2,
            boolean boss,
            String sprite) {
        super(name, pv, defPhysical, defSpecial, type1, type2, false, sprite);
        if (boss) {
            EBossList.add(this);
            this.boss = boss;
        } else {
            EList.add(this);
        }
    }

    public Ennemi() {
    }

    public Ennemi(Ennemi e) {
        super(e.getName(), e.getPv(), e.getPhysical(), e.getSpecial(), e.getType()[0], e.getType()[1], false,
                e.getSprite());
        this.boss = e.isBoss();
        setSpeed(e.getSpeed());
    }

    public int[] putEnnemi(String st) {
        try {
            String alphaPart = st.replaceAll("[^A-Z]", "");
            String numPart = st.replaceAll("[^0-9]", "");

            int posX;
            int posY;

            if (!st.matches("[A-Z]\\d+")) {
                posX = -1;
            } else {
                posX = alphaPart.charAt(0) - 'A';
            }

            if (!numPart.isEmpty()) {
                posY = Integer.parseInt(numPart) - 1;
            } else {
                posY = -1; // Si la partie numerique est vide, utilisezz 0 comme valeur par defaut.
            }

            int[] tab = { posX, posY };
            return tab;
        } catch (Exception e) {
            int[] tab = { -1, -1 };
            return tab;
        }
    }

    public boolean moveGraphic(JLabel sprite, UpdateMap uMap) {

        cellSize = uMap.getCellSize();
        exit = this.map.getExit()[0] == getPos()[0] && this.map.getExit()[1] == getPos()[1];

        Point spritePos = new Point(((int) sprite.getLocation().getX() + cellSize / 2),
                ((int) sprite.getLocation().getY() + cellSize / 2));

        Point tileLocation = tilesMap[getPos()[0]][getPos()[1]].getBox().getLocation();//

        if (getSpeed() == Speed.STUN) {
            numericSpeed = 0;
        } else if (getSpeed() == Speed.FAST) {
            numericSpeed = 3;
        } else if (getSpeed() == Speed.SLOW) {
            numericSpeed = 1;
        } else {
            numericSpeed = 2;
        }

        if (exit) {
            return true;
        }

        if (!inLoop) {
            moving(spritePos, tileLocation);
        } else {
            takeLoop(spritePos, tileLocation);
        }

        if (incrementY == 1) {
            sprite.setIcon(new ImageIcon(getSprite()));
        }
        if (incrementY == -1) {
            sprite.setIcon(new ImageIcon(getSprite().substring(0, getSprite().length() - 4) + "T.gif"));
        }
        if (incrementX == 1) {
            sprite.setIcon(new ImageIcon(getSprite().substring(0, getSprite().length() - 4) + "D.gif"));
        }
        if (incrementX == -1) {
            sprite.setIcon(new ImageIcon(getSprite().substring(0, getSprite().length() - 4) + "G.gif"));
        }

        sprite.setLocation((int) (sprite.getLocation().getX() + incrementX),
                (int) (sprite.getLocation().getY() + incrementY));
        if (spritePos.getX() != tileLocation.getX() || spritePos.getY() != tileLocation.getY()) {
            setPos(new int[] { (int) spritePos.getY() / cellSize, (int) spritePos.getX() / cellSize });
        }

        return false;
    }

    private void takeLoop(Point spritePos, Point tileLocation) {
        switch (tilesMap[getPos()[0]][getPos()[1]].getType()) {
            case X:
                Xpos[0] = getPos()[0];
                Xpos[1] = getPos()[1];
                getActualLoop();
                break;
            case V:
                calibrateX(spritePos, tileLocation);
                incrementX = 0;
                if (!corner.getTopOrientation()) {
                    exitLoop();
                }
                break;
            case H:
                calibrateY(spritePos, tileLocation);
                incrementY = 0;
                if (corner.getTopOrientation()) {
                    exitLoop();
                }
                break;
            case A:
                cornerA();
                break;
            case Z:
                cornerZ();
                break;
            case Q:
                cornerQ();
                break;
            case S:
                cornerS();
                break;
        }
    }

    private void exitLoop() {
        if (switcher) {
            inLoop = false;
        }
    }

    private void getActualLoop() {
        for (Map.Corner c : map.getCornerList()) {
            if (c.haveLoop()
                    && c.getCornerPos()[0] == Xpos[0]
                    && c.getCornerPos()[1] == Xpos[1]) {
                corner = c;
            }
        }
    }

    private void cornerA() {
        if (corner.getTopOrientation() && corner.getRightOrientation()) {
            incrementX = numericSpeed;
            incrementY = -numericSpeed;
            tilesMap[Xpos[0]][Xpos[1]].setType(TilesType.H);
            switcher = true;
        } else if (corner.getTopOrientation()) {
            // NOTHING
        } else if (corner.getRightOrientation()) {
            incrementX = numericSpeed;
            incrementY = -numericSpeed;
        } else {
            incrementY = numericSpeed;
            incrementX = -numericSpeed;
            tilesMap[Xpos[0]][Xpos[1]].setType(TilesType.V);
            switcher = true;
        }
    }

    private void cornerQ() {
        if (corner.getTopOrientation() && corner.getRightOrientation()) {
            incrementY = -numericSpeed;
            incrementX = -numericSpeed;
        } else if (corner.getTopOrientation()) {
            incrementX = numericSpeed;
            incrementY = numericSpeed;
        } else if (corner.getRightOrientation()) {
            incrementY = -numericSpeed;
            incrementX = -numericSpeed;
        } else {
            // NOTHING
        }

    }

    private void cornerS() {
        if (corner.getTopOrientation() && corner.getRightOrientation()) {
            incrementY = numericSpeed;
            incrementX = -numericSpeed;
        } else if (corner.getTopOrientation()) {
            incrementY = -numericSpeed;
            incrementX = numericSpeed;
        } else if (corner.getRightOrientation()) {
            // NOTHING
        } else {
            incrementY = -numericSpeed;
            incrementX = numericSpeed;
        }
    }

    private void cornerZ() {
        if (corner.getTopOrientation() && corner.getRightOrientation()) {
            // NOTHING
        } else if (corner.getTopOrientation()) {
            incrementX = -numericSpeed;
            incrementY = -numericSpeed;
            tilesMap[Xpos[0]][Xpos[1]].setType(TilesType.H);
            switcher = true;
        } else if (corner.getRightOrientation()) {
            incrementY = numericSpeed;
            incrementX = numericSpeed;
            tilesMap[Xpos[0]][Xpos[1]].setType(TilesType.V);
            switcher = true;
        } else {
            incrementY = -numericSpeed;
            incrementX = -numericSpeed;
        }
    }

    private void moving(Point spritePos, Point tileLocation) {
        switch (tilesMap[getPos()[0]][getPos()[1]].getType()) {
            case X:
                inLoop = true;
                switcher = false;
                break;
            case V:
                calibrateX(spritePos, tileLocation);
                incrementX = 0;

                break;
            case H:
                calibrateY(spritePos, tileLocation);
                incrementY = 0;

                break;
            case A:
                incrementY = numericSpeed;
                incrementX = -numericSpeed;
                break;
            case Z:
                incrementY = numericSpeed;
                incrementX = numericSpeed;
                break;
            case Q:
                incrementY = numericSpeed;
                incrementX = numericSpeed;
                break;
            case S:
                incrementY = numericSpeed;
                incrementX = -numericSpeed;
                break;
        }
    }

    private void calibrateY(Point spritePos, Point tileLocation) {
        if (spritePos.getY() / cellSize > tileLocation.getY() / cellSize + 0.5) {
            sprite.setLocation((int) (sprite.getLocation().getX()),
                    (int) (sprite.getLocation().getY() - 1));
        }
        if (spritePos.getY() / cellSize < tileLocation.getY() / cellSize + 0.5) {
            sprite.setLocation((int) (sprite.getLocation().getX()),
                    (int) (sprite.getLocation().getY() + 1));
        }
    }

    private void calibrateX(Point spritePos, Point tileLocation) {
        if (spritePos.getX() / cellSize > tileLocation.getX() / cellSize + 0.5) {
            sprite.setLocation((int) (sprite.getLocation().getX() - 1),
                    (int) (sprite.getLocation().getY()));
        }
        if (spritePos.getX() / cellSize < tileLocation.getX() / cellSize + 0.5) {
            sprite.setLocation((int) (sprite.getLocation().getX() + 1),
                    (int) (sprite.getLocation().getY()));
        }
    }

    public boolean move() {
        char direction = ' ';
        boolean exit = this.map.getExit()[0] == getPos()[0] && this.map.getExit()[1] == getPos()[1];
        int nbMove = 0;
        if (getSpeed() == Speed.STUN) {
            nbMove = 0;
        } else if (getSpeed() == Speed.FAST) {
            nbMove = 3;
        } else if (getSpeed() == Speed.CLASSIC) {
            nbMove = 2;
        } else {
            nbMove = 1;
        }
        for (int i = 0; i < nbMove; i++) {
            try {
                if (exit) {
                    return true;
                }

                if (onCorner) {
                    keepDirection();
                    onCorner = false;
                }

                else if (pas == roadMap[getPos()[0] + 1][getPos()[1]] - 1) { // BAS
                    moveBot();
                    direction = 'b';
                } else if (pas == roadMap[getPos()[0]][getPos()[1] - 1] - 1) { // GAUCHE
                    moveLeft();
                    direction = 'l';
                } else if (pas == roadMap[getPos()[0]][getPos()[1] + 1] - 1) { // DROITE
                    moveRight();
                    direction = 'r';
                } else if (pas == roadMap[getPos()[0] - 1][getPos()[1]] - 1) {// HAUT
                    moveTop();
                    direction = 't';
                } else {
                    keepDirection();
                    onCorner = true;
                }

                if (direction != ' ') {
                    prevDirection = direction;
                }
            } catch (Exception e) {

            }
        }
        return false;
    }

    private void keepDirection() {
        switch (prevDirection) {
            case 'b':
                moveBot();
                break;
            case 't':
                moveTop();
                break;
            case 'r':
                moveRight();
                break;
            case 'l':
                moveLeft();
                break;
        }
    }

    private void moveBot() {
        pas = roadMap[getPos()[0] + 1][getPos()[1]];
        int[] pos = { getPos()[0] + 1, getPos()[1] };
        setPos(pos);
    }

    private void moveRight() {
        pas = roadMap[getPos()[0]][getPos()[1] + 1];
        int[] pos = { getPos()[0], getPos()[1] + 1 };
        setPos(pos);
    }

    private void moveLeft() {
        pas = roadMap[getPos()[0]][getPos()[1] - 1];
        int[] pos = { getPos()[0], getPos()[1] - 1 };
        setPos(pos);
    }

    private void moveTop() {
        pas = roadMap[getPos()[0] - 1][getPos()[1]];
        int[] pos = { getPos()[0] - 1, getPos()[1] };
        setPos(pos);
    }

    public String toString() {
        return "NOM: " + getName()

                + " PV: " + getPv()
                + " DEF PHYSIQUE: " + getPhysical()
                + " DEF SPECIAL: " + getSpecial()
                /*
                 * + " \nTYPE: " + getType()[0] + " et " + getType()[1]
                 * + " \nVITESSE: " + getSpeed()
                 * + "\nMECHANT\n"
                 */
                + " BOSS:" + isBoss() + "\n";
    }

    public ArrayList<Ennemi> getList() {
        return EList;
    }

    public ArrayList<Ennemi> getBossList() {
        return EBossList;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setJLabelSprite(JLabel sprite) {
        this.sprite = sprite;
    }

    public JLabel getJLabelSprite() {
        return sprite;
    }

    public void buffAllStat(double multipler) {
        setPv(getPv() * multipler);
        setPhysical(getPhysical() * multipler);
        setSpecial(getSpecial() * multipler);
    }

    public void setTilesMap(DirectionTiles[][] tilesMap) {
        DirectionTiles[][] res = new DirectionTiles[tilesMap.length][tilesMap[0].length];
        for (int i = 0; i < tilesMap.length; i++) {
            for (int j = 0; j < tilesMap[0].length; j++) {

                if (tilesMap[i][j] != null) {
                    switch (tilesMap[i][j].getType()) {
                        case X:
                            res[i][j] = map.new DirectionTiles(TilesType.X, i, j);
                            break;
                        case H:
                            res[i][j] = map.new DirectionTiles(TilesType.H, i, j);
                            break;
                        case V:
                            res[i][j] = map.new DirectionTiles(TilesType.V, i, j);
                            break;
                        case A:
                            res[i][j] = map.new DirectionTiles(TilesType.A, i, j);
                            break;
                        case Z:
                            res[i][j] = map.new DirectionTiles(TilesType.Z, i, j);
                            break;
                        case Q:
                            res[i][j] = map.new DirectionTiles(TilesType.Q, i, j);
                            break;
                        case S:
                            res[i][j] = map.new DirectionTiles(TilesType.S, i, j);
                            break;
                    }
                }
            }
        }
        this.tilesMap = res;
    }

}
