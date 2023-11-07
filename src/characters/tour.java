package characters;

public class Tour extends Characters {

    public enum Rayon {
        NONE, SMALL, MEDIUM, LARGE, GLOBAL
    }

    private Rayon rayon;

    public Tour(String name, int value, Type type1, Type type2) {
        super(name, value, type1, type2, true);
        this.rayon = Rayon.MEDIUM;
    }

    public void setRayon(Rayon r) {
        this.rayon = r;
    }

    public Rayon getRayon() {
        return rayon;
    }
}
