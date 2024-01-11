package model;

import java.util.ArrayList;
import java.util.Arrays;

import characters.Anchwatt;
import characters.Chartor;
import characters.Ecrapince;
import characters.Escargaume;
import characters.Gringolem;
import characters.Lilia;
import characters.Makuhita;
import characters.MotismaFroid;
import characters.Mysdibule;
import characters.Ningale;
import characters.Nodulithe;
import characters.Obalie;
import characters.Rozbouton;
import characters.Tarsal;
import characters.Terhal;
import characters.Tylton;
import graphics.InGamePc;
import graphics.MainMenu;
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

    private MainMenu mainMenu;
    private InGamePc inGamePc;

    private MapState state;

    public Pc() {
        for (int i = 0; i < height * width; i = i + 1) {
            if (i == 0) {
                pc.add(new Anchwatt());
            } else if (i == 1) {
                pc.add(new Chartor());
            } else if (i == 2) {
                pc.add(new Ecrapince());
            } else if (i == 3) {
                pc.add(new Escargaume());
            } else if (i == 4) {
                pc.add(new Gringolem());
            } else if (i == 5) {
                pc.add(new Lilia());
            } else if (i == 6) {
                pc.add(new Makuhita());
            } else if (i == 7) {
                pc.add(new MotismaFroid());
            } else if (i == 8) {
                pc.add(new Mysdibule());
            } else if (i == 9) {
                pc.add(new Ningale());
            } else if (i == 10) {
                pc.add(new Nodulithe());
            } else if (i == 11) {
                pc.add(new Obalie());
            } else if (i == 12) {
                pc.add(new Rozbouton());
            } else if (i == 13) {
                pc.add(new Tarsal());
            } else if (i == 14) {
                pc.add(new Terhal());
            } else if (i == 15) {
                pc.add(new Tylton());
            }else {
                pc.add(new Tower("nothing", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.PC, -1, Rayon.NONE, -1));
            }
            pc.get(i).setState(State.PC);
        }
    }

    public int getIndex(Tower pkm) {
        for (int i = 0; i < team.length; i++) {
            if (team[i].getName().equals(pkm.getName()) && !team[i].getName().contains("nothing")) {
                return i;
            } else if (select1.getSprite().contains("ressources/sprite/nothing.png")) {
                return Arrays.asList(team).indexOf(pkm);
            } else if (select2.getSprite().contains("ressources/sprite/nothing.png")) {
                return Arrays.asList(team).indexOf(pkm);
            }
        }
        return -1;
    }

    public void echange() {
        team = Player.INSTANCE.getTeam();
        if (select1 != null && select2 != null) {
            if (select1.getState() == Tower.State.TEAM && select2.getState() == Tower.State.PC) {
                if (pkmTeamRemain() && (!select1.getSprite().contains("ressources/sprite/nothing.png") || !select2.getSprite().contains("ressources/sprite/nothing.png"))) {
                    int i = getIndex(select1);
                    int j = pc.indexOf(select2);

                    pc.set(j, select1);
                    team[i] = select2;

                    select1.setState(State.PC);
                    select2.setState(State.TEAM);

                    if (inGamePc != null) {
                        state.removeFromMap(select1);
                    }
                }
            } else if (select1.getState() == Tower.State.PC && select2.getState() == Tower.State.TEAM) {
                int i = pc.indexOf(select1);
                int j = getIndex(select2);

                team[j] = select1;
                pc.set(i, select2);

                select1.setState(State.TEAM);
                select2.setState(State.PC);

                if (inGamePc != null) {
                    state.removeFromMap(select2);
                }
            } else if (select1.getState() == Tower.State.TEAM && select2.getState() == Tower.State.TEAM) {
                int i = Arrays.asList(team).indexOf(select1);
                int j = Arrays.asList(team).indexOf(select2);

                team[i] = select2;
                team[j] = select1;
            } else if (select1.getState() == Tower.State.PC && select2.getState() == Tower.State.PC) {
                int i = pc.indexOf(select1);
                int j = pc.indexOf(select2);

                pc.set(i, select2);
                pc.set(j, select1);
            } else {
                System.out.println("PROBLEME");
            }
            select1 = null;
            select2 = null;
            lastSelectedTower = null;

            mainMenu.placeTeam();
            mainMenu.placePc();
            if (inGamePc != null) {
                inGamePc.placeTeam();
                inGamePc.placePc();
            }
        }
    }

    public Tower getLastSelectedTower() {
        return lastSelectedTower;
    }

    public void selected(Tower t) {
        team = Player.INSTANCE.getTeam();
        if (select1 == null) {
            select1 = t;
        } else if (select1 == t) {
            select1 = null;
        } else if (select2 == null && select1 != null) {
            select2 = t;
        }
        echange();
    }

    public boolean pkmTeamRemain() {
        int noNothing = 0;
        for (int i = 0; i < team.length; i = i + 1) {
            if (!team[i].getSprite().contains("ressources/sprite/nothing.png")) {
                noNothing++;
            }
        }
        if (noNothing > 1) {
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

    public void setState(MapState state) {
        this.state = state;
    }
}
