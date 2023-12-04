package model;

import java.util.ArrayList;
import java.util.Random;

//GENERE UNE MAP ALEATOIRE
public class Map {

    public static int[][] map;

    public static int[][] roadMap;

    private int[] entry = new int[2];
    private int[] exit = new int[2];;

    private Random rand = new Random();

    private ArrayList<Corner> cornerList = new ArrayList<>();

    public Map(int w, int l) {
        int entryRand = rand.nextInt(l);
        int exitRand = rand.nextInt(l);
        map = new int[w][l];
        entry[0] = 0;
        entry[1] = entryRand;
        map[entry[0]][entry[1]] = 4;
        exit[0] = w - 1;
        exit[1] = exitRand;
        map[exit[0]][exit[1]] = 5;

        mapBuilding();

    }

    public class Corner {

        private int[] pos;
        private boolean topOrientation;
        private boolean rightOrientation;

        private int taille = rand.nextInt(3) + 3;

        private boolean haveLoop = false;

        public Corner(int[] pos, boolean top, boolean right) {
            this.pos = pos;
            topOrientation = top;
            rightOrientation = right;

        }

        public void beALoop(int[][] map) {

            if (enoughtSpace()) {
                if (topOrientation && rightOrientation) {
                    makeAQLoop();
                    haveLoop = true;
                } else if (topOrientation) {
                    makeASLoop();
                    haveLoop = true;
                } else if (rightOrientation) {
                    makeAALoop();
                    haveLoop = true;
                } else {
                    makeAZLoop();
                    haveLoop = true;
                }
            }

        }

