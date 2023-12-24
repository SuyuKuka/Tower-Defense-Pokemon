package graphics;

import control.Control;

import javax.swing.*;
import java.awt.*;

public class Options extends JPanel {

    private Control manager = new Control();
    private GraphicsMaker maker = new GraphicsMaker();

    public Options(MainGraphics main) {
        setOpaque(false);
        setLayout(new BorderLayout());
        // Titre
        JPanel titlePanel = titlePanel();

        // Back
        JPanel backPanel = manager.backButton(main);

        add(titlePanel, BorderLayout.NORTH);
        add(backPanel, BorderLayout.SOUTH);
        setVisible(true);
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
