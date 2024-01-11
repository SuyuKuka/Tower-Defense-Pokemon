package characters;

import model.Hero;
import model.Player;
import model.Tower;

public class Sacha extends Hero {
    public Sacha() {
        super("Sasha", 20, 0, "ressources/heroprofile/sacha.png", "+20 Atk physique");
    }

    public void buff() {
        for (Tower t: Player.INSTANCE.getTeam()) {
            t.setPhysical(t.getPhysical() + getBuff());
        }
    }
}
