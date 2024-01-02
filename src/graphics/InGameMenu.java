package graphics;

import java.awt.*;
import javax.swing.*;

import model.MapState;

import control.Control;
import control.ControlTerminal;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Flow;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class InGameMenu extends JPanel {

    private GraphicsMaker maker = new GraphicsMaker();
    private Control control;

    private MapState ms;
    private JLabel pv;
    private JLabel money;
    private JLabel compteur;

    public InGameMenu(boolean topPanel, MapState ms) {

        this.ms = ms;

        control = new Control(ms);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 52));

        JPanel bg = maker.imagePanel("ressources/background/banner.png");
        add(bg, BorderLayout.CENTER);
        bg.setLayout(new BorderLayout());

        if (topPanel) {
            JPanel buttonsPanel = createTopPanel();
            bg.add(buttonsPanel, BorderLayout.CENTER);
        } else {
            JPanel info = createBotPanel();
            bg.add(info, BorderLayout.CENTER);
        }
        setVisible(true);
    }

    public JPanel createTopPanel() {

        JButton speed = new JButton();
        speed.setPreferredSize(new Dimension(42, 42));
        speed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.gameDoubleSpeed();
            }
        });

        JButton pause = new JButton();
        pause.setPreferredSize(new Dimension(42, 42));
        pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.gamePause();
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(speed);
        buttonsPanel.add(pause);
        buttonsPanel.setOpaque(false);

        compteur = new JLabel();

        compteur.setFont(maker.getCustomFont().deriveFont(30f));
        compteur.setForeground(Color.WHITE);

        JPanel nbWave = new JPanel();
        nbWave.setLayout(new BorderLayout());
        nbWave.setOpaque(false);
        nbWave.add(compteur, BorderLayout.CENTER);

        JPanel display = new JPanel();
        display.setLayout(new BorderLayout());
        display.setOpaque(false);

        display.add(nbWave, BorderLayout.WEST);
        display.add(buttonsPanel, BorderLayout.EAST);

        return display;
    }

    public JPanel createBotPanel() {
        // LA VIE
        JPanel heartIcon = maker.imagePanel("ressources/template/heart.png");
        heartIcon.setOpaque(false);
        heartIcon.setPreferredSize(new Dimension(42, 42));

        pv = new JLabel();
        pv.setText(ms.getPv() + "");
        pv.setForeground(Color.WHITE);
        pv.setFont(maker.getCustomFont().deriveFont(30f));

        JPanel lifePanel = new JPanel();
        lifePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        lifePanel.setOpaque(false);

        lifePanel.setPreferredSize(new Dimension(84, 42));
        lifePanel.add(heartIcon);
        lifePanel.add(pv);

        // L'ARGENT
        JPanel moneyIcon = maker.imagePanel("ressources/template/baie.png");
        moneyIcon.setOpaque(false);
        moneyIcon.setPreferredSize(new Dimension(42, 42));

        money = new JLabel();
        money.setText(0 + "");
        money.setForeground(Color.WHITE);
        money.setFont(maker.getCustomFont().deriveFont(30f));

        JPanel moneyPanel = new JPanel();
        moneyPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        moneyPanel.setOpaque(false);

        moneyPanel.add(moneyIcon);
        moneyPanel.add(money);

        JPanel info = new JPanel();
        info.setLayout(new BorderLayout());
        info.add(moneyPanel, BorderLayout.WEST);
        info.add(lifePanel, BorderLayout.EAST);

        info.setOpaque(false);

        return info;

    }

    public void UpdateInfo() {
        pv.setText(ms.getPv() + "");
        money.setText(ms.getMoney() + "");
    }

    public void updateNbWave() {
        compteur.setText("Vagues restants: " + ms.getAllEnnemis().size());
    }

}
