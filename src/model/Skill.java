package model;

public class Skill {
    public String name;
    private int physicalDamage;
    private int specialDamage;
    private Types type;
    private double speedAtk;
    private Pattern p;
    private String description;

    public enum Pattern {
        UNIQUE, AOE, AURA
    }

    public Skill(String name, int physicalDamage, int specialDamage, Types type, Pattern p, String description) {
        this.name = name;
        this.physicalDamage = physicalDamage;
        this.specialDamage = specialDamage;
        this.type = type;
        this.p = p;
        this.description = description;
    }

    public String toString() {
        return this.getName()
                + "#" + this.getPhysicalDamage()
                + "#" + this.getSpecialDamage()
                + "#" + this.getType()
                + "#" + this.getSpeedAtk()
                + "#" + this.getPatern()
                + "#" + this.getDescription();
    }
    // tower.learnSkill(new Skill(skillName, skillPhysicalDamage,
    // skillSpecialDamage, skillType, skillSpeedAtk, skillUnique,
    // skillDescription));

    public String getName() {
        return name;
    }

    public int getPhysicalDamage() {
        return physicalDamage;
    }

    public int getSpecialDamage() {
        return specialDamage;
    }

    public Types getType() {
        return type;
    }

    public double getSpeedAtk() {
        return speedAtk;
    }

    public Pattern getPatern() {
        return p;
    }

    public String getDescription() {
        return description;
    }
}
