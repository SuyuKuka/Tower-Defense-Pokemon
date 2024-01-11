package graphics;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import control.Control;

public class Options extends JPanel {
    private Control control = Control.INSTANCE;
    private GraphicsMaker maker = new GraphicsMaker();
    public static String selectedMap = "ete";

    public Options(MainGraphics main) {
        setOpaque(false);
        setLayout(new BorderLayout());
        setVisible(true);

        JPanel titlePanel = titlePanel();
        JPanel optionPanel = optionPanel();
        JPanel bottomPanel = bottomPanel(main);

        add(titlePanel, BorderLayout.NORTH);
        add(optionPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel optionPanel() {
        JPanel all = new JPanel();
        all.setOpaque(false);
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
    
        // Utilisation de FlowLayout pour les boutons radio
        JPanel radioPanel = new JPanel(new FlowLayout());
        radioPanel.setOpaque(false);
    
        ButtonGroup mapGroup = new ButtonGroup();
    
        JRadioButton springButton = new JRadioButton("printemps");
        JRadioButton summerButton = new JRadioButton("ete");
        JRadioButton autumnButton = new JRadioButton("automne");
        JRadioButton winterButton = new JRadioButton("hiver");
        springButton.setOpaque(false);
        summerButton.setOpaque(false);
        autumnButton.setOpaque(false);
        winterButton.setOpaque(false);
    
        Font customFont;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("ressources/munro.ttf"));
            customFont = customFont.deriveFont(Font.ITALIC, 30f);
            springButton.setFont(customFont);
            springButton.setForeground(Color.WHITE);
            springButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            summerButton.setFont(customFont);
            summerButton.setForeground(Color.WHITE);
            summerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            autumnButton.setFont(customFont);
            autumnButton.setForeground(Color.WHITE);
            autumnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            winterButton.setFont(customFont);
            winterButton.setForeground(Color.WHITE);
            winterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    
        ActionListener mapSelectionListener = e -> {
            selectedMap = ((JRadioButton) e.getSource()).getText();
        };
    
        springButton.addActionListener(mapSelectionListener);
        summerButton.addActionListener(mapSelectionListener);
        autumnButton.addActionListener(mapSelectionListener);
        winterButton.addActionListener(mapSelectionListener);
    
        mapGroup.add(springButton);
        mapGroup.add(summerButton);
        mapGroup.add(autumnButton);
        mapGroup.add(winterButton);
    
        radioPanel.add(springButton);
        radioPanel.add(summerButton);
        radioPanel.add(autumnButton);
        radioPanel.add(winterButton);
    
        all.add(radioPanel);
    
        // Ajout du JSlider pour régler le volume
        JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setOpaque(false);
        volumeSlider.setPreferredSize(new Dimension(200, 50));
    
        volumeSlider.addChangeListener(e -> {
            int volume = volumeSlider.getValue() / 50;
            System.out.println("Volume selected: " + volume);
            // Ajoutez le code ici pour ajuster le volume de la musique en fonction de la valeur du JSlider
        });
    
        all.add(volumeSlider);

        return all;
    }


    private JPanel titlePanel() {
        JPanel titlePanel = maker.imagePanel("ressources/background/banner.png");
        titlePanel.setPreferredSize(new Dimension(0, 52));

        JLabel heading = new JLabel("Options");
        heading.setFont(maker.getCustomFont().deriveFont(45f));
        heading.setForeground(Color.WHITE);

        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBackground(Color.BLACK);

        return titlePanel;
    }

    private JPanel bottomPanel(MainGraphics main) {
        JPanel bottomPanel = maker.imagePanel("ressources/background/banner.png");
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottomPanel.setPreferredSize(new Dimension(0, 52));

        JPanel backPanel = control.backButton(main);
        bottomPanel.add(backPanel, BorderLayout.WEST);
        bottomPanel.setOpaque(true);
        return bottomPanel;
    }
}
