package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.MapState;

public class Tower extends Pokemon {

    public enum Rayon {
        NONE, SMALL, MEDIUM, LARGE, GLOBAL
    }

    private Rayon rayon;
    private double exp;
    private Ennemi uniqueTarget;
    private ArrayList<Ennemi> zoneTarget;
    private int level;
    private int[] pos;
    private Skill[] skills = new Skill[4];

    private static ArrayList<Tower> TList = new ArrayList<Tower>();
    public static final Tower LIST = new Tower();

    public Tower(String name, int atkPhysical, int atkSpecial, Type type1, Type type2) {
        super(name, 0, atkPhysical, atkSpecial, type1, type2, true);
        this.rayon = Rayon.SMALL;
        exp = 0;
        TList.add(this);
    }

    private Tower() {
        level = 5;
    }

    public int[] putTower(String st) { // TERMINAL
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

    public void attack(ArrayList<Tower> towerInMap, ArrayList<Ennemi> ennemiInMap, MapState m) { // TERMINAL (?)
        for (int i = 0; i < towerInMap.size(); i++) {
            Tower tower = towerInMap.get(i);
            // skill pas encore imprémenter
            // if (tower.getCurrentSkill.getUnique()) { //true = cible unique; false = cible
            // zone
            boolean test0 = true;
            if (test0) {
                tower.getUniqueTarget(ennemiInMap);
                attackUnique(tower);

                if (uniqueTarget.getPv() <= 0) {
                    m.isDead(uniqueTarget);
                    tower.winExp(uniqueTarget);
                    tower.levelUp();
                }
            } else {
                tower.getZoneTarget(ennemiInMap);
                attackZone(tower);

                for (Ennemi e : zoneTarget) {
                    if (e.getPv() <= 0) {
                        m.isDead(e);
                        tower.winExp(e);
                        tower.levelUp();
                    }
                }
            }
        }

    }

    private void attackUnique(Tower tower) {
        if (uniqueTarget != null) {
            // skill non encore implementer
            // if (tower.getCurrentSkill.getStat()) { // true == physique; false ==
            // speciale
            boolean test = true;
            if (test) {
                int physical = uniqueTarget.getPhysical() - tower.getPhysical();
                if (physical > 0) {// si def = 100 et atk = 20, physique = 80
                    uniqueTarget.setPv(uniqueTarget.getPv() - 1); // donc on retire -1
                } else {// si def=20 et atk=100, il fera physique = -80
                    uniqueTarget.setPv(uniqueTarget.getPv() + physical); // physique est négatif
                }
            } else {
                int special = uniqueTarget.getSpecial() - tower.getSpecial();
                if (special > 0) {// si def = 100 et atk = 20, special = 80
                    uniqueTarget.setPv(uniqueTarget.getPv() - 1); // donc on retire -1
                } else {// si def=20 et atk=100, il fera special = -80
                    uniqueTarget.setPv(uniqueTarget.getPv() + special); // special est négatif
                }
            }
        }
    }

    private void attackZone(Tower tower) {
        if (zoneTarget.size() != 0) {
            for (Ennemi e : zoneTarget) {
                boolean test = true;
                // skill non encore implementer
                // if (tower.getCurrentSkill.getStat()) { // true == physique; false ==
                // speciale
                if (test) {
                    int physical = e.getPhysical() - tower.getPhysical();
                    if (physical > 0) {// si def = 100 et atk = 20, physique = 80
                        e.setPv(e.getPv() - 1); // donc on retire -1
                    } else {// si def=20 et atk=100, il fera physique = -80
                        e.setPv(e.getPv() + physical); // physique est négatif
                    }
                } else {
                    int special = e.getSpecial() - tower.getSpecial();
                    if (special > 0) {// si def = 100 et atk = 20, special = 80
                        e.setPv(e.getPv() - 1); // donc on retire -1
                    } else {// si def=20 et atk=100, il fera special = -80
                        e.setPv(e.getPv() + special); // special est négatif
                    }
                }
            }
        }
    }

    private void winExp(Ennemi target) {
        this.exp += target.getPv() / 3;
    }

    private void levelUp() {
        System.out.println(exp);
        Random r = new Random();
        if (exp > 5) {
            System.out.println("levelUp");
            level++;
            int addPhysical = r.nextInt(5);
            int addSpecial = r.nextInt(5);// fois un multiplicateur?
            setPhysical(getPhysical() + addPhysical);
            setSpecial(getSpecial() + addSpecial);
            exp = 0;
        }
    }

    private boolean chooseSkill(Skill newSkill){
        if(skills.length==4){
            System.out.println("Voulez-vous remplacer une attaque? O/N");
            Scanner sc = new Scanner(System.in);
            
            if(sc.nextLine().equals("O")){
                System.out.println("Par lequel souhaitez vous le remplacer? 1/2/3/4");
                if(sc.nextLine().equals("1")){
                    skills[1]=newSkill;
                    sc.close();
                    return true;
                }else if(sc.nextLine().equals("2")){
                    skills[2]=newSkill;
                    sc.close();
                    return true;
                }else if(sc.nextLine().equals("3")){
                    skills[3]=newSkill;
                    sc.close();
                    return true;
                }else if(sc.nextLine().equals("4")){
                    skills[4]=newSkill;
                    sc.close();
                    return true;
                }else{
                    chooseSkill(newSkill);
                }

            }else if(sc.nextLine().equals("N")){
                sc.close();
                return false;
            }else{
                chooseSkill(newSkill);
            }
        }
        return false;
    }

    public String toString() {
        return "NOM: " + getName()
                + "\nEXP: " + exp
                + " \nATK PHYSIQUE: " + getPhysical()
                + " \nATK SPECIAL: " + getSpecial()
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
        uniqueTarget = e;
    }

    public void getUniqueTarget(ArrayList<Ennemi> ennemiInMap) {
        for (Ennemi e : ennemiInMap) {
            if (estDansRayon(e)) {
                setTarget(e);
            }
        }
    }

    public void getZoneTarget(ArrayList<Ennemi> ennemiInMap) {
        for (Ennemi e : ennemiInMap) {
            if (estDansRayon(e)) {
                zoneTarget.add(e);
            } else {
                if (zoneTarget.contains(e)) {
                    zoneTarget.remove(e);
                }

            }
        }
    }

    private boolean estDansRayon(Ennemi ennemi) {
        int deltaX = Math.abs(ennemi.getPos()[0] - getPos()[0]);
        int deltaY = Math.abs(ennemi.getPos()[1] - getPos()[1]);

        int distance = deltaX + deltaY;
        int towerRayon = getRayonValue(rayon);

        return distance < towerRayon && deltaX <= towerRayon / 2 && deltaY <= towerRayon / 2;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public ArrayList<Tower> getList() {
        return TList;
    }
}
