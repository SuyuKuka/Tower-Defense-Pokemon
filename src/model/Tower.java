package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
    private int select = 1;
    private int totalSkill = 0;

    private static ArrayList<Tower> TList = new ArrayList<Tower>();
    public static final Tower LIST = new Tower();

    public Tower(String name, int atkPhysical, int atkSpecial, Type type1, Type type2) {
        super(name, 0, atkPhysical, atkSpecial, type1, type2, true);
        this.rayon = Rayon.SMALL;
        exp = 0;
        TList.add(this);

    }

    private Tower() {
        level = 1;
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
            if (skills[select].getUnique()) {
                tower.getUniqueTarget(ennemiInMap);
                attackUnique();

                if (uniqueTarget.getPv() <= 0) {
                    m.isDead(uniqueTarget);
                    tower.winExp(uniqueTarget);
                    tower.levelUp();
                }
            } else {
                tower.getZoneTarget(ennemiInMap);
                attackZone();

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

    private void attackUnique() {
        if (uniqueTarget != null) {
            if (skills[select].getPhysicalDamage() != 0 && skills[select].getSpecialDamage() == 0) {
                int physical = (int) ((uniqueTarget.getPhysical() - getPhysical() - skills[select].getPhysicalDamage())
                        * Type.typeChart(skills[select], uniqueTarget));
                if (physical >= 0) {
                    // Cas positif donc retire -1
                    uniqueTarget.setPv(uniqueTarget.getPv() - 1);
                } else {
                    // Cas négatif donc retire special vie
                    uniqueTarget.setPv(uniqueTarget.getPv() + physical);
                }
            } else if (skills[select].getPhysicalDamage() == 0 && skills[select].getSpecialDamage() != 0) {
                int special = (int) ((uniqueTarget.getSpecial() - getSpecial() - skills[select].getSpecialDamage())
                        * Type.typeChart(skills[select], uniqueTarget));
                if (special >= 0) {
                    // Cas positif donc retire -1
                    uniqueTarget.setPv(uniqueTarget.getPv() - 1);
                } else {
                    // Cas négatif donc retire special vie
                    uniqueTarget.setPv(uniqueTarget.getPv() + special);
                }
            } else {
                // Attaque de statut donc effect?
                System.out.println("ATQ STATUT");
            }
        }
    }

    private void attackZone() {
        if (zoneTarget.size() != 0) {
            for (Ennemi e : zoneTarget) {
                if (skills[select].getPhysicalDamage() != 0 && skills[select].getSpecialDamage() == 0) {
                    int physical = (int) ((e.getPhysical() - getPhysical() - skills[select].getPhysicalDamage())
                            * Type.typeChart(skills[select], e));
                    if (physical >= 0) {
                        // Cas positif donc retire -1
                        e.setPv(e.getPv() - 1);
                    } else {
                        // Cas négatif donc retire special vie
                        e.setPv(e.getPv() + physical);
                    }
                } else if (skills[select].getPhysicalDamage() == 0 && skills[select].getSpecialDamage() != 0) {
                    int special = (int) ((e.getSpecial() - getSpecial() - skills[select].getSpecialDamage())
                            * Type.typeChart(skills[select], e));
                    if (special >= 0) {
                        // Cas positif donc retire -1
                        e.setPv(e.getPv() - 1);
                    } else {
                        // Cas négatif donc retire special vie
                        e.setPv(e.getPv() + special);
                    }
                } else {
                    // Attaque de statut donc juste applique effet?
                    System.out.println("ATQ STATUT");
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

    public void learnSkill(Skill newSkill) {
        if (totalSkill > 4) {
            System.out.println("Probleme");
        } else if (skills[3] != null) {
            System.out.println("Votre pokémon a actuellement 4 attaques.");
            System.out.println("Souhaitez-vous remplacer une attaque par " + newSkill.getName() + " ? O/N");
            Scanner sc = new Scanner(System.in);
            if (sc.nextLine().equals("O")) {
                System.out.println("Par lequel souhaitez vous remplacer? 1/2/3/4");
                for (int i = 1; i <= totalSkill + 1; i++) {
                    System.out.println(i + ". " + newSkill.getName());
                }
                if (sc.nextLine().equals("1")) {
                        skills[0] = newSkill;
                    } else if (sc.nextLine().equals("2")) {
                        skills[1] = newSkill;
                    } else if (sc.nextLine().equals("3")) {
                        skills[2] = newSkill;
                    } else if (sc.nextLine().equals("4")) {
                        skills[3] = newSkill;
                    } else {
                        learnSkill(newSkill);
                }
            }
            sc.close();
        } else {
            totalSkill++;
            skills[totalSkill] = newSkill;
            System.out.println("Ajouté" + newSkill.getName());
        }

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
            if (isInRayon(e)) {
                setTarget(e);
            }
        }
    }

    public void getZoneTarget(ArrayList<Ennemi> ennemiInMap) {
        for (Ennemi e : ennemiInMap) {
            if (isInRayon(e)) {
                zoneTarget.add(e);
            } else {
                if (zoneTarget.contains(e)) {
                    zoneTarget.remove(e);
                }

            }
        }
    }

    private boolean isInRayon(Ennemi ennemi) {
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

    public int getLevel() {
        return level;
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        super.setName(name);
    }

    public Skill getCurrentSkill(int select){
        return skills[select];
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }
}
