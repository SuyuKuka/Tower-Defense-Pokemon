package graphics;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.ArrayList;

import model.Ennemi;

import javax.swing.border.LineBorder;

public class EnnemiPresent extends JPanel {
    private static GraphicsMaker maker = new GraphicsMaker();
    private ArrayList<Ennemi> ennemiDisplayed = new ArrayList<Ennemi>();
    private ArrayList<Ennemi> ennemiGone = new ArrayList<Ennemi>();
    private static JPanel container = new JPanel();

    private JPanel bg = maker.imagePanel("ressources/team/ennemiside.png");

    public EnnemiPresent() {
        setLayout(new BorderLayout());
        bg.setOpaque(false);
        add(bg, BorderLayout.CENTER);

        bg.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 0));
        setOpaque(false);

        // Ennemi
        container.setOpaque(false);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel ennemiLabel = new JLabel("Ennemi sur le terrain");
        ennemiLabel.setOpaque(false);
        ennemiLabel.setForeground(Color.WHITE);
        ennemiLabel.setFont(maker.getCustomFont().deriveFont(Font.PLAIN, 26f));

        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(ennemiLabel);
        box.add(Box.createHorizontalGlue());
        box.setOpaque(false);
        box.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        bg.add(box, BorderLayout.NORTH);

        JScrollPane ennemiList = new JScrollPane(container);
        ennemiList.setOpaque(false);
        JViewport viewport = ennemiList.getViewport();
        viewport.setOpaque(false);
        // ennemiList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        ennemiList.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        bg.add(ennemiList, BorderLayout.CENTER);
        setVisible(true);
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
        JPanel ennemiInfo = new JPanel(new GridBagLayout());
        ennemiInfo.setOpaque(false);

        JLabel profilPhoto = new JLabel(new ImageIcon(e.getSprite()));
        profilPhoto.setOpaque(false);
        profilPhoto.setPreferredSize(new Dimension(50, 50));

        JLabel nameLabel = new JLabel(e.getName());
        nameLabel.setOpaque(false);
        nameLabel.setFont(maker.getCustomFont().deriveFont(Font.PLAIN, 16f));
        nameLabel.setForeground(Color.WHITE);

        JProgressBar healthBar = new JProgressBar();
        healthBar.setOpaque(false);
        healthBar.setMinimum(0);
        healthBar.setMaximum((int) e.getTotalPv());
        healthBar.setValue((int) e.getPv());
        healthBar.setForeground(Color.RED);
        healthBar.setStringPainted(true);
        healthBar.setFont(maker.getCustomFont().deriveFont(12f));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.ipadx = 10;
        ennemiInfo.add(profilPhoto, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 8, 0, 0);
        ennemiInfo.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        ennemiInfo.add(healthBar, gbc);

        container.add(ennemiInfo);
        ennemiDisplayed.add(e);
        ennemiInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // affiche un popup avec les infos de l'ennemi
            }
        });
    }

    public void deleteEnnemiLabel(Ennemi e) {
        for (Component c : container.getComponents()) {
            if (c instanceof JPanel) {
                JPanel panel = (JPanel) c;
                if (panel.getComponent(1) instanceof JLabel
                        && ((JLabel) panel.getComponent(1)).getText().equals(e.getName())) {
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
                if (panel.getComponent(1) instanceof JLabel
                        && ((JLabel) panel.getComponent(1)).getText().equals(ennemi.getName())) {
                    JProgressBar healthBar = (JProgressBar) panel.getComponent(2);
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
