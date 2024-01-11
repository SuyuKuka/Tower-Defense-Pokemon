package characters;

import model.Skill;
import model.Tower;
import model.TowerLoader;
import model.Types;

public class Obalie extends Tower implements TowerLoader {
    public Obalie() {
        super("Obalie", 40, 55, Types.EAU, Types.GLACE, "ressources/sprite/spheal/spheal.gif",
                State.LOCKED, 100, Tower.Rayon.SMALL, 3000);
        primarySkill();
    }

    public void reset() {
        setName("Obalie");
        setLevel(1);
        setPhysical(40);
        setSpecial(55);
        setAtkCd(100);
        setRayon(Tower.Rayon.SMALL);
        setPrice(3000);
        setFirstType(Types.EAU);
        setSecondType(Types.GLACE);
        setSprite("ressources/sprite/spheal/spheal.gif");
        setExp(0);
    }


    @Override
    public void evolution() {
        if (getLevel() == 5) {
            setSprite("ressources/sprite/spheal/sealeo.gif");
            setName("Phogleur");
            setPhysical(getPhysical() + 40);
            setSpecial(getSpecial() + 40);
            setAtkCd(80);
            setRayon(Tower.Rayon.MEDIUM);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
        if (getLevel() == 15) {
            setSprite("ressources/sprite/spheal/walrein.gif");
            setName("Kaimorse");
            setPhysical(getPhysical() + 40);
            setSpecial(getSpecial() + 40);
            setAtkCd(50);
            setRayon(Tower.Rayon.LARGE);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Morsure", 80, 0, Types.TENEBRE, Skill.Pattern.UNIQUE, "", 0));
    }

    public void unlock() {
        if (getLevel() == 10) {
            learnSkill(new Skill("Coup Bas", 50, 0, Types.TENEBRE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 22) {
            learnSkill(new Skill("Tete de Fer", 75, 0, Types.ACIER, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 30) {
            learnSkill(new Skill("Calinerie", 80, 0, Types.FEE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 40) {
            learnSkill(new Skill("Griffe Acier", 90, 0, Types.ACIER, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 45) {
            learnSkill(new Skill("Force", 90, 0, Types.NORMAL, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("Lame de Roc", 80, 0, Types.ROCHE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 55) {
            learnSkill(new Skill("Damoclès", 120, 0, Types.NORMAL, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 70) {
            learnSkill(new Skill("Mitra-Poing", 0, 90, Types.COMBAT, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 80) {
            learnSkill(new Skill("Exploforce", 200, 0, Types.COMBAT, Skill.Pattern.UNIQUE, "", 200));
        }
    }
}
