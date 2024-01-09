package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Anchwatt extends Tower implements TowerLoader {

    public Anchwatt() {
        // RAILGUN SNIPE BOSSKILLER
        super("Anchwatt", 20, 20, Types.ELECTRIQUE, Types.NONE, "ressources/sprite/tynamo/tynamo.gif",
                State.LOCKED, 50, Tower.Rayon.SMALL, 400);
        primarySkill();

    }

    public Anchwatt reset() {
        return new Anchwatt();
    }

    @Override
    public void evolution() {
        if (getLevel() == 10) {
            setSprite("ressources/sprite/tynamo/eelektrik.gif");
            setName("Lamperoie");
            setPhysical(getPhysical() + 20);
            setSpecial(getSpecial() + 20);
            setAtkCd(100);
            setRayon(Tower.Rayon.MEDIUM);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
        if (getLevel() == 25) {
            setSprite("ressources/sprite/tynamo/eelektross.gif");
            setName("Ohmassacre");
            setPhysical(getPhysical() + 20);
            setSpecial(getSpecial() + 20);
            setAtkCd(200);
            setRayon(Tower.Rayon.GLOBAL);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Eclair", 0, 20, Types.ELECTRIQUE, Skill.Pattern.UNIQUE, "", 0));
    }

    public void unlock() {
        if (getLevel() == 10) {
            learnSkill(new Skill("Coup d'jus", 0, 10, Types.ELECTRIQUE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 16) {
            learnSkill(new Skill("Rayon Charge", 0, 50, Types.ELECTRIQUE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 20) {
            learnSkill(new Skill("Champs electrifie", 0, 20, Types.ELECTRIQUE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 25) {
            learnSkill(new Skill("Elecanon", 0, 175, Types.ELECTRIQUE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 37) {
            learnSkill(new Skill("Luminocanon", 0, 100, Types.ACIER, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 42) {
            learnSkill(new Skill("Surf", 0, 80, Types.EAU, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("OverDrive", 0, 275, Types.ELECTRIQUE, Skill.Pattern.UNIQUE, "", 200));
        }

    }

}
