package graphics;

import main.Game;

import javax.swing.*;

import control.Control;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Boutique extends JPanel {

    private Control manager = new Control();
    private GraphicsMaker maker = new GraphicsMaker();

    public Boutique(Game main) {

        setOpaque(false);
        setLayout(new BorderLayout());
        // Titre
        JPanel titlePanel = titlePanel();

        // Back
        JPanel backPanel = backButton(main);

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

    private JPanel backButton(Game main) {
        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);

        JButton backButton = new JButton(new ImageIcon("ressources/button/retour_button.png"));
        manager.buttonPressed(backButton, "retour", main);

        backPanel.setBorder(BorderFactory.createEmptyBorder(0, -1224 + backButton.getPreferredSize().width, 0, 0));
        backPanel.add(backButton);

        return backPanel;
    }
}
