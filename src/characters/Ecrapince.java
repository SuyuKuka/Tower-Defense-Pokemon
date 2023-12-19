package characters;

import model.Skill;
import model.Tower;
import model.Type;

public class Ecrapince extends Tower implements TowerLoader {

    public Ecrapince(String name, int atkPhysical, int atkSpecial, Type type1, Type type2) {
        super("Ecrapince", atkPhysical, atkSpecial, type1, type2, "ressources/sprite/torkoal.png");
        primarySkill();
    }

    @Override
    public void evolution() {
        if (getLevel() == 30) {
            setSprite("ressouces/sprite/crawdaunt.png");
            setName("Colhomard");
            setPhysical(getPhysical() + 40);
            setSpecial(getSpecial() + 40);
            setSecondType(Type.TENEBRE);
        }
    }

    @Override
    public void primarySkill() {
        // Manque les effets
        Skill pistoletAO = new Skill("Pistolet a O", 0, 40, Type.EAU, 0.5, null, true,
                "Tire de l'eau sur sa cible.");
        Skill BulleO = new Skill("Bulles d'O", 0, 65, Type.EAU, 2, null, false,
                "Lance une grosse bulle chargé d'eau.");
        learnSkill(pistoletAO);
        learnSkill(BulleO);
    }

    public void unlock() {
        if (getLevel() == 10) {
            Skill a = new Skill("Tranche-Nuit", 70, 0, Type.TENEBRE, 0.3, null, true,
                    "Lacere la cible avec une force obscure.");
            learnSkill(a);
        }
        if (getLevel() == 22) {
            Skill a = new Skill("Coqui-Lame", 75, 0, Type.EAU, 0.8, null, true,
                    "Tranche l'adversaire en utilisant un coquillage aiguisé.");
            learnSkill(a);
        }
        if (getLevel() == 30) {
            Skill a = new Skill("Machouille", 20, 0, Type.TENEBRE, 0.25, null, true,
                    "Pince a plusieur reprise sa cible.");
            learnSkill(a);
        }
        if (getLevel() == 50) {
            Skill a = new Skill("Blizzard", 0, 90, Type.GLACE, 10, null, false,
                    "Une violente tempete de glace s'abbat sur toute la carte");
            learnSkill(a);
        }
        if (getLevel() == 55) {
            Skill a = new Skill("Tir de boue", 0, 55, Type.SOL, 2, null, true,
                    "Projette de la Boue sur sa cible");
            learnSkill(a);
        }
        if (getLevel() == 96) {
            Skill a = new Skill("Guillotine", 100000000, 0, Type.NORMAL, 600, null, true,
                    "Annihile la cible d'un seul coup, mais necessite un temps de recharge considérable.");
            learnSkill(a);
        }
    }

}
