package characters;

import model.Skill;
import model.Tower;
import model.TowerLoader;
import model.Types;

public class Ecrapince extends Tower implements TowerLoader {

    public Ecrapince() {
        // MELEE BURST UNIQUE
        super("Ecrapince", 80, 20, Types.EAU, Types.NONE, "ressources/sprite/corphish/corphish.gif",
                State.LOCKED, 500, Tower.Rayon.SMALL, 200);
        primarySkill();
    }

    public void reset() {
        setName("Ecrapince");
        setLevel(1); // Mettez le niveau à 1 comme demandé
        setPhysical(80);
        setSpecial(20);
        setAtkCd(500);
        setRayon(Tower.Rayon.SMALL);
        setPrice(200);
        setFirstType(Types.EAU);
        setSecondType(Types.NONE);
        setSprite("ressources/sprite/corphish/corphish.gif");
        setExp(0);
    }


    @Override
    public void evolution() {
        if (getLevel() == 30) {
            setSprite("ressources/sprite/corphish/crawdaunt.gif");
            setName("Colhomard");
            setPhysical(getPhysical() + 40);
            setSpecial(getSpecial() + 10);
            setSecondType(Types.TENEBRE);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Tranche", 80, 0, Types.NORMAL, Skill.Pattern.UNIQUE, "", 0));
    }

    public void unlock() {
        if (getLevel() == 10) {
            learnSkill(new Skill("Aqua-Breche", 50, 0, Types.EAU, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 22) {
            learnSkill(new Skill("Coqui-Lame", 75, 0, Types.EAU, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 30) {
            learnSkill(new Skill("Tranche-Nuit", 80, 0, Types.TENEBRE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 40) {
            learnSkill(new Skill("Griffe Acier", 90, 0, Types.ACIER, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 45) {
            learnSkill(new Skill("Plaie Croix", 90, 0, Types.INSECTE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("Bombe Beurk", 0, 80, Types.POISON, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 55) {
            learnSkill(new Skill("Damoclès", 120, 0, Types.NORMAL, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 70) {
            learnSkill(new Skill("Vent Glace", 0, 90, Types.GLACE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 80) {
            learnSkill(new Skill("Pince-Masse", 200, 0, Types.EAU, Skill.Pattern.UNIQUE, "", 200));
        }
    }

}
