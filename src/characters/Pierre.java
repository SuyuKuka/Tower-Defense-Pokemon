package characters;

import model.Ennemi;
import model.Hero;
import model.Player;

public class Pierre extends Hero {

    private Player p;
    public Pierre(Player p) {
        super("Pierre", 0, 60, "ressources/heroprofile/pierre.png","-60% def physique");
    }
    public void debuff(){
        System.out.println(Ennemi.LIST.getBossList());
        for (Ennemi e: Ennemi.LIST.getBossList()){
            System.out.println(e.getName());
            e.setPhysical(e.getPhysical()*getDebuff()/100);
        }
    }

}
