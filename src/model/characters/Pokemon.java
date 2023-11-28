package model.characters;

public class Pokemon {

    public enum Speed {
        FAST, CLASSIC, SLOW, STUN
    }

    private String name;
    private int value; // tour -> atk, bad -> pv
    private Speed speed; // tour -> vitesse atk, bad -> vitesse deplace
    private Type[] type = new Type[2];
    private int lvl; // TODO+COMPETENCE
    private boolean isTower;

    public Pokemon(String name, int value, Type type0, Type type1, boolean isTower) {
        this.name = name;
        this.value = value;
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

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setSpeed(Speed sp) {
        speed = sp;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
