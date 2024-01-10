package graphics;

import model.Pc;
import model.Player;
import model.Tower;

import javax.swing.*;
import javax.swing.border.LineBorder;

import control.Control;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

public class MainMenu extends JPanel {

    private Player user;

    private Control control = Control.INSTANCE;
    private GraphicsMaker maker = new GraphicsMaker();

    private boolean displayProfile = false;
    private boolean inCadrePanel = false;

    private boolean displayPcPanel = false;
    JPanel teamPanel;

    public MainMenu(MainGraphics main, Player p) {
        Pc.INSTANCE.setMainMenu(this);
        CardLayout cdL = new CardLayout();
        user = p;
        control.setUser(p);

        setLayout(new BorderLayout());
        setOpaque(false);

        // Titre
        JPanel titlePanel = titlePanel();

        // Boutons

        JPanel buttonPanel = buttonPanel(main);

        // Team
        JPanel pcPanel = pcPanel();
        pcPanel.setVisible(false);
        JPanel teamPanel = teamPanel(pcPanel);

        // Profil
        JPanel bigProfil = bigProfilPanel();
        bigProfil.setVisible(false);

        JPanel profilPanel = profilPanel(bigProfil);
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

        JLabel pcTitle = new JLabel("PC");
        pcTitle.setFont(maker.getCustomFont());
        pcTitle.setForeground(Color.WHITE);

        JPanel heading = maker.imagePanel("ressources/background/pcheading.png");
        heading.setBorder(new LineBorder(Color.BLACK, 5));
        heading.add(pcTitle);

        JPanel pcPanel = new JPanel();
        pcPanel.setLayout(new BorderLayout());
        pcPanel.setBorder(new LineBorder(Color.BLACK, 2));
        pcPanel.setPreferredSize(new Dimension(350, 0));
        JPanel box = maker.imagePanel("ressources/background/pc.png");
        box.add(new Slot());

        pcPanel.add(box, BorderLayout.CENTER);
        pcPanel.add(heading, BorderLayout.NORTH);
        return pcPanel;
    }

    private JPanel teamPanel(JPanel pcPanel) {


         teamPanel = new JPanel();
        teamPanel.setBackground(new Color(40, 40, 40));
        JLabel pcLabel = maker.resizeJLabel("ressources/pc_sprite.png", 32);
        pcLabel.setOpaque(false);

        if (user.getTeam()[0] == null) {
            JLabel pokeicon = new JLabel("Vous n'avez aucun Pokemon dans votre equipe.");
            pokeicon.setFont(maker.getCustomFont().deriveFont(20f));
            pokeicon.setForeground(Color.WHITE);
            pokeicon.setOpaque(false);
            return teamPanel;
        } else {
            pcLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            teamPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            placeTeam();
            return teamPanel;
        }
    }

    public void placeTeam(){

       teamPanel.removeAll();
        JLabel pcLabel = maker.resizeJLabel("ressources/pc_sprite.png", 32);
        pcLabel.setOpaque(false);
        teamPanel.add(pcLabel);

        System.out.println("===========================================");
        for (int i = 0; i < 6; i++) {
            System.out.println(Player.INSTANCE.getTeam()[i].getName());
            Slot slot = new Slot();
            if (user.getTeam()[i] != null) {
                slot = new Slot(user.getTeam()[i],this);
                user.getTeam()[i].setSlot(slot);
            }
            teamPanel.add(slot);
        }
        teamPanel.revalidate();
    }




    private JPanel titlePanel() {

        JLabel heading = new JLabel("POKEREVOLUTE");
        heading.setFont(maker.getCustomFont().deriveFont(45f));
        heading.setForeground(Color.WHITE);

        JPanel titlePanel = maker.imagePanel("ressources/background/banner.png");
        titlePanel.setPreferredSize(new Dimension(0, 52));
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(heading);

        return titlePanel;
    }

