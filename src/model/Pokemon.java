package model;

public class Pokemon {

    public enum Speed {
        FAST, CLASSIC, SLOW, STUN
    }

    private String name;
    private double totalPv;
    private double pv; // pv
    private double physical; // tour -> atk physical, bad -> def physique
    private double special; // tour -> atk special, bad -> def special
    private Speed speed; // VITESSE DE DEPLA POUR LES ENNEMIS
    private Types[] type = new Types[2];
    private boolean isTower;
    private String sprite;

    public Pokemon(String name, double d, double physical, double special, Types type0, Types type1, boolean isTower,
            String sprite) {
        this.name = name;
        this.pv = d;
        this.physical = physical;
        this.special = special;
        type[0] = type0;
        type[1] = type1;
        speed = Pokemon.Speed.CLASSIC;
        this.isTower = isTower;
        this.sprite = sprite;
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

    public String getSprite() {
        return sprite;
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

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public double getTotalPv() {
        return totalPv;
    }

    public void setTotalPv(double totalPv) {
        this.totalPv = totalPv;
    }

}
