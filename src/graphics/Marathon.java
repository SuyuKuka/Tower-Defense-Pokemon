package graphics;

import javax.swing.*;

import main.Game;

import java.awt.*;

public class Marathon extends JPanel {
    public Marathon(Game main) {
        System.out.println("Classique");
        setOpaque(false);
        setLayout(new BorderLayout());
        //Titre
        JLabel heading = new JLabel("Choisis la difficulté");
        heading.setFont(new Font(Font.SERIF, Font.BOLD, 64));
        heading.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 48, 0));

        //Boutons
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JButton easyButton = new JButton(new ImageIcon("ressources/button/facile_button.png"));
        JButton mediumButton = new JButton(new ImageIcon("ressources/button/moyen_button.png"));
        JButton hardButton = new JButton(new ImageIcon("ressources/button/difficile_button.png"));

        main.buttonPressed(easyButton, "gameScreen_m_facile");
        main.buttonPressed(mediumButton, "gameScreen_m_moyen");
        main.buttonPressed(hardButton, "gameScreen_m_difficile");
        

        easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(easyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(mediumButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(hardButton);

        //Back
        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);
        
        JButton backButton = new JButton(new ImageIcon("ressources/button/retour_button.png"));
        main.buttonPressed(backButton, "retour");

        backPanel.setBorder(BorderFactory.createEmptyBorder(0, -1224+backButton.getPreferredSize().width, 0, 0));
        backPanel.add(backButton);

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


}