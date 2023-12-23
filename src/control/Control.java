package control;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.Timer;

import main.Game;
import model.Pokemon.State;
import model.Player;
import model.Tower;

import java.awt.Cursor;

public class Control {
    private Player user;

    public Control(Player p) {
        user = p;
    }

    public Control() {

    }

    public void buttonPressed(JButton button, String st, Game main) {
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
                main.changePanel(st);
            });
            timer.setRepeats(false);
            timer.start();
        });
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

}
