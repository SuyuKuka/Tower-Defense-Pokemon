package characters;

import model.Skill;
import model.Tower;
import model.TowerLoader;
import model.Types;

public class Tylton extends Tower implements TowerLoader {
    // TODO
    public Tylton() {
        // LASER
        super("Tylton", 1, 1, Types.VOL, Types.NORMAL, "ressources/sprite/swablu/swablu.gif",
                State.LOCKED, 25, Tower.Rayon.MEDIUM, 1000);
        primarySkill();
    }

    public Tylton reset() {
        return new Tylton();
    }

    @Override
    public void evolution() {
        if (getLevel() == 20) {
            setSprite("ressources/sprite/swablu/altaria.gif");
            setName("Altaria");
            setPhysical(getPhysical() + 9);
            setSpecial(getSpecial() + 9);
            setRayon(Tower.Rayon.LARGE);
            setSecondType(Types.DRAGON);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Voix Enjoleuse", 0, 1, Types.FEE, Skill.Pattern.UNIQUE, "", 0));
    }

    public void unlock() {
        if (getLevel() == 10) {
            learnSkill(new Skill("Météores", 0, 1, Types.NORMAL, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 16) {
            learnSkill(new Skill("Chant Canon", 0, 1, Types.NORMAL, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 20) {
            learnSkill(new Skill("Draco-Souffle", 0, 1, Types.DRAGON, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 25) {
            learnSkill(new Skill("Cri Draconique", 0, 1, Types.DRAGON, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 37) {
            learnSkill(new Skill("Laser Glace", 0, 1, Types.GLACE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 42) {
            learnSkill(new Skill("Draco-Choc", 0, 1, Types.DRAGON, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("Lance-Soleil", 0, 1, Types.PLANTE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 90) {
            learnSkill(new Skill("Requiem", 0, 10, Types.NORMAL, Skill.Pattern.AURA, "", 200));
        }

    }
}
