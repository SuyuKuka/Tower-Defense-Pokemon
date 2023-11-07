package characters;

public class Characters {

    public enum Speed {
        FAST, NORMAL, SLOW
    }

    public enum Type {
        NONE, EAU, FEU, SOL, VOL,
        POISON, NORMAL, SPECTRE, TENEBRE,
        ELECTIK, ACIER, ROCHE, DRAGON, COMBAT
    }

    private String name;
    private int value; // tour -> atk, bad -> pv
    private Speed speed; // tour -> vitesse atk, bad -> vitesse deplace
    private Type type1;
    private Type type2;
    private boolean isTower;

    public Characters(String name, int value, Type type1, Type type2, boolean isTower) {
        this.name = name;
        this.value = value;
        this.type1 = type1;
        this.type2 = type2;
        speed = Characters.Speed.NORMAL;
        this.isTower = isTower;
    }

    public void setSpeed(Speed sp) {
        speed = sp;
    }

    public boolean isTower() {
        return isTower;
    }

    public String toString() {
        return "NOM: " + name + " \nVALUE: " + value + " \nTYPE: " + type1 + " et " + type2 + " \nVITESSE: " + speed
                + "\n";
    }

}
