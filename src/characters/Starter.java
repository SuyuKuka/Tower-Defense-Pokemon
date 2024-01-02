package characters;

import model.Effect;
import model.Skill;
import model.Tower;
import model.Types;

public class Starter {
    public class Salameche extends Tower implements TowerLoader {
        public Salameche(String name, int atkPhysical, int atkSpecial, Types type1, Types type2) {
            super(name, atkPhysical, atkSpecial, type1, type2, "ressources/sprite/charmander.png", State.LOCKED);
            // innerFocus(); // jsp si faut l'appeller la ou pas
            primarySkill();
        }

        @Override
        public void evolution() { //marche pas
            System.out.println("j'ai le niveau?");
            if (getLevel() == 16) {
                System.out.println("j'ai évolué");
                setSprite("ressources/sprite/charmeleon.png");
                setName("Reptincel");
                setPhysical(getPhysical() + 15);
                setSpecial(getSpecial() + 15);
                super.evolution("ressources/sprite/charmeleon.png", "Reptincel", 15, 15, Types.FEU, Types.NONE);
            }
            if (getLevel() == 36) {
                //Methode 1
                setSprite("ressources/sprite/charizard.png");
                setName("Dracaufeu");
                setPhysical(getPhysical() + 30);
                setSpecial(getSpecial() + 30);
                setSecondType(Types.VOL);
                //methode 2
                super.evolution("ressources/sprite/charizard.png", "Dracaufeu", 30, 30, Types.FEU, Types.VOL);
            }
        }

        @Override
        public void primarySkill() {
            Skill griffe = new Skill("Griffe", 40, 0, Types.NORMAL, 1, true,
                    "Lacere la cible avec des griffes acerees pour lui infliger des degats.");
            Skill rugissement = new Skill("Rugissement", 0, 0, Types.NORMAL, 1, true,
                    "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser sa Defense.");
            Skill nothing = new Skill(" ", -1, -1, Types.NONE, -1, false, " ");
            Skill nothing2 = new Skill(" ", -1, -1, Types.NONE, -1, false, " ");
            learnSkill(griffe);
            learnSkill(rugissement);
            learnSkill(nothing);
            learnSkill(nothing2);
        }

        @Override
        public void unlock() {
            if (getLevel() == 6) {
                Skill flammeche = new Skill("Flammeche", 0, 40, Types.FEU, 1, true,
                        "La cible est attaquee par une faible flamme qui peut aussi la brûler.");
                learnSkill(flammeche);
            }
            if (getLevel() == 12) {
                Skill dracosouffle = new Skill("Draco-Souffle", 60, 0, Types.DRAGON, 1.2, true,
                        "Le lanceur souffle fort sur la cible pour lui infliger des degâts, ce qui peut aussi la paralyser.");
                learnSkill(dracosouffle);
            }
            if (getLevel() == 16) {
                Skill crocsfeu = new Skill("Crocs Feu", 65, 0, Types.FEU, 1, true,
                        "Le lanceur utilise une morsure enflammee qui peut aussi brûler ou apeurer la cible.");
                learnSkill(crocsfeu);
            }
            // Griffe 1 40 Normal Physique Lacere la cible avec des griffes acerees pour lui
            // infliger des degâts.
            // Rugissement 1 - 100 40 Normal Statut Le lanceur pousse un cri tout mimi pour
            // tromper la vigilance de la cible et baisser son Attaque.
            // Flammeche 4 40 100 25 Feu Speciale La cible est attaquee par une faible
            // flamme qui peut aussi la brûler.
            // Brouillard 8 - 100 20 Normal Statut Le lanceur disperse un nuage d’encre ou
            // de fumee qui reduit la Precision de la cible.
            // Draco-Souffle 12 60 100 20 Dragon Speciale Le lanceur souffle fort sur la
            // cible pour lui infliger des degâts, ce qui peut aussi la paralyser.
            // Crocs Feu 17 65 95 15 Feu Physique Le lanceur utilise une morsure enflammee
            // qui peut aussi brûler ou apeurer la cible.
            // Tranche 20 70 100 20 Normal Physique Le lanceur donne un coup de griffe ou de
            // faux. Taux de critiques eleve.
            // Lance-Flammes 24 90 100 15 Feu Speciale La cible recoit un torrent de flammes
            // qui peut aussi la brûler.
            // Grimace 28 - 100 10 Normal Statut Le lanceur fait une grimace qui effraie la
            // cible et reduit beaucoup sa Vitesse.
            // Danse Flammes 32 35 85 15 Feu Speciale Un tourbillon de flammes emprisonne la
            // cible pendant quatre à cinq tours.
            // Feu d’Enfer 36 100 50 5 Feu Speciale La cible est entouree d’un torrent de
            // flammes ardentes qui la brûlent.
            // Boutefeu 40 120 100 15 Feu Physique Le lanceur s’embrase avant de charger la
            // cible, ce qui peut la brûler. Le choc blesse aussi gravement le lanceur.
        }

