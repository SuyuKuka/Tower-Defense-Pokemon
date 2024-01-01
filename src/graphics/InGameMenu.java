package graphics;

import java.awt.*;
import javax.swing.*;

import model.MapState;

import control.Control;
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

    private JPanel buttonsPanel;
    private JPanel info;

    public InGameMenu(boolean topPanel, MapState ms) {

        this.ms = ms;

        control = new Control(ms);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 52));

        JPanel bg = maker.imagePanel("ressources/background/banner.png");
        add(bg, BorderLayout.CENTER);
        bg.setLayout(new BorderLayout());

        if (topPanel) {
            buttonsPanel = createTopPanel();
            bg.add(buttonsPanel, BorderLayout.EAST);
        } else {
            info = createBotPanel();
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

        return buttonsPanel;
    }

    public JPanel createBotPanel() {

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

        JPanel test = new JPanel();
        test.setOpaque(false);

        JPanel info = new JPanel();
        info.setLayout(new BorderLayout());
        info.add(moneyPanel, BorderLayout.WEST);
        info.add(lifePanel, BorderLayout.EAST);
        info.add(test, BorderLayout.CENTER);
        info.setOpaque(false);

        return info;

    }

    public JPanel getInfo() {
        return info;
    }

    public void UpdateInfo() {

        money.setText(ms.getMoney() + "");
        pv.setText(ms.getPv() + "");
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

}
