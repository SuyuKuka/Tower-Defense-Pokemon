package characters;

import model.Hero;
import model.Player;
import model.Tower;

public class Ondine extends Hero {
    public Ondine() {
        super("Ondine", 20, 0, "ressources/heroprofile/ondine.png", "+20 Atk special");
    }

    public void buff() {
        for (Tower t: Player.INSTANCE.getTeam()) {
            t.setSpecial(t.getSpecial() + getBuff());
        }
    }
}
