package characters;

import model.Skill;
import model.Tower;
import model.Type;

public class Chartor extends Tower implements TowerLoader {

    public Chartor(String name, int atkPhysical, int atkSpecial, Type type1, Type type2) {
        super("Chartor", atkPhysical, atkSpecial, type1, type2);
        System.out.println("Charge le sprite");
        primarySkill();
    }

    @Override
    public void evolution() {
        //NOTHING
    }

    @Override
    public void primarySkill() {
        // Manque les effets
        Skill Acide = new Skill("Acide", 0, 40, Type.POISON, 1.5, null, true,
                "Attaque la cible avec un jet d'accide corrosif.");
        Skill CoupBoule = new Skill("Coup d'Boule", 70, 0, Type.NORMAL, 2, null, true,
                "Donne un gros coup de tete.");
        learnSkill(Acide);
        learnSkill(CoupBoule);
    }

    public void unlock() {
        if (getLevel() == 10) {
            Skill a = new Skill("Coup d'jus", 0, 60, Type.ELECTRIQUE, 0.9, null, false,
                    "Electrocute toutes les cibles autour.");
            learnSkill(a);
        }
        if (getLevel() == 16) {
            Skill a = new Skill("Machouille", 20, 0, Type.TENEBRE, 0.25, null, true,
                    "Mord a plusieur reprise sa cible.");
            learnSkill(a);
        }
        if (getLevel() == 22) {
            Skill a = new Skill("Big Splash", 80, 0, Type.COMBAT, 3, null, true,
                    "Gros coup de physique");
            learnSkill(a);
        }
        if (getLevel() == 25) {
            Skill a = new Skill("Elecanon", 0, 175, Type.ELECTRIQUE, 100, null, false,
                    "Charge et lance un puissant rayon electrique");
            learnSkill(a);
        }
        if (getLevel() == 30) {
            Skill a = new Skill("Mania", 2, 0, Type.NORMAL, 0.1, null, true,
                    "Le lanceur lance une rafale de coups sur la cible");
            learnSkill(a);
        }
        if (getLevel() == 38) {
            Skill a = new Skill("Eclair Fou", 90, 0, Type.ELECTRIQUE, 7, null, true,
                    "Le lanceur fonce sur la cible en étant surchargé en electricité");
            learnSkill(a);
        }
        if (getLevel() == 45) {
            Skill a = new Skill("Aqua-Breche", 85, 0, Type.EAU, 4, null, true,
                    "Le lanceur utilise la force de l'eau pour attaquer.");
            learnSkill(a);
        }
        if (getLevel() == 60) {
            Skill a = new Skill("Colere", 120, 0, Type.DRAGON, 60, null, true,
                    "Le lanceur s'enrage et attaque brutalement la cible");
            learnSkill(a);
        }
    }

}

