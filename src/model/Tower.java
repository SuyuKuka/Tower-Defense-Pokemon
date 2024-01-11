package model;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

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

import graphics.*;

public class Tower extends Pokemon {
    public enum Rayon {
        NONE, SMALL, MEDIUM, LARGE, GLOBAL
    }

    public enum State {
        PC, TEAM, LOCKED
    }

    //GRAPHICS
    private Tiles tiles;
    private Slot slot;
    private TowerGUI gui = new TowerGUI(this);
    private JPanel rangePanel;
    private TowerManagement manager;

    //STATS
    private Rayon rayon;
    private double exp;
    private int price;
    private int level;
    private int[] pos;
    private int atkCd;
    private int baseCd;

    //SKILLS
    private Skill[] skills = new Skill[4];
    private int select = 0;
    private int totalSkill = 0;
    private Ennemi uniqueTarget;
    private ArrayList<Ennemi> zoneTarget = new ArrayList<Ennemi>();

    private State state;

    //LIST
    private static ArrayList<Tower> tList = new ArrayList<Tower>();
    public static final Tower LIST = new Tower();

    //OPTIONEL
    private int killCount = 0;

    public Tower(String name, double atkPhysical, double atkSpecial, Types type1, Types type2, String sprite,
            State state, int atkCd, Tower.Rayon r, int price) {
        super(name, 0, atkPhysical, atkSpecial, type1, type2, true, sprite);
        exp = 0;
        tList.add(this);
        level = 1;
        this.state = state;
        baseCd = atkCd;
        this.rayon = r;
        this.price = price;
    }

    private Tower() {
        level = 1;
    }

