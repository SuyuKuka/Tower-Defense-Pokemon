package characters;

import model.Hero;
import model.Player;
import model.Tower;

public class Ondine extends Hero {

    private Player p;
    public Ondine(Player p) {
        super("Ondine", 60, 0, "ressources/heroprofile/ondine.png","+60 Atk special");
    }
    public void buff(){
        for (Tower t: Player.INSTANCE.getTeam()){
            t.setSpecial(t.getSpecial()+getBuff());
        }
    }

}
