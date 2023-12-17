package graphics;

import main.Game;

import javax.swing.*;
import java.awt.*;

public class Boutique extends JPanel {
    public Boutique(Game main){
        setOpaque(false);
        setLayout(new BorderLayout());
        //Titre
        JLabel heading = new JLabel("Boutique");
        heading.setFont(new Font(Font.SERIF, Font.BOLD, 64));
        heading.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 48, 0));

        //Back
        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);
        
        JButton backButton = new JButton(new ImageIcon("ressources/button/retour_button.png"));
        main.buttonPressed(backButton, "retour");

        backPanel.setBorder(BorderFactory.createEmptyBorder(0, -1224+backButton.getPreferredSize().width, 0, 0));
        backPanel.add(backButton);

        add(titlePanel, BorderLayout.NORTH);
        add(backPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
