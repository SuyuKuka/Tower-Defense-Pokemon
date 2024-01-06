package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Ecrapince extends Tower implements TowerLoader {

    public Ecrapince() {
        super("Ecrapince", 80, 50, Types.EAU, Types.NONE, "ressources/sprite/corphish/corphish.gif",
                State.LOCKED);
        primarySkill();
    }

    public Ecrapince reset() {
        return new Ecrapince();
    }

    @Override
    public void evolution() {
        if (getLevel() == 30) {
            setSprite("ressources/sprite/corphish/crawdaunt.gif");
            setName("Colhomard");
            setPhysical(getPhysical() + 40);
            setSpecial(getSpecial() + 40);
            setSecondType(Types.TENEBRE);
            getTiles().replaceSprite(getSprite());
        }
    }

    @Override
    public void primarySkill() {
        // Manque les effets
        Skill pistoletAO = new Skill("Pistolet a O", 0, 40, Types.EAU, 0.5, true,
                "Tire de l'eau sur sa cible.");
        Skill BulleO = new Skill("Bulles d'O", 0, 65, Types.EAU, 2, false,
                "Lance une grosse bulle charge d'eau.");
        learnSkill(pistoletAO);
        learnSkill(BulleO);
    }

    public void unlock() {
        if (getLevel() == 10) {
            Skill a = new Skill("Tranche-Nuit", 70, 0, Types.TENEBRE, 0.3, true,
                    "Lacere la cible avec une force obscure.");
            learnSkill(a);
        }
        if (getLevel() == 22) {
            Skill a = new Skill("Coqui-Lame", 75, 0, Types.EAU, 0.8, true,
                    "Tranche l'adversaire en utilisant un coquillage aiguise.");
            learnSkill(a);
        }
        if (getLevel() == 30) {
            Skill a = new Skill("Machouille", 20, 0, Types.TENEBRE, 0.25, true,
                    "Pince a plusieur reprise sa cible.");
            learnSkill(a);
        }
        if (getLevel() == 50) {
            Skill a = new Skill("Blizzard", 0, 90, Types.GLACE, 10, false,
                    "Une violente tempete de glace s'abbat sur toute la carte");
            learnSkill(a);
        }
        if (getLevel() == 55) {
            Skill a = new Skill("Tir de boue", 0, 55, Types.SOL, 2, true,
                    "Projette de la Boue sur sa cible");
            learnSkill(a);
        }
        if (getLevel() == 96) {
            Skill a = new Skill("Guillotine", 100000000, 0, Types.NORMAL, 600, true,
                    "Annihile la cible d'un seul coup, mais necessite un temps de recharge considerable.");
            learnSkill(a);
        }
    }

}
