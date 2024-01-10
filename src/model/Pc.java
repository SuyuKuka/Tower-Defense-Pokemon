package model;

import com.sun.tools.javac.Main;

import characters.Starter;
import characters.Starter.Salameche;
import graphics.MainMenu;
import graphics.Slot;

import javax.swing.*;
import java.util.*;

import model.Tower.Rayon;
import model.Tower.State;

public class Pc {
    public static final Pc INSTANCE = new Pc();
    private Tower[] team;
    private ArrayList<Tower> pc = new ArrayList<Tower>();
    private Tower select1 = null;
    private Tower select2 = null;
    private Tower lastSelectedTower;
    private int height = 4;
    private int width = 6;

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

    private MainMenu mainMenu;

    public void echange(){
        team = Player.INSTANCE.getTeam();
        if(select1 != null && select2 != null){
            if(select1.getState() == Tower.State.TEAM && select2.getState() == Tower.State.PC){
                //si select1 est dans la team et select2 pc
                System.out.println("1 team, 2 pc");
                int i = Arrays.asList(team).indexOf(select1);
                int j = pc.indexOf(select2);
                select1.setState(State.PC);
                select2.setState(State.TEAM);
                pc.set(j, select1); //select 1 dans le pc
                team[i] = select2;  //select 2 dans la team
            }else if(select1.getState() == Tower.State.PC && select2.getState() == Tower.State.TEAM){
                //si select1 pc et select2 est dans la team
                System.out.println("1 pc, 2 team");
                int i = pc.indexOf(select1);
                int j = Arrays.asList(team).indexOf(select2);
                select1.setState(State.TEAM);
                select2.setState(State.PC);
                team[j] = select1; //select 1 dans la team
                pc.set(i,select2); //select 2 dans le pc       
            }else if(select1.getState() == Tower.State.TEAM && select2.getState() == Tower.State.TEAM){
                //si les deux sont dans la team
                System.out.println("1,2 team");
                int i = Arrays.asList(team).indexOf(select1);
                int j = Arrays.asList(team).indexOf(select2);
                team[i] = select2;
                team[j] = select1;
            }else if(select1.getState() == Tower.State.PC && select2.getState() == Tower.State.PC){
                //si les deux sont dans le pc
                System.out.println("1,2 pc");
                int i = pc.indexOf(select1);
                int j = pc.indexOf(select2);
                pc.set(i, select2);
                pc.set(j, select1);
            }else{
                System.out.println("sus?");
                System.out.println(select1.getState());
                System.out.println(select2.getState());
            }
            select1 = null;
            select2 = null;
            lastSelectedTower = null;
            for(int i = 0; i<Player.INSTANCE.getTeam().length; i=i+1){
                System.out.println(Player.INSTANCE.getTeam()[i].getName());
            }
            System.out.println();
            System.out.println();

            mainMenu.placeTeam();
            mainMenu.placePc();

            for(int i = 0; i<pc.size(); i=i+1){
                System.out.println(pc.get(i).getName() + " ");
            }
        }
    }

    public Tower getLastSelectedTower() {
        return lastSelectedTower;
    }

    public void selected(Tower t){
        System.out.println(pc.size());
        if(select1==null){
            select1=t;
            System.out.println("Selected 1 : " + select1.getName());
        }else if(select1==t){
            select1=null;
        }else if(select2==null && select1 != null){
            select2 = t;
            System.out.println("Selected 2 : " + select2.getName());
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
