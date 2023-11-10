package characters;

public class Ennemi extends Pokemon {
    private int[] pos;

    public Ennemi(String name, int pv, Type type1, Type type2) {
        super(name, pv, type1, type2, false);
    }

    public String toString() {
        return "NOM: " + getName()
                + " \nPV: " + getValue()
                + " \nTYPE: " + getType()[0] + " et " + getType()[1]
                + " \nVITESSE: " + getSpeed()
                + "\nMECHANT\n";
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
}