        public boolean enoughtSpace() {

            int x = pos[0];
            int y = pos[1];

            try {
                if (topOrientation && rightOrientation) {
                    for (int i = 0; i < taille; i++) {
                        for (int j = 0; j < taille; j++) {
                            if (map[x + i][y - j] != 0
                                    && (map[x + i][y - j] != 1 || pos[0] != (x + i) || pos[1] != (y - j))) {
                                return false;
                            }
                        }
                    }
                    return true;
                } else if (topOrientation) {
                    for (int i = 0; i < taille; i++) {
                        for (int j = 0; j < taille; j++) {
                            if (map[x + i][y + j] != 0
                                    && (map[x + i][y + j] != 1 || pos[0] != (x + i) || pos[1] != (y + j))) {
                                return false;
                            }
                        }
                    }
                    return true;
                } else if (rightOrientation) {
                    for (int i = 0; i < taille; i++) {
                        for (int j = 0; j < taille; j++) {
                            if (map[x - i][y - j] != 0
                                    && (map[x - i][y - j] != 1 || pos[0] != (x - i) || pos[1] != (y - j))) {
                                return false;
                            }
                        }
                    }
                    return true;
                } else {
                    for (int i = 0; i < taille; i++) {
                        for (int j = 0; j < taille; j++) {
                            if (map[x - i][y + j] != 0
                                    && (map[x - i][y + j] != 1 || pos[0] != (x - i) || pos[1] != (y + j))) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }

        public void makeAQLoop() {
            int x = pos[0];
            int y = pos[1];

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
                        map[x + i][y - j] = 6;
                    }
                }
            }
        }

        public void makeASLoop() {
            int x = pos[0];
            int y = pos[1];

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
                        map[x + i][y + j] = 6;
                    }
                }
            }
        }

        public void makeAALoop() {
            int x = pos[0];
            int y = pos[1];

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
                        map[x - i][y - j] = 6;
                    }
                }
            }
        }

        public void makeAZLoop() {
            int x = pos[0];
            int y = pos[1];

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
                        map[x - i][y + j] = 6;
                    }
                }
            }
        }

        public String toString() {
            if (topOrientation && rightOrientation) {
                return "corner: (x,y) " + pos[1] + "," + pos[0] + " Q";
            } else if (topOrientation) {
                return "corner: " + pos[1] + "," + pos[0] + " S";
            } else if (rightOrientation) {
                return "corner: " + pos[1] + "," + pos[0] + " A";
            } else {
                return "corner: " + pos[1] + "," + pos[0] + " Z";
            }
        }
    }

    private void mapBuilding() {
        // POUR FAIRE LE CHEMINS
        wayFinder();
        wayBuilderV();
        wayBuilderH();

        // AJOUTE UN CADRE
        border();

        // AJOUTE DES LOOPING
        findCorner();
        makeLoop();
        affiche(Ennemi.LIST.getList());

        // PATHFINDING
        fakeMove();
        System.out.println(this);
        // AJOUTE DES OBSTACLES
        // addObstacles();
        // deployObstacles();

    }

    public String toString(int[][] roadMap) {
        String res = "";
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (roadMap[i][j] != 0) {
                    if (roadMap[i][j] >= 10) {
                        res = res + roadMap[i][j] + " ";
                    } else {
                        res = res + roadMap[i][j] + "  ";
                    }
                } else {
                    res = res + "   ";
                }

            }
            res = res + "\n";
        }
        return res;
    }

    private int[] takeLoopZ(int increment, int[] oldPos) {
        int[] pos = new int[] { oldPos[0], oldPos[1] };
        System.out.println("LOOPING");

        return pos;
    }

    private int[] takeLoopQS(int increment, int[] oldPos) {
        int[] pos = new int[2];
        int taille = 0;
        boolean right = false;
        boolean top = false;
        for (Corner corner : cornerList) {
            if ((oldPos[0] == corner.pos[0]) && (oldPos[1] == corner.pos[1])) {
                taille = corner.taille;
                right = corner.rightOrientation;
                top = corner.topOrientation;
                pos[0] = corner.pos[0];
                pos[1] = corner.pos[1];
                break;
            }
        }

        if (right) {
            for (int i = 0; i < taille; i++) {
                roadMap[pos[0]][pos[1]] = increment;
                pos[1] = pos[1] + 1;
            }
        } else {

        }

        return pos;
    }

    private void fakeMove() {

        roadMap = new int[map.length][map[0].length];

        int increment = 1;
        int[] pos = getFrontEntry();
        roadMap[getFrontEntry()[0]][getFrontEntry()[1]] = increment++;

        test: while (pos[0] != exit[0] || pos[1] != exit[1]) {

            if (map[pos[0] + 1][pos[1]] != 0) {
                while (map[pos[0] + 1][pos[1]] != 0) {
                    roadMap[pos[0] + 1][pos[1]] = increment++;
                    pos = new int[] { pos[0] + 1, pos[1] };
                    if (map[pos[0]][pos[1]] == 6) {
                        pos = takeLoopQS(increment, pos);
                        break test;
                    }
                }
            }

            else if (map[pos[0]][pos[1] + 1] != 0) {
                while (map[pos[0]][pos[1] + 1] != 0) {
                    roadMap[pos[0]][pos[1] + 1] = increment++;
                    pos = new int[] { pos[0], pos[1] + 1 };
                    if (map[pos[0]][pos[1]] == 6) {
                        System.out.println("ZLOOP");
                        pos = takeLoopZ(increment, pos);
                        break;
                    }
                }
            }

            else if (map[pos[0]][pos[1] - 1] != 0) {
                while (map[pos[0]][pos[1] - 1] != 0) {
                    roadMap[pos[0]][pos[1] - 1] = increment++;
                    pos = new int[] { pos[0], pos[1] - 1 };
                    if (map[pos[0]][pos[1]] == 6) {
                        System.out.println("ALOOP");
                        // pos = takeLoopA(increment, pos);
                        break test;
                    }
                }
            }

        }

    }

    private void wayFinder() {
        for (int i = 1; i < map.length - 3; i = i + 3) {
            map[i][rand.nextInt(map[0].length - 3)] = 2;
        }
    }

    private void wayBuilderV() {
        int posPrec = 0;
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 0) {
                    posPrec = j;
                    break;
                }
            }
            map[i][posPrec] = 3;
        }
    }

    private void wayBuilderH() {
        int posPrec = 0;
        int posHead = 0;
        for (int i = 1; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                // Set le précédent, celui qui est à la case i-1
                if (map[i - 1][j] != 0) {
                    posPrec = j;
                }
                // Set le head, celui qui est à la case i
                if (map[i][j] != 0) {
                    posHead = j;
                }

            }
            // Créer le chemin ssi le précédént du posHead est différent de 3
            if (map[i - 1][posHead] != 3) {
                // Cas négatif, création du chemin
                if (posPrec - posHead < 0) {
                    for (int j = posHead; j >= posPrec; j = j - 1) {
                        if (map[i][j] != map[exit[0]][exit[1]]) {
                            map[i][j] = 3;
                        }
                    }
                }
                // Cas positif, création du chemin
                if (posPrec - posHead >= 0) {
                    for (int j = posHead; j <= posPrec; j = j + 1) {
                        if (map[i][j] != map[exit[0]][exit[1]]) {
                            map[i][j] = 3;
                        }
                    }
                }
            }
        }
    }

    private void border() {
        int[][] big = new int[map.length + 1][map[0].length + 2];
        for (int i = 0; i < big.length - 1; i++) {
            for (int j = 1; j < big[0].length - 1; j++) {
                big[i][j] = map[i][j - 1];
            }
        }
        map = big;

        for (int i = 0; i < big.length; i++) {
            for (int j = 0; j < big.length; j++) {
                if (map[i][j] == 4) {
                    entry = new int[2];
                    entry[0] = i;
                    entry[1] = j;
                }
                if (map[i][j] == 5) {
                    exit = new int[2];
                    exit[0] = i;
                    exit[1] = j;
                }
            }
        }
    }

    private void makeLoop() {
        for (int i = 0; i < cornerList.size(); i++) {
            if (rand.nextInt(5) == 2) {
                cornerList.get(i).beALoop(map);
            }
        }

    }

    private void findCorner() {
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 3) {

                    if (map[i - 1][j] == 3 && map[i][j + 1] == 3) {
                        map[i][j] = 1;
                        cornerList.add(new Corner(new int[] { i, j }, true, true));
                    }
                    if (map[i - 1][j] == 3 && map[i][j - 1] == 3) {
                        map[i][j] = 1;
                        cornerList.add(new Corner(new int[] { i, j }, true, false));
                    }
                    if (map[i + 1][j] == 3 && map[i][j + 1] == 3) {
                        map[i][j] = 1;
                        cornerList.add(new Corner(new int[] { i, j }, false, true));
                    }
                    if (map[i + 1][j] == 3 && map[i][j - 1] == 3) {
                        map[i][j] = 1;
                        cornerList.add(new Corner(new int[] { i, j }, false, false));
                    }
                }
            }
        }
    }

    private void addObstacles() {
        for (int i = 0; i < 45; i++) { // QUANTITE D'OBSTACLE AJUSTABLE
            int x = rand.nextInt(map.length);
            int y = rand.nextInt(map[0].length);
            if (map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 5) {
                i--;
            } else {
                map[x][y] = 6;
            }
        }
    }

    private void deployObstacles() {
        for (int i = 0; i < map.length; i++) {
            int rayon = 2;
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 6) {
                    try {
                        for (int j2 = 0; j2 < rayon; j2++) {
                            if (map[i + j2][j] != 3 && map[i + j2][j] != 4 && map[i + j2][j] != 5) {
                                map[i + j2][j] = 9;
                            }
                        }
                        for (int j2 = 0; j2 < rayon; j2++) {
                            if (map[i][j + j2] != 3 && map[i][j + j2] != 4 && map[i][j + j2] != 5) {
                                map[i][j + j2] = 9;
                            }
                        }
                        for (int j2 = 0; j2 < rayon; j2++) {
                            if (map[i - j2][j] != 3 && map[i - j2][j] != 4 && map[i - j2][j] != 5) {
                                map[i - j2][j] = 9;
                            }
                        }
                        for (int j2 = 0; j2 < rayon; j2++) {
                            if (map[i][j - j2] != 3 && map[i][j - j2] != 4 && map[i][j - j2] != 5) {
                                map[i][j - j2] = 9;
                            }
                        }
                    } catch (Exception e) {

                    }

                }
            }
        }
    }

    public void affiche(ArrayList<Ennemi> ennemiPresent) {
        System.out.print("      ");
        for (int j = 0; j < map[0].length; j = j + 1) {
            System.out.print((char) ('A' + j) + " ");
        }
        System.out.println();
        System.out.print("      ");
        for (int j = 0; j < map[0].length; j = j + 1) {
            System.out.print("==");
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            if (i + 1 < 10) {
                System.out.print("0" + (i + 1) + " || ");
            } else {
                System.out.print(i + 1 + " || ");
            }
            for (int j = 0; j < map[0].length; j++) {
                // System.out.print(intToChar(map, ennemiPresent)[i][j] + " ");
                // POSSIBLE PAS COMPATIBLE AVEC CERTAIN SHELL
                System.out.print(intToCharColor(map, ennemiPresent)[i][j]);
            }
            System.out.print(" ||");
            System.out.println();
        }
        System.out.print("      ");
        for (int j = 0; j < map[0].length; j = j + 1) {
            System.out.print("==");
        }
        System.out.println();
    }

    private char[][] intToChar(int[][] map, ArrayList<Ennemi> ennemiPresent) {
        char[][] plateau = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case 0:
                        plateau[i][j] = '.';
                        break;
                    case 2:
                        plateau[i][j] = 'T';
                        break;
                    case 3:
                        plateau[i][j] = ' ';
                        break;
                    case 4:
                        plateau[i][j] = 'E';
                        break;
                    case 5:
                        plateau[i][j] = 'S';
                        break;
                    case 9:
                        plateau[i][j] = (char) 5;
                        break;
                    default:
                        break;
                }
                for (Ennemi e : ennemiPresent) {
                    if (i == e.getPos()[0] && j == e.getPos()[1]) {
                        // plateau[i][j] = (char) 4;
                        plateau[i][j] = '#';
                    }
                }
            }
        }
        return plateau;
    }

    private String[][] intToCharColor(int[][] map, ArrayList<Ennemi> ennemiPresent) {
        String[][] plateau = new String[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case 0:
                        plateau[i][j] = ". ";
                        break;

                    case 2:
                        plateau[i][j] = "\u001B[47m" + "T " + "\u001B[0m";
                        break;
                    case 3:
                        plateau[i][j] = "\u001B[45m" + "  " + "\u001B[0m";
                        break;
                    case 4:
                        plateau[i][j] = "\u001B[45m" + "\u001B[31m" + "E " + "\u001B[0m";
                        break;
                    case 5:
                        plateau[i][j] = "\u001B[45m" + "\u001B[34m" + "S " + "\u001B[0m";
                        break;
                    case 9:
                        plateau[i][j] = "\u001B[32m" + (char) 5 + " " + "\u001B[0m";
                        break;
                    default:
                        plateau[i][j] = "? ";
                        break;
                }
                for (Ennemi e : ennemiPresent) {
                    if (i == e.getPos()[0] && j == e.getPos()[1]) {
                        // plateau[i][j] = (char) 4;
                        if (e.isBoss()) {
                            plateau[i][j] = "\u001B[45m" + "\u001B[36m" + "X " + "\u001B[0m";
                        } else {
                            plateau[i][j] = "\u001B[45m" + "\u001B[36m" + "i " + "\u001B[0m";
                        }
                    }
                }
            }
        }
        return plateau;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 0) {
                    res = res + map[i][j] + " ";
                } else {
                    res = res + "  ";
                }

            }
            res = res + "\n";
        }
        return res;
    }

    public int[][] getConfig() {
        return map;
    }

    public int[] getFrontEntry() {
        int[] frontEntry = { 1, entry[1] };
        return frontEntry;
    }

    public int[] getExit() {
        return exit;
    }
}
