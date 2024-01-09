package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Mysdibule extends Tower implements TowerLoader {
    public Mysdibule() {
        // FAST MELEEE UNIQUE
        super("Mysdibule", 85, 55, Types.ACIER, Types.FEE, "ressources/sprite/mawile/mawile.gif",
                State.LOCKED, 50, Tower.Rayon.SMALL, 200);
        primarySkill();
    }

    public Mysdibule reset() {
        return new Mysdibule();
    }

    @Override
    public void evolution() {
        // NOTHING
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
