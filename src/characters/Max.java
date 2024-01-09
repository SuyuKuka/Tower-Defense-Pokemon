package characters;

import model.Ennemi;
import model.Hero;
import model.Player;

public class Max extends Hero {

    private Player p;
    public Max(Player p) {
        super("Max", 0, 60, "ressources/heroprofile/max.png","-60% def special");
    }
    public void debuff(){
        for (Ennemi e: Ennemi.LIST.getBossList()){
            e.setSpecial(e.getSpecial()*getDebuff()/100);
        }
    }

}
