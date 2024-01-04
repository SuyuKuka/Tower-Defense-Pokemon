package characters;

import model.Skill;
import model.Tower;
import model.Types;

public class Starter {
    public class Salameche extends Tower implements TowerLoader {
        public Salameche() {
            super("Salameche", 22, 38, Types.FEU, Types.NONE, "ressources/sprite/charmander/charmander.gif",
                    State.LOCKED);
            primarySkill();
        }

        @Override
        public void evolution() { // marche pas
            if (getLevel() == 16) {
                System.out.println("j'ai évolué");
                setSprite("ressources/sprite/charmander/charmeleon.gif");
                setName("Reptincel");
                setPhysical(getPhysical() + 10);
                setSpecial(getSpecial() + 10);
                getTiles().replaceSprite(getSprite());
            }
            if (getLevel() == 36) {
                // Methode 1
                setSprite("ressources/sprite/charmander/charizard.gif");
                setName("Dracaufeu");
                setPhysical(getPhysical() + 20);
                setSpecial(getSpecial() + 20);
                setSecondType(Types.VOL);
                getTiles().replaceSprite(getSprite());
            }
        }

        @Override
        public void primarySkill() {
            learnSkill(new Skill("Griffe", 40, 0, Types.NORMAL, 1, true,
                    "Utilise ses griffes pour lui infliger des degats."));
            learnSkill(new Skill(" ", -1, -1, Types.NONE, -1, false, " "));
            learnSkill(new Skill(" ", -1, -1, Types.NONE, -1, false, " "));
            learnSkill(new Skill(" ", -1, -1, Types.NONE, -1, false, " "));
        }

        @Override
        public void unlock() { // 9
            if (getLevel() == 7) {
                learnSkill(
                        new Skill("Flammeche", 0, 40, Types.FEU, 1, true, "Crache des petites flamme sur son ennemi."));
            }
            if (getLevel() == 16) {
                learnSkill(new Skill("Draco-Rage", 0, 40, Types.DRAGON, 1, true,
                        "Utilise sa rage pour lancer un cri avec une forme de dragon."));
            }
            if (getLevel() == 24) {
                learnSkill(new Skill("Crocs Feu", 60, 0, Types.FEU, 1, true,
                        "Enflamme ses crocs, pour infliger des ddegats."));
            }
            if (getLevel() == 32) {
                learnSkill(new Skill("Rebondifeu", 0, 55, Types.FEU, 1, true, "Balance une flamme qui rebondit."));
            }
            if (getLevel() == 36) {
                learnSkill(new Skill("Cru-Aile", 65, 0, Types.VOL, 1, true,
                        "Utilise ses ailes pour frapper son ennemi en plein fouet."));
            }
            if (getLevel() == 41) {
                learnSkill(new Skill("Tranche", 70, 0, Types.NORMAL, 1, true, "Materialise une epee tranchante."));
            }
            if (getLevel() == 52) {
                learnSkill(new Skill("Lame d'Air", 85, 0, Types.VOL, 1, true,
                        "Forme des lames d'airs avec les battements de ses ailes."));
            }
            if (getLevel() == 60) {
                learnSkill(new Skill("Boutefeu", 100, 0, Types.FEU, 1, true,
                        "Enflamme tout son corps d'un feu ardent puis fonce sur son ennemi."));
            }
            if (getLevel() == 70) {
                learnSkill(new Skill("Feu d'enfer", 0, 150, Types.FEU, 1, true,
                        "Utilise les flammes des enfers infligeants d'enormes degats."));
            }
        }
    }

    public class Arcko extends Tower implements TowerLoader { // Treecko - Grovyle - Sceptile
        public Arcko() {
            super("Arcko", 20, 40, Types.PLANTE, Types.NONE, "ressources/sprite/treecko/treecko.gif", State.LOCKED);
            primarySkill();
        }

        @Override
        public void evolution() { // marche pas
            System.out.println("j'ai le niveau?");
            if (getLevel() == 16) {
                System.out.println("j'ai évolué");
                setSprite("ressources/sprite/treecko/grovyle.gif");
                setName("Massko");
                setPhysical(getPhysical() + 10);
                setSpecial(getSpecial() + 10);
                getTiles().replaceSprite(getSprite());

            }
            if (getLevel() == 36) {
                // Methode 1
                setSprite("ressources/sprite/treecko/sceptile.gif");
                setName("Jungko");
                setPhysical(getPhysical() + 20);
                setSpecial(getSpecial() + 20);
                getTiles().replaceSprite(getSprite());

            }
        }

        @Override
        public void primarySkill() {
            learnSkill(new Skill("Ecras'Face", 40, 0, Types.NORMAL, 1, true,
                    "Fonce sur son ennemi et l'ecrase de toutes ses forces."));
            learnSkill(new Skill(" ", -1, -1, Types.NONE, -1, false, " "));
            learnSkill(new Skill(" ", -1, -1, Types.NONE, -1, false, " "));
            learnSkill(new Skill(" ", -1, -1, Types.NONE, -1, false, " "));
        }

