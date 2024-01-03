package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Nodulithe extends Tower implements TowerLoader {

    public Nodulithe() {
        super("Nodulithe", 75, 25, Types.ROCHE, Types.NONE,
                "ressouces/sprite/roggenrola/roggenrola.gif",
                State.LOCKED);
        System.out.println("Charge le sprite");
        primarySkill();
    }

    @Override
    public void evolution() {
        if (getLevel() == 25) {
            setSprite("ressouces/sprite/roggenrola/boldore.gif");
            setName("Geolithe");
            setPhysical(getPhysical() + 30);
            setSpecial(getSpecial() + 10);
        }
        if (getLevel() == 50) {
            setSprite("ressouces/sprite/roggenrola/gigalith.gif");
            setName("Gigalithe");
            setPhysical(getPhysical() + 30);
            setSpecial(getSpecial() + 20);
        }
    }

    @Override
    public void primarySkill() {
        // Manque les effets
        Skill charge = new Skill("Charge", 20, 0, Types.NORMAL, 1, null, true,
                "Coups de tete !");
        learnSkill(charge);
    }

    public void unlock() {
        if (getLevel() == 10) {
            Skill a = new Skill("Rayon Gemme", 0, 80, Types.ROCHE, 2, null, false,
                    "Charge et tire un rayon minérale.");
            learnSkill(a);
        }
        if (getLevel() == 16) {
            Skill a = new Skill("Anti-Air", 40, 0, Types.ROCHE, 1.5, null, true,
                    "Attaque tres efficace contre les unités volantes.");
            learnSkill(a);
        }
        if (getLevel() == 22) {
            Skill a = new Skill("Big Splash", 80, 0, Types.COMBAT, 3, null, true,
                    "Gros coup de physique");
            learnSkill(a);
        }
        if (getLevel() == 30) {
            Skill a = new Skill("Lame de Roc", 175, 0, Types.ROCHE, 100, null, true,
                    "Ca fait mal, mais prend du temps a se recharger.");
            learnSkill(a);
        }
        if (getLevel() == 45) {
            Skill a = new Skill("Execu-Son", 90, 0, Types.TENEBRE, 9, null, true,
                    "Donne un puissant coup discretement a la cible.");
            learnSkill(a);
        }
        if (getLevel() == 53) {
            Skill a = new Skill("Lance-Soleil", 0, 120, Types.PLANTE, 7, null, true,
                    "Charge et tire un puissant rayon solaire.");
            learnSkill(a);
        }
        if (getLevel() == 65) {
            Skill a = new Skill("Tete de fer", 85, 0, Types.ACIER, 4, null, true,
                    "Coup de tete renforcé !.");
            learnSkill(a);
        }
        if (getLevel() == 70) {
            Skill a = new Skill("Seisme", 120, 0, Types.SOL, 30, null, false,
                    "Frappe toute la map au sol.");
            learnSkill(a);
        }
    }

}
