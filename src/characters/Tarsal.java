package characters;

import model.Skill;
import model.Tower;
import model.TowerLoader;
import model.Types;

public class Tarsal extends Tower implements TowerLoader {
    // TODO
    public Tarsal() {
        // DUAL PATH, BIG RANGE ZONING/ FAST MELEE
        super("Tarsal", 25, 45, Types.PSY, Types.FEE, "ressources/sprite/ralts/ralts.gif",
                State.LOCKED, 200, Tower.Rayon.GLOBAL, 900);
        primarySkill();
    }

    public void reset() {
        setName("Tarsal");
        setLevel(1); // Mettez le niveau à 1 comme demandé
        setPhysical(25);
        setSpecial(45);
        setAtkCd(200);
        setRayon(Tower.Rayon.GLOBAL);
        setPrice(900);
        setFirstType(Types.PSY);
        setSecondType(Types.FEE);
        setSprite("ressources/sprite/ralts/ralts.gif");
        setExp(0);
    }


    @Override
    public void evolution() {
        if (getLevel() == 13) {
            setSprite("ressources/sprite/ralts/kirlia.gif");
            setName("Kirlia");
            setPhysical(getPhysical() + 40);
            setSpecial(getSpecial() + 40);
            if (getTiles() != null) {
                getTiles().replaceSprite(getSprite());
            }
        }

        if (getLevel() == 25) {
            boolean choix = true;
            if (choix) { // CONTROLE DE CHOIX
                setSprite("ressources/sprite/ralts/gardevoir.gif");
                setName("Gardevoir");
                setPhysical(getPhysical() + -80);
                setSpecial(getSpecial() + 90);
                if (getTiles() != null) {
                    getTiles().replaceSprite(getSprite());
                }
            } else {
                setSprite("ressources/sprite/ralts/gallade.gif");
                setName("Gallame");
                setPhysical(getPhysical() + 200);
                setSpecial(getSpecial() + -80);
                setSecondType(Types.COMBAT);
                if (getTiles() != null) {
                    getTiles().replaceSprite(getSprite());
                }
            }

        }

    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Cogne", 30, 0, Types.COMBAT, Skill.Pattern.AURA, "", 0));
    }

    public void unlock() { // TODO CHANGER
        if (getLevel() == 10) {
            learnSkill(new Skill("Cyclone", 50, 0, Types.NORMAL, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 22) {
            learnSkill(new Skill("Eboulement", 75, 0, Types.ROCHE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 30) {
            learnSkill(new Skill("Close Combat", 50, 0, Types.COMBAT, Skill.Pattern.AURA, "", 200));
        }
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
        if (getLevel() == 70) {
            learnSkill(new Skill("Balayette", 120, 0, Types.COMBAT, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 80) {
            learnSkill(new Skill("Exploforce", 200, 0, Types.COMBAT, Skill.Pattern.AURA, "", 200));
        }
    }
}
