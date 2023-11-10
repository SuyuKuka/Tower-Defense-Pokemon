package characters;

import java.util.ArrayList;

public class Tower extends Pokemon {

    public enum Rayon {
        NONE, SMALL, MEDIUM, LARGE, GLOBAL
    }

    private Rayon rayon;
    private Ennemi target; // TODO
    private double exp;

    private static ArrayList<Tower> TList = new ArrayList<Tower>();
    public static final Tower LIST = new Tower();

    public Tower(String name, int atk, Type type1, Type type2) {
        super(name, atk, type1, type2, true);
        this.rayon = Rayon.SMALL;
        target = null;
        exp = 0;
        TList.add(this);
    }

    private Tower() {
    }

    public String toString() {
        return "NOM: " + getName()
                + "\nEXP: " + exp
                + " \nATK: " + getValue()
                + " \nTYPE: " + getType()[0] + " et " + getType()[1]
                + " \nVITESSE: " + getSpeed()
                + " \nPORTEE " + rayon
                + "\nGENTIL\n";
    }

    // NONE, SMALL, MEDIUM, LARGE, GLOBAL
    private int setRayonValue(Rayon rayon) {
        setRayon(rayon);
        if (rayon == Rayon.NONE) {
            return 0;
        } else if (rayon == Rayon.SMALL) {
            return 1;
        } else if (rayon == Rayon.MEDIUM) {
            return 3;
        } else if (rayon == Rayon.LARGE) {
            return 5;
        } else {
            return -1; // Global?
            // return taille de map *2 TODO
        }
    }

    public void setRayon(Rayon r) {
        this.rayon = r;
    }

    public Rayon getRayon() {
        return rayon;
    }

    public void setTarget(Ennemi b) {
        target = b;
    }

    public ArrayList<Tower> getList() {
        return TList;
    }
}
