package graphics;

import javax.swing.*;

import control.Control;

import java.awt.*;

public class Boutique extends JPanel {

    private GraphicsMaker maker = new GraphicsMaker();

    public Boutique(MainGraphics main) {

        setOpaque(false);
        setLayout(new BorderLayout());
        // Titre
        JPanel titlePanel = titlePanel();

        // Back
        Control control = Control.INSTANCE;
        JPanel backPanel = control.backButton(main);

        add(titlePanel, BorderLayout.NORTH);
        add(backPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel titlePanel() {

        JPanel titlePanel = new JPanel();
        JLabel heading = new JLabel("Boutique");
        heading.setFont(maker.getCustomFont());
        heading.setForeground(Color.WHITE);

        titlePanel.setOpaque(false);
        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 48, 0));

        return titlePanel;
    }

}
