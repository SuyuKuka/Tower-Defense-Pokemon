package model;

public class Pokemon {

    public enum Speed {
        FAST, CLASSIC, SLOW, STUN
    }

    private String name;
    private double totalPv;
    private double pv; // pv
    private double physical; // tour -> atk physical, bad -> def physique
    private double special; // tour -> atk spécial, bad -> def special
    private Speed speed; // tour -> vitesse atk, bad -> vitesse deplace
    private Types[] type = new Types[2];
    private int lvl; // TODO+COMPETENCE
    private boolean isTower;

    public Pokemon(String name, double d, double physical, double special, Types type0, Types type1, boolean isTower) {
        this.name = name;
        this.pv = d;
        this.physical = physical;
        this.special = special;
        type[0] = type0;
        type[1] = type1;
        speed = Pokemon.Speed.CLASSIC;
        this.isTower = isTower;
        totalPv = pv;
    }

    public Pokemon() {
    }

    public boolean isTower() {
        return isTower;
    }

    public static Speed stringToSpeed(String info) {
        switch (info) {
            case "SLOW":
                return Speed.SLOW;
            case "STUN":
                return Speed.STUN;
            case "FAST":
                return Speed.FAST;
            default:
                return Speed.CLASSIC;
        }
    }

    // GETTER SETTER
    public Types[] getType() {
        return type;
    }

    public Speed getSpeed() {
        return speed;
    }

    public double getPv() {
        return pv;
    }

    public double getPhysical() {
        return physical;
    }

    public double getSpecial() {
        return special;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(Speed sp) {
        speed = sp;
    }

    public void setPv(double pv) {
        this.pv = pv;
    }

    public void setPhysical(double physical) {
        this.physical = physical;
    }

    public void setSpecial(double special) {
        this.special = special;
    }

    public void setFirstType(Types type) {
        this.type[0] = type;
    }

    public void setSecondType(Types type) {
        this.type[1] = type;
    }

    public double getTotalPv() {
        return totalPv;
    }
}
