package model;

import java.util.*;

public class Pc {
    public static final Pc INSTANCE = new Pc();
    private Tower[] team;
    private ArrayList<Tower> pc = new ArrayList<Tower>();
    private Tower select1 = null;
    private Tower select2 = null;

    public void echange(){
        team = Player.INSTANCE.getTeam();
        if(select1 != null && select2 != null){
            if(select1.getState() == Tower.State.TEAM && select2.getState() == Tower.State.PC){
                //si select1 est dans la team et select2 pc
                int i = Arrays.asList(team).indexOf(select1);
                int j = pc.indexOf(select2);
                pc.set(i, select1); //select 1 dans le pc
                team[j] = select2;  //select 2 dans la team
                System.out.println(i);
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
        }
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
}
