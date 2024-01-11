package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class MotismaFroid extends Tower implements TowerLoader {
    public MotismaFroid() {
        // CROWD HAZARD GLOBAL DOT ELEC/GLACE
        super("MotismaFroid", 2, 105, Types.ELECTRIQUE, Types.GLACE, "ressources/sprite/frostrotom/frostrotom.gif",
                State.LOCKED, 20, Tower.Rayon.GLOBAL, 900);
        primarySkill();
    }

    public void reset() {
        setName("MotismaFroid");
        setLevel(1); // Mettez le niveau à 1 comme demandé
        setPhysical(2);
        setSpecial(105);
        setAtkCd(20);
        setRayon(Tower.Rayon.GLOBAL);
        setPrice(900);
        setFirstType(Types.ELECTRIQUE);
        setSecondType(Types.GLACE);
        setSprite("ressources/sprite/frostrotom/frostrotom.gif");
        setExp(0);
    }


    @Override
    public void evolution() {
        // NOTHING
    }

    @Override
    public void primarySkill() {
        learnSkill(new Skill("Cage Eclair", 0, 2, Types.ELECTRIQUE, Skill.Pattern.AURA, "", 0));
    }

    public void unlock() {
        if (getLevel() == 10) {
            learnSkill(new Skill("Brouhaha", 0, 10, Types.NORMAL, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 19) {
            learnSkill(new Skill("Vibrobscur", 0, 10, Types.TENEBRE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 25) {
            learnSkill(new Skill("Onde de Choc", 0, 30, Types.ELECTRIQUE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 30) {
            learnSkill(new Skill("Champ Electrifie", 0, 10, Types.ELECTRIQUE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 38) {
            learnSkill(new Skill("Vent Glace", 0, 30, Types.GLACE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 40) {
            learnSkill(new Skill("Tonnerre", 0, 50, Types.ELECTRIQUE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 45) {
            learnSkill(new Skill("Esprit Frappeur", 0, 30, Types.SPECTRE, Skill.Pattern.AURA, "", 200));
        }
        if (getLevel() == 50) {
            learnSkill(new Skill("Fatal-Foudre", 0, 140, Types.ELECTRIQUE, Skill.Pattern.AURA, "", 200));
        }
    }
}
