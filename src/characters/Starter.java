package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Starter {
    public class Salameche extends Tower implements TowerLoader {
        public Salameche() {
            // ROCKET WITHOUT AOE
            super("Salameche", 22, 38, Types.FEU, Types.NONE, "ressources/sprite/charmander/charmander.gif",
                    State.LOCKED, 50, Tower.Rayon.SMALL, 100);
            primarySkill();
        }

        public Salameche reset() {
            return new Salameche();
        }

        @Override
        public void evolution() {
            if (getLevel() == 15) {
                setSprite("ressources/sprite/charmander/charmeleon.gif");
                setName("Reptincel");
                setPhysical(getPhysical() + 10);
                setSpecial(getSpecial() + 10);
                setAtkCd(80);
                getTiles().replaceSprite(getSprite());
            }
            if (getLevel() == 30) {
                setSprite("ressources/sprite/charmander/charizard.gif");
                setName("Dracaufeu");
                setPhysical(getPhysical() + 20);
                setSpecial(getSpecial() + 20);
                setSecondType(Types.VOL);
                setAtkCd(150);
                setRayon(Tower.Rayon.LARGE);
                getTiles().replaceSprite(getSprite());
            }
        }

        @Override
        public void primarySkill() {
            learnSkill(new Skill("Griffe", 40, 0, Types.NORMAL, Skill.Pattern.UNIQUE, ""));
        }

        @Override
        public void unlock() { // 9
            if (getLevel() == 7) {
                learnSkill(new Skill("Flammeche", 0, 40, Types.FEU, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 16) {
                learnSkill(new Skill("Draco-Rage", 0, 40, Types.DRAGON, Skill.Pattern.AURA, ""));
            }
            if (getLevel() == 24) {
                learnSkill(new Skill("Crocs Feu", 60, 0, Types.FEU, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 32) {
                learnSkill(new Skill("Rebondifeu", 0, 55, Types.FEU, Skill.Pattern.AURA, ""));
            }
            if (getLevel() == 36) {
                learnSkill(new Skill("Cru-Aile", 65, 0, Types.VOL, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 41) {
                learnSkill(new Skill("Tranche", 70, 0, Types.NORMAL, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 52) {
                learnSkill(new Skill("Lame d'Air", 85, 0, Types.VOL, Skill.Pattern.AURA, ""));
            }
            if (getLevel() == 60) {
                learnSkill(new Skill("Boutefeu", 100, 0, Types.FEU, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 70) {
                learnSkill(new Skill("Feu d'enfer", 0, 150, Types.FEU, Skill.Pattern.UNIQUE, ""));
            }
        }
    }

    public class Arcko extends Tower implements TowerLoader { // Treecko - Grovyle - Sceptile
        public Arcko() {
            // FAST MELEE AOE
            super("Arcko", 20, 40, Types.PLANTE, Types.NONE, "ressources/sprite/treecko/treecko.gif", State.LOCKED, 50,
                    Tower.Rayon.SMALL, 100);
            primarySkill();
        }

        public Arcko reset() {
            return new Arcko();
        }

        @Override
        public void evolution() {
            if (getLevel() == 16) {
                setSprite("ressources/sprite/treecko/grovyle.gif");
                setName("Massko");
                setPhysical(getPhysical() + 10);
                setSpecial(getSpecial() + 10);
                setRayon(Tower.Rayon.MEDIUM);
                getTiles().replaceSprite(getSprite());

            }
            if (getLevel() == 36) {
                setSprite("ressources/sprite/treecko/sceptile.gif");
                setName("Jungko");
                setPhysical(getPhysical() + 20);
                setSpecial(getSpecial() + 20);
                getTiles().replaceSprite(getSprite());
            }
        }

        @Override
        public void primarySkill() {
            learnSkill(new Skill("Ecras'Face", 40, 0, Types.NORMAL, Skill.Pattern.UNIQUE, ""));
        }

        @Override
        public void unlock() {
            if (getLevel() == 6) {
                learnSkill(new Skill("Feuillage", 0, 40, Types.PLANTE, Skill.Pattern.AURA, ""));
            }
            if (getLevel() == 9) {
                learnSkill(new Skill("Vive-Attaque", 40, 0, Types.NORMAL, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 16) {
                learnSkill(new Skill("Mega-Sangsue", 0, 40, Types.PLANTE, Skill.Pattern.AURA, ""));
            }
            if (getLevel() == 25) {
                learnSkill(new Skill("Giga-Sangsue", 0, 55, Types.PLANTE, Skill.Pattern.AURA, ""));
            }
            if (getLevel() == 36) {
                learnSkill(new Skill("Souplesse", 60, 0, Types.NORMAL, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 44) {
                learnSkill(new Skill("Lame-Feuille", 70, 40, Types.PLANTE, Skill.Pattern.AURA, ""));
            }
            if (getLevel() == 52) {
                learnSkill(new Skill("Plaie Croix", 80, 0, Types.INSECTE, Skill.Pattern.AURA, ""));
            }
            if (getLevel() == 58) {
                learnSkill(new Skill("Eco-Sphere", 0, 100, Types.PLANTE, Skill.Pattern.AURA, ""));
            }
            if (getLevel() == 70) {
                learnSkill(new Skill("Tempete Verte", 0, 150, Types.PLANTE, Skill.Pattern.AURA, ""));
            }
        }
    }

    public class Moustillon extends Tower implements TowerLoader { // Froakie - Frogadier - Greninja
        public Moustillon() {
            // MID RANGE MID
            super("Moustillon", 25, 35, Types.EAU, Types.NONE, "ressources/sprite/oshawott/oshawott.gif", State.LOCKED,
                    50, Tower.Rayon.SMALL, 100);
            primarySkill();
        }

        public Moustillon reset() {
            return new Moustillon();
        }

        @Override
        public void evolution() {
            if (getLevel() == 16) {
                setSprite("ressources/sprite/oshawott/dewott.gif");
                setName("Mateloutre");
                setPhysical(getPhysical() + 10);
                setSpecial(getSpecial() + 10);
                getTiles().replaceSprite(getSprite());

            }
            if (getLevel() == 36) {
                setSprite("ressources/sprite/oshawott/samurott.gif");
                setName("Clamiral");
                setPhysical(getPhysical() + 20);
                setSpecial(getSpecial() + 20);
                setRayon(Tower.Rayon.MEDIUM);
                getTiles().replaceSprite(getSprite());

            }
        }

        @Override
        public void primarySkill() {
            learnSkill(new Skill("Ecras'Face", 40, 0, Types.NORMAL, Skill.Pattern.UNIQUE, ""));
        }

        @Override
        public void unlock() {
            if (getLevel() == 5) {
                learnSkill(new Skill("Ecume", 0, 40, Types.EAU, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 8) {
                learnSkill(new Skill("Vive-Attaque", 40, 0, Types.NORMAL, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 10) {
                learnSkill(new Skill("Coqui-Lame", 75, 0, Types.EAU, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 14) {
                learnSkill(new Skill("Vibraqua", 0, 60, Types.EAU, Skill.Pattern.AURA, ""));
            }
            if (getLevel() == 23) {
                learnSkill(new Skill("Aeropique", 40, 0, Types.VOL, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 36) {
                learnSkill(new Skill("Aqua-Jet", 80, 0, Types.EAU, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 45) {
                learnSkill(new Skill("Tranche-Nuit", 70, 0, Types.TENEBRE, Skill.Pattern.AURA, ""));
            }
            if (getLevel() == 52) {
                learnSkill(new Skill("Hydrocanon", 0, 100, Types.EAU, Skill.Pattern.UNIQUE, ""));
            }
            if (getLevel() == 70) {
                learnSkill(new Skill("Lame Sainte", 150, 0, Types.COMBAT, Skill.Pattern.UNIQUE, ""));
            }
        }
    }
}
