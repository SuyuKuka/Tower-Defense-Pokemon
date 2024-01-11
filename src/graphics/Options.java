package graphics;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import control.Control;

public class Options extends JPanel {
    private Control control = Control.INSTANCE;
    private GraphicsMaker maker = new GraphicsMaker();
    private MainGraphics main;
    public static String selectedMap = "ete";

    public Options(MainGraphics main) {
        this.main = main;
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
    
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("ressources/munro.ttf"));
            customFont = customFont.deriveFont(Font.ITALIC, 30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    
        JLabel mapJLabel = new JLabel("Theme de la map :");
        mapJLabel.setFont(customFont);
        mapJLabel.setForeground(Color.WHITE);
    
        ButtonGroup mapGroup = new ButtonGroup();
    
        JRadioButton springButton = new JRadioButton("printemps");
        JRadioButton summerButton = new JRadioButton("ete");
        JRadioButton autumnButton = new JRadioButton("automne");
        JRadioButton winterButton = new JRadioButton("hiver");
        springButton.setOpaque(false);
        summerButton.setOpaque(false);
        autumnButton.setOpaque(false);
        winterButton.setOpaque(false);
    
        springButton.setFont(customFont);
        springButton.setForeground(Color.WHITE);
        summerButton.setFont(customFont);
        summerButton.setForeground(Color.WHITE);
        autumnButton.setFont(customFont);
        autumnButton.setForeground(Color.WHITE);
        winterButton.setFont(customFont);
        winterButton.setForeground(Color.WHITE);
    
        ActionListener mapSelectionListener = e -> {
            selectedMap = ((JRadioButton) e.getSource()).getText();
        };
    
        springButton.setFocusPainted(false);
        summerButton.setFocusPainted(false);
        autumnButton.setFocusPainted(false);
        winterButton.setFocusPainted(false);
        springButton.setMargin(new Insets(0, 20, 0, 0));
        summerButton.setMargin(new Insets(0, 20, 0, 0));
        autumnButton.setMargin(new Insets(0, 20, 0, 0));
        winterButton.setMargin(new Insets(0, 20, 0, 0));
        springButton.addActionListener(mapSelectionListener);
        summerButton.addActionListener(mapSelectionListener);
        autumnButton.addActionListener(mapSelectionListener);
        winterButton.addActionListener(mapSelectionListener);
        summerButton.setSelected(true);
    
        mapGroup.add(springButton);
        mapGroup.add(summerButton);
        mapGroup.add(autumnButton);
        mapGroup.add(winterButton);
    
        JLabel musicLabel = new JLabel("Activer la musique :");
        musicLabel.setFont(customFont);
        musicLabel.setForeground(Color.WHITE);

        JRadioButton enableMusicButton = new JRadioButton("ON");
        JRadioButton disableMusicButton = new JRadioButton("OFF");

        enableMusicButton.setOpaque(false);
        disableMusicButton.setOpaque(false);

        enableMusicButton.setFont(customFont);
        enableMusicButton.setForeground(Color.WHITE);
        disableMusicButton.setFont(customFont);
        disableMusicButton.setForeground(Color.WHITE);

        ButtonGroup musicGroup = new ButtonGroup();
        musicGroup.add(enableMusicButton);
        musicGroup.add(disableMusicButton);

        ActionListener musicSelectionListener = e -> {
            if(((JRadioButton) e.getSource()).getText().contains("OFF")){
                main.getMusicPlayer().setPlayed(false);
                main.getMusicPlayer().getClip().stop();
            }else{
                main.getMusicPlayer().setPlayed(true);
                main.getMusicPlayer().getClip().start();
            }
        };

        enableMusicButton.setFocusPainted(false);
        disableMusicButton.setFocusPainted(false);
        enableMusicButton.setMargin(new Insets(0, 20, 0, 0));
        disableMusicButton.setMargin(new Insets(0, 20, 0, 0));
        enableMusicButton.addActionListener(musicSelectionListener);
        disableMusicButton.addActionListener(musicSelectionListener);
        enableMusicButton.setSelected(true);

        GroupLayout layout = new GroupLayout(all);
        all.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(mapJLabel)
                .addComponent(springButton)
                .addComponent(summerButton)
                .addComponent(autumnButton)
                .addComponent(winterButton)
                .addComponent(musicLabel)
                .addComponent(enableMusicButton)
                .addComponent(disableMusicButton));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(mapJLabel)
                .addComponent(springButton)
                .addComponent(summerButton)
                .addComponent(autumnButton)
                .addComponent(winterButton)
                .addComponent(musicLabel)
                .addComponent(enableMusicButton)
                .addComponent(disableMusicButton);
        layout.setVerticalGroup(vGroup);
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
