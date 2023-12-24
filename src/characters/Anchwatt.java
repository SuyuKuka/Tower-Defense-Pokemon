package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Anchwatt extends Tower implements TowerLoader {

    public Anchwatt(String name, int atkPhysical, int atkSpecial, Types Types1, Types Types2) {
        super("Anchwatt", atkPhysical, atkSpecial, Types1, Types2, "ressources/sprite/tynamo.png", State.LOCKED);
        primarySkill();
    }

    @Override
    public void evolution() {
        if (getLevel() == 39) {
            setSprite("ressources/sprite/eelektrik.png");
            setName("Lamperoie");
            setPhysical(getPhysical() + 20);
            setSpecial(getSpecial() + 10);
        }
        if (getLevel() == 45) {
            setSprite("ressources/sprite/eelektross.png");
            setName("Ohmassacre");
            setPhysical(getPhysical() + 60);
            setSpecial(getSpecial() + 30);
        }
    }

    @Override
    public void primarySkill() {
        // Manque les effets
        Skill Acide = new Skill("Acide", 0, 40, Types.POISON, 1.5, null, true,
                "Attaque la cible avec un jet d'accide corrosif.");
        Skill CoupBoule = new Skill("Coup d'Boule", 70, 0, Types.NORMAL, 2, null, true,
                "Donne un gros coup de tete.");
        learnSkill(Acide);
        learnSkill(CoupBoule);
    }

    public void unlock() {
        if (getLevel() == 10) {
            Skill a = new Skill("Coup d'jus", 0, 60, Types.ELECTRIQUE, 0.9, null, false,
                    "Electrocute toutes les cibles autour.");
            learnSkill(a);
        }
        if (getLevel() == 16) {
            Skill a = new Skill("Machouille", 20, 0, Types.TENEBRE, 0.25, null, true,
                    "Mord a plusieur reprise sa cible.");
            learnSkill(a);
        }
        if (getLevel() == 22) {
            Skill a = new Skill("Big Splash", 80, 0, Types.COMBAT, 3, null, true,
                    "Gros coup de physique");
            learnSkill(a);
        }
        if (getLevel() == 25) {
            Skill a = new Skill("Elecanon", 0, 175, Types.ELECTRIQUE, 100, null, false,
                    "Charge et lance un puissant rayon electrique");
            learnSkill(a);
        }
        if (getLevel() == 30) {
            Skill a = new Skill("Mania", 2, 0, Types.NORMAL, 0.1, null, true,
                    "Le lanceur lance une rafale de coups sur la cible");
            learnSkill(a);
        }
        if (getLevel() == 38) {
            Skill a = new Skill("Eclair Fou", 90, 0, Types.ELECTRIQUE, 7, null, true,
                    "Le lanceur fonce sur la cible en etant surcharge en electricite");
            learnSkill(a);
        }
        if (getLevel() == 45) {
            Skill a = new Skill("Aqua-Breche", 85, 0, Types.EAU, 4, null, true,
                    "Le lanceur utilise la force de l'eau pour attaquer.");
            learnSkill(a);
        }
        if (getLevel() == 60) {
            Skill a = new Skill("Colere", 120, 0, Types.DRAGON, 60, null, true,
                    "Le lanceur s'enrage et attaque brutalement la cible");
            learnSkill(a);
        }
    }

}