    public int[] putTower(String st) { // VIA TERMINAL
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
            int[] tab = {posY, posX};
            return tab;
        } catch (Exception e) {
            int[] tab = {-1, -1};
            return tab;
        }
    }

    public void attack(ArrayList<Ennemi> ennemiInMap, MapState m) { // ATK WITH CD
        if (gui != null) {
            if (gui.isVisible()) {
                gui.updatePkm();
            }
        }
        if (skills[select].getPatern() == Skill.Pattern.UNIQUE) {
            getUniqueTarget(this, ennemiInMap);
            if (atkCd <= 0) {
                atkCd = baseCd;
                attackUnique();
            } else {
                atkCd = atkCd - 1;
            }
            if (uniqueTarget != null) {
                if (uniqueTarget.getPv() <= 0) {
                    m.isDead(uniqueTarget);
                    winExp(uniqueTarget);
                    levelUp();
                    uniqueTarget = null;
                    killCount++;
                }
            }
        } else if (skills[select].getPatern() == Skill.Pattern.AURA) {
            getZoneTarget(this, ennemiInMap);
            if (zoneTarget.size() != 0) {
                if (atkCd <= 0) {
                    atkCd = baseCd;
                    attackZone();
                } else {
                    atkCd = atkCd - 1;
                }
                for (int i = 0; i < zoneTarget.size(); i++) {
                    Ennemi e = zoneTarget.get(i);
                    if (e.getPv() <= 0) {
                        m.isDead(e);
                        winExp(e);
                        levelUp();
                        zoneTarget.remove(e);
                        killCount++;
                    }
                }
            }
        }
    }

    private void winExp(Ennemi target) {
        multiexp(target);
        setExp(getExp() + Math.abs(target.getTotalPv() / 2));
    }

    private void multiexp(Ennemi e) {
        for (Tower tower : Player.INSTANCE.getTeam()) {
            winLessExp(e, tower);
            tower.levelUp();
        }
    }

    private void winLessExp(Ennemi target, Tower tower) {
        tower.setExp(tower.getExp() + Math.abs(target.getTotalPv() / 5));
    }

    public double getExp() {
        return exp;
    }

    private void attackUnique() {
        if (uniqueTarget != null) {
            uniqueTarget.setProjectile(new Projectile(uniqueTarget, this, "ressources/projectiles/" + getCurrentSkill(getSelect()).getType() + ".gif"));
            uniqueTarget.getProjectile().setVisible(true);

            if (skills[select].getPhysicalDamage() != 0 && skills[select].getSpecialDamage() == 0) {
                if (Types.typeChart(skills[select], uniqueTarget) != 0) {
                    double physical = (uniqueTarget.getPhysical() - getPhysical() - skills[select].getPhysicalDamage())
                            * Types.typeChart(skills[select], uniqueTarget);
                    if (skills[select].getType() == getType()[0] || skills[select].getType() == getType()[1]) {
                        physical *= 0.5;
                    }
                    if (physical >= 0) {
                        // Cas positif donc retire -1
                        uniqueTarget.setPv(uniqueTarget.getPv() - 1);
                    } else {
                        // Cas negatif donc retire special vie
                        uniqueTarget.setPv(uniqueTarget.getPv() + physical);
                    }
                    // System.out.println(getName() + " a attaque " + uniqueTarget.getName());
                    // System.out.println(
                    // getName() + " a inflige " + physical + "pv, il lui reste " +
                    // uniqueTarget.getPv() + "pv");
                }
            } else if (skills[select].getPhysicalDamage() == 0 && skills[select].getSpecialDamage() != 0) {
                if (Types.typeChart(skills[select], uniqueTarget) != 0) {
                    int special = (int) ((uniqueTarget.getSpecial() - getSpecial() - skills[select].getSpecialDamage())
                            * Types.typeChart(skills[select], uniqueTarget));
                    if (skills[select].getType() == getType()[0] || skills[select].getType() == getType()[1]) {
                        special *= 0.5;
                    }
                    if (special >= 0) {
                        // Cas positif donc retire -1
                        uniqueTarget.setPv(uniqueTarget.getPv() - 1);
                    } else {
                        // Cas negatif donc retire special vie
                        uniqueTarget.setPv(uniqueTarget.getPv() + special);
                    }
                    // System.out.println(getName() + " a attaque " + uniqueTarget.getName());
                    // System.out.println(
                    // getName() + " a inflige " + special + "pv, il lui reste " +
                    // uniqueTarget.getPv() + "pv");
                }
            }
        }
    }

    private void attackZone() {
        if (zoneTarget.size() != 0) {
            for (Ennemi e : zoneTarget) {
                e.setProjectile(new Projectile(e, this, "ressources/projectiles/" + getCurrentSkill(getSelect()).getType() + ".gif"));
                e.getProjectile().setVisible(true);

                if (skills[select].getPhysicalDamage() != 0 && skills[select].getSpecialDamage() == 0) {
                    if (Types.typeChart(skills[select], e) != 0) {
                        double physical = (e.getPhysical() - getPhysical() - skills[select].getPhysicalDamage())
                                * Types.typeChart(skills[select], e);
                        if (skills[select].getType() == getType()[0] || skills[select].getType() == getType()[1]) {
                            physical *= 0.5;
                        }
                        if (physical >= 0) {
                            // Cas positif donc retire -1
                            e.setPv(e.getPv() - 1);
                        } else {
                            // Cas negatif donc retire special vie
                            e.setPv(e.getPv() + physical);

                        }
                        // System.out.println(getName() + " a attaque " + e.getName());
                        // System.out
                        // .println(getName() + " a inflige " + physical + "pv, il lui reste " +
                        // e.getPv() + "pv");
                    }
                } else if (skills[select].getPhysicalDamage() == 0 && skills[select].getSpecialDamage() != 0) {
                    if (Types.typeChart(skills[select], e) != 0) {
                        double special = (e.getSpecial() - getSpecial() - skills[select].getSpecialDamage())
                                * Types.typeChart(skills[select], e);
                        if (skills[select].getType() == getType()[0] || skills[select].getType() == getType()[1]) {
                            special *= 0.5;
                        }
                        if (special >= 0) {
                            // Cas positif donc retire -1
                            e.setPv(e.getPv() - 1);
                        } else {
                            // Cas negatif donc retire special vie
                            e.setPv(e.getPv() + special);
                        }
                        // System.out.println(getName() + " a attaque " + e.getName());
                        // System.out
                        // .println(getName() + " a inflige " + special + "pv, il lui reste " +
                        // e.getPv() + "pv");
                    }
                }
            }
        }
    }

    private void levelUp() {
        if (level < 100) {
            Random r = new Random();
            if (exp > 5 * level) {
                level++;
                int addPhysical = r.nextInt(5);
                int addSpecial = r.nextInt(5);
                setPhysical(getPhysical() + addPhysical);
                setSpecial(getSpecial() + addSpecial);
                exp = 0;
                updatePokemon();

                if (manager != null) {
                    manager.updateManager(this);
                }
            }
        } else {
            level = 100;
        }
    }

    public int getTotalSkill() {
        return totalSkill;
    }

    public void learnSkill(Skill newSkill) {
        if (totalSkill > 4) {
            throw new IllegalArgumentException("Trop de capacité");
        } else {
            if (totalSkill == 4) {
                gui.opendLearnSkill(newSkill);
            } else {
                skills[totalSkill] = newSkill;
                totalSkill++;
            }
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

    public int getDiametreValue(Rayon r) {
        if (r == Rayon.NONE) {
            return 0;
        } else if (r == Rayon.SMALL) {
            return 4;
        } else if (r == Rayon.MEDIUM) {
            return 7;
        } else if (r == Rayon.LARGE) {
            return 11;
        } else {
            return 20;
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
            if (tower.isInRayon(e)) {
                tower.uniqueTarget = e;
                break;
            }
        }
    }

    public void getZoneTarget(Tower tower, ArrayList<Ennemi> ennemiInMap) {
        for (Ennemi e : ennemiInMap) {
            if (tower.isInRayon(e)) {
                if (!tower.zoneTarget.contains(e)) {
                    tower.zoneTarget.add(e);
                }
            } else {
                tower.zoneTarget.remove(e);
            }
        }
    }

    private void updatePokemon() {
        if (this instanceof Starter.Salameche) {
            ((Starter.Salameche) this).evolution();
            ((Starter.Salameche) this).unlock();
        }
        if (this instanceof Starter.Arcko) {
            ((Starter.Arcko) this).evolution();
            ((Starter.Arcko) this).unlock();
        }
        if (this instanceof Starter.Moustillon) {
            ((Starter.Moustillon) this).evolution();
            ((Starter.Moustillon) this).unlock();
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

    public boolean isInRayon(Ennemi ennemi) {
        double deltaX = Math.abs(ennemi.getJLabelSprite().getLocation().getX() / 32 - getPos()[1]);
        double deltaY = Math.abs(ennemi.getJLabelSprite().getLocation().getY() / 32 - getPos()[0]);
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        double towerRayon = (double) getDiametreValue(rayon) / 2;
        return distance < towerRayon;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public ArrayList<Tower> getList() {
        return tList;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public Skill getCurrentSkill(int i) {
        return skills[i];
    }

    public void setCurrentSkill(Skill s) {
        for (int i = 0; i < getSkills().length; i++) {
            if (getSkills()[i] == s) {
                setSelect(i);
            }
        }
    }

    public void setSelect(int select) {
        this.select = select;
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

    public void setTiles(Tiles tiles) {
        this.tiles = tiles;
    }

    public Tiles getTiles() {
        return tiles;
    }

    public static ArrayList<Tower> getTList() {
        return tList;
    }

    public int getKillCount() {
        return killCount;
    }

    public void setManager(TowerManagement manager) {
        this.manager = manager;
    }

    public int getAtkCd() {
        return atkCd;
    }

    public void setAtkCd(int atkCd) {
        this.baseCd = atkCd;
    }

    public int getSelect() {
        return select;
    }

    public TowerGUI getGUI() {
        return gui;
    }

    public void setGUI(TowerGUI newGUI) {
        this.gui = newGUI;
    }

    public void setRangePanel(JPanel rangePanel) {
        this.rangePanel = rangePanel;
    }

    public JPanel getRangePanel() {
        return rangePanel;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
