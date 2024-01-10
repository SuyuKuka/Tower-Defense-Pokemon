package control;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import graphics.GameScreen;
import graphics.MainGraphics;
import graphics.TowerGUI.SkillButton;
import model.Tower.State;
import model.MapState;
import model.Player;
import model.Skill;
import model.Tower;
import model.InitWaves.Difficulty;

import java.awt.Cursor;
import java.io.FileNotFoundException;

public class Control {

    private Player user;
    private MapState ms;

    public static final Control INSTANCE = new Control();

    private Control() {

    }

    public void gamePause(JButton pause) {
        if (ms.getPause() == true) {
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
            if (st.length() >= 10 && st.substring(0, 10).equals("gameScreen")) {
                button.setIcon(
                        new ImageIcon("ressources/button/" + st.substring(13, st.length()) + "_button_pressed.png"));
            } else {
                button.setIcon(new ImageIcon("ressources/button/" + st + "_button_pressed.png"));
            }

            Timer timer = new Timer(200, e1 -> {
                if (st.length() >= 10 && st.substring(0, 10).equals("gameScreen")) {
                    button.setIcon(new ImageIcon("ressources/button/" + st.substring(13, st.length()) + "_button.png"));
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
        Timer timer = new Timer(1, e1 -> {
            if (buttonName.equals("retour")) {
                main.getCardLayout().show(main.getMainPanel(), "menu");
            } else {

                if (buttonName.length() >= 10 && buttonName.substring(0, 10).equals("gameScreen")) {

                    Difficulty diff = Difficulty.stringToDifficulty(buttonName.substring(13, buttonName.length()));
                    MapState mState;
                    if (buttonName.substring(11, buttonName.length()).charAt(0) == 'c') {
                        mState = new MapState(diff, 0);
                    } else {
                        mState = new MapState(diff, 1);
                    }
                    GameScreen screen = new GameScreen(main);
                    screen.setmState(mState);
                    main.getMainPanel().add(screen, "gameScreen");
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            mState.startGraphic(screen);
                            return null;
                        }
                    };
                    main.getCardLayout().show(main.getMainPanel(), buttonName.substring(0, 10));
                    worker.execute();
                } else {
                    main.getCardLayout().show(main.getMainPanel(), buttonName);
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
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
        } catch (FileNotFoundException e) {
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

    public void setMs(MapState ms) {
        this.ms = ms;
    }

    public void setUser(Player user) {
        this.user = user;
    }
}
