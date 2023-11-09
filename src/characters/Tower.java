package characters;

public class Tower extends Pokemon {

    public enum Rayon {
        NONE, SMALL, MEDIUM, LARGE, GLOBAL
    }

    private Rayon rayon;
    private Ennemi target;
    private double exp;

    public Tower(String name, int atk, Type type1, Type type2) {
        super(name, atk, type1, type2, true);
        this.rayon = Rayon.MEDIUM;
        target = null;
        exp = 0;
    }

    public void putTower(String st) {
        try {
            String alphaPart = st.replaceAll("[^A-Z]", "");
            String numPart = st.replaceAll("[^0-9]", "");

            int posX;
            if (!numPart.isEmpty()) {
                posX = Integer.parseInt(numPart) - 1;
            } else {
                posX = 0; // Si la partie numérique est vide, utilisezz 0 comme valeur par défaut.
            }

            int posY = alphaPart.charAt(0) - 'A';

            System.out.println(posX);
            System.out.println(posY);
        } catch (Exception e) {
            // Continue
        }

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
}
