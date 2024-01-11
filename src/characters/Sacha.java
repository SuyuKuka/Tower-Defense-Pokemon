package characters;

import model.Hero;
import model.Player;
import model.Tower;

public class Sacha extends Hero {

    private Player p;
    public Sacha(Player p) {
        super("Sasha", 60, 0, "ressources/heroprofile/sacha.png", "+60 Atk physique");
    }
    public void buff(){
        for (Tower t: Player.INSTANCE.getTeam()){
            t.setPhysical(t.getPhysical()+getBuff());
        }
    }

}
