package characters;

import model.Effect;
import model.Skill;
import model.Tower;
import model.Type;

public class Terhal extends Tower implements TowerLoader {

    public Terhal(String name, int atkPhysical, int atkSpecial, Type type1, Type type2) {
        super("Terhal", atkPhysical, atkSpecial, type1, type2);
        System.out.println("Charge le sprite"); // ressouces/sprite/terhal.png
        primarySkill();
    }

    @Override
    public void evolution() {
        if (getLevel() == 20) {
            System.out.println("Charge le sprite"); // ressouces/sprite/Metang.png
            setName("Metang");
            setPhysical(getPhysical() + 20);
            setSpecial(getSpecial() + 10);
        }
        if (getLevel() == 45) {
            System.out.println("Charge le sprite"); // ressouces/sprite/Metalosse.png
            setName("Metalosse");
            setPhysical(getPhysical() + 60);
            setSpecial(getSpecial() + 30);
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
        if (getLevel() == 20) {
            Skill a = new Skill("Choc Mental", 0, 50, Type.PSY, 2, null, true,
                    "Utilise ses pouvoir télékinésique pour frapper a l'interieur de sa cible.");
            learnSkill(a);
        }
        if (getLevel() == 22) {
            Skill a = new Skill("Griffe Acier", 60, 0, Type.ACIER, 1.2, null, true,
                    "Griffe sa cible avec des griffes renforcées.");
            learnSkill(a);
        }
        if (getLevel() == 30) {
            Skill a = new Skill("Luminocanon", 0, 80, Type.ACIER, 3, null, false,
                    "Lance un laser sur une ligne.");
            learnSkill(a);
        }
        if (getLevel() == 38) {
            Skill a = new Skill("Eboulement", 80, 0, Type.ROCHE, 3, null, false,
                    "Fait tomber de gros rochers sur les cibles.");
            learnSkill(a);
        }
        if (getLevel() == 50) {
            Skill a = new Skill("Ultralaser", 0, 200, Type.NORMAL, 10, null, true,
                    "Lance un puissant laser, mais le lanceur doit se reposer apres.");
            learnSkill(a);
        }
        if (getLevel() == 60) {
            Skill a = new Skill("Seisme", 100, 0, Type.SOL, 5, null, false,
                    "Frappe toute la map au sol.");
            learnSkill(a);
        }
    }

}
