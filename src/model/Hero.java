package model;

public class Hero extends Tower {

    public Hero(String name, double atkPhysical, double atkSpecial, Types type1, Types type2, String sprite,
            State state, int atkCd, int range) {
        super(name, atkPhysical, atkSpecial, type1, type2, sprite, state, atkCd, range);
    }

}
