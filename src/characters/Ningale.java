package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Ningale extends Tower implements TowerLoader {
    // TODO
    public Ningale() {
        // FAST MELEEE UNIQUE
        super("Ningale", 45, 30, Types.INSECTE, Types.SOL, "ressources/sprite/nincada/nincada.gif",
                State.LOCKED, 50, Tower.Rayon.SMALL, 200);
        primarySkill();
    }

    public void reset() {
        setName("Ningale");
        setLevel(1); // Mettez le niveau à 1 comme demandé
        setPhysical(45);
        setSpecial(30);
        setAtkCd(50);
        setRayon(Tower.Rayon.SMALL);
        setPrice(200);
        setFirstType(Types.INSECTE);
        setSecondType(Types.SOL);
        setSprite("ressources/sprite/nincada/nincada.gif");
        setExp(0);
    }


    @Override
    public void evolution() {
        if (getLevel() == 30) {

            boolean choix = true;
            if (choix) { // CONTROLE DE CHOIX

                setSprite("ressources/sprite/nincada/ninjask.gif");
                setName("Ninjask");
                setPhysical(getPhysical() + 40);
                setSpecial(getSpecial() + 40);
                setSecondType(Types.VOL);
                getTiles().replaceSprite(getSprite());
            } else {

                setSprite("ressources/sprite/nincada/shedinja.gif");
                setName("Munja");
                setPhysical(getPhysical() + 40);
                setSpecial(getSpecial() + 40);
                setSecondType(Types.SPECTRE);
                getTiles().replaceSprite(getSprite());
            }
        }
    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Piqure", 0, 20, Types.INSECTE, Skill.Pattern.UNIQUE, "", 0));
    }

    public void unlock() {
        if (getLevel() == 10) {
            learnSkill(new Skill("Acide", 0, 30, Types.POISON, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 16) {
            learnSkill(new Skill("Eco-Sphere", 0, 50, Types.PLANTE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 20) {
            learnSkill(new Skill("Balle Graine", 20, 20, Types.PLANTE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 25) {
            learnSkill(new Skill("Ultralaser", 0, 60, Types.NORMAL, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 37) {
            learnSkill(new Skill("Piege de Roc", 0, 50, Types.ROCHE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 42) {
            learnSkill(new Skill("Lance-Soleil", 0, 80, Types.PLANTE, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("Lame de Roc", 0, 90, Types.ROCHE, Skill.Pattern.UNIQUE, "", 200));
        }

    }
}
