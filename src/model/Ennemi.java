package model;

import java.util.ArrayList;

import model.Map.Corner;

public class Ennemi extends Pokemon {

    private Map map;
    private int[][] roadMap = Map.roadMap.readableMap();
    private int pas = 1;

    private int[] pos;

    private char keepDirection;
    private boolean onCorner = false;

    private static ArrayList<Ennemi> EList = new ArrayList<>();
    private static ArrayList<Ennemi> EBossList = new ArrayList<>();
    public static final Ennemi LIST = new Ennemi();
    public boolean boss;

    public Ennemi(String name, double pv, int defPhysical, int defSpecial, Type type1, Type type2, boolean boss) {
        super(name, pv, defPhysical, defSpecial, type1, type2, false);
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
        super(e.getName(), e.getPv(), e.getPhysical(), e.getSpecial(), e.getType()[0], e.getType()[1], false);
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

        if (exit) {
            return true;
        }

        if (onCorner) {
            switch (keepDirection) {
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
            System.out.println("CORNEEEEEEEEEEEER");
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
            switch (keepDirection) {
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
            onCorner = true;
        }

        if (direction != ' ') {
            keepDirection = direction;
        }

        return false;

    }

    private boolean moveAgain2() { // TODO
        return false;
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

    /*
     * public boolean move() {
     * if (getSpeed() != Speed.STUN) {
     * if (getSpeed() == Speed.FAST) {
     * moveAgain();
     * moveAgain();
     * } else if (getSpeed() == Speed.CLASSIC) {
     * moveAgain();
     * }
     * 
     * if (config[getPos()[0] + 1][getPos()[1]] == 5 ||
     * config[getPos()[0]][getPos()[1] + 1] == 5
     * || config[getPos()[0]][getPos()[1] - 1] == 5) {
     * return true;
     * }
     * 
     * if (config[getPos()[0]][getPos()[1] + 1] == 3 &&
     * config[getPos()[0]][getPos()[1] - 1] == 3) {
     * 
     * if (wasRight()) {
     * int[] pos = { getPos()[0], getPos()[1] + 1 };
     * setPos(pos);
     * 
     * } else {
     * int[] pos = { getPos()[0], getPos()[1] - 1 };
     * setPos(pos);
     * 
     * }
     * 
     * } else if (config[getPos()[0] + 1][getPos()[1]] == 3) { // BAS
     * int[] pos = { getPos()[0] + 1, getPos()[1] };
     * setPos(pos);
     * 
     * } else if (config[getPos()[0]][getPos()[1] + 1] == 3) { // DROITE
     * int[] pos = { getPos()[0], getPos()[1] + 1 };
     * setPos(pos);
     * 
     * setWasRight(true);
     * 
     * } else if (config[getPos()[0]][getPos()[1] - 1] == 3) { // GAUCHE
     * int[] pos = { getPos()[0], getPos()[1] - 1 };
     * setPos(pos);
     * 
     * setWasRight(false);
     * 
     * }
     * 
     * }
     * return false;
     * 
     * }
     * 
     * public boolean moveAgain() {
     * if (config[getPos()[0] + 1][getPos()[1]] == 5 ||
     * config[getPos()[0]][getPos()[1] + 1] == 5
     * || config[getPos()[0]][getPos()[1] - 1] == 5) {
     * return true;
     * }
     * 
     * if (config[getPos()[0]][getPos()[1] + 1] == 3 &&
     * config[getPos()[0]][getPos()[1] - 1] == 3) {
     * 
     * if (wasRight()) {
     * int[] pos = { getPos()[0], getPos()[1] + 1 };
     * setPos(pos);
     * 
     * } else {
     * int[] pos = { getPos()[0], getPos()[1] - 1 };
     * setPos(pos);
     * 
     * }
     * 
     * } else if (config[getPos()[0] + 1][getPos()[1]] == 3) { // BAS
     * int[] pos = { getPos()[0] + 1, getPos()[1] };
     * setPos(pos);
     * 
     * } else if (config[getPos()[0]][getPos()[1] + 1] == 3) { // DROITE
     * int[] pos = { getPos()[0], getPos()[1] + 1 };
     * setPos(pos);
     * 
     * setWasRight(true);
     * 
     * } else if (config[getPos()[0]][getPos()[1] - 1] == 3) { // GAUCHE
     * int[] pos = { getPos()[0], getPos()[1] - 1 };
     * setPos(pos);
     * 
     * setWasRight(false);
     * 
     * }
     * return false;
     * }
     */
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

}
