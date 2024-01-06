package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Terhal extends Tower implements TowerLoader {

    public Terhal() {
        super("Terhal", 55, 35, Types.PSY, Types.ACIER, "ressources/sprite/beldum/beldum.gif",
                State.LOCKED);
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
            getTiles().replaceSprite(getSprite());
        }
        if (getLevel() == 45) {
            setSprite("ressources/sprite/beldum/metagross.gif");
            setName("Metalosse");
            setPhysical(getPhysical() + 60);
            setSpecial(getSpecial() + 30);
            getTiles().replaceSprite(getSprite());
        }
    }

    @Override
    public void primarySkill() {
        // Manque les effets
        Skill charge = new Skill("Charge", 20, 0, Types.NORMAL, 1, true,
                "Coups de tete !");
        learnSkill(charge);
    }

    public void unlock() {
        if (getLevel() == 20) {
            Skill a = new Skill("Choc Mental", 0, 50, Types.PSY, 2, true,
                    "Utilise ses pouvoir telekinesique pour frapper a l'interieur de sa cible.");
            learnSkill(a);
        }
        if (getLevel() == 22) {
            Skill a = new Skill("Griffe Acier", 60, 0, Types.ACIER, 1.2, true,
                    "Griffe sa cible avec des griffes renforcees.");
            learnSkill(a);
        }
        if (getLevel() == 30) {
            Skill a = new Skill("Luminocanon", 0, 80, Types.ACIER, 3, false,
                    "Lance un laser sur une ligne.");
            learnSkill(a);
        }
        if (getLevel() == 38) {
            Skill a = new Skill("Eboulement", 80, 0, Types.ROCHE, 3, false,
                    "Fait tomber de gros rochers sur les cibles.");
            learnSkill(a);
        }
        if (getLevel() == 50) {
            Skill a = new Skill("Ultralaser", 0, 200, Types.NORMAL, 10, true,
                    "Lance un puissant laser, mais le lanceur doit se reposer apres.");
            learnSkill(a);
        }
        if (getLevel() == 60) {
            Skill a = new Skill("Seisme", 100, 0, Types.SOL, 5, false,
                    "Frappe toute la map au sol.");
            learnSkill(a);
        }
    }

}
