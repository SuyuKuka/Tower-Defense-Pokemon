package graphics;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import java.util.ArrayList;

import model.Ennemi;
import model.Types;
import model.Pokemon.Speed;

import javax.swing.border.*;

public class EnnemiPresent extends JPanel {
    private static GraphicsMaker maker = new GraphicsMaker();
    private ArrayList<Ennemi> ennemiDisplayed = new ArrayList<Ennemi>();
    private ArrayList<Ennemi> ennemiGone = new ArrayList<Ennemi>();
    private static JPanel container = new JPanel();
    private JPanel ennemiInfoPanel;
    private Ennemi ennemiInInfoPanel;

    private JPanel bg = maker.imagePanel("ressources/team/ennemiside.png");
    JScrollPane ennemiList;

    public EnnemiPresent() {
        bg.setOpaque(false);
        bg.setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(246, 0));
        setOpaque(false);
        add(bg, BorderLayout.CENTER);
        setVisible(true);

        // Ennemi
        container.setOpaque(false);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel ennemiLabel = new JLabel("Ennemi sur le terrain");
        ennemiLabel.setOpaque(false);
        ennemiLabel.setForeground(Color.WHITE);
        ennemiLabel.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));
        ennemiLabel.setFont(maker.getCustomFont().deriveFont(Font.PLAIN, 26f));

        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(ennemiLabel);
        box.add(Box.createHorizontalGlue());
        box.setOpaque(false);
        box.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        ennemiList = new JScrollPane(container);
        ennemiList.setOpaque(false);
        // ennemiList.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        ennemiList.setBorder(BorderFactory.createEmptyBorder(20, 0, 100, 0));
        // ennemiList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JViewport viewport = ennemiList.getViewport();
        viewport.setOpaque(false);

        // ENNEMI INFO CADRE
        ennemiInfoPanel = maker.imagePanel("ressources/template/ennemiInfoBg.png");
        ennemiInfoPanel.setPreferredSize(new Dimension(400, 100));
        ennemiInfoPanel.setVisible(false);

        bg.add(ennemiInfoPanel, BorderLayout.SOUTH);
        bg.add(box, BorderLayout.NORTH);
        bg.add(ennemiList, BorderLayout.CENTER);
    }

    public void setEnnemiPresent(ArrayList<Ennemi> ennemiPresent) {
        for (Ennemi e : ennemiGone) {
            deleteEnnemiLabel(e);
        }

        for (Ennemi e : ennemiPresent) {
            if (!ennemiDisplayed.contains(e)) {
                createEnnemiLabel(e);
            }
        }

        ennemiGone.clear();
        container.revalidate();
        container.repaint();
    }

    public void createEnnemiLabel(Ennemi e) {
        JPanel ennemiInfo = new JPanel(new FlowLayout(FlowLayout.LEADING));
        ennemiInfo.setOpaque(false);
        e.setEnnemiInfo(ennemiInfo);

        JLabel profilPhoto = new JLabel(new ImageIcon(e.getSprite()));
        profilPhoto.setOpaque(false);
        profilPhoto.setPreferredSize(new Dimension(32, 32));

        JProgressBar healthBar = new JProgressBar();
        if (e.isBoss()) {
            healthBar.setFont(maker.getCustomFont().deriveFont(20f));
            healthBar.setForeground(new Color(160, 0, 0));
        } else {
            healthBar.setForeground(Color.RED);
            healthBar.setFont(maker.getCustomFont().deriveFont(12f));
        }
        healthBar.setBackground(Color.BLACK);
        healthBar.setMinimum(0);
        healthBar.setMaximum((int) e.getTotalPv());
        healthBar.setValue((int) e.getPv());
        healthBar.setString(e.getName());
        healthBar.setStringPainted(true);
        healthBar.setName(e.getName());
        healthBar.setBorder(new EmptyBorder(2, 2, 2, 2));
        ennemiInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                healthBar.setBorder(new LineBorder(Color.WHITE, 2));
                ennemiInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                configEnnemiInfoPanel(e);
                ennemiList.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                ennemiInfoPanel.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                healthBar.setBorder(new EmptyBorder(2, 2, 2, 2));
                ennemiInfoPanel.setVisible(false);
                ennemiList.setBorder(BorderFactory.createEmptyBorder(20, 0, 100, 0));
            }
        });

        ennemiInfo.add(profilPhoto);
        ennemiInfo.add(healthBar);

        container.add(ennemiInfo);

        ennemiDisplayed.add(e);
    }

    private void configEnnemiInfoPanel(Ennemi e) {
        ennemiInfoPanel.removeAll();
        ennemiInInfoPanel = e;
        ennemiInfoPanel.setBackground(Color.WHITE);
        ennemiInfoPanel.setBorder(new LineBorder(Color.WHITE));
        GridLayout grid = new GridLayout(5, 1);
        ennemiInfoPanel.setLayout(grid);

        JLabel name = new JLabel();
        if (e.isBoss()) {
            name.setText("Nom: " + e.getName() + " (BOSS)");
        } else {

            name.setText("Nom: " + e.getName());
        }
        name.setFont(maker.getCustomFont().deriveFont(20f));
        name.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));
        name.setForeground(Color.WHITE);

        JLabel pv = new JLabel();
        pv.setText("Vie: " + e.getPv() + "/" + e.getTotalPv());
        pv.setFont(maker.getCustomFont().deriveFont(17f));
        pv.setForeground(Color.WHITE);

        JLabel speed = new JLabel();
        if (e.getSpeed() == Speed.CLASSIC) {
            speed.setText("Vitesse: Normal");
        }
        if (e.getSpeed() == Speed.FAST) {
            speed.setText("Vitesse: Rapide");
        }
        if (e.getSpeed() == Speed.SLOW) {
            speed.setText("Vitesse: Lent");
        }
        speed.setFont(maker.getCustomFont().deriveFont(17f));
        speed.setForeground(Color.WHITE);

        JLabel types = new JLabel();
        if (e.getType()[1] == Types.NONE) {
            types.setText("Type: " + e.getType()[0]);
        } else {
            types.setText("Types: " + e.getType()[0] + ", " + e.getType()[1]);
        }
        types.setFont(maker.getCustomFont().deriveFont(17f));
        types.setForeground(Color.WHITE);

        JLabel infoSuppl = new JLabel();
        infoSuppl.setText(setInfoText(e));
        if (!setInfoText(e).equals("")) {
            infoSuppl.setBorder(new LineBorder(Color.RED, 2));
        }
        infoSuppl.setFont(maker.getCustomFont().deriveFont(15f));
        infoSuppl.setForeground(Color.WHITE);

        ennemiInfoPanel.add(name);
        ennemiInfoPanel.add(types);
        ennemiInfoPanel.add(pv);
        ennemiInfoPanel.add(speed);
        ennemiInfoPanel.add(infoSuppl);

        ennemiInfoPanel.revalidate();
        ennemiInfoPanel.repaint();
    }

    private String setInfoText(Ennemi e) {
        if (e.getType()[0] == Types.SPECTRE || e.getType()[1] == Types.SPECTRE) {
            return "IMM contre les atk Normal et Combat";
        }
        if (e.getType()[0] == Types.SOL || e.getType()[1] == Types.SOL) {
            return "IMM contre les atk Electric";
        }
        if (e.getType()[0] == Types.ACIER || e.getType()[1] == Types.ACIER) {
            return "IMM contre les atk Poison";
        }
        if (e.getType()[0] == Types.VOL || e.getType()[1] == Types.VOL) {
            return "IMM contre les atk Sol";
        }
        if (e.getType()[0] == Types.TENEBRE || e.getType()[1] == Types.TENEBRE) {
            return "IMM contre les atk Psy";
        }
        if (e.getType()[0] == Types.FEE || e.getType()[1] == Types.FEE) {
            return "IMM contre les atk Dragon";
        }
        if (e.getType()[0] == Types.NORMAL || e.getType()[1] == Types.NORMAL) {
            return "IMM contre les atk Spectre";
        }
        return "";

    }

    public void deleteEnnemiLabel(Ennemi e) {
        for (Component c : container.getComponents()) {
            if (c instanceof JPanel) {
                JPanel panel = (JPanel) c;
                if (panel.getComponent(1) instanceof JProgressBar
                        && (panel.getComponent(1).getName().equals(e.getName()))) {
                    container.remove(panel);
                    break;
                }
            }
        }
        if (ennemiInInfoPanel == e) {
            ennemiInfoPanel.setVisible(false);
        }
        ennemiDisplayed.remove(e);
        container.revalidate();
        container.repaint();
    }

    public void reset() {
        while (ennemiDisplayed.size() > 0) {
            deleteEnnemiLabel(ennemiDisplayed.get(ennemiDisplayed.size() - 1));
        }

    }

    public static void updateEnnemiHealth(Ennemi ennemi) {
        if (ennemi.getEnnemiInfo() != null) {

            for (Component compo : ennemi.getEnnemiInfo().getComponents()) {
                if (compo instanceof JProgressBar) {
                    JProgressBar healthBar = (JProgressBar) compo;
                    int newHealth = (int) ennemi.getPv();
                    healthBar.setValue(newHealth);
                    break;
                }
            }
        }
        container.revalidate();
        container.repaint();
    }

    public ArrayList<Ennemi> getEnnemiGone() {
        return ennemiGone;
    }
}