        @Override
        public void unlock() {
            if (getLevel() == 6) {
                learnSkill(new Skill("Feuillage", 0, 40, Types.PLANTE, 1, true,
                        "Produis des feiilles et les projectes sur son adversaire."));
            }
            if (getLevel() == 9) {
                learnSkill(new Skill("Vive-Attaque", 40, 0, Types.NORMAL, 0.6, true,
                        "Fonce rapidement sur son ennemi pour lui infliger des degats."));
            }
            if (getLevel() == 16) {
                learnSkill(new Skill("Mega-Sangsue", 0, 40, Types.PLANTE, 1, true, "Drainne la force de son ennemi."));
            }
            if (getLevel() == 25) {
                learnSkill(new Skill("Giga-Sangsue", 0, 55, Types.PLANTE, 1, true,
                        "Absorbe la force vitale de son ennemi."));
            }
            if (getLevel() == 36) {
                learnSkill(new Skill("Souplesse", 60, 0, Types.NORMAL, 1, true,
                        "Utilise son corps pour frapper son ennemi."));
            }
            if (getLevel() == 44) {
                learnSkill(new Skill("Lame-Feuille", 70, 40, Types.PLANTE, 1, true, "Produis des lames de feuilles."));
            }
            if (getLevel() == 52) {
                learnSkill(new Skill("Plaie Croix", 80, 0, Types.INSECTE, 1, true,
                        "Entaille ses ennemies de maniere brutal."));
            }
            if (getLevel() == 58) {
                learnSkill(new Skill("Eco-Sphere", 0, 100, Types.PLANTE, 1, true,
                        "Se concentre pour former une boule d'energie."));
            }
            if (getLevel() == 70) {
                learnSkill(new Skill("Tempete Verte", 0, 150, Types.PLANTE, 1, true,
                        "Provoque une enorme tempete avec sa foce pure."));
            }
        }
    }

    public class Tiplouf extends Tower implements TowerLoader { // Froakie - Frogadier - Greninja
        public Tiplouf() {
            super("Tiplouf", 25, 35, Types.EAU, Types.NONE, "ressources/sprite/froakie.png", State.LOCKED);
            primarySkill();
        }

        @Override
        public void evolution() { // marche pas
            if (getLevel() == 16) {
                System.out.println("j'ai évolué");
                setSprite("ressources/sprite/frogadier.png");
                setName("Croaporal");
                setPhysical(getPhysical() + 10);
                setSpecial(getSpecial() + 10);
                getTiles().replaceSprite(getSprite());

            }
            if (getLevel() == 36) {
                // Methode 1
                setSprite("ressources/sprite/greninja.png");
                setName("Amphinobi");
                setPhysical(getPhysical() + 20);
                setSpecial(getSpecial() + 20);
                getTiles().replaceSprite(getSprite());

            }
        }

        @Override
        public void primarySkill() {
            learnSkill(new Skill("Ecras'Face", 40, 0, Types.NORMAL, 1, true,
                    "Fonce sur son ennemi et l'ecrase de toutes ses forces."));
            learnSkill(new Skill(" ", -1, -1, Types.NONE, -1, false, " "));
            learnSkill(new Skill(" ", -1, -1, Types.NONE, -1, false, " "));
            learnSkill(new Skill(" ", -1, -1, Types.NONE, -1, false, " "));
        }

        @Override
        public void unlock() {
            if (getLevel() == 5) {
                learnSkill(new Skill("Ecume", 0, 40, Types.EAU, 1, true,
                        "Produis des eccumes et les projectes sur son adversaire."));
            }
            if (getLevel() == 8) {
                learnSkill(new Skill("Vive-Attaque", 40, 0, Types.NORMAL, 0.6, true,
                        "Fonce rapidement sur son ennemi pour lui infliger des degats."));
            }
            if (getLevel() == 10) {
                learnSkill(new Skill("Lechouile", 30, 0, Types.SPECTRE, 1, true,
                        "Utilise sa langue pour lecher son ennemi."));
            }
            if (getLevel() == 14) {
                learnSkill(new Skill("Vibraqua", 0, 60, Types.EAU, 1, true, "Lance des anneaux d'eau."));
            }
            if (getLevel() == 23) {
                learnSkill(new Skill("Ombre Portee", 40, 0, Types.SPECTRE, 1, true, "Utilise son ombre pour taper."));
            }
            if (getLevel() == 36) {
                learnSkill(new Skill("Extrasenceur", 0, 80, Types.PSY, 1, true,
                        "Utilise des pouvoirs psychiques pour infliger des degats."));
            }
            if (getLevel() == 45) {
                learnSkill(new Skill("Tranche-Nuit", 70, 0, Types.TENEBRE, 1, true, "Tranche son ennemi."));
            }
            if (getLevel() == 52) {
                learnSkill(new Skill("Hydrocanon", 0, 100, Types.EAU, 1, true, "Tire un grand jet d'eau."));
            }
            if (getLevel() == 70) {
                learnSkill(new Skill("Sheauriken", 150, 0, Types.EAU, 1, true,
                        "Balance son sheauriken derriere son dos, le frappe violemment son ennemmi."));
            }
        }
    }
}
