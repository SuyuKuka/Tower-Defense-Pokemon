package characters;

import model.Ennemi;
import model.Hero;

public class Rocket extends Hero {
    public Rocket() {
        super("La Team Rocket", 60, 60, "ressources/heroprofile/rocket.png","-60% pv");
    }

    public void debuff(Ennemi e) {
          e.setPv(e.getPv() * (100 - getDebuff()) / 100);
    }
}