        /*
         * 
         * @Override
         * public void innerFocus() {
         * // Augmente son atq de 5% (par exemple)
         * setPhysical((int) (getPhysical() + getPhysical() * 0.05));
         * }
         */
    }

    public class Arcko extends Tower implements TowerLoader{ //Treecko - Grovyle - Sceptile
        public Arcko(String name, int atkPhysical, int atkSpecial, Types type1, Types type2) {
            super(name, atkPhysical, atkSpecial, type1, type2, "ressources/sprite/treecko.png", State.LOCKED);
            // innerFocus(); // jsp si faut l'appeller la ou pas
            primarySkill();
        }

        @Override
        public void evolution() { //marche pas
            System.out.println("j'ai le niveau?");
            if (getLevel() == 16) {
                System.out.println("j'ai évolué");
                setSprite("ressources/sprite/grovyle.png");
                setName("Massko");
                setPhysical(getPhysical() + 15);
                setSpecial(getSpecial() + 15);
                super.evolution("ressources/sprite/grovyle.png", "Massko", 15, 15, Types.PLANTE, Types.NONE);
            }
            if (getLevel() == 36) {
                //Methode 1
                setSprite("ressources/sprite/sceptile.png");
                setName("Jungko");
                setPhysical(getPhysical() + 30);
                setSpecial(getSpecial() + 30);
                //methode 2
                super.evolution("ressources/sprite/sceptile.png", "Jungko", 30, 30, Types.PLANTE, Types.NONE);
            }
        }

        @Override
        public void primarySkill() {
            Skill griffe = new Skill("Griffe", 40, 0, Types.NORMAL, 1, true,
                    "Lacere la cible avec des griffes acerees pour lui infliger des degats.");
            Skill rugissement = new Skill("Rugissement", 0, 0, Types.NORMAL, 1, true,
                    "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser sa Defense.");
            Skill nothing = new Skill(" ", -1, -1, Types.NONE, -1, false, " ");
            Skill nothing2 = new Skill(" ", -1, -1, Types.NONE, -1, false, " ");
            learnSkill(griffe);
            learnSkill(rugissement);
            learnSkill(nothing);
            learnSkill(nothing2);
        }

        @Override
        public void unlock() {
            if (getLevel() == 6) {
                Skill flammeche = new Skill("Flammeche", 0, 40, Types.FEU, 1, true,
                        "La cible est attaquee par une faible flamme qui peut aussi la brûler.");
                learnSkill(flammeche);
            }
        }

        /*
         * 
         * @Override
         * public void innerFocus() {
         * }
         */
    }

    public class Grenousse extends Tower implements TowerLoader{ //Froakie - Frogadier - Greninja
        public Grenousse(String name, int atkPhysical, int atkSpecial, Types type1, Types type2) {
            super(name, atkPhysical, atkSpecial, type1, type2, "ressources/sprite/froakie.png", State.LOCKED);
            // innerFocus(); // jsp si faut l'appeller la ou pas
            primarySkill();
        }

