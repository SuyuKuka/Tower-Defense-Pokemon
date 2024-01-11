package graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import control.Control;
import model.Hero;

public class Marathon extends JPanel {
    private Control control = Control.INSTANCE;
    private GraphicsMaker maker = new GraphicsMaker();
    private Hero selected;

    private JPanel prevSelected;

    public Marathon(MainGraphics main) {
        setOpaque(false);
        setLayout(new BorderLayout());
        setVisible(true);

        JPanel titlePanel = titlePanel();
        JPanel buttonPanel = buttonPanel(main);
        JPanel bottomPanel = bottomPanel(main);
        JPanel heroPanel = heroPanel();

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        add(heroPanel, BorderLayout.WEST);
    }

    private  JPanel heroPanel() {
        JPanel heroPanel = new JPanel();
        heroPanel.setLayout(new BoxLayout(heroPanel, BoxLayout.Y_AXIS));
        heroPanel.setOpaque(false);
        for (Hero h: Hero.getList()) {
            heroPanel.add(heroLabel(h));
        }
        return heroPanel;
    }

    private JPanel heroLabel(Hero h) {
        JLabel img = new JLabel(new ImageIcon(h.getImage()));

        JLabel name = maker.oneLineTextMaker();
        name.setText(h.getName());

        JLabel desc = maker.oneLineTextMaker();
        desc.setText(h.getDesc());

        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(new LineBorder(Color.BLACK));
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new GridLayout(2, 1));
        infoPanel.add(name);
        infoPanel.add(desc);
        infoPanel.setPreferredSize(new Dimension(500, 0));

        JPanel heroLabel = new JPanel();
        heroLabel.setBackground(new Color(20, 20, 20));
        heroLabel.setLayout(new BorderLayout());
        heroLabel.setBorder(new EmptyBorder(1, 1, 1, 1));
        heroLabel.add(img, BorderLayout.WEST);
        heroLabel.add(infoPanel, BorderLayout.CENTER);
        heroLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selected == null) {
                    selected = h;
                    prevSelected = heroLabel;
                    heroLabel.setBackground(Color.RED);
                } else {
                    selected = h;
                    prevSelected.setBackground(new Color(20, 20, 20));
                    prevSelected = heroLabel;
                    heroLabel.setBackground(Color.RED);
                }
                control.setSelectedHero(selected);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               heroLabel.setBorder(new LineBorder(Color.WHITE));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                heroLabel.setBorder(new EmptyBorder(1, 1, 1, 1));
            }
        });
        return heroLabel;
    }

    private JPanel titlePanel() {
        JPanel titlePanel = maker.imagePanel("ressources/background/banner.png");
        titlePanel.setPreferredSize(new Dimension(0, 52));

        JLabel heading = new JLabel("Choisis la difficulte");
        heading.setFont(maker.getCustomFont().deriveFont(45f));
        heading.setForeground(Color.WHITE);

        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBackground(Color.BLACK);

        return titlePanel;
    }

    private JPanel buttonPanel(MainGraphics main) {
        JPanel buttonPanel = maker.imagePanel("ressources/button/buttonpanel.png");
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JButton easyButton = new JButton(new ImageIcon("ressources/button/facile_button.png"));
        easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton mediumButton = new JButton(new ImageIcon("ressources/button/moyen_button.png"));
        mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton hardButton = new JButton(new ImageIcon("ressources/button/difficile_button.png"));
        hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        control.buttonPressed(easyButton, "gameScreen_m_facile", main);
        control.buttonPressed(mediumButton, "gameScreen_m_moyen", main);
        control.buttonPressed(hardButton, "gameScreen_m_difficile", main);

        buttonPanel.add(easyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(mediumButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(hardButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        return buttonPanel;
    }

    private JPanel bottomPanel(MainGraphics main) {
        JPanel bottomPanel = maker.imagePanel("ressources/background/banner.png");
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottomPanel.setPreferredSize(new Dimension(0, 52));

        JPanel backPanel = control.backButton(main);
        bottomPanel.add(backPanel, BorderLayout.WEST);
        bottomPanel.setOpaque(true);
        return bottomPanel;
    }

    public Hero getSelected() {
        return selected;
    }
}