    private JPanel buttonPanel(MainGraphics main) {
        JPanel buttonPanel = maker.imagePanel("ressources/button/buttonpanel.png");
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JButton classiqueButton = new JButton(new ImageIcon("ressources/button/classique_button.png"));
        JButton marathonButton = new JButton(new ImageIcon("ressources/button/marathon_button.png"));
        JButton boutiqueButton = new JButton(new ImageIcon("ressources/button/boutique_button.png"));
        JButton optionsButton = new JButton(new ImageIcon("ressources/button/options_button.png"));

        control.buttonPressed(classiqueButton, "classique", main);
        control.buttonPressed(marathonButton, "marathon", main);
        control.buttonPressed(boutiqueButton, "boutique", main);
        control.buttonPressed(optionsButton, "options", main);

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
        JPanel bigProfilePanel = maker.imagePanel("ressources/profile/profilebg.png");
        bigProfilePanel.setOpaque(false);
        bigProfilePanel.setPreferredSize(new Dimension(300, 0));
        
        bigProfilePanel.setLayout(new BoxLayout(bigProfilePanel, BoxLayout.Y_AXIS));
        bigProfilePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JLabel profilPhoto = maker.resizeJLabel("ressources/profile/profil_photo.png", 80);
        profilPhoto.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JLabel pseudo = new JLabel(user.getPseudo());
        pseudo.setForeground(Color.WHITE);
        pseudo.setFont(maker.getCustomFont().deriveFont(20f));
        pseudo.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JLabel levelAccount = new JLabel("Nv du compte : " + user.getLvl());
        levelAccount.setForeground(Color.WHITE);
        levelAccount.setFont(maker.getCustomFont().deriveFont(20f));
        levelAccount.setAlignmentX(Component.CENTER_ALIGNMENT);

        JProgressBar expBar = new JProgressBar();
        expBar.setMinimum(0);
        expBar.setMaximum((int) user.expCap());
        expBar.setValue((int) user.getExp());

        expBar.setForeground(Color.BLUE);
        expBar.setString(user.getExp() + "/" + user.expCap());
        expBar.setStringPainted(true);
        expBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Dimension expBarSize = new Dimension(200, 20); // Ajustez la taille selon vos préférences
        expBar.setMaximumSize(expBarSize);

        profilPhoto.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        pseudo.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        levelAccount.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        bigProfilePanel.add(profilPhoto);
        bigProfilePanel.add(pseudo);
        bigProfilePanel.add(levelAccount);
        bigProfilePanel.add(expBar);
    
        return bigProfilePanel;
    }

    private JPanel profilPanel(JPanel bigProfil) {
        JPanel profilPanel = maker.imagePanel("ressources/background/banner.png");
        profilPanel.setLayout(new BorderLayout());
        profilPanel.setPreferredSize(new Dimension(0, 52));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(Color.BLACK);

        JPanel cadrePanel = new JPanel();
        cadrePanel.setLayout(new BorderLayout());
        cadrePanel.setBackground(Color.BLACK);
        cadrePanel.setBorder(new LineBorder(Color.WHITE, 2));
        cadrePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel profilPhoto = maker.resizeJLabel("ressources/profile/profil_photo.png", 32);

        JLabel pseudo = new JLabel("  " + user.getPseudo());
        pseudo.setForeground(Color.WHITE);
        pseudo.setFont(maker.getCustomFont().deriveFont(20f));

        JLabel levelAccount = new JLabel("  Nv du compte : " + user.getLvl() + " ");
        levelAccount.setForeground(Color.WHITE);
        levelAccount.setFont(maker.getCustomFont().deriveFont(20f));

        profilPanel.setBorder(new EmptyBorder(2, 5, 2, 5));
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

    public boolean isDisplayProfile() {
        return displayProfile;
    }

    public void setDisplayProfile(boolean displayProfile) {
        this.displayProfile = displayProfile;
    }

}
    // public class Slot extends JPanel {

    //     private Tower pkm;
    //     private boolean selected = false;
    //     private static ArrayList<Slot> exchange = new ArrayList<>();

