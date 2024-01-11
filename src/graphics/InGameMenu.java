package graphics;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.MapState;
import control.Control;
import main.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.border.EmptyBorder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import java.util.ArrayList;

import javax.swing.*;

import model.Ennemi;

import model.MapState;
import model.Player;
import model.Tower;

import javax.swing.border.*;

public class InGameMenu extends JPanel {

    private GraphicsMaker maker = new GraphicsMaker();
    private Control control = Control.INSTANCE;
    private GameScreen gameScreen;

    private MapState ms;
    private JLabel pv;
    private JLabel money;
    private JLabel compteur;
    private JLabel waveTimer;
    private JLabel pc;
    private JLabel timerName;
    private static boolean inPc=false;

    public InGameMenu(boolean topPanel, MapState ms, GameScreen gameScreen) {

        this.ms = ms;
        this.gameScreen = gameScreen;

        control.setMs(ms);

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
        JButton speed = new JButton(new ImageIcon("ressources/template/normalButton.png"));
        speed.setPreferredSize(new Dimension(42, 42));
        speed.setOpaque(false);
        speed.setBackground(Color.BLACK);
        speed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!inPc){
                    control.gameDoubleSpeed(speed);
                }
            }
        });

        JButton pause = new JButton(new ImageIcon("ressources/template/pauseButton.png"));
        pause.setPreferredSize(new Dimension(42, 42));
        pause.setOpaque(false);
        pause.setBackground(Color.BLACK);
        pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!inPc){
                    control.gamePause(pause);
                }
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
        timerName = new JLabel();
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
        JPanel teamPanel = gameScreen.getInGamePc().getTeamPanel();
        teamPanel.setVisible(false);
        pc = maker.resizeJLabel("ressources/template/pc_sprite.png", 40);
        pc.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        pc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(inPc){
                    inPc=false;
                    teamPanel.setVisible(false);
                }else{
                    inPc=true;
                    teamPanel.setVisible(true);
                }
                control.gamePause(new JButton());
                gameScreen.changeRightSide();
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

        lifePanel.add(teamPanel);
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

    public void updateButton(int pas, int limiteDeTemps, int count, boolean lose) {
        if (ms.getEndless()) {
            compteur.setText("Vague: " + count);
        } else {

            if ((ms.getAllEnnemis().size() - 1) < 0) {
                compteur.setText("Vagues restants: " + 0);
            } else {
                compteur.setText("Vagues restantes: " + (ms.getAllEnnemis().size() - 1));
            }
        }

        if (!lose) {

            if (pas >= limiteDeTemps * 100) {
                pas = limiteDeTemps * 100;
            }
            waveTimer.setText(pas + "/" + limiteDeTemps * 100);
        }

    }

    public static boolean isPc(){
        return inPc;
    }

}
