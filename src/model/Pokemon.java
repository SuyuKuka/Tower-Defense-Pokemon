package model;

public class Pokemon {

    public enum Speed {
        FAST, CLASSIC, SLOW, STUN
    }

    private String name;
    private int pv; // pv
    private int physical; // tour -> atk physical, bad -> def physique
    private int special; // tour -> atk spécial, bad -> def special
    private Speed speed; // tour -> vitesse atk, bad -> vitesse deplace
    private Type[] type = new Type[2];
    private int lvl; // TODO+COMPETENCE
    private boolean isTower;

    public Pokemon(String name, int pv, int physical, int special, Type type0, Type type1, boolean isTower) {
        this.name = name;
        this.pv = pv;
        this.physical = physical;
        this.special = special;
        type[0] = type0;
        type[1] = type1;
        speed = Pokemon.Speed.CLASSIC;
        this.isTower = isTower;
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
    public Type[] getType() {
        return type;
    }

    public Speed getSpeed() {
        return speed;
    }

    public int getPv() {
        return pv;
    }

    public int getPhysical() {
        return physical;
    }

    public int getSpecial() {
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

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void setPhysical(int physical) {
        this.physical = physical;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public void setFirstType(Type type){
        this.type[0]=type;
    }
    
    public void setSecondType(Type type){
        this.type[1]=type;
    }
}
