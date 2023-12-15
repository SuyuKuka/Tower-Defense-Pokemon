package graphics;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Classique extends MainMenu {
    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
    private JPanel backPanel = new JPanel();

    public Classique() {
        setLayout(new BorderLayout());
        setOpaque(false);

        //Titre
        JLabel heading = new JLabel("Choisis la difficulté");
        heading.setFont(new Font(Font.SERIF, Font.BOLD, 64));
        heading.setForeground(Color.WHITE);

        titlePanel.setOpaque(false);
        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 48, 0));
        
        add(titlePanel, BorderLayout.NORTH);

        //Boutons
        JButton easyButton = new JButton(new ImageIcon("ressources/button/facile_button.png"));
        JButton mediumButton = new JButton(new ImageIcon("ressources/button/moyen_button.png"));
        JButton hardButton = new JButton(new ImageIcon("ressources/button/difficile_button.png"));

        buttonPressed(easyButton, "facile");
        buttonPressed(mediumButton, "moyen");
        buttonPressed(hardButton, "difficle");
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(easyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(mediumButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(hardButton);

        add(buttonPanel, BorderLayout.CENTER);
        
        //Back
        backPanel.setOpaque(false);
        JButton backButton = new JButton(new ImageIcon("ressources/button/retour_button.png"));
        buttonPressed(backButton, "retour");
        backPanel.add(backButton);

        backPanel.setBorder(BorderFactory.createEmptyBorder(0, -1224+backButton.getPreferredSize().width, 0, 0));
        add(backPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
    }

    public JPanel getBackPanel() {
        return backPanel;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }
    
    public JPanel getButtonPanel() {
        return buttonPanel;
    }
}