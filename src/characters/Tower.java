package characters;

import java.util.ArrayList;

import model.MapState;

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
        this.rayon = Rayon.SMALL;
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
            return tab;
        } catch (Exception e) {
            int[] tab = { -1, -1 };
            return tab;
        }
    }

    public void attaque(ArrayList<Tower> towerInMap, ArrayList<Ennemi> ennemiInMap, MapState a) {
        for (int i = 0; i < towerInMap.size(); i++) {
            Tower tower = towerInMap.get(i);
            Ennemi target = tower.getTarget(ennemiInMap);
    
            if (target != null) {
                tower.setTarget(target);
                target.setValue(target.getValue() - tower.getValue());
                System.out.println("J'ai attaqué");
                System.out.println(target);
    
                if (target.getValue() <= 0) {
                    a.isDead(target);
                    tower.winExp(target);
                    tower.levelUp();
                }
            }
        }
    }
    
    
    

    private void winExp(Ennemi target) {
        this.exp += target.getValue() / 3;
    }

    private void levelUp() {
        System.out.println(exp);
        if(exp>5){
            System.out.println("levelUp");
            level++;
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

    public void setTarget(Ennemi e) {
        target = e;
    }

    public Ennemi getTarget(ArrayList<Ennemi> ennemiInMap) {
        for(Ennemi e: ennemiInMap){
             if (estDansRayon(e)){
                return e;
             }
        }
        return null;
    }

    private boolean estDansRayon(Ennemi ennemi) {
        int deltaX = Math.abs(ennemi.getPos()[0] - getPos()[0]);
        int deltaY = Math.abs(ennemi.getPos()[1] - getPos()[1]);

        int distance = deltaX+deltaY;
        int towerRayon = getRayonValue(rayon);

        return distance < towerRayon && deltaX <= towerRayon/2 && deltaY <= towerRayon/2;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
}