    //     public Slot(Tower pkm) {
    //         this.pkm = pkm;

    //         JLabel pokeicon = new JLabel(new ImageIcon(pkm.getSprite()));
    //         pokeicon.setOpaque(false);

    //         setOpaque(true);
    //         setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    //         setBorder(new EmptyBorder(1, 1, 1, 1));
    //         setBackground(new Color(50, 50, 50));
    //         add(pokeicon);

    //         addMouseListener(new MouseAdapter() {
    //             @Override
    //             public void mouseClicked(MouseEvent e) {
    //                 if (!selected) {
    //                     setBorder(new LineBorder(Color.RED, 1));
    //                     selected = true;
    //                     waitForExchange();
    //                 } else {
    //                     setBorder(new EmptyBorder(1, 1, 1, 1));
    //                     selected = false;
    //                     removeExchange();
    //                 }
    //             }

    //             @Override
    //             public void mouseEntered(MouseEvent e) {
    //                 setBackground(new Color(100, 100, 100));
    //             }

    //             @Override
    //             public void mouseExited(MouseEvent e) {
    //                 setBackground(new Color(50, 50, 50));
    //             }
    //         });

    //     }

    //     public Slot() {

    //         JLabel pokeicon = maker.resizeJLabel("ressources/sprite/nothing.png", 32);

    //         add(pokeicon);
    //         setOpaque(true);
    //         setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    //         setBorder(new EmptyBorder(1, 1, 1, 1));
    //         setBackground(new Color(50, 50, 50));
    //         pokeicon.setOpaque(false);

    //         addMouseListener(new MouseAdapter() {
    //             @Override
    //             public void mouseClicked(MouseEvent e) {
    //                 if (!selected) {
    //                     setBorder(new LineBorder(Color.RED, 1));
    //                     selected = true;
    //                     waitForExchange();
    //                 } else {
    //                     setBorder(new EmptyBorder(1, 1, 1, 1));
    //                     selected = false;
    //                     removeExchange();
    //                 }
    //             }

    //             @Override
    //             public void mouseEntered(MouseEvent e) {
    //                 setBackground(new Color(100, 100, 100));
    //             }

    //             @Override
    //             public void mouseExited(MouseEvent e) {
    //                 setBackground(new Color(50, 50, 50));
    //             }
    //         });

    //     }

    //     private void removeExchange() {
    //         exchange.remove(this);
    //     }

    //     private void waitForExchange() {
    //         if (exchange.size() < 2) {
    //             exchange.add(this);
    //         }
    //         if (exchange.size() == 2) {
    //             Timer timer = new Timer(200, new ActionListener() {
    //                 @Override
    //                 public void actionPerformed(ActionEvent arg0) {

    //                     Slot slot1 = exchange.get(0);
    //                     Slot slot2 = exchange.get(1);

    //                     Container parent1 = slot1.getParent();
    //                     Container parent2 = slot2.getParent();

    //                     parent1.remove(slot1);
    //                     parent2.remove(slot2);

    //                     parent1.add(slot2);
    //                     parent2.add(slot1);

    //                     parent1.revalidate();
    //                     parent2.revalidate();

    //                     for (Slot slot : exchange) {
    //                         if (slot.getPkm() != null) {
    //                             control.switchPkm(slot.getPkm());
    //                         }
    //                     }

    //                     exchange.get(0).setBorder(new LineBorder(Color.RED, 0));
    //                     exchange.get(0).setSelected(false);
    //                     exchange.get(1).setBorder(new LineBorder(Color.RED, 0));
    //                     exchange.get(1).setSelected(false);

    //                     exchange.clear();

    //                 }
    //             });
    //             timer.setRepeats(false);
    //             timer.start();
    //         }
    //         repaint();

    //     }

    //     public Tower getPkm() {
    //         return pkm;
    //     }

    //     public boolean getSelected() {
    //         return selected;
    //     }

    //     public void setSelected(boolean selected) {
    //         this.selected = selected;
    //     }
    // }

// }
