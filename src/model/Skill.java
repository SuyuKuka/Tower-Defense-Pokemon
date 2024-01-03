package model;

public class Skill {
    public String name;
    private int physicalDamage;
    private int specialDamage;
    private Types type;
    private double speedAtk;
    private boolean unique;
    private String description;

    public Skill(String name, int physicalDamage, int specialDamage, Types type, double speedAtk, boolean unique, String description) {
        this.name = name;
        this.physicalDamage = physicalDamage;
        this.specialDamage = specialDamage;
        this.type = type;
        this.speedAtk = speedAtk;
        this.unique = unique;
        this.description = description;
    }

    public String toString(){
        return this.getName()
        +"#"+this.getPhysicalDamage() 
        +"#"+this.getSpecialDamage() 
        +"#"+this.getType()
        +"#"+this.getSpeedAtk()
        +"#"+this.getUnique()
        +"#"+this.getDescription();
    }
    //tower.learnSkill(new Skill(skillName, skillPhysicalDamage, skillSpecialDamage, skillType, skillSpeedAtk, skillUnique, skillDescription));

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

    public boolean getUnique() {
        return unique;
    }

    public String getDescription() {
        return description;
    }
}
