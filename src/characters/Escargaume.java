package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Escargaume extends Tower implements TowerLoader {

    public Escargaume() {
        // MEDIUM MELEE
        super("Escargaume", 20, 20, Types.INSECTE, Types.NONE, "ressources/sprite/shelmet/shelmet.gif",
                State.LOCKED, 500, Tower.Rayon.SMALL, 1000);
        primarySkill();
    }

    public Escargaume reset() {
        return new Escargaume();
    }

    @Override
    public void evolution() {
        if (getLevel() == 30) {
            setSprite("ressources/sprite/shelmet/accelgor.gif");
            setName("Lancargot");
            setPhysical(getPhysical() + 100);
            setSecondType(Types.ACIER);
            setAtkCd(50);
            setRayon(Tower.Rayon.MEDIUM);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Taillade", 50, 0, Types.INSECTE, Skill.Pattern.UNIQUE, "", 0));
    }

    public void unlock() {
        if (getLevel() == 10) {
            learnSkill(new Skill("Bombe Acide", 0, 80, Types.POISON, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 22) {
            learnSkill(new Skill("Bélier", 80, 0, Types.NORMAL, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 28) {
            learnSkill(new Skill("Megacorne", 100, 0, Types.INSECTE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 30) {
            learnSkill(new Skill("Tunnelier", 20, 0, Types.SOL, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 32) {
            learnSkill(new Skill("Fulmifer", 100, 0, Types.ACIER, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 40) {
            learnSkill(new Skill("Giga Impact", 100, 0, Types.NORMAL, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("Close Combat", 150, 0, Types.COMBAT, Skill.Pattern.UNIQUE, "", 200));
        }
    }
}
