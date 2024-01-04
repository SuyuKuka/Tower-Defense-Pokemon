package graphics;

import javax.swing.*;

import java.awt.*;

import control.Control;

public class Classique extends JPanel {

    private Control manager = new Control();
    private GraphicsMaker maker = new GraphicsMaker();

    public Classique(MainGraphics main) {
        setOpaque(false);
        setLayout(new BorderLayout());
        // Titre
        JPanel titlePanel = titlePanel();

        // Boutons
        JPanel buttonPanel = buttonPanel(main);

        // Back
        JPanel bottomPanel = bottomPanel(main);
        //

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
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
        JButton mediumButton = new JButton(new ImageIcon("ressources/button/moyen_button.png"));
        JButton hardButton = new JButton(new ImageIcon("ressources/button/difficile_button.png"));

        manager.buttonPressed(easyButton, "gameScreen_c_facile", main);
        manager.buttonPressed(mediumButton, "gameScreen_c_moyen", main);
        manager.buttonPressed(hardButton, "gameScreen_c_difficile", main);

        easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        bottomPanel.setPreferredSize(new Dimension(0, 52));

        JPanel backPanel = manager.backButton(main);
        bottomPanel.add(backPanel, BorderLayout.WEST);
        bottomPanel.setOpaque(true);
        return bottomPanel;
    }

}