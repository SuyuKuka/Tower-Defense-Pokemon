package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import characters.Anchwatt;
import characters.Chartor;
import characters.Ecrapince;
import characters.Escargaume;
import characters.Gringolem;
import characters.Lilia;
import characters.Makuhita;
import characters.MotismaFroid;
import characters.Mysdibule;
import characters.Ningale;
import characters.Nodulithe;
import characters.Obalie;
import characters.Rozbouton;
import characters.Starter;
import characters.Tarsal;
import characters.Terhal;
import characters.Tylton;
import graphics.EnnemiPresent;
import graphics.UpdateMap;

import java.io.File;
import java.io.FileNotFoundException;

public class Tower extends Pokemon {

    public enum Rayon {
        NONE, SMALL, MEDIUM, LARGE, GLOBAL
    }

    // ref vers tout les this
    /*
     * 
     * private Starter starter;
     * private Anchwatt anchwatt;
     * private Chartor chartor;
     * private Ecrapince ecrapince;
     * private Escargaume escargaume;
     * private Gringolem gringolem;
     * private Lilia lilia;
     * private Makuhita makuhita;
     * private MotismaFroid motismaFroid;
     * private Mysdibule mysdibule;
     * private Ningale ningale;
     * private Nodulithe nodulithe;
     * private Obalie obalie;
     * private Rozbouton rozbouton;
     * private Tarsal tarsal;
     * private Terhal terhal;
     * private Tylton tylton;
     */
    //
    private UpdateMap.Tiles tiles;
    private Rayon rayon;
    private double exp;
    private Ennemi uniqueTarget;
    private ArrayList<Ennemi> zoneTarget = new ArrayList<Ennemi>();
    private int level;
    private int[] pos;
    private Skill[] skills = new Skill[4];
    private int select = 0;
    private int totalSkill = 0;
    private int price;
    private State state = State.LOCKED;

    private static ArrayList<Tower> TList = new ArrayList<Tower>();
    public static final Tower LIST = new Tower();

    public Tower(String name, double atkPhysical, double atkSpecial, Types type1, Types type2, String sprite,
            State state) {
        super(name, 0, atkPhysical, atkSpecial, type1, type2, true, sprite);
        this.rayon = Rayon.SMALL;
        exp = 0;
        TList.add(this);
        level = 1;
    }

    private Tower() {
        level = 1;
    }

    public Skill loadSkill(String skillName) throws FileNotFoundException {
        File file = new File("ressources/config/Pokemon Moves.csv");
        Scanner sc = new Scanner(file);
        int phys = 0;
        int spe = 0;
        while (sc.hasNext()) {
            String[] info = new String[9];
            info = sc.nextLine().split(";");
            if (info[7].equals(skillName)) {
                if (info[2].equals("Physical")) {
                    phys = Integer.valueOf(info[4]);
                    spe = 0;
                } else if (info[2].equals("Special")) {
                    phys = 0;
                    spe = Integer.valueOf(info[4]);
                }

                return new Skill(skillName, phys, spe, Types.stringToType(info[1]), exp, isTower(), skillName);
            }
        }
        sc.close();
        return null;
    }

