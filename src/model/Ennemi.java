package model;

import java.util.ArrayList;

import javax.swing.*;

import graphics.UpdateMap;
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

    int incrementX = 0;
    int incrementY = 1;
    boolean inCorner = false;
    boolean inLoop = false;

    private Map map;
    private int[][] roadMap = Map.roadMap.readableMap();
    private DirectionTiles[][] tilesMap;

    private int pas = 2;

    private int[] pos;

    private char prevDirection;
    private boolean onCorner = false;

    private static ArrayList<Ennemi> EList = new ArrayList<>();
    private static ArrayList<Ennemi> EBossList = new ArrayList<>();
    public static final Ennemi LIST = new Ennemi();
    public boolean boss;

    private JLabel sprite;

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
                posY = -1; // Si la partie numérique est vide, utilisezz 0 comme valeur par défaut.
            }

            int[] tab = { posX, posY };
            return tab;
        } catch (Exception e) {
            int[] tab = { -1, -1 };
            return tab;
        }
    }

    public boolean moveGraphic3(JLabel sprite, UpdateMap uMap) {
        int spriteModifierX = 0;
        int spriteModifierY = 0;
        boolean exit = this.map.getExit()[0] == getPos()[0] && this.map.getExit()[1] == getPos()[1];
        int cellSize = uMap.getCellSize();

        // System.out.println(map.getConfig()[getPos()[1]][getPos()[0]]);
        if (exit) {
            return true;
        }

        switch (tilesMap[getPos()[0]][getPos()[1]].getType()) {
            case X:
                if (!inCorner) {
                    inCorner = true;
                }
                break;
            case V:
                incrementX = 0;
                incrementY = 1;
                break;
            case H:
                incrementY = 0;
                break;
            case A:

                spriteModifierX = -32;

                incrementX = 0;
                incrementY = 1;

                break;
            case Z:
                incrementX = 0;
                incrementY = 1;

                break;
            case Q:
                incrementY = 0;
                incrementX = 1;
                break;
            case S:
                incrementY = 0;
                incrementX = -1;

                break;

        }
        // System.out.println(goRight);
        sprite.setLocation((int) sprite.getLocation().getX() + incrementX,
                (int) sprite.getLocation().getY() + incrementY);

        // System.out.println(tilesMap[getPos()[1]][getPos()[0]].getType());
        // System.out.println(incrementX + " " + incrementY);
        int[] spritePos1 = { (int) (sprite.getLocation().getY() + cellSize / 2) / cellSize,
                (int) (sprite.getLocation().getX() + cellSize / 2) / cellSize };

        if (spritePos1[0] != getPos()[0] || spritePos1[1] != getPos()[1]) {
            setPos(spritePos1);
        }
        return false;
    }

    public boolean moveGraphic2(JLabel sprite, UpdateMap uMap) {
        boolean exit = this.map.getExit()[0] == getPos()[0] && this.map.getExit()[1] == getPos()[1];
        int cellSize = uMap.getCellSize();
        char direction = ' ';

        try {
            // System.out.println(map.getConfig()[getPos()[1]][getPos()[0]]);
            if (exit) {
                return true;
            }
            if (onCorner) {
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
                onCorner = false;
            }

            else if (pas == roadMap[getPos()[0] + 1][getPos()[1]] - 1) { // BAS

                incrementX = 0;
                incrementY = 1;
                direction = 'b';
            } else if (pas == roadMap[getPos()[0]][getPos()[1] - 1] - 1) { // GAUCHE

                incrementX = -1;
                incrementY = 0;
                direction = 'l';
            } else if (pas == roadMap[getPos()[0]][getPos()[1] + 1] - 1) { // DROITE

                incrementX = 1;
                incrementY = 0;
                direction = 'r';
            } else if (pas == roadMap[getPos()[0] - 1][getPos()[1]] - 1) {// HAUT

                incrementX = 0;
                incrementY = -1;
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

        sprite.setLocation((int) sprite.getLocation().getX() + incrementX,
                (int) sprite.getLocation().getY() + incrementY);

        int[] spritePos = { (int) (sprite.getLocation().getY()) / cellSize,
                (int) (sprite.getLocation().getX()) / cellSize };

        if (spritePos[0] != getPos()[0] || spritePos[1] != getPos()[1]) {
            pas = roadMap[getPos()[0]][getPos()[1]];

            setPos(spritePos);
        }
        System.out.println(spritePos[0] + " " + getPos()[0]);
        System.out.println(spritePos[1] + " " + getPos()[1]);
        // System.out.println(tilesMap[getPos()[1]][getPos()[0]].getType());
        // System.out.println(incrementX + " " + incrementY);

        return false;
    }

    public boolean moveGraphic(JLabel sprite, UpdateMap uMap) {
        boolean exit = this.map.getExit()[0] == getPos()[0] && this.map.getExit()[1] == getPos()[1];
        int cellSize = uMap.getCellSize();
        Point spritePos = new Point(((int) sprite.getLocation().getX() + 32 / 2),
                ((int) sprite.getLocation().getY() + 32 / 2));
        DirectionTiles[][] tilesmap = map.getdirMap();
        Point tileLocation = tilesmap[getPos()[0]][getPos()[1]].getBox().getLocation();

        if (exit) {
            return true;
        }

        if (!inLoop) {
            moving();
        } else {
            takeLoop();
        }
        System.out.println(inLoop);
        // System.out.println("incrementX " + incrementX);
        // System.out.println("incrementY " + incrementY);

        sprite.setLocation((int) sprite.getLocation().getX() + incrementX,
                (int) sprite.getLocation().getY() + incrementY);

        if (spritePos.getX() != tileLocation.getX() || spritePos.getY() != tileLocation.getY()) {
            setPos(new int[] { (int) spritePos.getY() / 32, (int) spritePos.getX() / 32 });
        }

        return false;
    }

    private void takeLoop() {

        switch (tilesMap[getPos()[0]][getPos()[1]].getType()) {
            case X:
                inLoop = false;
                break;
            case V:
                incrementX = 0;
                break;
            case H:
                incrementY = 0;
                break;
            case A:
                incrementX = 1;
                break;
            case Z:

                break;
            case Q:
                incrementY = -1;
                break;
            case S:
                incrementX = -1;
                break;
        }

    }

    private void moving() {
        switch (tilesMap[getPos()[0]][getPos()[1]].getType()) {
            case X:
                inLoop = true;
                break;
            case V:
                incrementX = 0;
                break;
            case H:
                incrementY = 0;
                break;
            case A:
                incrementY = 1;
                break;
            case Z:
                incrementY = 1;
                break;
            case Q:
                incrementX = 1;
                break;
            case S:
                incrementX = -1;
                break;
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
        this.tilesMap = tilesMap;
    }

}
