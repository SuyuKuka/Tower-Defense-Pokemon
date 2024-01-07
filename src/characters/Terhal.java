package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Terhal extends Tower implements TowerLoader {

    public Terhal() {
        // RAILGUN SNIPE BOSSKILLER WITH AOE LESS RANGE
        super("Terhal", 55, 35, Types.PSY, Types.ACIER, "ressources/sprite/beldum/beldum.gif",
                State.LOCKED, 50, Tower.Rayon.SMALL, 400);
        primarySkill();
    }

    public Terhal reset() {
        return new Terhal();
    }

    @Override
    public void evolution() {
        if (getLevel() == 20) {
            setSprite("ressources/sprite/beldum/metang.gif");
            setName("Metang");
            setPhysical(getPhysical() + 20);
            setSpecial(getSpecial() + 10);
            setRayon(Tower.Rayon.MEDIUM);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
        if (getLevel() == 45) {
            setSprite("ressources/sprite/beldum/metagross.gif");
            setName("Metalosse");
            setPhysical(getPhysical() + 60);
            setSpecial(getSpecial() + 30);
            setAtkCd(20);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
    }

    @Override
    public void primarySkill() { // TODO CHANGER
        learnSkill(new Skill("Eclair", 0, 20, Types.ELECTRIQUE, Skill.Pattern.UNIQUE, ""));
    }

    public void unlock() {
        if (getLevel() == 10) {
            learnSkill(new Skill("Coup d'jus", 0, 10, Types.ELECTRIQUE, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 16) {
            learnSkill(new Skill("Rayon Charge", 0, 50, Types.ELECTRIQUE, Skill.Pattern.UNIQUE, ""));
        }
        if (getLevel() == 20) {
            learnSkill(new Skill("Champs electrifie", 0, 20, Types.ELECTRIQUE, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 25) {
            learnSkill(new Skill("Elecanon", 0, 175, Types.ELECTRIQUE, Skill.Pattern.UNIQUE, ""));
        }
        if (getLevel() == 37) {
            learnSkill(new Skill("Luminocanon", 0, 100, Types.ACIER, Skill.Pattern.UNIQUE, ""));
        }
        if (getLevel() == 42) {
            learnSkill(new Skill("Surf", 0, 80, Types.EAU, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("OverDrive", 0, 275, Types.ELECTRIQUE, Skill.Pattern.UNIQUE, ""));
        }

    }

}
