package control;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import characters.*;
import graphics.GameScreen;
import graphics.MainGraphics;
import graphics.UpdateMap;
import graphics.TowerGUI.SkillButton;
import model.*;
import model.Tower.State;
import model.InitWaves.Difficulty;

import java.awt.Cursor;
import java.io.FileNotFoundException;

public class Control {

    private Player user;
    private MapState ms;

    public static final Control INSTANCE = new Control();

    private Hero selectedHero;



    private Control() {

    }

    public void gamePause(JButton pause) {
        if (ms.getPause()) {
            ms.setPause(false);
            pause.setIcon(new ImageIcon("ressources/template/pauseButton.png"));
        } else {
            ms.setPause(true);
            pause.setIcon(new ImageIcon("ressources/template/resumeButton.png"));
        }
    }

    public void gameDoubleSpeed(JButton speed) {
        if (ms.getGameSpeed() == 50) {
            ms.setGameSpeed(1);
            speed.setIcon(new ImageIcon("ressources/template/speedButton.png"));
        } else if (ms.getGameSpeed() == 1) {
            ms.setGameSpeed(50);
            speed.setIcon(new ImageIcon("ressources/template/normalButton.png"));
        }

    }

    public void buttonPressed(JButton button, String st, MainGraphics main) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> {
            if (st.startsWith("gameScreen")) {
                button.setIcon(
                        new ImageIcon("ressources/button/" + st.substring(13) + "_button_pressed.png"));
            } else {
                button.setIcon(new ImageIcon("ressources/button/" + st + "_button_pressed.png"));
            }

            Timer timer = new Timer(200, e1 -> {
                if (st.startsWith("gameScreen")) {
                    button.setIcon(new ImageIcon("ressources/button/" + st.substring(13) + "_button.png"));
                } else {
                    button.setIcon(new ImageIcon("ressources/button/" + st + "_button.png"));
                }
                changePanel(st, main);
            });
            timer.setRepeats(false);
            timer.start();
        });
    }

    public void changePanel(String buttonName, MainGraphics main) {
        System.out.println(buttonName);
        Timer timer = new Timer(1, e1 -> {
            if (buttonName.startsWith("gameScreen")) {
                startGame(buttonName,main);
            }
            if (buttonName.equals("retour")) {
                main.getCardLayout().show(main.getMainPanel(), "menu");
            } else {
                main.getCardLayout().show(main.getMainPanel(), buttonName);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void startGame(String buttonName, MainGraphics main){
        Difficulty diff = Difficulty.stringToDifficulty(buttonName.substring(13));
        MapState mState;




        if (buttonName.substring(11).charAt(0) == 'c') {
            mState = new MapState(diff, 0);
        } else {
            mState = new MapState(diff, 1);
        }
        GameScreen screen = new GameScreen(main);
        screen.setmState(mState);
        main.getMainPanel().add(screen, "gameScreen");
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                if(selectedHero!=null){
                   mState.setSelectedHero(selectedHero);
                }
                mState.startGraphic(screen);
                return null;
            }
        };
        main.getCardLayout().show(main.getMainPanel(), buttonName.substring(0, 10));
        worker.execute();
    }



    public void switchPkm(Tower pkm) {
        if (pkm.getState() == State.PC) {
            user.getPc().remove(pkm);
            user.addteam(pkm);
        } else {
            user.getPc().add(pkm);
            user.removeTeam(pkm);
        }
    }

    public JPanel backButton(MainGraphics main) {

        JButton backButton = new JButton(new ImageIcon("ressources/button/retour_button.png"));
        buttonPressed(backButton, "retour", main);

        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);
        backPanel.add(backButton);

        return backPanel;
    }

    public void goHome(GameScreen screen) {
        screen.getePresent().reset();
        Player.INSTANCE.resetTeam();

        screen.getMain().getCardLayout().show(screen.getMain().getMainPanel(), "menu");

        screen.revalidate();
        screen.repaint();

    }

    public void restart(GameScreen screen, MapState state) {
        screen.getePresent().reset();
        Player.INSTANCE.resetTeam();

        String prompt = "gameScreen_";
        if (state.getEndless()) {
            prompt = prompt + "e_";
        } else {
            prompt = prompt + "c_";
        }
        if (state.getDiff() == Difficulty.EASY) {
            prompt = prompt + "facile";
        }
        if (state.getDiff() == Difficulty.NORMAL) {
            prompt = prompt + "moyen";
        }
        if (state.getDiff() == Difficulty.HARD) {
            prompt = prompt + "difficile";
        }

        changePanel(prompt, screen.getMain());

    }

    public void freeplay(GameScreen screen, JPanel tmp, MapState state) {
        screen.removeAll();
        screen.add(tmp);
        screen.setVisible(true);

        screen.revalidate();
        screen.repaint();

        state.setEndless(true);
        state.setInc(state.getCount());
        try {
            state.startGraphic(screen);
        } catch (FileNotFoundException ignored) {
        }

    }

    public void setTowerOnMap(int[] pos, Tower pkm, MapState state) {
        state.setTower(pos, pkm);
        state.setMoney(state.getMoney() - pkm.getPrice());
        pkm.setPrice(pkm.getPrice() + pkm.getPrice() / 10);
    }

    public void openPC() {
        // TODO OPEN THE PC
    }

    public void chooseSkill(SkillButton skill, Tower pkm) {
        if (pkm.getCurrentSkill(pkm.getSelect()) != skill.getSkill()) {
            pkm.setCurrentSkill(skill.getSkill());
            pkm.getGUI().updatePkm();
            System.out.println(skill.getSkill().getName());
        }
    }

    public boolean skillReplace(Tower pkm, Skill newSkill) {
        if (ms.getMoney() - newSkill.getPrice() >= 0) {
            pkm.getSkills()[pkm.getSelect()] = newSkill;
            ms.setMoney(ms.getMoney() - newSkill.getPrice());
            return true;
        } else {
            return false;
        }
    }

    public void giveTiles(Tower pkm, UpdateMap.Tiles tile) {
        pkm.setTiles(tile);
    }

    public void giveRangePanel(Tower pkm, JPanel towerRange) {
        pkm.setRangePanel(towerRange);// CONTROLE
    }

    public void setMs(MapState ms) {
        this.ms = ms;
    }

    public void setUser(Player user) {
        this.user = user;
    }

    public void setSelectedHero(Hero selectedHero) {
        this.selectedHero = selectedHero;
    }
}
