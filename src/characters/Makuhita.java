package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Makuhita extends Tower implements TowerLoader {

    public Makuhita() {
        // MELEE AURA
        super("Makuhita", 60, 20, Types.COMBAT, Types.NONE, "ressources/sprite/makuhita/makuhita.gif",
                State.LOCKED, 250, Tower.Rayon.SMALL, 100);
        primarySkill();
    }

    public Makuhita reset() {
        return new Makuhita();
    }

    @Override
    public void evolution() {
        if (getLevel() == 40) {
            setSprite("ressources/sprite/makuhita/hariyama.gif");
            setName("Hariyama");
            setPhysical(getPhysical() + 60);
            setRayon(Tower.Rayon.MEDIUM);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Cogne", 30, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
    }

    public void unlock() {
        if (getLevel() == 10) {
            learnSkill(new Skill("Cyclone", 50, 0, Types.NORMAL, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 22) {
            learnSkill(new Skill("Eboulement", 75, 0, Types.ROCHE, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 30) {
            learnSkill(new Skill("Close Combat", 50, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 40) {
            learnSkill(new Skill("Forte-Paume", 60, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 45) {
            learnSkill(new Skill("Tacle Lourd", 90, 0, Types.ACIER, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("Frappe Atlas", 80, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 55) {
            learnSkill(new Skill("Mitra-Poing", 90, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 70) {
            learnSkill(new Skill("Balayette", 120, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 80) {
            learnSkill(new Skill("Exploforce", 200, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
        }
    }

}
