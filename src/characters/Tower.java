package characters;

import java.util.List;

public class Tower extends Pokemon {

    public enum Rayon {
        NONE, SMALL, MEDIUM, LARGE, GLOBAL
    }

    private Rayon rayon;
    private Ennemi target;
    private double exp;
    private int level;
    private int[] pos;

    public Tower(String name, int atk, Type type1, Type type2) {
        super(name, atk, type1, type2, true);
        this.rayon = Rayon.MEDIUM;
        target = null;
        exp = 0;
        level = 5;
    }

    public int[] putTower(String st) {
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
            System.out.println(posX + " " + posY);
            return tab;
        } catch (Exception e) {
            int[] tab = { -1, -1 };
            return tab;
        }
    }

    public void attaque(List<Ennemi> ennemiInMap) {
        target = getTarget(ennemiInMap);
        if (target != null) {
            System.out.println("J'ai attaqué");
            target.setValue(target.getValue() - getValue());
            if (target.getValue() <= 0) {
                ennemiInMap.remove(target);
                winExp(target);
                levelUp();
            }
        }
    }

    private void winExp(Ennemi target) {
        this.exp += target.getValue() / 3;
    }

    private void levelUp() {

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
    public int getRayonValue(Rayon rayon) {
        if (rayon == Rayon.NONE) {
            return 0;
        } else if (rayon == Rayon.SMALL) {
            return 3;
        } else if (rayon == Rayon.MEDIUM) {
            return 5;
        } else if (rayon == Rayon.LARGE) {
            return 7;
        } else {
            return 1000; // Global?
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

    public Ennemi getTarget(List<Ennemi> ennemiInMap) {
        // Parcours la liste des ennemis pour trouver celui dans le rayon de la tour
        for (Ennemi ennemi : ennemiInMap) {
            if (estDansRayon(ennemi)) {
                return ennemi;
            }
        }
        return null; // Aucun ennemi dans le rayon
    }

    private boolean estDansRayon(Ennemi ennemi) {
        // Implémente la logique pour vérifier si l'ennemi est dans le rayon de la tour
        int distance = calculerDistance(ennemi.getPos(), getPos());
        int rayonTour = getRayonValue(rayon);
        return distance <= rayonTour;
    }

    private int calculerDistance(int[] position1, int[] position2) {
        // Implémente la logique pour calculer la distance entre deux positions
        // (par exemple, la distance euclidienne)
        int deltaX = position1[0] - position2[0];
        int deltaY = position1[1] - position2[1];
        return (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
}
