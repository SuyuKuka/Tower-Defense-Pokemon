package model;

import java.util.ArrayList;
import java.util.Random;

import graphics.Classique;
import model.InitWaves.Difficulty;
import model.Map.DirectionTiles;

import java.awt.Rectangle;

//GENERE UNE MAP ALEATOIRE
public class Map {
    // ATTRIBUT
    public int[][] map;
    public static RoadMap roadMap; // A SUPPR

    private DirectionTiles[][] dirMap;
    private DirectionTiles[][] dirMapWithCorner;

    private int[] entry;
    private int[] exit;

    private Random rand = new Random();

    private ArrayList<Corner> cornerList = new ArrayList<>();
    private int increment;

    private ArrayList<int[]> obstaclePos = new ArrayList<>();
    private Difficulty diff;

    public Map(int height, int width, Difficulty diff) {

        map = new int[height][width];
        dirMap = new DirectionTiles[height][width];

        entry = new int[] { 0, rand.nextInt(width - 2) + 1 };
        exit = new int[] { height - 1, rand.nextInt(width - 2) + 1 };

        map[entry[0]][entry[1]] = 4;
        map[exit[0]][exit[1]] = 5;

        map[exit[0] - 1][exit[1]] = 3;
        this.diff = diff;
        mapBuilding();

    }

    private void mapBuilding() {

        // POUR FAIRE LE CHEMINS
        wayFinder();
        wayBuilderV();
        wayBuilderH();
        if (getFrontEntry()[1] + 1 != 3 && getFrontEntry()[1] - 1 != 3
                && dirMap[getFrontEntry()[0]][getFrontEntry()[1]].getType() == TilesType.H) {
            dirMap[getFrontEntry()[0]][getFrontEntry()[1]] = new DirectionTiles(TilesType.V, getFrontEntry()[0],
                    getFrontEntry()[1]);
        }
        dirMap[getEntry()[0]][getEntry()[1]] = new DirectionTiles(TilesType.V, getEntry()[0], getEntry()[1]);
        dirMap[getExit()[0]][getExit()[1]] = new DirectionTiles(TilesType.V, getExit()[0], getExit()[1]);
        // AJOUTE DES LOOPING
        findCorner();
        makeLoop();

        // PATHFINDING
        roadMap = new RoadMap();

        // debug
        // System.out.println(this);
        // System.out.println(roadMap.toString());
        // debugTilesMap();
        dirMapWithCorner = spriteTilesMap();

        unify();

        // AJOUTE DES OBSTACLES
        addObstacles();
        deployObstacles();
    }

