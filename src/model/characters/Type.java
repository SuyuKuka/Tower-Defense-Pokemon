package model.characters;

public enum Type {
    NONE, EAU, FEU, SOL, VOL,
    POISON, NORMAL, SPECTRE, TENEBRE,
    ELECTRIK, ACIER, ROCHE, DRAGON, COMBAT,
    INSECTE, PLANTE, PSY, GLACE, FEE;

    public static Type stringToType(String st) {
        switch (st) {
            case "EAU":
                return EAU;
            case "FEU":
                return FEU;
            case "SOL":
                return SOL;
            case "VOL":
                return VOL;
            case "POISON":
                return POISON;
            case "NORMAL":
                return NORMAL;
            case "SPECTRE":
                return SPECTRE;
            case "TENEBRE":
                return TENEBRE;
            case "ELECTRIK":
                return ELECTRIK;
            case "ACIER":
                return ACIER;
            case "ROCHE":
                return ROCHE;
            case "DRAGON":
                return DRAGON;
            case "COMBAT":
                return COMBAT;
            case "INSECTE":
                return INSECTE;
            case "PLANTE":
                return PLANTE;
            case "PSY":
                return PSY;
            case "GLACE":
                return GLACE;
            case "FEE":
                return FEE;
            default:
                return NONE;
        }
    }
}
