package model;

import java.util.ArrayList;

public class Ennemi extends Pokemon {

    private int[][] config;

    private int[] pos;
    private boolean wasRight = false;
    private static ArrayList<Ennemi> EList = new ArrayList<>();
    private static ArrayList<Ennemi> EBossList = new ArrayList<>();
    public static final Ennemi LIST = new Ennemi();

    private boolean boss;

    public Ennemi(String name, int pv, Type type1, Type type2, boolean boss) {
        super(name, pv, type1, type2, false);
        if (boss) {
            EBossList.add(this);
            this.boss = boss;
        } else {
            EList.add(this);
        }
        config = Map.map;
    }

    public Ennemi() {
    }

    public Ennemi(Ennemi e) {
        super(e.getName(), e.getValue(), e.getType()[0], e.getType()[1], false);
        this.boss = e.isBoss();
        setSpeed(e.getSpeed());
        config = Map.map;
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

    public boolean move() {
        if (getSpeed() != Speed.STUN) {
            if (getSpeed() == Speed.FAST) {
                moveAgain();
                moveAgain();
            } else if (getSpeed() == Speed.CLASSIC) {
                moveAgain();
            }

            if (config[getPos()[0] + 1][getPos()[1]] == 5 || config[getPos()[0]][getPos()[1] + 1] == 5
                    || config[getPos()[0]][getPos()[1] - 1] == 5) {
                return true;
            }

            if (config[getPos()[0]][getPos()[1] + 1] == 3 &&
                    config[getPos()[0]][getPos()[1] - 1] == 3) {

                if (wasRight()) {
                    int[] pos = { getPos()[0], getPos()[1] + 1 };
                    setPos(pos);

                } else {
                    int[] pos = { getPos()[0], getPos()[1] - 1 };
                    setPos(pos);

                }

            } else if (config[getPos()[0] + 1][getPos()[1]] == 3) { // BAS
                int[] pos = { getPos()[0] + 1, getPos()[1] };
                setPos(pos);

            } else if (config[getPos()[0]][getPos()[1] + 1] == 3) { // DROITE
                int[] pos = { getPos()[0], getPos()[1] + 1 };
                setPos(pos);

                setWasRight(true);

            } else if (config[getPos()[0]][getPos()[1] - 1] == 3) { // GAUCHE
                int[] pos = { getPos()[0], getPos()[1] - 1 };
                setPos(pos);

                setWasRight(false);

            }

        }
        return false;

    }

    public boolean moveAgain() {
        if (config[getPos()[0] + 1][getPos()[1]] == 5 || config[getPos()[0]][getPos()[1] + 1] == 5
                || config[getPos()[0]][getPos()[1] - 1] == 5) {
            return true;
        }

        if (config[getPos()[0]][getPos()[1] + 1] == 3 &&
                config[getPos()[0]][getPos()[1] - 1] == 3) {

            if (wasRight()) {
                int[] pos = { getPos()[0], getPos()[1] + 1 };
                setPos(pos);

            } else {
                int[] pos = { getPos()[0], getPos()[1] - 1 };
                setPos(pos);

            }

        } else if (config[getPos()[0] + 1][getPos()[1]] == 3) { // BAS
            int[] pos = { getPos()[0] + 1, getPos()[1] };
            setPos(pos);

        } else if (config[getPos()[0]][getPos()[1] + 1] == 3) { // DROITE
            int[] pos = { getPos()[0], getPos()[1] + 1 };
            setPos(pos);

            setWasRight(true);

        } else if (config[getPos()[0]][getPos()[1] - 1] == 3) { // GAUCHE
            int[] pos = { getPos()[0], getPos()[1] - 1 };
            setPos(pos);

            setWasRight(false);

        }
        return false;
    }

    public String toString() {
        return "NOM: " + getName()
                + " PV: " + getValue()
                + " TYPE: " + getType()[0] + " et " + getType()[1]
                + " VITESSE: " + getSpeed()
                + " MECHANT "
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

    public boolean wasRight() {
        return wasRight;
    }

    public void setWasRight(boolean wasRight) {
        this.wasRight = wasRight;
    }

    public boolean isBoss() {
        return boss;
    }
}
