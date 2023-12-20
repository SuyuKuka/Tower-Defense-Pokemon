package control;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import main.Game;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonManager {
    private JButton button;

    public ButtonManager() {

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

}
