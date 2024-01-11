package model;

import characters.Starter;
import characters.Starter.Salameche;
import graphics.InGamePc;
import graphics.MainMenu;
import graphics.Slot;
import model.Tower.*;

import java.util.*;


public class Pc {
    public static final Pc INSTANCE = new Pc();
    private Tower[] team;
    private ArrayList<Tower> pc = new ArrayList<Tower>();
    private Tower select1 = null;
    private Tower select2 = null;
    private Tower lastSelectedTower;
    private int height = 4;
    private int width = 6;

    private MainMenu mainMenu;
    private InGamePc inGamePc;

    public Pc(){
        for(int i=0; i<height * width; i=i+1){
            if(i == 6){
                Salameche sala = new Starter().new Salameche();
                sala.setState(State.PC);
                pc.add(sala);
            }else{
                pc.add(new Tower("nothing", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.PC, -1, Rayon.NONE, -1));
            }
        }
    }

    public void echange(){
        team = Player.INSTANCE.getTeam();
        if(select1 != null && select2 != null){
            if(select1.getState() == Tower.State.TEAM && select2.getState() == Tower.State.PC){
                if(pkmTeamRemain() && (!select1.getSprite().contains("ressources/sprite/nothing.png") || !select2.getSprite().contains("ressources/sprite/nothing.png"))){
                    int i = Arrays.asList(team).indexOf(select1);
                    int j = pc.indexOf(select2);
                    select1.setState(State.PC);
                    select2.setState(State.TEAM);
                    pc.set(j, select1); //select 1 dans le pc
                    team[i] = select2;  //select 2 dans la team
                }
            }else if(select1.getState() == Tower.State.PC && select2.getState() == Tower.State.TEAM){
                int i = pc.indexOf(select1);
                int j = Arrays.asList(team).indexOf(select2);
                select1.setState(State.TEAM);
                select2.setState(State.PC);
                team[j] = select1; //select 1 dans la team
                pc.set(i,select2); //select 2 dans le pc
            }else if(select1.getState() == Tower.State.TEAM && select2.getState() == Tower.State.TEAM){
                int i = Arrays.asList(team).indexOf(select1);
                int j = Arrays.asList(team).indexOf(select2);
                team[i] = select2;
                team[j] = select1;
            }else if(select1.getState() == Tower.State.PC && select2.getState() == Tower.State.PC){
                int i = pc.indexOf(select1);
                int j = pc.indexOf(select2);
                pc.set(i, select2);
                pc.set(j, select1);
            }else{
                System.out.println("PROBLEME");
                System.out.println(select1.getState());
                System.out.println(select2.getState());
            }
            select1 = null;
            select2 = null;
            lastSelectedTower = null;

            mainMenu.placeTeam();
            mainMenu.placePc();
            if(inGamePc!=null){
                inGamePc.placeTeam();
                inGamePc.placePc();
            }
        }
    }

    public Tower getLastSelectedTower() {
        return lastSelectedTower;
    }

    public void selected(Tower t){
        // for(int i=0; i<6; i=i+1){
        //     System.out.println(Player.INSTANCE.getTeam()[i].getName());
        // }
        // System.out.println("-------------------------");
        // for(int i=0; i<pc.size(); i=i+1){
        //     System.out.println(pc.get(i).getName());
        // }
        // System.out.println("==========================");
        team = Player.INSTANCE.getTeam();
        if(select1==null){
            select1=t;
            System.out.println(select1.getState());
        }else if(select1==t){
            select1=null;
        }else if(select2==null && select1 != null){
            select2 = t;
            System.out.println(select2.getState());
        }
        
        echange();
    }

    public boolean pkmTeamRemain(){
        int noNothing = 0;
        for(int i=0; i<team.length; i=i+1){
            if(!team[i].getSprite().contains("ressources/sprite/nothing.png")){
                noNothing++;
            }
        }
        if(noNothing>1){
            return true;
        }
        return false;
    }

    public void setTeam(Tower[] team) {
        this.team = team;
    }

    public Tower[] getTeam() {
        return team;
    }

    public ArrayList<Tower> getPc() {
        return pc;
    }

    public void setSelect1(Tower select1) {
        this.select1 = select1;
    }

    public void setSelect2(Tower select2) {
        this.select2 = select2;
    }

    public Tower getSelect1() {
        return select1;
    }

    public Tower getSelect2() {
        return select2;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void setInGamePc(InGamePc inGamePc) {
        this.inGamePc = inGamePc;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
