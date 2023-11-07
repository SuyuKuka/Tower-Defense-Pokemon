package config;

import java.util.Random;
import config.geometry.IntCoordinates;


//GENERE UNE MAP ALEATOIRE
public class Map {

    private int[][] map; 

    final private IntCoordinates entry;
    final private IntCoordinates exit;

    private Random rand= new Random();

    public Map(int w, int l){
        map=new int[w][l];
        entry=new IntCoordinates(0,l-1);
        map[entry.x()][entry.y()]=5;
        exit=new IntCoordinates(w-1, 0);
        map[exit.x()][exit.y()]=4;
    }

    public void mapBuilding(){
        wayFinder();
        wayBuilderV();
    }

    public void wayFinder(){
        for (int i = 1; i < map.length-3; i=i+3) {
            map[i][rand.nextInt(map[0].length-3)]=2;
        }
    }

    public void wayBuilderV(){
        int posPrec=0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]!=0){
                    posPrec=j;
                    break;
                }
            }
            map[i][posPrec]=3;
        }
    }
    

    public boolean droite (int[] map, int index){
        for (int i = index; i < map.length; i++) {
            if(map[index]!=0){
                return true;
            }
        }
        return false;
    }

    public boolean gauche (int[] map, int index){
        for (int i = index; i >=0; i--) {
            if(map[index]!=0){
                return true;
            }
        }
        return false;
    }
    

    public String toString(){
        String res="";
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                 if(map[i][j]!=0){
                     res=res+map[i][j]+" ";
                }
                else{
                    res=res+"  ";
                }
                
            }
            res=res+"\n";
        }
        return res;
    }

}
