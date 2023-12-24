package characters;

import model.Effect;
import model.Skill;
import model.Tower;
import model.Types;

public class Starter {
    public class Salameche extends Tower implements TowerLoader {
        public Salameche(String name, int atkPhysical, int atkSpecial, Types type1, Types type2) {
            super("Salameche", atkPhysical, atkSpecial, type1, type2, "ressources/sprite/charmander.png", State.LOCKED);
            System.out.println("Charge le sprite"); // ressouces/sprite/salameche.png
            // innerFocus(); // jsp si faut l'appeller la ou pas
            primarySkill();
        }

        @Override
        public void evolution() {
            if (getLevel() == 16) {
                setSprite("ressources/sprite/charmeleon.png");
                setName("Reptincel");
                setPhysical(getPhysical() + 15);
                setSpecial(getSpecial() + 15);
            }
            if (getLevel() == 36) {
                setSprite("ressources/sprite/charizard.png");
                setName("Dracaufeu");
                setPhysical(getPhysical() + 30);
                setSpecial(getSpecial() + 30);
                setSecondType(Types.VOL);
            }
        }

        @Override
        public void primarySkill() {
            // Manque les effets
            Skill griffe = new Skill("Griffe", 40, 0, Types.NORMAL, 1, null, true,
                    "Lacere la cible avec des griffes acerees pour lui infliger des degats.");
            Skill rugissement = new Skill("Rugissement", 0, 0, Types.NORMAL, 1, null, true,
                    "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser sa Defense.");
            learnSkill(griffe);
            learnSkill(rugissement);
        }

        @Override
        public void unlock() {
            // manque les effets
            if (getLevel() == 6) {
                Skill flammeche = new Skill("Flammeche", 0, 40, Types.FEU, 1, null, true,
                        "La cible est attaquee par une faible flamme qui peut aussi la brûler.");
                learnSkill(flammeche);
            }
            if (getLevel() == 12) {
                Skill dracosouffle = new Skill("Draco-Souffle", 60, 0, Types.DRAGON, 1.2, null, true,
                        "Le lanceur souffle fort sur la cible pour lui infliger des degâts, ce qui peut aussi la paralyser.");
                learnSkill(dracosouffle);
            }
            if (getLevel() == 16) {
                Skill crocsfeu = new Skill("Crocs Feu", 65, 0, Types.FEU, 1, null, true,
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

    public class Carapuce {

    }

    public class Bulbizarre {

    }

    public class Hericendre {

    }

    public class Kaiminus {

    }

    public class Germignon {

    }

    public class Poussifeu {

    }

    public class Gobou {

    }

    public class Arcko {

    }

    public class Ouisticram {

    }

    public class Tiplouf {

    }

    public class Tortipouss {

    }

    public class Gruikui {

    }

    public class Moustillon {

    }

    public class Vipelierre {

    }

    public class Feunnec {

    }

    public class Grenousse {

    }

    public class Marisson {

    }

    public class Flamiaou {

    }

    public class Otaquin {

    }

    public class Brindibou {

    }

    public class Flambino {

    }

    public class Larmeleon {

    }

    public class Ouistempo {

    }

    public class Chochodile {

    }

    public class Coiffeton {

    }

    public class Poussacha {

    }
}
