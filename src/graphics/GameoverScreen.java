package graphics;

import model.Ennemi;
import model.MapState;
import model.Player;
import model.Tower;
import control.Control;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameoverScreen extends JPanel {
    private GraphicsMaker maker = new GraphicsMaker();
    private Control control = Control.INSTANCE;
    private MapState state;
    private GameScreen screen;

    private int totalWavePassed;
    private int totalMoneyEarned;
    private ArrayList<Ennemi> ennemiEscaped;

    public GameoverScreen(MapState state, GameScreen screen, boolean win) {
        this.state = state;
        this.screen = screen;

        setLayout(new BorderLayout());
        setOpaque(false);

        totalWavePassed = state.getCount();
        totalMoneyEarned = (int) state.getCountMoney();
        ennemiEscaped = state.getEnnemiEscaped();

        JLabel headText = new JLabel();
        if (win) {
            headText.setText("VICTOIRE!!!");
        } else {
            headText.setText("PERDU...");
        }
        headText.setForeground(Color.WHITE);
        headText.setFont(maker.getCustomFont());

        JPanel heading = maker.imagePanel("ressources/background/banner.png");
        heading.add(headText);
        heading.setOpaque(false);

        JPanel tmp = screen.getContainerScreen();
        JPanel stats = middleScreen(win, tmp);
        JPanel bottom = maker.imagePanel("ressources/background/banner.png");
        bottom.setPreferredSize(new Dimension(0, 60));

        JLabel teamHeading = new JLabel();
        teamHeading.setText("Votre equipe");
        teamHeading.setFont(maker.getCustomFont().deriveFont(25f));
        teamHeading.setForeground(Color.WHITE);
        teamHeading.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));

        JPanel team = new JPanel();
        team.setLayout(new GridLayout(7, 1));
        team.setBackground(Color.BLACK);
        team.setBorder(new LineBorder(Color.WHITE));
        team.add(teamHeading);

        for (Tower pkm : Player.INSTANCE.getTeam()) {
            if (pkm != null) {
                JLabel pokeIcon = new JLabel(new ImageIcon(pkm.getSprite()));
                pokeIcon.setBackground(Color.BLACK);
                team.add(pokeIcon);
            }
        }

        add(stats, BorderLayout.CENTER);
        add(heading, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
        add(team, BorderLayout.EAST);

        screen.remove(screen.getContainerScreen());
        screen.setOpaque(false);
        screen.add(this);
        screen.revalidate();
        screen.repaint();
    }

    private JPanel middleScreen(boolean win, JPanel tmp) {
        JPanel statPanel = new JPanel();
        statPanel.setLayout(new GridLayout(6, 1));
        statPanel.setOpaque(false);

        JLabel wave = new JLabel();
        wave.setText("Nombre de vagues : " + (totalWavePassed - 1));
        wave.setFont(maker.getCustomFont().deriveFont(20f));
        wave.setForeground(Color.WHITE);

        JLabel money = new JLabel();
        money.setText("Nombre total de baies obtenu: " + totalMoneyEarned);
        money.setFont(maker.getCustomFont().deriveFont(20f));
        money.setForeground(Color.WHITE);

        int totalKill = 0;
        Tower topkill = Player.INSTANCE.getTeam()[0];
        for (Tower pkm : Player.INSTANCE.getTeam()) {
            if (pkm != null) {
                if (pkm.getKillCount() > topkill.getKillCount()) {
                    topkill = pkm;
                }
                totalKill = totalKill + pkm.getKillCount();
            }
        }

        JLabel kill = new JLabel();
        kill.setText("Nombre total de pokemon vaincu: " + totalKill);
        kill.setFont(maker.getCustomFont().deriveFont(20f));
        kill.setForeground(Color.WHITE);

        JLabel best = new JLabel();
        if (topkill.getKillCount() > 0) {
            best.setText("Le meilleur pokemon est: " + topkill.getName() + " avec " + topkill.getKillCount()
                    + " pokemons vaincu!");
        } else {
            best.setText("Tout les pokemon sont a egalite");
        }
        best.setFont(maker.getCustomFont().deriveFont(20f));
        best.setForeground(Color.WHITE);

        JLabel esc = new JLabel();
        esc.setText("Les ennemis qui sont sortis: ");
        esc.setFont(maker.getCustomFont().deriveFont(20f));
        esc.setForeground(Color.WHITE);

        JPanel ePanel = new JPanel();
        ePanel.setBackground(Color.BLACK);
        ePanel.setBorder(new LineBorder(Color.WHITE));
        for (Ennemi ennemi : ennemiEscaped) {
            JLabel pokeIcon = new JLabel(new ImageIcon(ennemi.getSprite()));
            pokeIcon.setBackground(Color.BLACK);
            ePanel.add(pokeIcon);
        }
        statPanel.add(wave);
        statPanel.add(money);
        statPanel.add(kill);
        statPanel.add(best);
        statPanel.add(esc);
        statPanel.add(ePanel);

        JButton home = new JButton("HOME");
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.goHome(screen);
            }
        });

        JButton restart = new JButton("RESTART");
        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.restart(screen, state);
            }
        });

        JButton freeplay = new JButton("FREEPLAY");
        freeplay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.freeplay(screen, tmp, state);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(home);
        buttonPanel.add(restart);
        if (win) {
            buttonPanel.add(freeplay);
        }

        JPanel stats = new JPanel();
        stats.setLayout(new BorderLayout());
        stats.setBackground(new Color(20, 20, 20, 200));
        stats.setBorder(new EmptyBorder(20, 0, 20, 0));
        stats.add(statPanel, BorderLayout.CENTER);
        stats.add(buttonPanel, BorderLayout.SOUTH);

        return stats;
    }
}
