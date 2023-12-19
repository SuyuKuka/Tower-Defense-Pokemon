package graphics;

import javax.swing.*;

import control.ButtonManager;
import main.Game;

import java.awt.*;

public class Marathon extends JPanel {

    private ButtonManager manager = new ButtonManager();
    private GraphicsMaker maker = new GraphicsMaker();

    public Marathon(Game main) {
        setOpaque(false);
        setLayout(new BorderLayout());
        // Titre
        JPanel titlePanel = titlePanel();

        // Boutons

        JPanel buttonPanel = buttonPanel(main);

        // Back
        JPanel backPanel = backButton(main);

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

    private JPanel buttonPanel(Game main) {
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JButton easyButton = new JButton(new ImageIcon("ressources/button/facile_button.png"));
        JButton mediumButton = new JButton(new ImageIcon("ressources/button/moyen_button.png"));
        JButton hardButton = new JButton(new ImageIcon("ressources/button/difficile_button.png"));

        easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        manager.buttonPressed(easyButton, "gameScreen_m_facile", main);
        manager.buttonPressed(mediumButton, "gameScreen_m_moyen", main);
        manager.buttonPressed(hardButton, "gameScreen_m_difficile", main);

        buttonPanel.add(easyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(mediumButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(hardButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        return buttonPanel;
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