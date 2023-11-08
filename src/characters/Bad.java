package characters;

public class Bad extends Pokemon {

    public Bad(String name, int value, Type type1, Type type2) {
        super(name, value, type1, type2, false);
    }

    public String toString() {
        return "NOM: " + getName()
                + " \nPV: " + getValue()
                + " \nTYPE: " + getType()[0] + " et " + getType()[1]
                + " \nVITESSE: " + getSpeed()
                + "\nMECHANT\n";
    }
}
