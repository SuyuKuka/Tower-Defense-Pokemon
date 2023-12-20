package graphics;

import main.Game;
import model.Player;
import model.Pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import characters.Starter.Chochodile;
import control.ButtonManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.border.EmptyBorder;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainMenu extends JLayeredPane {
    private Player user;
    private ButtonManager manager = new ButtonManager();
    private GraphicsMaker maker = new GraphicsMaker();
    private boolean displayProfile = false;
    private boolean inCadrePanel = false;

    public MainMenu(Game main, Player p) {

        user = p;

        setOpaque(false);

        main.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = main.getContentPane().getSize();
                setSize(newSize);

            }
        });

        JPanel menu = new JPanel(new BorderLayout());
        menu.setOpaque(false);
        // Titre
        JPanel titlePanel = titlePanel();

        // Boutons
        JPanel buttonPanel = buttonPanel(main);

        // Profil
        JPanel bigProfil = bigProfilPanel();
        JPanel profilPanel = profilPanel(bigProfil);
        bigProfil.setVisible(false);

        // Team
        JPanel pcPanel = pcPanel();
        JPanel teamPanel = teamPanel(pcPanel);
        pcPanel.setVisible(false);

        profilPanel.add(teamPanel, BorderLayout.EAST);

        menu.add(titlePanel, BorderLayout.NORTH);
        menu.add(buttonPanel, BorderLayout.EAST);
        menu.add(profilPanel, BorderLayout.SOUTH);
        menu.add(bigProfil, BorderLayout.WEST);

        menu.setBounds(0, 0, main.getWidth(), main.getHeight() - 30);
        menu.setVisible(false);
        add(menu);

    }

    private JPanel pcPanel() {
        JPanel pcPanel = new JPanel();
        JLabel pc = new JLabel("   PC   ");
        pc.setFont(maker.getCustomFont());
        pcPanel.add(pc);
        return pcPanel;
    }

    private JPanel teamPanel(JPanel pcPanel) {
        JPanel teamPanel = addBgPanel("ressources/background/teampanel.png");
        if (user.getTeam()[0] == null) {
            JLabel pokeicon = new JLabel("Vous n'avez aucun Pokemon dans votre equipe.");
            pokeicon.setFont(maker.getCustomFont().deriveFont(20f));
            pokeicon.setForeground(Color.WHITE);
            pokeicon.setOpaque(false);
            teamPanel.setBorder(new EmptyBorder(0, 70, 0, 30));
            teamPanel.setOpaque(false);

            teamPanel.add(pokeicon);

            return teamPanel;
        }
        teamPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        teamPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        for (Pokemon pkm : user.getTeam()) {
            if (pkm != null) {

                BufferedImage resize = loadBgImage(pkm.getSprite());
                Image resized = resize.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // RESIZE
                JLabel pokeicon = new JLabel(new ImageIcon(resized));
                pokeicon.setOpaque(false);
                teamPanel.add(pokeicon);
            }
        }
        teamPanel.setOpaque(false);
        teamPanel.setBorder(new EmptyBorder(0, 70, 0, 30));

        teamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pcPanel.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pcPanel.setVisible(false);
            }

        });

        return teamPanel;
    }

    private JPanel titlePanel() {
        JPanel titlePanel = addBgPanel("ressources/background/banner.png");
        JLabel heading = new JLabel("POKEREVOLUTE");
        heading.setFont(maker.getCustomFont());
        heading.setForeground(Color.WHITE);

        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBackground(Color.BLACK);

        return titlePanel;
    }

    private JPanel buttonPanel(Game main) {
        JPanel buttonPanel = addBgPanel("ressources/button/buttonpanel.png");
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JButton classiqueButton = new JButton(new ImageIcon("ressources/button/classique_button.png"));
        JButton marathonButton = new JButton(new ImageIcon("ressources/button/marathon_button.png"));
        JButton boutiqueButton = new JButton(new ImageIcon("ressources/button/boutique_button.png"));
        JButton optionsButton = new JButton(new ImageIcon("ressources/button/options_button.png"));

        manager.buttonPressed(classiqueButton, "classique", main);
        manager.buttonPressed(marathonButton, "marathon", main);
        manager.buttonPressed(boutiqueButton, "boutique", main);
        manager.buttonPressed(optionsButton, "options", main);

        // Centrer les boutons verticalement
        classiqueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        marathonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boutiqueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ajouter des boutons avec des rigid areas (espace fixe) entre eux
        buttonPanel.add(classiqueButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(marathonButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(boutiqueButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(optionsButton);

        return buttonPanel;

    }

    private JPanel bigProfilPanel() {
        JPanel profilPanel = new JPanel();

        profilPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(Color.BLACK);

        JPanel cadrePanel = new JPanel();

        cadrePanel.setLayout(new BorderLayout());
        cadrePanel.setBackground(Color.BLACK);
        cadrePanel.setBorder(new LineBorder(Color.WHITE, 2));

        cadrePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BufferedImage resize = loadBgImage("ressources/profile/profil_photo.png");
        Image resized = resize.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel profilPhoto = new JLabel(new ImageIcon(resized));

        JLabel pseudo = new JLabel("  " + user.getPseudo());
        pseudo.setForeground(Color.WHITE);
        pseudo.setFont(maker.getCustomFont().deriveFont(20f));

        JLabel levelAccount = new JLabel("  Nv du compte : " + user.getLvl());
        levelAccount.setForeground(Color.WHITE);
        levelAccount.setFont(maker.getCustomFont().deriveFont(20f));

        cadrePanel.add(profilPhoto, BorderLayout.WEST);
        infoPanel.add(pseudo, BorderLayout.NORTH);
        infoPanel.add(levelAccount, BorderLayout.CENTER);

        cadrePanel.add(infoPanel, BorderLayout.CENTER);
        profilPanel.add(cadrePanel);

        return profilPanel;
    }

    private JPanel profilPanel(JPanel bigProfil) {
        JPanel profilPanel = addBgPanel("ressources/background/banner.png");

        profilPanel.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(Color.BLACK);

        JPanel cadrePanel = new JPanel();

        cadrePanel.setLayout(new BorderLayout());
        cadrePanel.setBackground(Color.BLACK);
        cadrePanel.setBorder(new LineBorder(Color.WHITE, 2));

        cadrePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BufferedImage resize = loadBgImage("ressources/profile/profil_photo.png");
        Image resized = resize.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // RESIZE
        JLabel profilPhoto = new JLabel(new ImageIcon(resized));

        JLabel pseudo = new JLabel("  " + user.getPseudo());
        pseudo.setForeground(Color.WHITE);
        pseudo.setFont(maker.getCustomFont().deriveFont(20f));

        JLabel levelAccount = new JLabel("  Nv du compte : " + user.getLvl() + " ");
        levelAccount.setForeground(Color.WHITE);
        levelAccount.setFont(maker.getCustomFont().deriveFont(20f));

        profilPanel.setBorder(new EmptyBorder(10, 10, 10, 0));
        cadrePanel.add(profilPhoto, BorderLayout.WEST);
        infoPanel.add(pseudo, BorderLayout.NORTH);
        infoPanel.add(levelAccount, BorderLayout.CENTER);

        cadrePanel.add(infoPanel, BorderLayout.CENTER);
        profilPanel.add(cadrePanel, BorderLayout.WEST);

        cadrePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                inCadrePanel = false;
                Timer timer = new Timer(400, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        if (!inCadrePanel && !displayProfile) {
                            bigProfil.setVisible(false);
                        }
                    }
                });
                timer.setRepeats(false);
                timer.start();
                System.out.println("Exited cadre " + inCadrePanel);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                inCadrePanel = true;
                if (!displayProfile) {
                    bigProfil.setVisible(true);
                }
            }
        });

        bigProfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                displayProfile = false;
                if (!inCadrePanel && !displayProfile) {
                    bigProfil.setVisible(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                displayProfile = true;
            }
        });

        return profilPanel;
    }

    private BufferedImage loadBgImage(String path) {
        BufferedImage backgroundImage = null;
        if (backgroundImage == null) {
            File file = new File(path);
            try {
                backgroundImage = ImageIO.read(file);
                if (backgroundImage == null) {
                    backgroundImage = ImageIO.read(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return backgroundImage;
    }

    private JPanel addBgPanel(String path) {
        BufferedImage backgroundImage = loadBgImage(path);
        JPanel IPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        return IPanel;
    }

    public boolean isDisplayProfile() {
        return displayProfile;
    }

    public void setDisplayProfile(boolean displayProfile) {
        this.displayProfile = displayProfile;
    }

}
