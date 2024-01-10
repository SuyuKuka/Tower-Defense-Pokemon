package characters;

import model.Ennemi;
import model.Hero;
import model.Player;

public class Pierre extends Hero {

    private Player p;
    public Pierre(Player p) {
        super("Pierre", 0, 60, "ressources/heroprofile/pierre.png","-60% def physique");
    }
    public void debuff(Ennemi e){
        e.setPhysical(e.getPhysical()*(100-getDebuff())/100);
    }

}
