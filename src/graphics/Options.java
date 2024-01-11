package graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import control.Control;

public class Options extends JPanel {
    private Control control = Control.INSTANCE;
    private GraphicsMaker maker = new GraphicsMaker();

    public Options(MainGraphics main) {
        setOpaque(false);
        setLayout(new BorderLayout());
        setVisible(true);

        JPanel titlePanel = titlePanel();
        JPanel bottomPanel = bottomPanel(main);

        add(titlePanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel titlePanel() {
        JPanel titlePanel = maker.imagePanel("ressources/background/banner.png");
        titlePanel.setPreferredSize(new Dimension(0, 52));

        JLabel heading = new JLabel("Options");
        heading.setFont(maker.getCustomFont().deriveFont(45f));
        heading.setForeground(Color.WHITE);

        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBackground(Color.BLACK);

        return titlePanel;
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
}
