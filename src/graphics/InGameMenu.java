package graphics;

import java.awt.*;
import javax.swing.*;

import model.MapState;
import control.Control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;

public class InGameMenu extends JPanel {

    private GraphicsMaker maker = new GraphicsMaker();
    private Control control;

    private MapState ms;
    private JLabel pv;
    private JLabel money;
    private JLabel compteur;
    private JLabel waveTimer;
    private JButton pc;

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
        // LES BOUTONS
        JButton speed = new JButton("S");
        speed.setPreferredSize(new Dimension(42, 42));
        speed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.gameDoubleSpeed();
            }
        });

        JButton pause = new JButton("P");
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

        // COMPTER DE WAVE
        compteur = new JLabel();
        compteur.setFont(maker.getCustomFont().deriveFont(30f));
        compteur.setForeground(Color.WHITE);

        JPanel nbWave = new JPanel();
        nbWave.setLayout(new BorderLayout());
        nbWave.setOpaque(false);
        nbWave.add(compteur, BorderLayout.CENTER);

        // TIMER DE VAGUE
        JLabel timerName = new JLabel();
        timerName.setText("Prochaine vague dans: ");
        timerName.setFont(maker.getCustomFont().deriveFont(30f));
        timerName.setForeground(Color.WHITE);

        waveTimer = new JLabel();
        waveTimer.setFont(maker.getCustomFont().deriveFont(30f));
        waveTimer.setForeground(Color.WHITE);

        JPanel timer = new JPanel();
        timer.setLayout(new BorderLayout());
        timer.setOpaque(false);
        timer.add(waveTimer, BorderLayout.CENTER);
        timer.add(timerName, BorderLayout.WEST);

        // PANEL QUI RETIENT TOUT
        JPanel display = new JPanel();
        display.setLayout(new GridLayout(1, 3));
        display.setOpaque(false);

        display.add(nbWave, BorderLayout.CENTER);
        display.add(timer, BorderLayout.WEST);
        display.add(buttonsPanel, BorderLayout.EAST);

        return display;
    }

    public JPanel createBotPanel() {
        // PC BUTTON
        pc = new JButton("LE PCCCCCCC");
        pc.setBorder(new EmptyBorder(0, 0, 0, 0));
        pc.setPreferredSize(new Dimension(126, 42));
        pc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("LE PCCCCCCCCCCCCCC");
                control.openPC();
            }
        });

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

        lifePanel.add(pc);
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
        info.add(lifePanel, BorderLayout.CENTER);

        info.setOpaque(false);

        return info;

    }

    public void UpdateInfo() {
        pv.setText(ms.getPv() + "");
        money.setText((int) ms.getMoney() + "");
        if ((ms.getAllEnnemis().size() - 1) % 6 == 0) {
            pc.setVisible(true);
        } else {
            pc.setVisible(false);
        }
    }

    public void updateButton(int pas, int limiteDeTemps, int count) {
        // System.out.println(ms.getEndless());
        if (ms.getEndless()) {
            compteur.setText("Vague: " + count);
        } else {

            if ((ms.getAllEnnemis().size() - 1) < 0) {
                compteur.setText("Vagues restants: " + 0);
            } else {
                compteur.setText("Vagues restants: " + (ms.getAllEnnemis().size() - 1));
            }
        }

        if (pas >= limiteDeTemps * 100) {
            pas = limiteDeTemps * 100;
        }
        waveTimer.setText(pas + "/" + limiteDeTemps * 100);

    }

}
