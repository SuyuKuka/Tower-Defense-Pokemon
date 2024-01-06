package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Chartor extends Tower implements TowerLoader {

    public Chartor() {
        super("Chartor", 85, 85, Types.FEU, Types.NONE, "ressources/sprite/torkoal/torkoal.gif",
                State.LOCKED);
        primarySkill();
    }

    public Chartor reset() {
        return new Chartor();
    }

    @Override
    public void evolution() {
        // NOTHING
    }

    @Override
    public void primarySkill() {
        // Manque les effets
        Skill Flammeche = new Skill("Flammeche", 0, 40, Types.FEU, 1, true,
                "Attaque avec une faible flamme");
        Skill CoupBoule = new Skill("Coup d'Boule", 70, 0, Types.NORMAL, 2, true,
                "Donne un gros coup de tete.");
        learnSkill(Flammeche);
        learnSkill(CoupBoule);
    }

    public void unlock() {
        if (getLevel() == 10) {
            Skill a = new Skill("Puredpois", 0, 30, Types.POISON, 1.8, false,
                    "Gaz toxique");
            learnSkill(a);
        }
        if (getLevel() == 16) {
            Skill a = new Skill("Machouille", 20, 0, Types.TENEBRE, 0.25, true,
                    "Mord a plusieur reprise sa cible.");
            learnSkill(a);
        }
        if (getLevel() == 22) {
            Skill a = new Skill("Big Splash", 80, 0, Types.COMBAT, 3, true,
                    "Gros coup de physique");
            learnSkill(a);
        }
        if (getLevel() == 25) {
            Skill a = new Skill("Roue de Feu", 60, 0, Types.FEU, 3, true,
                    "S'entoure de feu et charge la cible");
            learnSkill(a);
        }
        if (getLevel() == 30) {
            Skill a = new Skill("Mania", 2, 0, Types.NORMAL, 0.1, true,
                    "Le lanceur lance une rafale de coups sur la cible");
            learnSkill(a);
        }
        if (getLevel() == 38) {
            Skill a = new Skill("Abime", 90, 0, Types.SOL, 60, false,
                    "Gross fissure sur toute la zone");
            learnSkill(a);
        }
        if (getLevel() == 45) {
            Skill a = new Skill("Aqua-Breche", 85, 0, Types.EAU, 4, true,
                    "Le lanceur utilise la force de l'eau pour attaquer.");
            learnSkill(a);
        }
        if (getLevel() == 60) {
            Skill a = new Skill("Colere", 120, 0, Types.DRAGON, 60, true,
                    "Le lanceur s'enrage et attaque brutalement la cible");
            learnSkill(a);
        }
    }

}
