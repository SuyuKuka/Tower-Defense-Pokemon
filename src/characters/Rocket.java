package characters;

import model.Ennemi;
import model.Hero;
import model.Player;
import model.Tower;

public class Rocket extends Hero {

    private Player p;
    public Rocket(Player p) {
        super("La Team Rocket", 60, 60, "ressources/heroprofile/rocket.png","-60% pv");
    }
    public void debuff(Ennemi e){
          e.setPv(e.getPv()*(100-getDebuff())/100);
    }

//    public void buff(){
//        for (Tower t: Player.INSTANCE.getTeam()){
//            t.setSpecial(t.getSpecial()+getBuff());
//            t.setPhysical(t.getPhysical()+getBuff());
//        }
//    }

}
