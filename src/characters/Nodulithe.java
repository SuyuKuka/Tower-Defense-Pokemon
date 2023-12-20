package characters;

import model.Skill;
import model.Tower;
import model.Type;

public class Nodulithe extends Tower implements TowerLoader {

    public Nodulithe(String name, int atkPhysical, int atkSpecial, Type type1, Type type2) {
        super("Nodulithe", atkPhysical, atkSpecial, type1, type2, "ressouces/sprite/roggenrola.png");
        System.out.println("Charge le sprite");
        primarySkill();
    }

    @Override
    public void evolution() {
        if (getLevel() == 25) {
            setSprite("ressouces/sprite/boldore.png");
            setName("Geolithe");
            setPhysical(getPhysical() + 30);
            setSpecial(getSpecial() + 10);
        }
        if (getLevel() == 50) {
            setSprite("ressouces/sprite/gigalith.png");
            setName("Gigalithe");
            setPhysical(getPhysical() + 30);
            setSpecial(getSpecial() + 20);
        }
    }

    @Override
    public void primarySkill() {
        // Manque les effets
        Skill charge = new Skill("Charge", 20, 0, Type.NORMAL, 1, null, true,
                "Coups de tete !");
        learnSkill(charge);
    }

    public void unlock() {
        if (getLevel() == 10) {
            Skill a = new Skill("Rayon Gemme", 0, 80, Type.ROCHE, 2, null, false,
                    "Charge et tire un rayon minérale.");
            learnSkill(a);
        }
        if (getLevel() == 16) {
            Skill a = new Skill("Anti-Air", 40, 0, Type.ROCHE, 1.5, null, true,
                    "Attaque tres efficace contre les unités volantes.");
            learnSkill(a);
        }
        if (getLevel() == 22) {
            Skill a = new Skill("Big Splash", 80, 0, Type.COMBAT, 3, null, true,
                    "Gros coup de physique");
            learnSkill(a);
        }
        if (getLevel() == 30) {
            Skill a = new Skill("Lame de Roc", 175, 0, Type.ROCHE, 100, null, true,
                    "Ca fait mal, mais prend du temps a se recharger.");
            learnSkill(a);
        }
        if (getLevel() == 45) {
            Skill a = new Skill("Execu-Son", 90, 0, Type.TENEBRE, 9, null, true,
                    "Donne un puissant coup discretement a la cible.");
            learnSkill(a);
        }
        if (getLevel() == 53) {
            Skill a = new Skill("Lance-Soleil", 0, 120, Type.PLANTE, 7, null, true,
                    "Charge et tire un puissant rayon solaire.");
            learnSkill(a);
        }
        if (getLevel() == 65) {
            Skill a = new Skill("Tete de fer", 85, 0, Type.ACIER, 4, null, true,
                    "Coup de tete renforcé !.");
            learnSkill(a);
        }
        if (getLevel() == 70) {
            Skill a = new Skill("Seisme", 120, 0, Type.SOL, 30, null, false,
                    "Frappe toute la map au sol.");
            learnSkill(a);
        }
    }

}
