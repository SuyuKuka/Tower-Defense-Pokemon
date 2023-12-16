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
    private ArrayList<Ennemi> zoneTarget = new ArrayList<Ennemi>();
    private int level;
    private int[] pos;
    private Skill[] skills = new Skill[4];
    private int select = 0;
    private int totalSkill = 0;

    private static ArrayList<Tower> TList = new ArrayList<Tower>();
    public static final Tower LIST = new Tower();

    public Tower(String name, double atkPhysical, double atkSpecial, Type type1, Type type2) {
        super(name, 0, atkPhysical, atkSpecial, type1, type2, true);
        this.rayon = Rayon.SMALL;
        exp = 0;
        TList.add(this);

    }

    private Tower() {
        level = 1;
    }

    public int[] putTower(String st) { // TERMINAL
        int posX;
        int posY;

        String alphaPart = st.replaceAll("[^A-Za-z]", "");
        String numPart = st.replaceAll("[^0-9]", "");
        
        if (!alphaPart.isEmpty() && !numPart.isEmpty()) {
            posX = Character.toUpperCase(alphaPart.charAt(0)) - 'A';
            posY = Integer.parseInt(numPart) - 1;
        } else if (st.matches("[A-Za-z]\\d+")) {
            // Si alphabet puis chiffre
            posX = Character.toUpperCase(st.charAt(0)) - 'A';
            posY = Integer.parseInt(st.replaceAll("[^0-9]", "")) - 1;
        } else if (st.matches("\\d+[A-Za-z]")) {
            // Si chiffre puis alphabet
            posX = Character.toUpperCase(st.replaceAll("[^A-Za-z]", "").charAt(0)) - 'A';
            posY = Integer.parseInt(st.replaceAll("[^0-9]", "")) - 1;
        } else {
            posX = -1;
            posY = -1;
        }

        int[] tab = {posX, posY};
        return tab;
    }

    public void attack(ArrayList<Tower> towerInMap, ArrayList<Ennemi> ennemiInMap, MapState m) { // TERMINAL (?)
        for (int i = 0; i < towerInMap.size(); i++) {
            Tower tower = towerInMap.get(i);
            if (skills[select].getUnique()) {
                tower.getUniqueTarget(tower, ennemiInMap);
                tower.attackUnique();
                if(tower.uniqueTarget!=null){
                    if (tower.uniqueTarget.getPv() <= 0) {
                        m.isDead(tower.uniqueTarget);
                        tower.winExp(tower.uniqueTarget);
                        tower.levelUp();
                    }
                }
            } else {
                getZoneTarget(tower, ennemiInMap);
                if (tower.zoneTarget.size() != 0) {
                    tower.attackZone();
                    for (Ennemi e : tower.zoneTarget) {
                        if (e.getPv() <= 0) {
                            m.isDead(e);
                            tower.winExp(e);
                            tower.levelUp();
                        }
                    }
                }
            }
        }

    }

    private void attackUnique() {
        if (uniqueTarget == null) {
            System.out.println("Pas de cible");
        }else{
            if (skills[select].getPhysicalDamage() != 0 && skills[select].getSpecialDamage() == 0) {
                if(Type.typeChart(skills[select], uniqueTarget)==0){
                    System.out.println("Immunisé");
                }else{
                    double physical = (uniqueTarget.getPhysical() - getPhysical() - skills[select].getPhysicalDamage())
                            * Type.typeChart(skills[select], uniqueTarget);
                    if(skills[select].getType()==getType()[0] || skills[select].getType()==getType()[1]){
                            physical *= 0.5;
                        }
                    if (physical >= 0) {
                        // Cas positif donc retire -1
                        uniqueTarget.setPv(uniqueTarget.getPv() - 1);
                    } else {
                        // Cas négatif donc retire special vie
                        uniqueTarget.setPv(uniqueTarget.getPv() + physical);
                    }
                    System.out.println(getName() + " a attaqué " + uniqueTarget.getName());
                    System.out.println(getName() + " a infligé " + physical + "pv, il lui reste " + uniqueTarget.getPv() + "pv");
                }
            } else if (skills[select].getPhysicalDamage() == 0 && skills[select].getSpecialDamage() != 0) {
                if(Type.typeChart(skills[select], uniqueTarget)==0){
                    System.out.println("Immunisé");
                }else{
                    int special = (int) ((uniqueTarget.getSpecial() - getSpecial() - skills[select].getSpecialDamage())
                            * Type.typeChart(skills[select], uniqueTarget));
                    if(skills[select].getType()==getType()[0] || skills[select].getType()==getType()[1]){
                        special *= 0.5;
                    }
                    if (special >= 0) {
                        // Cas positif donc retire -1
                        uniqueTarget.setPv(uniqueTarget.getPv() - 1);
                    } else {
                        // Cas négatif donc retire special vie
                        uniqueTarget.setPv(uniqueTarget.getPv() + special);
                    }
                    System.out.println(getName() + " a attaqué " + uniqueTarget.getName());
                    System.out.println(getName() + " a infligé " + special + "pv, il lui reste " + uniqueTarget.getPv() + "pv");
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
                    if(Type.typeChart(skills[select], e)==0){
                        System.out.println("Immunisé");
                    }else{
                        double physical = (e.getPhysical() - getPhysical() - skills[select].getPhysicalDamage())
                                * Type.typeChart(skills[select], e);
                        if(skills[select].getType()==getType()[0] || skills[select].getType()==getType()[1]){
                            physical *= 0.5;
                        }
                        if (physical >= 0) {
                            // Cas positif donc retire -1
                            e.setPv(e.getPv() - 1);
                        } else {
                            // Cas négatif donc retire special vie
                            e.setPv(e.getPv() + physical);
                        }
                        System.out.println(getName() + " a attaqué " + e.getName());
                        System.out.println(getName() + " a infligé " + physical + "pv, il lui reste " + e.getPv() + "pv");
                    }
                } else if (skills[select].getPhysicalDamage() == 0 && skills[select].getSpecialDamage() != 0) {
                    if(Type.typeChart(skills[select], e)==0){
                        System.out.println("Immunisé");

                    }else{
                        double special = (e.getSpecial() - getSpecial() - skills[select].getSpecialDamage())
                                * Type.typeChart(skills[select], e);
                        if(skills[select].getType()==getType()[0] || skills[select].getType()==getType()[1]){
                            special *= 0.5;
                        }
                        if (special >= 0) {
                            // Cas positif donc retire -1
                            e.setPv(e.getPv() - 1);
                        } else {
                            // Cas négatif donc retire special vie
                            e.setPv(e.getPv() + special);
                        }
                        System.out.println(getName() + " a attaqué " + e.getName());
                        System.out.println(getName() + " a infligé " + special + "pv, il lui reste " + e.getPv() + "pv");
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
            System.out.println("Probleme, il peut pas avoir + de 4 skills");
        } else {
            if (totalSkill == 4) {
                System.out.println("Votre pokémon a 4 attaques.");
                System.out.println("Souhaitez-vous remplacer une attaque par " + newSkill.getName() + " ? O/N");
                Scanner sc = new Scanner(System.in);
                String st = sc.nextLine();
                if (st.equalsIgnoreCase("O")) {
                    replaceSkill(newSkill, sc);
                }
            } else {
                skills[totalSkill] = newSkill;
                totalSkill++;
                System.out.println(getName() + " a appris " + newSkill.getName());
                System.out.println(totalSkill);
            }
        }
    }

    private void replaceSkill(Skill newSkill, Scanner sc) {
        System.out.println("Par lequel souhaitez-vous remplacer? 1/2/3/4");
        prinSkill();
        int skillIndex = sc.nextInt();
        if (skillIndex >= 1 && skillIndex <= totalSkill) {
            System.out.println(skills[skillIndex - 1].getName() + " a bien été remplacé par " + newSkill.getName());
            skills[skillIndex - 1] = newSkill;
        } else {
            System.out.println("Choix invalide.");
        }
    }

    private void prinSkill() {
        for (int i = 0; i < totalSkill; i++) {
            System.out.println(i + 1 + ". " + skills[i].getName());
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

    public void getUniqueTarget(Tower tower, ArrayList<Ennemi> ennemiInMap) {
        for (Ennemi e : ennemiInMap) {
            if (tower.isInRayon(tower, e)) {
                tower.uniqueTarget=e;
            }
        }
    }

    public void getZoneTarget(Tower tower, ArrayList<Ennemi> ennemiInMap) {
        for (Ennemi e : ennemiInMap) {
            if (tower.isInRayon(tower, e)) {
                if(!tower.zoneTarget.contains(e)){
                    tower.zoneTarget.add(e);
                }
            } else {
                if (tower.zoneTarget.contains(e)) {
                    tower.zoneTarget.remove(e);
                }

            }
        }
    }

    private boolean isInRayon(Tower tower, Ennemi ennemi) {
        int deltaX = Math.abs(ennemi.getPos()[0] - tower.getPos()[0]);
        int deltaY = Math.abs(ennemi.getPos()[1] - tower.getPos()[1]);

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
