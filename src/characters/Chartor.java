package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Chartor extends Tower implements TowerLoader {

    public Chartor() {
        // CROWD HAZARD GLOBAL DOT FEU/POISON
        super("Chartor", 50, 10, Types.FEU, Types.NONE, "ressources/sprite/torkoal/torkoal.gif",
                State.LOCKED, 2, Tower.Rayon.GLOBAL, 900); // GLOBAL POUR LE TEST
        primarySkill();
    }

    public Chartor reset() {
        return new Chartor();
    }

    @Override
    public void evolution() {
        // FAUSSE EVOLUTION
        if (getLevel() == 10) {
            // setAtkCd(40);
            // setRayon(Tower.Rayon.MEDIUM);
        }
        if (getLevel() == 30) {
            // setAtkCd(20);
            // setRayon(Tower.Rayon.LARGE);
        }
    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Zenith", 0, 2, Types.FEU, Skill.Pattern.AURA, "", 0));
    }

    public void unlock() {
        if (getLevel() == 5) {
            learnSkill(new Skill("Danse Flamme", 0, 10, Types.FEU, Skill.Pattern.UNIQUE, "", 200));
        }
        if (getLevel() == 8) {
            learnSkill(new Skill("Seisme", 10, 0, Types.SOL, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 14) {
            learnSkill(new Skill("Ebullilave", 0, 30, Types.FEU, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 20) {
            learnSkill(new Skill("Bain de smog", 0, 30, Types.POISON, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 23) {
            learnSkill(new Skill("Surchauffe", 0, 50, Types.FEU, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 30) {
            learnSkill(new Skill("Feu d'Enfer", 0, 80, Types.FEU, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 35) {
            learnSkill(new Skill("Eruption", 0, 100, Types.FEU, Skill.Pattern.UNIQUE, "", 200));
        }
    }

}
