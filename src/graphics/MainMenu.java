package graphics;

import main.Game;
import model.Player;
import model.Pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import control.ButtonManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

public class MainMenu extends JPanel {
    private Player user;
    private ButtonManager manager = new ButtonManager();
    private GraphicsMaker maker = new GraphicsMaker();

    private boolean displayProfile = false; // tkt a changer
    private boolean inCadrePanel = false;

    private boolean displayPcPanel = false;

    public MainMenu(Game main, Player p) {

        CardLayout cdL = new CardLayout();
        user = p;
        setLayout(new BorderLayout());
        setOpaque(false);

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

        // right
        JPanel rightSidePanel = new JPanel(cdL);
        rightSidePanel.add(buttonPanel, "button");
        rightSidePanel.add(pcPanel, "pc");
        rightSidePanel.setOpaque(false);

        teamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (displayPcPanel) {
                    cdL.show(rightSidePanel, "button");

                    displayPcPanel = false;
                } else {

                    cdL.show(rightSidePanel, "pc");
                    displayPcPanel = true;
                }
            }
        });

        add(titlePanel, BorderLayout.NORTH);
        add(rightSidePanel, BorderLayout.EAST);
        add(profilPanel, BorderLayout.SOUTH);
        add(bigProfil, BorderLayout.WEST);

        setVisible(true);
    }

    private JPanel pcPanel() {
        JPanel pcPanel = new JPanel();
        JPanel heading = imagePanel("ressources/background/pcheading.png");
        JLabel pcTitle = new JLabel("   PC   ");
        heading.add(pcTitle);
        heading.setBorder(new LineBorder(Color.BLACK, 5));
        pcTitle.setForeground(Color.WHITE);

        pcPanel.setLayout(new BorderLayout());
        pcPanel.setBorder(new LineBorder(Color.BLACK, 2));

        JPanel box = imagePanel("ressources/background/pc.png");

        box.setLayout(new GridLayout(3, 3));
        for (Pokemon pkm : user.getPc()) {

            Slot slot = new Slot(pkm);

            Slot slot1 = new Slot(pkm);
            Slot slot2 = new Slot(pkm);
            box.add(slot);
            box.add(slot1);
            box.add(slot2);

        }
        box.setPreferredSize(new Dimension(300, 200));
        box.setBorder(new EmptyBorder(40, 20, 40, 20));

        pcTitle.setFont(maker.getCustomFont());
        pcPanel.add(box, BorderLayout.CENTER);
        pcPanel.add(heading, BorderLayout.NORTH);
        return pcPanel;
    }

    private JPanel teamPanel(JPanel pcPanel) {
        JPanel teamPanel = imagePanel("ressources/background/teampanel.png");
        if (user.getTeam()[0] == null) {
            JLabel pokeicon = new JLabel("Vous n'avez aucun Pokemon dans votre equipe.");
            pokeicon.setFont(maker.getCustomFont().deriveFont(20f));
            pokeicon.setForeground(Color.WHITE);
            pokeicon.setOpaque(false);
            teamPanel.setBorder(new EmptyBorder(0, 70, 0, 30));
            teamPanel.setOpaque(false);

            return teamPanel;
        }
        teamPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        teamPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        for (Pokemon pkm : user.getTeam()) {
            if (pkm != null) {

                Slot slot = new Slot(pkm);

                teamPanel.add(slot);

            }
        }
        teamPanel.setOpaque(false);
        teamPanel.setBorder(new EmptyBorder(0, 70, 0, 30));

        return teamPanel;
    }

    private JPanel titlePanel() {
        JPanel titlePanel = imagePanel("ressources/background/banner.png");
        JLabel heading = new JLabel("POKEREVOLUTE");
        heading.setFont(maker.getCustomFont());
        heading.setForeground(Color.WHITE);

        titlePanel.add(heading, new GridBagConstraints());
        titlePanel.setBackground(Color.BLACK);

        return titlePanel;
    }

    private JPanel buttonPanel(Game main) {
        JPanel buttonPanel = imagePanel("ressources/button/buttonpanel.png");
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

        BufferedImage resize = loadImage("ressources/profile/profil_photo.png");
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
        JPanel profilPanel = imagePanel("ressources/background/banner.png");

        profilPanel.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(Color.BLACK);

        JPanel cadrePanel = new JPanel();

        cadrePanel.setLayout(new BorderLayout());
        cadrePanel.setBackground(Color.BLACK);
        cadrePanel.setBorder(new LineBorder(Color.WHITE, 2));

        cadrePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BufferedImage resize = loadImage("ressources/profile/profil_photo.png");
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
            public void mouseEntered(MouseEvent e) {
                inCadrePanel = true;
                if (!displayProfile) {
                    bigProfil.setVisible(true);
                }
            }

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
            }

        });

        bigProfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                displayProfile = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                displayProfile = false;
                if (!inCadrePanel && !displayProfile) {
                    bigProfil.setVisible(false);
                }
            }

        });

        return profilPanel;
    }

    private BufferedImage loadImage(String path) {
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

    private JPanel imagePanel(String path) {
        BufferedImage backgroundImage = loadImage(path);
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

    public class Slot extends JPanel {

        private Pokemon pkm;
        private boolean selected = false;
        private static ArrayList<Slot> exchange = new ArrayList<>();

        public Slot(Pokemon pkm) {
            this.pkm = pkm;
            BufferedImage resize = loadImage(pkm.getSprite());
            Image resized = resize.getScaledInstance(60, 60, Image.SCALE_SMOOTH); // RESIZE
            JLabel pokeicon = new JLabel(new ImageIcon(resized));

            add(pokeicon);
            setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            pokeicon.setOpaque(false);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!selected) {

                        setBorder(new LineBorder(Color.RED, 1));
                        selected = true;
                        waitForExchange();
                    } else {
                        selected = false;
                        setBorder(new LineBorder(Color.RED, 0));
                    }
                }
            });

        }

        private void waitForExchange() {
            if (exchange.size() < 2) {
                exchange.add(this);
                if (exchange.size() == 2) {
                    System.out.println("echange");

                    Timer timer = new Timer(400, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            exchange.get(0).setBorder(new LineBorder(Color.RED, 0));
                            exchange.get(0).setSelected(false);
                            exchange.get(1).setBorder(new LineBorder(Color.RED, 0));
                            exchange.get(1).setSelected(false);

                            exchange.clear();

                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }

        }

        public Pokemon getPkm() {
            return pkm;
        }

        public boolean getSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }

}
