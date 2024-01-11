package characters;

import model.Ennemi;
import model.Hero;

public class Pierre extends Hero {
    public Pierre() {
        super("Pierre", 0, 20, "ressources/heroprofile/pierre.png", "-20% def physique");
    }

    public void debuff(Ennemi e) {
        e.setPhysical(e.getPhysical() * (100 - getDebuff()) / 100);
    }
}