    public int[] putTower(String st) {
        try {
            String alphaPart = st.replaceAll("[^A-Za-z]", "");
            String numPart = st.replaceAll("[^0-9]", "");

            int posX;
            int posY;

            if (!st.matches("[A-Za-z]+\\d+")) {
                posX = -1;
            } else {
                posX = alphaPart.toUpperCase().charAt(0) - 'A';
            }

            if (!numPart.isEmpty()) {
                posY = Integer.parseInt(numPart) - 1;
            } else {
                posY = -1;
            }

            int[] tab = { posY, posX };
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
                tower.getUniqueTarget(tower, ennemiInMap);
                tower.attackUnique();
                if (tower.uniqueTarget != null) {
                    if (tower.uniqueTarget.getPv() <= 0) {
                        m.isDead(tower.uniqueTarget);
                        tower.winExp(tower.uniqueTarget);
                        tower.levelUp();
                        tower.uniqueTarget = null;
                    }
                }
            } else {
                tower.getZoneTarget(tower, ennemiInMap);
                if (tower.zoneTarget.size() != 0) {
                    tower.attackZone();
                    for (Ennemi e : tower.zoneTarget) {
                        if (e.getPv() <= 0) {
                            m.isDead(e);
                            tower.winExp(e);
                            tower.levelUp();
                            tower.zoneTarget.remove(e);
                        }
                    }
                }
            }
        }
    }

    private void attackUnique() {
        if (uniqueTarget == null) {
            // System.out.println("Pas de cible");
        } else {
            if (skills[select].getPhysicalDamage() != 0 && skills[select].getSpecialDamage() == 0) {
                if (Types.typeChart(skills[select], uniqueTarget) == 0) {
                    // System.out.println("Immunise");
                } else {
                    double physical = (uniqueTarget.getPhysical() - getPhysical() - skills[select].getPhysicalDamage())
                            * Types.typeChart(skills[select], uniqueTarget);
                    if (skills[select].getType() == getType()[0] || skills[select].getType() == getType()[1]) {
                        physical *= 0.5;
                    }
                    if (physical >= 0) {
                        // Cas positif donc retire -1
                        uniqueTarget.setPv(uniqueTarget.getPv() - 1);
                        EnnemiPresent.updateEnnemiHealth(uniqueTarget, -1);
                    } else {
                        // Cas negatif donc retire special vie
                        uniqueTarget.setPv(uniqueTarget.getPv() + physical);
                        EnnemiPresent.updateEnnemiHealth(uniqueTarget, (int) physical);
                    }
                    // System.out.println(getName() + " a attaque " + uniqueTarget.getName());
                    // System.out.println(
                    // getName() + " a inflige " + physical + "pv, il lui reste " +
                    // uniqueTarget.getPv() + "pv");
                }
            } else if (skills[select].getPhysicalDamage() == 0 && skills[select].getSpecialDamage() != 0) {
                if (Types.typeChart(skills[select], uniqueTarget) == 0) {
                    // System.out.println("Immunise");
                } else {
                    int special = (int) ((uniqueTarget.getSpecial() - getSpecial() - skills[select].getSpecialDamage())
                            * Types.typeChart(skills[select], uniqueTarget));
                    if (skills[select].getType() == getType()[0] || skills[select].getType() == getType()[1]) {
                        special *= 0.5;
                    }
                    if (special >= 0) {
                        // Cas positif donc retire -1
                        uniqueTarget.setPv(uniqueTarget.getPv() - 1);
                        EnnemiPresent.updateEnnemiHealth(uniqueTarget, -1);
                    } else {
                        // Cas negatif donc retire special vie
                        uniqueTarget.setPv(uniqueTarget.getPv() + special);
                        EnnemiPresent.updateEnnemiHealth(uniqueTarget, special);
                    }
                    // System.out.println(getName() + " a attaque " + uniqueTarget.getName());
                    // System.out.println(
                    // getName() + " a inflige " + special + "pv, il lui reste " +
                    // uniqueTarget.getPv() + "pv");
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
                    if (Types.typeChart(skills[select], e) == 0) {
                        // System.out.println("Immunise");
                    } else {
                        double physical = (e.getPhysical() - getPhysical() - skills[select].getPhysicalDamage())
                                * Types.typeChart(skills[select], e);
                        if (skills[select].getType() == getType()[0] || skills[select].getType() == getType()[1]) {
                            physical *= 0.5;
                        }
                        if (physical >= 0) {
                            // Cas positif donc retire -1
                            e.setPv(e.getPv() - 1);
                            EnnemiPresent.updateEnnemiHealth(uniqueTarget, -1);
                        } else {
                            // Cas negatif donc retire special vie
                            e.setPv(e.getPv() + physical);
                            EnnemiPresent.updateEnnemiHealth(uniqueTarget, (int) physical);
                        }
                        // System.out.println(getName() + " a attaque " + e.getName());
                        // System.out
                        // .println(getName() + " a inflige " + physical + "pv, il lui reste " +
                        // e.getPv() + "pv");
                    }
                } else if (skills[select].getPhysicalDamage() == 0 && skills[select].getSpecialDamage() != 0) {
                    if (Types.typeChart(skills[select], e) == 0) {
                        // System.out.println("Immunise");
                    } else {
                        double special = (e.getSpecial() - getSpecial() - skills[select].getSpecialDamage())
                                * Types.typeChart(skills[select], e);
                        if (skills[select].getType() == getType()[0] || skills[select].getType() == getType()[1]) {
                            special *= 0.5;
                        }
                        if (special >= 0) {
                            // Cas positif donc retire -1
                            e.setPv(e.getPv() - 1);
                            EnnemiPresent.updateEnnemiHealth(uniqueTarget, -1);
                        } else {
                            // Cas negatif donc retire special vie
                            e.setPv(e.getPv() + special);
                            EnnemiPresent.updateEnnemiHealth(uniqueTarget, (int) special);
                        }
                        // System.out.println(getName() + " a attaque " + e.getName());
                        // System.out
                        // .println(getName() + " a inflige " + special + "pv, il lui reste " +
                        // e.getPv() + "pv");
                    }
                } else {
                    // Attaque de statut donc juste applique effet?
                    System.out.println("ATQ STATUT");
                }
            }
        }
    }

    private void winExp(Ennemi target) {
        this.exp += Math.abs(target.getTotalPv() / 3);
    }

    private void levelUp() {
        Random r = new Random();
        if (exp > 5 * level) {
            level++;
            System.out.println("levelUp " + level);
            int addPhysical = r.nextInt(5);
            int addSpecial = r.nextInt(5);// fois un multiplicateur?
            setPhysical(getPhysical() + addPhysical);
            setSpecial(getSpecial() + addSpecial);
            exp = 0;
            updatePokemon();
        }
    }

    public int getTotalSkill() {
        return totalSkill;
    }

    public void learnSkill(Skill newSkill) {
        if (totalSkill > 4) {
            System.out.println("Probleme, il peut pas avoir + de 4 skills");
        } else {
            if (totalSkill == 4) {
                System.out.println("Votre pokemon a 4 attaques.");
                System.out.println("Souhaitez-vous remplacer une attaque par " + newSkill.getName() + " ? O/N");
                Scanner sc = new Scanner(System.in);
                String st = sc.nextLine();
                if (st.equalsIgnoreCase("O")) {
                    replaceSkill(newSkill, sc);
                }
            } else {
                skills[totalSkill] = newSkill;
                totalSkill++;
                // System.out.println(getName() + " a appris " + newSkill.getName());
                // System.out.println(totalSkill);
            }
        }
    }

    private void replaceSkill(Skill newSkill, Scanner sc) {
        System.out.println("Par lequel souhaitez-vous remplacer? 1/2/3/4");
        printSkill();
        int skillIndex = sc.nextInt();
        if (skillIndex >= 1 && skillIndex <= totalSkill) {
            System.out.println(skills[skillIndex - 1].getName() + " a bien ete remplace par " + newSkill.getName());
            skills[skillIndex - 1] = newSkill;
        } else {
            System.out.println("Choix invalide.");
        }
    }

    private void printSkill() {
        for (int i = 0; i < totalSkill; i++) {
            System.out.println(i + 1 + ". " + skills[i].getName());
        }
    }

    private String arrayToString(Skill[] array) {
        StringBuilder result = new StringBuilder();
        for (Skill skill : array) {
            if (skill != null) {
                result.append(skill.toString()).append("#");
            } else {
                result.append("null#");
            }
        }
        return result.toString();
    }

    public String toString() {
        return getName()
                + ";" + getLevel()
                + ";" + exp
                + ";" + getPhysical()
                + ";" + getSpecial()
                + ";" + getType()[0]
                + ";" + getType()[1]
                + ";" + arrayToString(getSkills())
                + ";" + getSprite();
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
        tower.uniqueTarget = null;
        for (Ennemi e : ennemiInMap) {
            if (tower.isInRayon(tower, e)) {
                tower.uniqueTarget = e;
                break;
            }
        }
    }

    public void getZoneTarget(Tower tower, ArrayList<Ennemi> ennemiInMap) {
        for (Ennemi e : ennemiInMap) {
            if (tower.isInRayon(tower, e)) {
                if (!tower.zoneTarget.contains(e)) {
                    tower.zoneTarget.add(e);
                }
            } else {
                if (tower.zoneTarget.contains(e)) {
                    tower.zoneTarget.remove(e);
                }

            }
        }
    }

    private void updatePokemon() {
        // TODO IL MANQUE POUR LES STARTERS
        if (this instanceof Starter.Salameche) {
            ((Starter.Salameche) this).evolution();
            ((Starter.Salameche) this).unlock();
        }
        if (this instanceof Starter.Arcko) {
            ((Starter.Arcko) this).evolution();
            ((Starter.Arcko) this).unlock();
        }
        if (this instanceof Starter.Tiplouf) {
            ((Starter.Tiplouf) this).evolution();
            ((Starter.Tiplouf) this).unlock();
        }
        if (this instanceof Anchwatt) {
            ((Anchwatt) this).evolution();
            ((Anchwatt) this).unlock();
        }
        if (this instanceof Obalie) {
            ((Obalie) this).evolution();
            ((Obalie) this).unlock();
        }
        if (this instanceof Tylton) {
            ((Tylton) this).evolution();
            ((Tylton) this).unlock();
        }
        if (this instanceof Terhal) {
            ((Terhal) this).evolution();
            ((Terhal) this).unlock();
        }
        if (this instanceof Tarsal) {
            ((Tarsal) this).evolution();
            ((Tarsal) this).unlock();
        }
        if (this instanceof Rozbouton) {
            ((Rozbouton) this).evolution();
            ((Rozbouton) this).unlock();
        }
        if (this instanceof Ningale) {
            ((Ningale) this).evolution();
            ((Ningale) this).unlock();
        }
        if (this instanceof Nodulithe) {
            ((Nodulithe) this).evolution();
            ((Nodulithe) this).unlock();
        }
        if (this instanceof Mysdibule) {
            ((Mysdibule) this).evolution();
            ((Mysdibule) this).unlock();
        }
        if (this instanceof MotismaFroid) {
            ((MotismaFroid) this).evolution();
            ((MotismaFroid) this).unlock();
        }
        if (this instanceof Makuhita) {
            ((Makuhita) this).evolution();
            ((Makuhita) this).unlock();
        }
        if (this instanceof Lilia) {
            ((Lilia) this).evolution();
            ((Lilia) this).unlock();
        }
        if (this instanceof Gringolem) {
            ((Gringolem) this).evolution();
            ((Gringolem) this).unlock();
        }
        if (this instanceof Escargaume) {
            ((Escargaume) this).evolution();
            ((Escargaume) this).unlock();
        }
        if (this instanceof Ecrapince) {
            ((Ecrapince) this).evolution();
            ((Ecrapince) this).unlock();
        }
        if (this instanceof Chartor) {
            ((Chartor) this).evolution();
            ((Chartor) this).unlock();
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
        super.setName(name);
    }

    public Skill getCurrentSkill(int select) {
        return skills[select];
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public void setTiles(UpdateMap.Tiles tiles) {
        this.tiles = tiles;
    }

    public UpdateMap.Tiles getTiles() {
        return tiles;
    }

    public static ArrayList<Tower> getTList() {
        return TList;
    }

}
