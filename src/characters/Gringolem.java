package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Gringolem extends Tower implements TowerLoader {
    public Gringolem() {
        // MAP NUKER
        super("Gringolem", 500, 200, Types.SPECTRE, Types.SOL, "ressources/sprite/golett/golett.gif",
                State.LOCKED, 2000, Tower.Rayon.SMALL, 3000);
        primarySkill();
    }

    public Gringolem reset() {
        return new Gringolem();
    }

    @Override
    public void evolution() {
        if (getLevel() == 50) {
            setSprite("ressources/sprite/golett/golurk.gif");
            setName("Golemastoc");
            setPhysical(getPhysical() + 200);
            setSpecial(getSpecial() + 50);
            setRayon(Tower.Rayon.GLOBAL);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Malediction", 0, 80, Types.SPECTRE, Skill.Pattern.AURA, ""));
    }

    public void unlock() {
        if (getLevel() == 20) {
            learnSkill(new Skill("Dynamo-Poing", 80, 10, Types.COMBAT, Skill.Pattern.UNIQUE, ""));
        }
        if (getLevel() == 30) {
            learnSkill(new Skill("Seisme", 10, 0, Types.SOL, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 40) {
            learnSkill(new Skill("Ultimapoing", 60, 0, Types.NORMAL, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 45) {
            learnSkill(new Skill("Tomberoche", 200, 0, Types.ROCHE, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("Marto-Poing", 80, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 60) {
            learnSkill(new Skill("Sable Ardent", 0, 200, Types.SOL, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 70) {
            learnSkill(new Skill("Téra Explosion", 300, 0, Types.NORMAL, Skill.Pattern.AURA, ""));
        }

    }

}
