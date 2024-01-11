package graphics;

import model.MapState;
import control.Control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private static JButton pause;
    private static boolean inPc = false;

    public InGameMenu(boolean topPanel, MapState ms, GameScreen gameScreen) {
        this.ms = ms;
        this.gameScreen = gameScreen;
        control.setMs(ms);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 52));

        JPanel bg = maker.imagePanel("ressources/background/banner.png");
        bg.setLayout(new BorderLayout());
        add(bg, BorderLayout.CENTER);

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
        JButton speed = new JButton(new ImageIcon("ressources/template/normalButton.png"));
        speed.setPreferredSize(new Dimension(42, 42));
        speed.setOpaque(false);
        speed.setBackground(Color.BLACK);
        speed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!inPc) {
                    control.gameDoubleSpeed(speed);
                }
            }
        });

         pause = new JButton(new ImageIcon("ressources/template/pauseButton.png"));
        pause.setPreferredSize(new Dimension(42, 42));
        pause.setOpaque(false);
        pause.setBackground(Color.BLACK);
        pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!inPc) {
                    control.gamePause(pause);
                }
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
                if (inPc) {
                    inPc = false;
                    teamPanel.setVisible(false);
                } else {
                    inPc = true;
                    teamPanel.setVisible(true);
                }
                control.gamePause(pause);
                gameScreen.changeRightSide();
            }
        });

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

    public void updateInfo() {
        pv.setText(ms.getPv() + "");
        money.setText((int) ms.getMoney() + "");
        if ((ms.getAllEnnemis().size() - 1) % 1 == 0) {
            pc.setVisible(true);
        } else {
            pc.setVisible(false);
        }
    }

    public void updateButton(int pas, int timeLimit, int count, boolean lose) {
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
            if (pas >= timeLimit * 100) {
                pas = timeLimit * 100;
            }
            waveTimer.setText(pas + "/" + timeLimit * 100);
        }
    }

    public static boolean isPc() {
        return inPc;
    }
}
