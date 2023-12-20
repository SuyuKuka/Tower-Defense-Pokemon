package model;

import java.util.ArrayList;

import model.Map.Corner;

public class Ennemi extends Pokemon {

    private Map map;
    private int[][] roadMap = Map.roadMap.readableMap();
    private int pas = 1;

    private int[] pos;

    private char prevDirection;
    private boolean onCorner = false;

    private static ArrayList<Ennemi> EList = new ArrayList<>();
    private static ArrayList<Ennemi> EBossList = new ArrayList<>();
    public static final Ennemi LIST = new Ennemi();
    public boolean boss;
    private double totalPv;

    public Ennemi(String name, double pv, double defPhysical, double defSpecial, Types type1, Types type2, boolean boss,
            String sprite) {
        super(name, pv, defPhysical, defSpecial, type1, type2, false, sprite);
        totalPv = pv;
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

    public boolean move2() {
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
                + " \nPV: " + getPv()
                + " \nDEF PHYSIQUE: " + getPhysical()
                + " \nDEF SPECIAL: " + getSpecial()
                + " \nTYPE: " + getType()[0] + " et " + getType()[1]
                + " \nVITESSE: " + getSpeed()
                + "\nMECHANT\n"
                + "BOSS:" + isBoss() + "\n";
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

    public double getTotalPv() {
        return totalPv;
    }

}
