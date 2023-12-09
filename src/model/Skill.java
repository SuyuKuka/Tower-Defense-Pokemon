package model;

public class Skill {
    public String name;
    private int physicalDamage;
    private int specialDamage;
    private Type type;
    private double speedAtk;
    private Effect effect;
    private boolean unique;
    private String description;

    public Skill(String name, int physicalDamage, int specialDamage, Type type, double speedAtk, Effect effect, boolean unique, String description) {
        this.name=name;
        this.physicalDamage = physicalDamage;
        this.specialDamage = specialDamage;
        this.type = type;
        this.speedAtk = speedAtk;
        this.effect = effect;
        this.unique = unique;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public int getPhysicalDamage() {
        return physicalDamage;
    }

    public int getSpecialDamage() {
        return specialDamage;
    }

    public Type getType() {
        return type;
    }

    public double getSpeedAtk() {
        return speedAtk;
    }

    public Effect getEffect() {
        return effect;
    }

    public boolean getUnique() {
        return unique;
    }

    public String getDescription() {
        return description;
    }
}
