package control;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import graphics.GameScreen;
import graphics.MainGraphics;
import graphics.TowerPresent;
import model.Pokemon.State;
import model.MapState;
import model.Player;
import model.Tower;
import model.InitWaves.Difficulty;

import java.awt.Cursor;

public class Control {

    private Player user;
    private MapState ms;

    public Control(Player p) {
        user = p;
    }

    public Control(MapState ms) {
        this.ms = ms;
    }

    public Control() {

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
        if (ms.getGameSpeed() == 20) {
            ms.setGameSpeed(5);
            speed.setIcon(new ImageIcon("ressources/template/speedButton.png"));
        } else if (ms.getGameSpeed() == 5) {
            ms.setGameSpeed(20);
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
                    GameScreen screen = new GameScreen();
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

    public void setTowerOnMap(int[] pos, TowerPresent tPresent, MapState state, Tower[] team) {
        // TODO C'EST QUOI CA ???????
    }

    public void openPC() {
        // TODO OPEN THE PC
    }

}
