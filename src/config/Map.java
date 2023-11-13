package config;

import java.util.Random;

import geometry.IntCoordinates;

//GENERE UNE MAP ALEATOIRE
public class Map {

    public static int[][] map;

    private int[] entry = new int[2];
    private int[] exit = new int[2];;

    private Random rand = new Random();

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

    private void mapBuilding() {
        // POUR FAIRE LE CHEMINS
        wayFinder();
        wayBuilderV();
        wayBuilderH();

        // AJOUTE UN CADRE
        border();

        // AJOUTE DES OBSTACLES
        addObstacles();
        deployObstacles();

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

    private void addObstacles() {
        for (int i = 0; i < 20; i++) { // QUANTITE D'OBSTACLE AJUSTABLE
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

    public static char[][] intToChar(int[][] map) {
        char[][] plateau = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case 0:
                        plateau[i][j] = '+';
                        break;
                    case 1:
                        plateau[i][j] = '1';
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
                    case 6:
                        plateau[i][j] = '/';
                        break;
                    case 9:
                        plateau[i][j] = '/';
                        break;
                    default:
                        break;
                }
            }
        }
        return plateau;
    }

    public void affiche() {
        System.out.print("   ");
        for (int j = 1; j < map[0].length + 1; j = j + 1) {
            if (j < 10) {
                System.out.print("0" + j + " ");
            } else {
                System.out.print(j + " ");
            }
        }
        System.out.println();
        System.out.print("   ");
        for (int j = 1; j < map[0].length + 1; j = j + 1) {
            if (j < 10) {
                System.out.print("==");
            } else {
                System.out.print("==");
            }
            if (j != map.length) {
                System.out.print("=");
            }
        }
        System.out.println();

        for (int i = 0; i < map.length; i++) {
            System.out.print((char) ('A' + i) + "|| ");
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(intToChar(map)[i][j] + "  ");
            }

            System.out.println();
        }
    }

    public int[][] getConfig() {
        return map;
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

    public int[] getEntry() {
        return entry;
    }

    public int[] getExit() {
        return exit;
    }
}
