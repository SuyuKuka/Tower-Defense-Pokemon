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
        int entryRand = rand.nextInt(l);
        int exitRand = rand.nextInt(l);
        map=new int[w][l];
        entry=new IntCoordinates(0, entryRand);
        map[entry.x()][entry.y()]=4;
        exit=new IntCoordinates(w-1, exitRand);
        map[exit.x()][exit.y()]=5;
    }

    public void mapBuilding(){
        wayFinder();
        wayBuilderV();
        wayBuilderH();
    }

    public void wayFinder(){
        for (int i = 1; i < map.length-3; i=i+3) {
            map[i][rand.nextInt(map[0].length-3)]=2;
        }
    }

    public void wayBuilderV(){
        int posPrec=0;
        for (int i = 1; i < map.length-1; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]!=0){
                    posPrec=j;
                    break;
                }
            }
            map[i][posPrec]=3;
        }
    }
    
    public void wayBuilderH(){
        int posPrec=0;
        int posHead=0;
        for(int i = 1; i<20; i++){
            for (int j = 0; j < 20; j++) {
                //Set le précédent, celui qui est à la case i-1
                if(map[i-1][j]!=0){
                    posPrec=j;
                }
                //Set le head, celui qui est à la case i
                if(map[i][j]!=0){
                    posHead=j;
                }
                
            }
            //Créer le chemin ssi le précédént du posHead est différent de 3
            if(map[i-1][posHead]!=3){
                //Cas négatif, création du chemin
                if(posPrec-posHead<0){
                    for(int j=posHead; j>=posPrec; j=j-1){
                        if(map[i][j]!= map[exit.x()][exit.y()]){
                            map[i][j]=3;
                        }
                    }
                }
                //Cas positif, création du chemin
                if(posPrec-posHead>=0){
                    for(int j=posHead; j<=posPrec; j=j+1){
                        if(map[i][j]!= map[exit.x()][exit.y()]){
                            map[i][j]=3;
                        }
                    }
                }
            }
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
