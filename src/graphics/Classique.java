package graphics;

import javax.swing.*;
import java.awt.*;

public class Classique extends MainMenu {
    public Classique() {
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel heading = new JLabel("Choisis la difficulté");
        add(heading, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1));

        JButton facileButton = new JButton("Facile");
        JButton moyenButton = new JButton("Moyen");
        JButton difficileButton = new JButton("Difficile");

        buttonsPanel.add(facileButton);
        buttonsPanel.add(moyenButton);
        buttonsPanel.add(difficileButton);

        add(buttonsPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
    }
}