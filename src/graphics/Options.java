package graphics;

import main.Game;

import javax.swing.*;

import control.ButtonManager;

import java.awt.*;

public class Options extends JPanel {

    private ButtonManager manager = new ButtonManager();
    private GraphicsMaker maker = new GraphicsMaker();

    public Options(Game main) {
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
        JLabel heading = new JLabel("Options");
        heading.setFont(maker.getCustomFont());
        heading.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
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
