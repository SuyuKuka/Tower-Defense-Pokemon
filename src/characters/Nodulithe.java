package characters;

import model.Skill;
import model.Tower;
import model.TowerLoader;
import model.Types;

public class Nodulithe extends Tower implements TowerLoader {

    public Nodulithe() {
        // Zone destruction
        super("Nodulithe", 75, 25, Types.ROCHE, Types.NONE, "ressources/sprite/roggenrola/roggenrola.gif", State.LOCKED,
                100, Tower.Rayon.SMALL, 1000);
        System.out.println("Charge le sprite");
        primarySkill();
    }

    public Nodulithe reset() {
        return new Nodulithe();
    }

    @Override
    public void evolution() {
        if (getLevel() == 15) {
            setSprite("ressources/sprite/roggenrola/boldore.gif");
            setName("Geolithe");
            setPhysical(getPhysical() + 30);
            setSpecial(getSpecial() + 10);
            setAtkCd(250);
            setRayon(Tower.Rayon.MEDIUM);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
        if (getLevel() == 30) {
            setSprite("ressources/sprite/roggenrola/gigalith.gif");
            setName("Gigalithe");
            setPhysical(getPhysical() + 30);
            setSpecial(getSpecial() + 20);
            setAtkCd(300);
            setRayon(Tower.Rayon.GLOBAL);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }
    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Charge", 50, 0, Types.NORMAL, Skill.Pattern.UNIQUE, "", 0));
    }

    public void unlock() {
        if (getLevel() == 20) {
            learnSkill(new Skill("Rayon-Gemme", 0, 60, Types.ROCHE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 30) {
            learnSkill(new Skill("Seisme", 100, 0, Types.SOL, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 40) {
            learnSkill(new Skill("Eboulement", 60, 0, Types.ROCHE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 45) {
            learnSkill(new Skill("Tomberoche", 200, 0, Types.ROCHE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("Tempete de sable", 80, 0, Types.COMBAT, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 60) {
            learnSkill(new Skill("Sable Ardent", 0, 200, Types.SOL, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 70) {
            learnSkill(new Skill("Téra Explosion", 300, 0, Types.NORMAL, Skill.Pattern.AURA, "", 200));
        }

    }

}
