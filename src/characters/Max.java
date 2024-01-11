package characters;

import model.Ennemi;
import model.Hero;

public class Max extends Hero {
    public Max() {
        super("Max", 0, 100, "ressources/heroprofile/max.png","-60% def special");
    }

    public void debuff(Ennemi e) {
        e.setSpecial(e.getSpecial() * (100 - getDebuff()) / 100);
    }
}
