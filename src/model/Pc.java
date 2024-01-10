package model;

import com.sun.tools.javac.Main;
import graphics.MainMenu;
import graphics.Slot;

import javax.swing.*;
import java.util.*;

public class Pc {
    public static final Pc INSTANCE = new Pc();
    private Tower[] team;
    private ArrayList<Tower> pc = new ArrayList<Tower>();
    private Tower select1 = null;
    private Tower select2 = null;
    private Tower lastSelectedTower;

    private MainMenu mainMenu;

    public void echange(){
        team = Player.INSTANCE.getTeam();
        if(select1 != null && select2 != null){
           // System.out.println(select1.getName()+" "+select2.getName());




            if(select1.getState() == Tower.State.TEAM && select2.getState() == Tower.State.PC){
                //si select1 est dans la team et select2 pc
                int i = Arrays.asList(team).indexOf(select1);
                int j = pc.indexOf(select2);
                pc.set(i, select1); //select 1 dans le pc
                team[j] = select2;  //select 2 dans la team
            }else if(select1.getState() == Tower.State.PC && select2.getState() == Tower.State.TEAM){
                //si select2 est dans la team et select1 pc
                int i = pc.indexOf(select1);
                int j = Arrays.asList(team).indexOf(select1);
                pc.set(j,select2); //select 2 dans le pc
                team[i] = select1; //select 1 dans la team
            }else if(select1.getState() == Tower.State.TEAM && select2.getState() == Tower.State.TEAM){
                //si les deux sont dans la team
                int i = Arrays.asList(team).indexOf(select1);
                int j = Arrays.asList(team).indexOf(select2);
                team[i] = select2;
                team[j] = select1;
            }else{
                //si les deux sont dans le pc
                int i = pc.indexOf(select1);
                int j = pc.indexOf(select2);
                pc.set(i, select2);
                pc.set(j, select1);
            }
            select1 = null;
            select2 = null;
            lastSelectedTower = null;
            for(int i = 0; i<Player.INSTANCE.getTeam().length; i=i+1){
                System.out.println(Player.INSTANCE.getTeam()[i].getName());
            }
            System.out.println();

            mainMenu.placeTeam();
        }else{
            //Vide on verra
        }
    }

    public Tower getLastSelectedTower() {
        return lastSelectedTower;
    }

    public void selected(Tower t){
        if(select1==null){
            select1=t;
        }else if(select1==t){
            select1=null;
        }else if(select2==null && select1 != null){
            select2 = t;
        }

        echange();
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
}