    private void wayFinder() {
        if (diff == Difficulty.EASY) {
            for (int i = 1; i < map.length - 3; i = i + 3) {
                map[i][rand.nextInt(map[0].length - 2) + 1] = 2;
            }
        } else if (diff == Difficulty.NORMAL) {
            for (int i = 1; i < map.length - 3; i = i + 5) {
                map[i][rand.nextInt(map[0].length - 2) + 1] = 2;
            }
        } else {
            for (int i = 1; i < map.length - 3; i = i + 9) {
                map[i][rand.nextInt(map[0].length - 2) + 1] = 2;
            }
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
            dirMap[i][posPrec] = new DirectionTiles(TilesType.V, i, posPrec);
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
                createLeftWay(i, posHead, posPrec);
                // Cas positif, création du chemin
                createRightWay(i, posHead, posPrec);
            }
        }
    }

    private void createRightWay(int i, int posHead, int posPrec) {
        if (posPrec - posHead >= 0) {
            for (int j = posHead; j <= posPrec; j = j + 1) {
                if (map[i][j] != map[exit[0]][exit[1]]) {
                    map[i][j] = 3;
                    dirMap[i][j] = new DirectionTiles(TilesType.H, i, j);
                }
            }
        }
    }

    private void createLeftWay(int i, int posHead, int posPrec) {
        if (posPrec - posHead < 0) {
            for (int j = posHead; j >= posPrec; j = j - 1) {
                if (map[i][j] != map[exit[0]][exit[1]]) {
                    map[i][j] = 3;
                    dirMap[i][j] = new DirectionTiles(TilesType.H, i, j);
                }
            }
        }
    }

    private void findCorner() {
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 3) {

                    makeTopRightCorner(i, j);
                    makeTopLeftCorner(i, j);
                    makeBotLeftCorner(i, j);
                    makeBotRightCorner(i, j);
                }
            }
        }
    }

    private void makeTopRightCorner(int i, int j) {
        if ((map[i - 1][j] == 3 || map[i - 1][j] == 4) && (map[i][j + 1] == 3 || map[i][j + 1] == 1)) {
            map[i][j] = 1;
            dirMap[i][j] = new DirectionTiles(TilesType.H, i, j);
            Corner c = new Corner(new int[] { i, j }, true, true);
            if (map[i - 1][j] == 4) {
                c.setLoopLocked(true);
            }
            cornerList.add(c);
        }
    }

    private void makeTopLeftCorner(int i, int j) {
        if ((map[i - 1][j] == 3 || map[i - 1][j] == 4) && (map[i][j - 1] == 3 || map[i][j - 1] == 1)) {
            map[i][j] = 1;
            dirMap[i][j] = new DirectionTiles(TilesType.H, i, j);
            Corner c = new Corner(new int[] { i, j }, true, false);
            if (map[i - 1][j] == 4) {
                c.setLoopLocked(true);
            }
            cornerList.add(c);
        }
    }

    private void makeBotLeftCorner(int i, int j) {
        if ((map[i + 1][j] == 3 || map[i + 1][j] == 5) && (map[i][j + 1] == 3 || map[i][j + 1] == 1)) {
            map[i][j] = 1;
            dirMap[i][j] = new DirectionTiles(TilesType.V, i, j);

            Corner c = new Corner(new int[] { i, j }, false, true);
            if (map[i + 1][j] == 5) {
                c.setLoopLocked(true);
            }
            cornerList.add(c);
        }
    }

    private void makeBotRightCorner(int i, int j) {
        if ((map[i + 1][j] == 3 || map[i + 1][j] == 5) && (map[i][j - 1] == 3 || map[i][j - 1] == 1)) {
            map[i][j] = 1;
            dirMap[i][j] = new DirectionTiles(TilesType.V, i, j);

            Corner c = new Corner(new int[] { i, j }, false, false);
            if (map[i + 1][j] == 5) {
                c.setLoopLocked(true);
            }
            cornerList.add(c);
        }
    }

    private void makeLoop() {
        boolean chance;
        if (diff == Difficulty.EASY) {
            chance = true; // 100%
        } else if (diff == Difficulty.NORMAL) {
            chance = rand.nextInt(4) > 2; // 50%
        } else {
            chance = false; // 0%
        }

        for (int i = 0; i < cornerList.size(); i++) {
            if (chance) {
                cornerList.get(i).beALoop(map);

            }
        }

    }

    private void unify() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 6 || map[i][j] == 1) {
                    map[i][j] = 3;
                }
            }
        }
    }

    private void addObstacles() {
        int nbObstacles = 0;
        if (diff == Difficulty.EASY) {
            nbObstacles = 20;
        } else if (diff == Difficulty.NORMAL) {
            nbObstacles = 40;
        } else {
            nbObstacles = 60;
        }

        for (int i = 0; i < nbObstacles; i++) { // QUANTITE D'OBSTACLE AJUSTABLE
            int x = rand.nextInt(map.length);
            int y = rand.nextInt(map[0].length);
            if (map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 5) {
                i--;
            } else {
                map[x][y] = 6;
                obstaclePos.add(new int[] { x, y });
            }
        }
    }

    private void deployObstacles() {

        for (int[] obst : obstaclePos) {
            map[obst[0]][obst[1]] = 9;
            expandTop(obst);
            expandBot(obst);
            expandLeft(obst);
            expandRight(obst);
        }
    }

    private void expandTop(int[] obst) {

        if (obst[0] - 1 >= 0
                && map[obst[0] - 1][obst[1]] != 3
                && map[obst[0] - 1][obst[1]] != 4
                && map[obst[0] - 1][obst[1]] != 5) {
            map[obst[0] - 1][obst[1]] = 9;
        }

    }

    private void expandBot(int[] obst) {

        if (obst[0] + 1 <= map.length - 1
                && map[obst[0] + 1][obst[1]] != 3
                && map[obst[0] + 1][obst[1]] != 4
                && map[obst[0] + 1][obst[1]] != 5) {
            map[obst[0] + 1][obst[1]] = 9;
        }

    }

    private void expandRight(int[] obst) {

        if (obst[1] + 1 <= map.length - 1
                && map[obst[0]][obst[1] + 1] != 3
                && map[obst[0]][obst[1] + 1] != 4
                && map[obst[0]][obst[1] + 1] != 5) {
            map[obst[0]][obst[1] + 1] = 9;
        }

    }

    private void expandLeft(int[] obst) {

        if (obst[1] - 1 >= 0
                && map[obst[0]][obst[1] - 1] != 3
                && map[obst[0]][obst[1] - 1] != 4
                && map[obst[0]][obst[1] - 1] != 5) {
            map[obst[0]][obst[1] - 1] = 9;
        }

    }

    public void affiche(ArrayList<Ennemi> ennemiPresent) {

        topLetterPart();
        for (int i = 0; i < map.length; i++) {

            numbersAtStart(i);

            for (int j = 0; j < map[0].length; j++) {
                // System.out.print(intToChar(map, ennemiPresent)[i][j] + " ");
                System.out.print(intToCharColor(map, ennemiPresent)[i][j]);
            }

            System.out.print(" ||");
            System.out.println();
        }

        bottomPart();
    }

    private void topLetterPart() {
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
    }

    private void bottomPart() {
        System.out.print("      ");
        for (int j = 0; j < map[0].length; j = j + 1) {
            System.out.print("==");
        }
        System.out.println();
    }

    private void numbersAtStart(int i) {
        if (i + 1 < 10) {
            System.out.print("0" + (i + 1) + " || ");
        } else {
            System.out.print(i + 1 + " || ");
        }
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
            }
        }
        for (Ennemi e : ennemiPresent) {
            // plateau[i][j] = (char) 4;
            if (e.isBoss()) {
                plateau[e.getPos()[0]][e.getPos()[1]] = "\u001B[45m" + "\u001B[36m" + "X " + "\u001B[0m";
            } else {
                plateau[e.getPos()[0]][e.getPos()[1]] = "\u001B[45m" + "\u001B[36m" + "i " + "\u001B[0m";
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

    public int[] getFrontExit() {
        int[] frontExit = { map.length - 2, exit[1] };
        return frontExit;
    }

    public int[] getEntry() {
        return entry;
    }

    public int[] getExit() {
        return exit;
    }

    public int[][] getRoadMap() {
        return roadMap.readableMap();
    }

    public ArrayList<Corner> getCornerList() {
        return cornerList;
    }

    public int[][] getMap() {
        return map;
    }

    public DirectionTiles[][] getdirMap() {
        return dirMap;
    }

    public class Corner {

        private int[] pos;
        private boolean topOrientation;
        private boolean rightOrientation;

        private int taille = rand.nextInt(3) + 3;

        private boolean haveLoop = false;
        private boolean loopLocked = false;

        public Corner(int[] pos, boolean top, boolean right) {
            this.pos = pos;
            topOrientation = top;
            rightOrientation = right;

        }

        public void setLoopLocked(boolean loopLocked) {
            this.loopLocked = loopLocked;
        }

        private void beALoop(int[][] map) {
            if (map[pos[0]][pos[1] + 1] == 5 || map[pos[0]][pos[1] - 1] == 4) {
                haveLoop = false;
            } else {

                if (enoughtSpace() && !loopLocked) {
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

        }

        private boolean enoughtSpace() {
            int x = pos[0];
            int y = pos[1];

            try {
                if (topOrientation && rightOrientation) {
                    return check1(x, y);
                } else if (topOrientation) {
                    return check2(x, y);
                } else if (rightOrientation) {
                    return check3(x, y);
                } else {
                    return check4(x, y);
                }
            } catch (Exception e) {
                return false;
            }
        }

        private boolean check1(int x, int y) {

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (x + taille >= map.length || y - taille <= 0) {

                        return false;
                    }
                    if (map[x + i][y - j] != 0 && (map[x + i][y - j] != 1 || pos[0] != (x + i) || pos[1] != (y - j))) {

                        return false;

                    }
                }
            }
            return true;
        }

        private boolean check2(int x, int y) {

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (x + taille >= map.length || y + taille >= map.length) {
                        return false;
                    }
                    if (map[x + i][y + j] != 0 && (map[x + i][y + j] != 1 || pos[0] != (x + i) || pos[1] != (y + j))) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean check3(int x, int y) {

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (x - taille <= 0 || y - taille <= 0) {

                        return false;
                    }
                    if (map[x - i][y - j] != 0 && (map[x - i][y - j] != 1 || pos[0] != (x - i) || pos[1] != (y - j))) {

                        return false;

                    }
                }
            }
            return true;
        }

        private boolean check4(int x, int y) {
            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (x - taille <= 0 || y + taille >= map.length) {

                        return false;
                    }
                    if (map[x - i][y + j] != 0 && (map[x - i][y + j] != 1 || pos[0] != (x - i) || pos[1] != (y + j))) {

                        return false;

                    }
                }
            }
            return true;
        }

        private void makeAQLoop() {
            int x = pos[0];
            int y = pos[1];

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
                        map[x + i][y - j] = 6;
                    }
                }
            }
            taille = taille - 1;
            map[x][y] = 1;
            for (int i = 0; i < taille; i++) {
                dirMap[x + i][y] = new DirectionTiles(TilesType.V, x + i, y);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.X, x, y);
            x = x + taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x][y - i] = new DirectionTiles(TilesType.H, x, y - i);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.S, x, y);
            y = y - taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x - i][y] = new DirectionTiles(TilesType.V, x - i, y);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.Q, x, y);
            x = x - taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x][y + i] = new DirectionTiles(TilesType.H, x, y + i);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.A, x, y);
            taille = taille + 1;
        }

        private void makeASLoop() {
            int x = pos[0];
            int y = pos[1];

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
                        map[x + i][y + j] = 6;
                    }
                }
            }
            taille = taille - 1;
            map[x][y] = 1;
            for (int i = 0; i < taille; i++) {
                dirMap[x + i][y] = new DirectionTiles(TilesType.V, x + i, y);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.X, x, y);
            x = x + taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x][y + i] = new DirectionTiles(TilesType.H, x, y + i);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.Q, x, y);
            y = y + taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x - i][y] = new DirectionTiles(TilesType.V, x - i, y);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.S, x, y);
            x = x - taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x][y - i] = new DirectionTiles(TilesType.H, x, y - i);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.Z, x, y);
            taille = taille + 1;
        }

        private void makeAALoop() {
            int x = pos[0];
            int y = pos[1];

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
                        map[x - i][y - j] = 6;
                    }
                }
            }
            taille = taille - 1;
            map[x][y] = 1;
            for (int i = 0; i < taille; i++) {
                dirMap[x][y - i] = new DirectionTiles(TilesType.H, x, y - i);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.X, x, y);
            y = y - taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x - i][y] = new DirectionTiles(TilesType.V, x - i, y);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.Q, x, y);
            x = x - taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x][y + i] = new DirectionTiles(TilesType.H, x, y + i);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.A, x, y);
            y = y + taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x + i][y] = new DirectionTiles(TilesType.V, x + i, y);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.Z, x, y);
            taille = taille + 1;
        }

        private void makeAZLoop() {
            int x = pos[0];
            int y = pos[1];

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
                        map[x - i][y + j] = 6;
                    }
                }
            }
            map[x][y] = 1;
            taille = taille - 1;
            for (int i = 0; i < taille; i++) {
                dirMap[x][y + i] = new DirectionTiles(TilesType.H, x, y + i);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.X, x, y);
            y = y + taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x - i][y] = new DirectionTiles(TilesType.V, x - i, y);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.S, x, y);
            x = x - taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x][y - i] = new DirectionTiles(TilesType.H, x, y - i);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.Z, x, y);
            y = y - taille;
            for (int i = 0; i < taille; i++) {
                dirMap[x + i][y] = new DirectionTiles(TilesType.V, x + i, y);
            }
            dirMap[x][y] = new DirectionTiles(TilesType.A, x, y);
            taille = taille + 1;
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

        public boolean haveLoop() {
            return haveLoop;
        }

        public int[] getCornerPos() {
            return pos;
        }

        public boolean getTopOrientation() {
            return topOrientation;
        }

        public boolean getRightOrientation() {
            return rightOrientation;
        }
    }

    public class RoadMap {

        private int[][] roadMap = new int[map.length][map[0].length];

        public RoadMap() {
            int[] pos = getEntry();
            roadMap[getEntry()[0]][getEntry()[1]] = increment++;

            while (!atEnd(pos)) {

                // debug(pos);
                pos = straightBot(pos);
                pos = straightLeft(pos);
                pos = straightRight(pos);

            }
            roadMap[pos[0]][pos[1]] = increment++;
        }

        private boolean atEnd(int[] pos) {
            if (pos[0] == exit[0] && pos[1] == exit[1]) {
                return true;
            }
            return false;
        }

        private void debug(int pos[]) {
            ///////////////////////////
            // affiche(Ennemi.LIST.getBossList());
            System.out.println(this);
            System.out.println(toString(roadMap));
            System.out.println("POSITION ACTUELLE :" + pos[0] + " " + pos[1]);
            System.out.println("==========================================================");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            ////////////////////////////
        }

        private Corner getCorner(int[] pos) {
            for (Corner corner : cornerList) {
                if ((pos[0] == corner.pos[0]) && (pos[1] == corner.pos[1])) {
                    return corner;
                }
            }
            return null;
        }

        private int[] straightBot(int[] pos) {

            while (map[pos[0] + 1][pos[1]] != 0
                    && map[pos[0] + 1][pos[1]] != 6
                    && roadMap[pos[0] + 1][pos[1]] == 0) {

                if (roadMap[pos[0]][pos[1]] == 0) {
                    roadMap[pos[0]][pos[1]] = increment++;
                }
                pos = new int[] { pos[0] + 1, pos[1] };
                if (atEnd(pos)) {
                    break;
                }
                if (map[pos[0]][pos[1]] == 1 && getCorner(pos) != null && getCorner(pos).haveLoop) {
                    pos = takeLoopQS(pos);
                    break;
                }
            }
            return pos;
        }

        private int[] straightRight(int[] pos) {

            while (map[pos[0]][pos[1] + 1] != 0
                    && map[pos[0]][pos[1] + 1] != 6
                    && roadMap[pos[0]][pos[1] + 1] == 0) {
                if (roadMap[pos[0]][pos[1]] == 0) {
                    roadMap[pos[0]][pos[1]] = increment++;
                }
                pos = new int[] { pos[0], pos[1] + 1 };
                if (atEnd(pos)) {
                    break;
                }
                if (map[pos[0]][pos[1]] == 1 && getCorner(pos) != null && getCorner(pos).haveLoop) {
                    pos = takeLoopZ(pos);
                    break;
                }
            }
            return pos;

        }

        private int[] straightLeft(int[] pos) {

            while (map[pos[0]][pos[1] - 1] != 0
                    && map[pos[0]][pos[1] - 1] != 6
                    && roadMap[pos[0]][pos[1] - 1] == 0) {

                if (roadMap[pos[0]][pos[1]] == 0) {
                    roadMap[pos[0]][pos[1]] = increment++;
                }
                pos = new int[] { pos[0], pos[1] - 1 };
                if (atEnd(pos)) {
                    break;
                }
                if (map[pos[0]][pos[1]] == 1 && getCorner(pos) != null && getCorner(pos).haveLoop) {
                    pos = takeLoopA(pos);
                    break;
                }
            }
            return pos;

        }

        private int[] takeLoopZ(int[] oldPos) {

            int[] pos = new int[] { oldPos[0], oldPos[1] };
            int LoopLength = 0;

            if (getCorner(oldPos) != null) {
                LoopLength = getCorner(oldPos).taille;
            }
            if (getCorner(oldPos).haveLoop) {
                goRight(LoopLength, pos);
                goTop(LoopLength, pos);
                goLeft(LoopLength, pos);
                goBot(LoopLength, pos);
            }
            return pos;
        }

        private int[] takeLoopA(int[] oldPos) {

            int[] pos = new int[] { oldPos[0], oldPos[1] };
            int LoopLength = 0;

            if (getCorner(oldPos) != null) {
                LoopLength = getCorner(oldPos).taille;
            }
            if (getCorner(oldPos).haveLoop) {
                goLeft(LoopLength, pos);
                goTop(LoopLength, pos);
                goRight(LoopLength, pos);
                goBot(LoopLength, pos);
            }
            return pos;
        }

        private int[] takeLoopQ(int[] oldPos) {

            int[] pos = new int[] { oldPos[0], oldPos[1] };
            int LoopLength = 0;

            if (getCorner(oldPos) != null) {
                LoopLength = getCorner(oldPos).taille;
            }
            if (getCorner(oldPos).haveLoop) {
                goBot(LoopLength, pos);
                goLeft(LoopLength, pos);
                goTop(LoopLength, pos);
                goRight(LoopLength, pos);
            }
            return pos;
        }

        private int[] takeLoopS(int[] oldPos) {

            int[] pos = new int[] { oldPos[0], oldPos[1] };
            int LoopLength = 0;

            if (getCorner(oldPos) != null) {
                LoopLength = getCorner(oldPos).taille;
            }
            if (getCorner(oldPos).haveLoop) {
                goBot(LoopLength, pos);
                goRight(LoopLength, pos);
                goTop(LoopLength, pos);
                goLeft(LoopLength, pos);
            }
            return pos;
        }

        private int[] takeLoopQS(int[] oldPos) {
            if (getCorner(oldPos) != null && getCorner(oldPos).haveLoop) {
                if (getCorner(oldPos).rightOrientation && getCorner(oldPos).topOrientation) {
                    return takeLoopQ(oldPos);
                } else if (getCorner(oldPos).topOrientation) {
                    return takeLoopS(oldPos);
                }
            }
            return oldPos;
        }

        private void goRight(int LoopLength, int[] pos) {
            for (int j = 0; j < LoopLength - 1; j++) {
                if (roadMap[pos[0]][pos[1]] == 0) {
                    roadMap[pos[0]][pos[1]] = increment++;
                }
                pos[1] = pos[1] + 1;
            }
        }

        private void goTop(int LoopLength, int[] pos) {
            for (int j = 0; j < LoopLength - 1; j++) {
                if (roadMap[pos[0]][pos[1]] == 0) {
                    roadMap[pos[0]][pos[1]] = increment++;
                }
                pos[0] = pos[0] - 1;
            }
        }

        private void goLeft(int LoopLength, int[] pos) {
            for (int j = 0; j < LoopLength - 1; j++) {
                if (roadMap[pos[0]][pos[1]] == 0) {
                    roadMap[pos[0]][pos[1]] = increment++;
                }
                pos[1] = pos[1] - 1;
            }
        }

        private void goBot(int LoopLength, int[] pos) {
            for (int j = 0; j < LoopLength - 1; j++) {
                if (roadMap[pos[0]][pos[1]] == 0) {
                    roadMap[pos[0]][pos[1]] = increment++;
                }
                pos[0] = pos[0] + 1;
            }
        }

        public int[][] readableMap() {
            return roadMap;
        }

        public String toString(int[][] roadMap) {
            //////////////////////////////////////////////////////////
            String res = "";
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (roadMap[i][j] != 0) {
                        if (roadMap[i][j] >= 100) {
                            res = res + roadMap[i][j] + " ";
                        } else if (roadMap[i][j] >= 10) {
                            res = res + roadMap[i][j] + "  ";
                        } else {
                            res = res + roadMap[i][j] + "   ";
                        }
                    } else {
                        res = res + "    ";
                    }
                }
                res = res + "\n";
            }
            return res;
            /////////////////////////////////////////////////////////////
        }
    }

    public enum TilesType {
        H, V, A, Z, Q, S, X;
    }

    public class DirectionTiles {

        private TilesType type;
        private Rectangle box;

        public DirectionTiles(TilesType type, int i, int j) {
            box = new Rectangle(j * 32, i * 32, 32, 32);
            this.type = type;

        }

        public TilesType getType() {
            return type;
        }

        public Rectangle getBox() {
            return box;
        }

        public void setType(TilesType type) {
            this.type = type;
        }
    }

    private DirectionTiles[][] spriteTilesMap() {
        DirectionTiles[][] withCorner = dirMap.clone();
        for (Corner c : cornerList) {
            if (c.topOrientation && c.rightOrientation && !c.haveLoop) {
                withCorner[c.getCornerPos()[0]][c.getCornerPos()[1]] = new DirectionTiles(TilesType.Q,
                        c.getCornerPos()[0], c.getCornerPos()[1]);
            } else if (c.topOrientation && !c.haveLoop) {
                withCorner[c.getCornerPos()[0]][c.getCornerPos()[1]] = new DirectionTiles(TilesType.S,
                        c.getCornerPos()[0], c.getCornerPos()[1]);
            } else if (c.rightOrientation && !c.haveLoop) {
                withCorner[c.getCornerPos()[0]][c.getCornerPos()[1]] = new DirectionTiles(TilesType.A,
                        c.getCornerPos()[0], c.getCornerPos()[1]);
            } else if (!c.haveLoop) {
                withCorner[c.getCornerPos()[0]][c.getCornerPos()[1]] = new DirectionTiles(TilesType.Z,
                        c.getCornerPos()[0], c.getCornerPos()[1]);
            }
        }
        return withCorner;
    }

    public DirectionTiles[][] copyTiles(DirectionTiles[][] tilesMap) {
        DirectionTiles[][] res = new DirectionTiles[tilesMap.length][tilesMap[0].length];
        for (int i = 0; i < tilesMap.length; i++) {
            for (int j = 0; j < tilesMap[0].length; j++) {
                if (tilesMap[i][j] != null) {
                    switch (tilesMap[i][j].getType()) {
                        case X:
                            res[i][j] = new DirectionTiles(TilesType.X, i, j);
                            break;
                        case H:
                            res[i][j] = new DirectionTiles(TilesType.H, i, j);
                            break;
                        case V:
                            res[i][j] = new DirectionTiles(TilesType.V, i, j);
                            break;
                        case A:
                            res[i][j] = new DirectionTiles(TilesType.A, i, j);
                            break;
                        case Z:
                            res[i][j] = new DirectionTiles(TilesType.Z, i, j);
                            break;
                        case Q:
                            res[i][j] = new DirectionTiles(TilesType.Q, i, j);
                            break;
                        case S:
                            res[i][j] = new DirectionTiles(TilesType.S, i, j);
                            break;
                    }
                }
            }
        }
        return res;
    }

    private void debugTilesMap() {
        for (int i = 0; i < dirMap.length; i++) {
            for (int j = 0; j < dirMap[0].length; j++) {
                if (dirMap[i][j] == null) {
                    System.out.print("  ");
                } else if (dirMap[i][j].getType() == TilesType.A) {
                    System.out.print("A ");
                } else if (dirMap[i][j].getType() == TilesType.S) {
                    System.out.print("S ");
                } else if (dirMap[i][j].getType() == TilesType.Q) {
                    System.out.print("Q ");
                } else if (dirMap[i][j].getType() == TilesType.Z) {
                    System.out.print("Z ");
                } else if (dirMap[i][j].getType() == TilesType.H) {
                    System.out.print("H ");
                } else if (dirMap[i][j].getType() == TilesType.V) {
                    System.out.print("V ");
                } else if (dirMap[i][j].getType() == TilesType.X) {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }

    }

    public DirectionTiles[][] getDirMapWithCorner() {
        return dirMapWithCorner;
    }

}
