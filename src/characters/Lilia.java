package characters;

import model.Skill;
import model.Tower;
import model.TowerLoader;
import model.Types;

public class Lilia extends Tower implements TowerLoader {
    public Lilia() {
        super("Lilia", 80, 80, Types.PLANTE, Types.ROCHE, "ressources/sprite/lileep/lileep.gif",
                State.LOCKED, 75, Tower.Rayon.LARGE, 300);
        primarySkill();
    }

    public void reset() {
        setName("Lilia");
        setLevel(1);
        setPhysical(80);
        setSpecial(80);
        setAtkCd(75);
        setRayon(Tower.Rayon.MEDIUM);
        setPrice(300);
        setFirstType(Types.PLANTE);
        setSecondType(Types.ROCHE);
        setSprite("ressources/sprite/lileep/lileep.gif");
        setExp(0);
    }

    @Override
    public void evolution() {
        if (getLevel() == 15) {
            setSprite("ressources/sprite/lileep/cradily.gif");
            setName("Vacilys");
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
        learnSkill(new Skill("Piqure", 0, 20, Types.INSECTE, Skill.Pattern.UNIQUE, "", 0));
    }

    public void unlock() {
        if (getLevel() == 10) {
            learnSkill(new Skill("Acide", 0, 30, Types.POISON, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 16) {
            learnSkill(new Skill("Eco-Sphere", 0, 50, Types.PLANTE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 20) {
            learnSkill(new Skill("Balle Graine", 20, 20, Types.PLANTE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 25) {
            learnSkill(new Skill("Ultralaser", 0, 60, Types.NORMAL, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 37) {
            learnSkill(new Skill("Piege de Roc", 0, 50, Types.ROCHE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 42) {
            learnSkill(new Skill("Lance-Soleil", 0, 80, Types.PLANTE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("Lame de Roc", 0, 90, Types.ROCHE, Skill.Pattern.UNIQUE, "", 200));
        }
    }
}