package characters;

import java.util.ArrayList;

import config.Map;

public class Ennemi extends Pokemon {
    private int[] pos;
    private boolean wasRight = false;
    private static ArrayList<Ennemi> EList = new ArrayList<>();
    public static final Ennemi LIST = new Ennemi();

    public Ennemi(String name, int pv, Type type1, Type type2) {
        super(name, pv, type1, type2, false);
        EList.add(this);

    }

    private Ennemi() {
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

    public String toString() {
        return "NOM: " + getName()
                + " \nPV: " + getValue()
                + " \nTYPE: " + getType()[0] + " et " + getType()[1]
                + " \nVITESSE: " + getSpeed()
                + "\nMECHANT\n";
    }

    public ArrayList<Ennemi> getList() {
        return EList;
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
}
