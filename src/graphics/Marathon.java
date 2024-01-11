package graphics;

import control.Control;

import javax.swing.*;
import java.awt.*;

public class Marathon extends JPanel {

    private Control control = Control.INSTANCE;
    private GraphicsMaker maker = new GraphicsMaker();

    public Marathon(MainGraphics main) {
        setOpaque(false);
        setLayout(new BorderLayout());
        // Titre
        JPanel titlePanel = titlePanel();

        // Boutons

        JPanel buttonPanel = buttonPanel(main);

        // Back
        JPanel backPanel = control.backButton(main);

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel titlePanel() {
        JLabel heading = new JLabel("Choisis la difficulte");
        heading.setFont(maker.getCustomFont());
        heading.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 48, 0));

        return titlePanel;
    }

    private JPanel buttonPanel(MainGraphics main) {
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JButton easyButton = new JButton(new ImageIcon("ressources/button/facile_button.png"));
        JButton mediumButton = new JButton(new ImageIcon("ressources/button/moyen_button.png"));
        JButton hardButton = new JButton(new ImageIcon("ressources/button/difficile_button.png"));

        easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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

}