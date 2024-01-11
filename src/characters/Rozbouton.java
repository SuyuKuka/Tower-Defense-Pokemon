package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Rozbouton extends Tower implements TowerLoader {
    // TODO
    public Rozbouton() {
        // FAST SHOOTER/ZONE
        super("Rozbouton", 30, 50, Types.PLANTE, Types.POISON, "ressources/sprite/budew/budew.gif",
                State.LOCKED, 200, Tower.Rayon.SMALL, 300);
        primarySkill();
    }

    public void reset() {
        setName("Rozbouton");
        setLevel(1); // Mettez le niveau à 1 comme demandé
        setPhysical(30);
        setSpecial(50);
        setAtkCd(200);
        setRayon(Tower.Rayon.SMALL);
        setPrice(300);
        setFirstType(Types.PLANTE);
        setSecondType(Types.POISON);
        setSprite("ressources/sprite/budew/budew.gif");
        setExp(0);
    }


    @Override
    public void evolution() {
        if (getLevel() == 9) {
            setSprite("ressources/sprite/budew/roselia.gif");
            setName("Roselia");
            setPhysical(getPhysical() + 40);
            setSpecial(getSpecial() + 40);
            setAtkCd(100);
            setRayon(Tower.Rayon.MEDIUM);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }

        if (getLevel() == 13) {
            setSprite("ressources/sprite/budew/roserade.gif");
            setName("Roserade");
            setPhysical(getPhysical() + 40);
            setSpecial(getSpecial() + 40);
            setAtkCd(50);
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
