package characters;

import java.util.ArrayList;

public class Ennemi extends Pokemon {

    private static ArrayList<Ennemi> EList = new ArrayList<>();
    public static final Ennemi LIST = new Ennemi();

    public Ennemi(String name, int pv, Type type1, Type type2) {
        super(name, pv, type1, type2, false);
        EList.add(this);
    }

    private Ennemi() {
    }

    public String toString() {
        return "NOM: " + getName()
                + " \nPV: " + getValue()
                + " \nTYPE: " + getType()[0] + " et " + getType()[1]
                + " \nVITESSE: " + getSpeed()
                + "\nMECHANT\n";
    }

    public void move() {

    }

    public ArrayList<Ennemi> getList() {
        return EList;
    }
}
