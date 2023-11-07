package characters;

public class character {

    private String name;
    private int value; //tour -> atk, bad -> pv
    private type type1;
    private type type2; 

    public character(String name, int value, type type1, type type2){
        this.name = name;
        this.value= value;
        this.type1= type1;
        this.type2 = type2;
    }
}