        @Override
        public void evolution() { //marche pas
            System.out.println("j'ai le niveau?");
            if (getLevel() == 16) {
                System.out.println("j'ai évolué");
                setSprite("ressources/sprite/frogadier.png");
                setName("Croaporal");
                setPhysical(getPhysical() + 15);
                setSpecial(getSpecial() + 15);
                super.evolution("ressources/sprite/frogadier.png", "Croaporal", 15, 15, Types.EAU, Types.NONE);
            }
            if (getLevel() == 36) {
                //Methode 1
                setSprite("ressources/sprite/greninja.png");
                setName("Amphinobi");
                setPhysical(getPhysical() + 30);
                setSpecial(getSpecial() + 30);
                //methode 2
                super.evolution("ressources/sprite/greninja.png", "Amphinobi", 30, 30, Types.EAU, Types.NONE);
            }
        }

        @Override
        public void primarySkill() {
            Skill griffe = new Skill("Griffe", 40, 0, Types.NORMAL, 1, true,
                    "Lacere la cible avec des griffes acerees pour lui infliger des degats.");
            Skill rugissement = new Skill("Rugissement", 0, 0, Types.NORMAL, 1, true,
                    "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser sa Defense.");
            Skill nothing = new Skill(" ", -1, -1, Types.NONE, -1, false, " ");
            Skill nothing2 = new Skill(" ", -1, -1, Types.NONE, -1, false, " ");
            learnSkill(griffe);
            learnSkill(rugissement);
            learnSkill(nothing);
            learnSkill(nothing2);
        }

        @Override
        public void unlock() {
            if (getLevel() == 6) {
                Skill flammeche = new Skill("Flammeche", 0, 40, Types.FEU, 1, true,
                        "La cible est attaquee par une faible flamme qui peut aussi la brûler.");
                learnSkill(flammeche);
            }
            if (getLevel() == 12) {
                Skill dracosouffle = new Skill("Draco-Souffle", 60, 0, Types.DRAGON, 1.2, true,
                        "Le lanceur souffle fort sur la cible pour lui infliger des degâts, ce qui peut aussi la paralyser.");
                learnSkill(dracosouffle);
            }
            if (getLevel() == 16) {
                Skill crocsfeu = new Skill("Crocs Feu", 65, 0, Types.FEU, 1, true,
                        "Le lanceur utilise une morsure enflammee qui peut aussi brûler ou apeurer la cible.");
                learnSkill(crocsfeu);
            }
            // Griffe 1 40 Normal Physique Lacere la cible avec des griffes acerees pour lui
            // infliger des degâts.
            // Rugissement 1 - 100 40 Normal Statut Le lanceur pousse un cri tout mimi pour
            // tromper la vigilance de la cible et baisser son Attaque.
            // Flammeche 4 40 100 25 Feu Speciale La cible est attaquee par une faible
            // flamme qui peut aussi la brûler.
            // Brouillard 8 - 100 20 Normal Statut Le lanceur disperse un nuage d’encre ou
            // de fumee qui reduit la Precision de la cible.
            // Draco-Souffle 12 60 100 20 Dragon Speciale Le lanceur souffle fort sur la
            // cible pour lui infliger des degâts, ce qui peut aussi la paralyser.
            // Crocs Feu 17 65 95 15 Feu Physique Le lanceur utilise une morsure enflammee
            // qui peut aussi brûler ou apeurer la cible.
            // Tranche 20 70 100 20 Normal Physique Le lanceur donne un coup de griffe ou de
            // faux. Taux de critiques eleve.
            // Lance-Flammes 24 90 100 15 Feu Speciale La cible recoit un torrent de flammes
            // qui peut aussi la brûler.
            // Grimace 28 - 100 10 Normal Statut Le lanceur fait une grimace qui effraie la
            // cible et reduit beaucoup sa Vitesse.
            // Danse Flammes 32 35 85 15 Feu Speciale Un tourbillon de flammes emprisonne la
            // cible pendant quatre à cinq tours.
            // Feu d’Enfer 36 100 50 5 Feu Speciale La cible est entouree d’un torrent de
            // flammes ardentes qui la brûlent.
            // Boutefeu 40 120 100 15 Feu Physique Le lanceur s’embrase avant de charger la
            // cible, ce qui peut la brûler. Le choc blesse aussi gravement le lanceur.
        }

        /*
         * 
         * @Override
         * public void innerFocus() {
         * }
         */
    }
}
