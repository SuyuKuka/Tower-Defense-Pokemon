package graphics;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import java.util.ArrayList;

import model.Ennemi;
import javax.swing.border.*;

public class EnnemiPresent extends JPanel {
    private static GraphicsMaker maker = new GraphicsMaker();
    private ArrayList<Ennemi> ennemiDisplayed = new ArrayList<Ennemi>();
    private ArrayList<Ennemi> ennemiGone = new ArrayList<Ennemi>();
    private static JPanel container = new JPanel();
    private JPanel ennemiInfoPanel;
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
        ennemiInfoPanel = new JPanel();
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
        // TODO:AMELIORER AFFICHAGE INFO DES ENNEMIES
        ennemiInfoPanel.removeAll();

        JLabel name = new JLabel();
        name.setText(e.getName() + e.getPv() + e.getSpeed() + e.getType()[0] + e.getType()[1]);

        ennemiInfoPanel.add(name);
        ennemiInfoPanel.revalidate();
        ennemiInfoPanel.repaint();
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
        ennemiDisplayed.remove(e);
        container.revalidate();
        container.repaint();
    }

    public static void updateEnnemiHealth(Ennemi ennemi, int damageTaken) {
        for (Component c : container.getComponents()) {
            if (c instanceof JPanel) {
                JPanel panel = (JPanel) c;
                if (panel.getComponent(1) instanceof JProgressBar
                        && (((JProgressBar) panel.getComponent(1)).getName().equals(ennemi.getName()))) {
                    JProgressBar healthBar = (JProgressBar) panel.getComponent(1);
                    int currentHealth = healthBar.getValue();
                    int newHealth = Math.max(currentHealth + damageTaken, 0);
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
