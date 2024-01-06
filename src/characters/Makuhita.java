package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Makuhita extends Tower implements TowerLoader {

    public Makuhita() {
        super("Makuhita", 60, 20, Types.COMBAT, Types.NONE, "ressources/sprite/makuhita/makuhita.gif",
                State.LOCKED);
        primarySkill();
    }

    public Makuhita reset() {
        return new Makuhita();
    }

    @Override
    public void evolution() {
        if (getLevel() == 16) {
            setSprite("ressources/sprite/makuhita/hariyama.gif");
            setName("Hariyama");
            setPhysical(getPhysical() + 60);
            setSpecial(getSpecial());
            getTiles().replaceSprite(getSprite());
        }
    }

    @Override
    public void primarySkill() {
        // Manque les effets
        Skill cogne = new Skill("Cogne", 20, 0, Types.COMBAT, 0.5, true,
                "Attaque rapidement la cible avec ses poings.");
        Skill JetDeSable = new Skill("Jet de Sable", 0, 0, Types.SOL, 1, false,
                "Baisse la Vitesse de la cible d'un niveau.");
        learnSkill(cogne);
        learnSkill(JetDeSable);
    }

    public void unlock() {
        if (getLevel() == 7) {
            Skill a = new Skill("Bluff", 40, 0, Types.NORMAL, 1.5, true,
                    "Met un coup discret aux pokemon adverse.");
            learnSkill(a);
        }
        if (getLevel() == 16) {
            Skill a = new Skill("Forte-Paume", 60, 0, Types.COMBAT, 2, true,
                    "Frappe violemment avec la paume de la main.");
            learnSkill(a);
        }
        if (getLevel() == 30) {
            Skill a = new Skill("Tackle Lourd", 200, 0, Types.ACIER, 3, true,
                    "Tackle brutalement l'adversaire mais prend du temps a se relever");
            learnSkill(a);
        }
        if (getLevel() == 50) {
            Skill a = new Skill("Close Combat", 120, 0, Types.COMBAT, 1.8, true,
                    "Une attaque rapide et puissante, parfait pour les combatants.");
            learnSkill(a);
        }
    }

}
