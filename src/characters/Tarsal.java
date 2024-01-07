package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Tarsal extends Tower implements TowerLoader {
    // TODO
    public Tarsal() {
        // DUAL PATH, BIG RANGE ZONING/ FAST MELEE
        super("Tarsal", 25, 45, Types.PSY, Types.FEE, "ressources/sprite/ralts/ralts.gif",
                State.LOCKED, 200, Tower.Rayon.GLOBAL, 900);
        primarySkill();
    }

    public Tarsal reset() {
        return new Tarsal();
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
        learnSkill(new Skill("Cogne", 30, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
    }

    public void unlock() { // TODO CHANGER
        if (getLevel() == 10) {
            learnSkill(new Skill("Cyclone", 50, 0, Types.NORMAL, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 22) {
            learnSkill(new Skill("Eboulement", 75, 0, Types.ROCHE, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 30) {
            learnSkill(new Skill("Close Combat", 50, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 10) {
            learnSkill(new Skill("Acide", 0, 30, Types.POISON, Skill.Pattern.UNIQUE, ""));
        }
        if (getLevel() == 16) {
            learnSkill(new Skill("Eco-Sphere", 0, 50, Types.PLANTE, Skill.Pattern.UNIQUE, ""));
        }
        if (getLevel() == 20) {
            learnSkill(new Skill("Balle Graine", 20, 20, Types.PLANTE, Skill.Pattern.UNIQUE, ""));
        }
        if (getLevel() == 25) {
            learnSkill(new Skill("Ultralaser", 0, 60, Types.NORMAL, Skill.Pattern.UNIQUE, ""));
        }
        if (getLevel() == 37) {
            learnSkill(new Skill("Piege de Roc", 0, 50, Types.ROCHE, Skill.Pattern.UNIQUE, ""));
        }
        if (getLevel() == 42) {
            learnSkill(new Skill("Lance-Soleil", 0, 80, Types.PLANTE, Skill.Pattern.UNIQUE, ""));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("Lame de Roc", 0, 90, Types.ROCHE, Skill.Pattern.UNIQUE, ""));
        }
        if (getLevel() == 70) {
            learnSkill(new Skill("Balayette", 120, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
        }
        if (getLevel() == 80) {
            learnSkill(new Skill("Exploforce", 200, 0, Types.COMBAT, Skill.Pattern.AURA, ""));
        }
    }
}
