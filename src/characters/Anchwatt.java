package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Anchwatt extends Tower implements TowerLoader {

    public Anchwatt() {
        // RAILGUN SNIPE BOSSKILLER
        super("Anchwatt", 1.0, 1.0, Types.ELECTRIQUE, Types.NONE, "ressources/sprite/tynamo/tynamo.gif",
                State.LOCKED, 50, Tower.Rayon.GLOBAL, 0);
        primarySkill();

    }

    public Anchwatt reset() {
        return new Anchwatt();
    }

    @Override
    public void evolution() {
        if (getLevel() == 5 && getName().equals("Anchwatt")) {
            setSprite("ressources/sprite/tynamo/eelektrik.gif");
            setName("Lamperoie");

            getTiles().replaceSprite(getSprite());
        }
        if (getLevel() == 7 && getName().equals("Lamperoie")) {
            setSprite("ressources/sprite/tynamo/eelektross.gif");
            setName("Ohmassacre");

            getTiles().replaceSprite(getSprite());
        }
    }

    @Override
    public void primarySkill() {
        Skill Acide = new Skill("Acide", 0, 1, Types.POISON, 1.5, true,
                "Attaque la cible avec un jet d'accide corrosif.");
        Skill CoupBoule = new Skill("Coup d'Boule", 70, 0, Types.NORMAL, 2, true,
                "Donne un gros coup de tete.");
        learnSkill(Acide);
        learnSkill(CoupBoule);
    }

    public void unlock() {
        if (getLevel() == 10) {
            Skill a = new Skill("Coup d'jus", 0, 60, Types.ELECTRIQUE, 0.9, false,
                    "Electrocute toutes les cibles autour.");
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
            Skill a = new Skill("Elecanon", 0, 175, Types.ELECTRIQUE, 100, false,
                    "Charge et lance un puissant rayon electrique");
            learnSkill(a);
        }
        if (getLevel() == 30) {
            Skill a = new Skill("Mania", 2, 0, Types.NORMAL, 0.1, true,
                    "Le lanceur lance une rafale de coups sur la cible");
            learnSkill(a);
        }
        if (getLevel() == 38) {
            Skill a = new Skill("Eclair Fou", 90, 0, Types.ELECTRIQUE, 7, true,
                    "Le lanceur fonce sur la cible en etant surcharge en electricite");
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
