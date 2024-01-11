package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Control;

public class Options extends JPanel {
    private GraphicsMaker maker = new GraphicsMaker();

    public Options(MainGraphics main) {
        setOpaque(false);
        setLayout(new BorderLayout());
        setVisible(true);

        JPanel titlePanel = titlePanel();
        Control control = Control.INSTANCE;
        JPanel backPanel = control.backButton(main);

        add(titlePanel, BorderLayout.NORTH);
        add(backPanel, BorderLayout.SOUTH);
    }

    private JPanel titlePanel() {
        JLabel heading = new JLabel("Options");
        heading.setFont(maker.getCustomFont());
        heading.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 48, 0));
        return titlePanel;
    }
}
