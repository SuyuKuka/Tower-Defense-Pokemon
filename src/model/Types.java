package model;

public enum Types {
    NORMAL, FEU, EAU, PLANTE, ELECTRIQUE, GLACE,
    COMBAT, POISON, SOL, VOL, PSY, INSECTE, ROCHE,
    SPECTRE, DRAGON, TENEBRE, ACIER, FEE, NONE;

    private static double[][] typeWeakness = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 0, 1, 1, 0.5, 1 }, // NORMAL
            { 1, 0.5, 0.5, 2, 1, 2, 1, 1, 1, 1, 1, 2, 0.5, 1, 0.5, 1, 2, 1 }, // FIRE
            { 1, 2, 0.5, 0.5, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 0.5, 1, 1, 1 }, // WATER
            { 1, 0.5, 2, 0.5, 1, 1, 1, 0.5, 2, 0.5, 1, 0.5, 2, 1, 0.5, 1, 0.5, 1 }, // GRASS
            { 1, 1, 2, 0.5, 0.5, 1, 1, 1, 0, 2, 1, 1, 1, 1, 0.5, 1, 1, 1 }, // ELECTRIC
            { 1, 0.5, 0.5, 2, 1, 0.5, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 0.5, 1 }, // ICE
            { 2, 1, 1, 1, 1, 2, 1, 0.5, 1, 0.5, 0.5, 0.5, 2, 0, 1, 2, 2, 0.5 }, // FIGHTING
            { 1, 1, 1, 2, 1, 1, 1, 0.5, 0.5, 1, 1, 1, 0.5, 0.5, 1, 1, 0, 2 }, // POISON
            { 1, 2, 1, 0.5, 2, 1, 1, 2, 1, 0, 1, 0.5, 2, 1, 1, 1, 2, 1 }, // GROUND
            { 1, 1, 1, 2, 0.5, 1, 2, 1, 1, 1, 1, 2, 0.5, 1, 1, 1, 0.5, 1 }, // FLYING
            { 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 0.5, 1, 1, 1, 1, 0, 0.5, 1 }, // PSYCHIC
            { 1, 0.5, 1, 2, 1, 1, 0.5, 0.5, 1, 0.5, 2, 1, 1, 0.5, 1, 2, 0.5, 0.5 }, // BUG
            { 1, 2, 1, 1, 1, 2, 0.5, 1, 0.5, 2, 1, 2, 1, 1, 1, 1, 0.5, 1 }, // ROCK
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 0.5, 1, 1 }, // GHOST
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 0.5, 0 }, // DRAGON
            { 1, 1, 1, 1, 1, 1, 0.5, 1, 1, 1, 2, 1, 1, 2, 1, 0.5, 1, 0.5 }, // DARK
            { 1, 0.5, 0.5, 1, 0.5, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0.5, 2 }, // STEEL
            { 1, 0.5, 1, 1, 1, 1, 2, 0.5, 1, 1, 1, 1, 1, 1, 2, 2, 0.5, 1 },// FAIRY
    };

    public static Types stringToType(String st) {
        switch (st) {
            //ANGLAIS TO TYPES
            case "Normal":
                return NORMAL;
            case "Fire":
                return FEU;
            case "Water":
                return EAU;
            case "Grass":
                return PLANTE;
            case "Electric":
                return ELECTRIQUE;
            case "Ice":
                return GLACE;
            case "Fighting":
                return COMBAT;
            case "Poison":
                return POISON;
            case "Ground":
                return SOL;
            case "Flying":
                return VOL;
            case "Psychic":
                return PSY;
            case "Bug":
                return INSECTE;
            case "Rock":
                return ROCHE;
            case "Ghost":
                return SPECTRE;
            case "Dragon":
                return DRAGON;
            case "Dark":
                return TENEBRE;
            case "Steel":
                return ACIER;
            case "Fairy":
                return FEE;
            //FRANCAIS TO TYPES
            case "NORMAL":
                return NORMAL;
            case "FEU":
                return FEU;    
            case "EAU":
                return EAU;
            case "PLANTE":
                return PLANTE;
            case "ELECTRIQUE":
                return ELECTRIQUE;
            case "GLACE":
                return GLACE;
            case "COMBAT":
                return COMBAT;
            case "POISON":
                return POISON;
            case "SOL":
                return SOL;
            case "VOL":
                return VOL;
            case "PSY":
                return PSY;
            case "INSECTE":
                return INSECTE;
            case "ROCHE":
                return ROCHE;
            case "SPECTRE":
                return SPECTRE;
            case "DRAGON":
                return DRAGON;
            case "TENEBRE":
                return TENEBRE;
            case "ACIER":
                return ACIER;
            case "FEE":
                return FEE;
            default:
                return NONE;
        }
    }

    public static int typeToInt(Types type) {
        switch (type) {
            case NORMAL:
                return 0;
            case FEU:
                return 1;
            case EAU:
                return 2;
            case PLANTE:
                return 3;
            case ELECTRIQUE:
                return 4;
            case GLACE:
                return 5;
            case COMBAT:
                return 6;
            case POISON:
                return 7;
            case SOL:
                return 8;
            case VOL:
                return 9;
            case PSY:
                return 10;
            case INSECTE:
                return 11;
            case ROCHE:
                return 12;
            case SPECTRE:
                return 13;
            case DRAGON:
                return 14;
            case TENEBRE:
                return 15;
            case ACIER:
                return 16;
            case FEE:
                return 17;
            default:
                return -1;
        }
    }

    public static double typeChart(Skill attacker, Ennemi defender) {
        int attakerInt = typeToInt(attacker.getType());
        int defenderInt1 = typeToInt(defender.getType()[0]);
        int defenderInt2 = typeToInt(defender.getType()[1]);

        // si il a juste un type
        if (defenderInt2 == -1) {
            return typeWeakness[attakerInt][defenderInt1];
        }
        // si il a un double type
        return (typeWeakness[attakerInt][defenderInt1] * typeWeakness[attakerInt][defenderInt2]);
    }

    public static String typeToImg(Types type){
        switch (type) {
            case NORMAL:
                return "ressources/type/NORMAL.png";
            case FEU:
                return "ressources/type/FEU.png";
            case EAU:
                return "ressources/type/EAU.png";
            case PLANTE:
                return "ressources/type/PLANTE.png";
            case ELECTRIQUE:
                return "ressources/type/ELECTRIQUE.png";
            case GLACE:
                return "ressources/type/GLACE.png";
            case COMBAT:
                return "ressources/type/COMBAT.png";
            case POISON:
                return "ressources/type/POISON.png";
            case SOL:
                return "ressources/type/SOL.png";
            case VOL:
                return "ressources/type/VOL.png";
            case PSY:
                return "ressources/type/PSY.png";
            case INSECTE:
                return "ressources/type/INSECTE.png";
            case ROCHE:
                return "ressources/type/ROCHE.png";
            case SPECTRE:
                return "ressources/type/SPECTRE.png";
            case DRAGON:
                return "ressources/type/DRAGON.png";
            case TENEBRE:
                return "ressources/type/TENEBRE.png";
            case ACIER:
                return "ressources/type/ACIER.png";
            case FEE:
                return "ressources/type/FEE.png";
            default:
                return "ressources/sprite/nothing.png";
        }
    }

    public static String typeToString(Types type){
        switch (type) {
            case NORMAL:
                return "NORMAL";
            case FEU:
                return "FEU";
            case EAU:
                return "EAU";
            case PLANTE:
                return "PLANTE";
            case ELECTRIQUE:
                return "ELECTRIQUE";
            case GLACE:
                return "GLACE";
            case COMBAT:
                return "COMBAT";
            case POISON:
                return "POISON";
            case SOL:
                return "SOL";
            case VOL:
                return "VOL";
            case PSY:
                return "PSY";
            case INSECTE:
                return "INSECTE";
            case ROCHE:
                return "ROCHE";
            case SPECTRE:
                return "SPECTRE";
            case DRAGON:
                return "DRAGON";
            case TENEBRE:
                return "TENEBRE";
            case ACIER:
                return "ACIER";
            case FEE:
                return "FEE";
            default:
                return "NOTHING";
        }
    }
}
