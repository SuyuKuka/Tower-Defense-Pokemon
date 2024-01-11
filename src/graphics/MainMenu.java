package graphics;

import model.Pc;
import model.Player;
import control.Control;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MainMenu extends JPanel {
    private Player user = Player.INSTANCE;
    private Control control = Control.INSTANCE;
    private GraphicsMaker maker = new GraphicsMaker();

    private boolean displayProfile = false;
    private boolean inCadrePanel = false;
    private boolean displayPcPanel = false;

    private JPanel teamPanel;
    private JPanel pcPanel;
    private JPanel box;
    private JProgressBar expBar;
    private JLabel levelAccount;
    private JLabel levelAccount2;

    public MainMenu(MainGraphics main) {
        Pc.INSTANCE.setMainMenu(this);
        CardLayout cdL = new CardLayout();

        setLayout(new BorderLayout());
        setOpaque(false);
        setVisible(true);

        JPanel titlePanel = titlePanel();
        JPanel buttonPanel = buttonPanel(main);

        pcPanel = pcPanel();
        pcPanel.setVisible(false);
        teamPanel = teamPanel();

        JPanel bigProfil = bigProfilPanel();
        bigProfil.setVisible(false);

        JPanel profilPanel = profilPanel(bigProfil);
        profilPanel.add(teamPanel, BorderLayout.EAST);

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
    }

    private JPanel pcPanel() {
        JLabel pcTitle = new JLabel("PC");
        pcTitle.setFont(maker.getCustomFont());
        pcTitle.setForeground(Color.WHITE);

        JPanel heading = maker.imagePanel("ressources/background/pcheading.png");
        heading.setBorder(new LineBorder(Color.BLACK, 5));
        heading.add(pcTitle);

        pcPanel = new JPanel();
        pcPanel.setLayout(new BorderLayout());
        pcPanel.setBorder(new LineBorder(Color.BLACK, 2));
        pcPanel.setPreferredSize(new Dimension(350, 0));

        box = maker.imagePanel("ressources/background/pc.png");
        box.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        placePc();
        pcPanel.add(heading, BorderLayout.NORTH);
        return pcPanel;
    }

    public void placePc() {
        if (box != null) {
            box.removeAll();
        }
        for (int i = 0; i < Pc.INSTANCE.getHeight() * Pc.INSTANCE.getWidth(); i++) {
            Slot slot = new Slot(Pc.INSTANCE.getPc().get(i), this);
            slot.setPreferredSize(new Dimension(75, 75));
            slot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            box.add(slot);
        }
        pcPanel.add(box, BorderLayout.CENTER);
        if (box != null) {
            box.repaint();
        }
    }

    private JPanel teamPanel() {
        teamPanel = new JPanel();
        teamPanel.setBackground(new Color(40, 40, 40));
        JLabel pcLabel = maker.resizeJLabel("ressources/template/pc_sprite.png", 32);
        pcLabel.setOpaque(false);
        pcLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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

    public void placeTeam() {
        teamPanel.removeAll();
        JLabel pcLabel = maker.resizeJLabel("ressources/template/pc_sprite.png", 32);
        pcLabel.setOpaque(false);
        teamPanel.add(pcLabel);

        for (int i = 0; i < 6; i++) {
            Slot slot = new Slot(user.getTeam()[i], this);
            user.getTeam()[i].setSlot(slot);
            teamPanel.add(slot);
        }
        teamPanel.revalidate();
    }

    public void updateProfile() {
        levelAccount2.setText("Nv du compte : " + user.getLvl());
        levelAccount.setText("Nv du compte : " + user.getLvl());
        expBar.setValue((int) (user.getExp() / user.expCap() * 100));
        expBar.setString(user.getExp() + "/" + user.expCap());
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
        classiqueButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton marathonButton = new JButton(new ImageIcon("ressources/button/marathon_button.png"));
        marathonButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton optionsButton = new JButton(new ImageIcon("ressources/button/options_button.png"));
        optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        control.buttonPressed(classiqueButton, "classique", main);
        control.buttonPressed(marathonButton, "marathon", main);
        control.buttonPressed(optionsButton, "options", main);

        buttonPanel.add(classiqueButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(marathonButton);
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
        profilPhoto.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel pseudo = new JLabel(user.getPseudo());
        pseudo.setForeground(Color.WHITE);
        pseudo.setFont(maker.getCustomFont().deriveFont(20f));
        pseudo.setAlignmentX(Component.CENTER_ALIGNMENT);
        pseudo.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        levelAccount2 = new JLabel("Nv du compte : " + user.getLvl());
        levelAccount2.setForeground(Color.WHITE);
        levelAccount2.setFont(maker.getCustomFont().deriveFont(20f));
        levelAccount2.setAlignmentX(Component.CENTER_ALIGNMENT);
        levelAccount2.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        expBar = new JProgressBar();
        expBar.setMinimum(0);
        expBar.setMaximum((int) user.expCap());
        expBar.setValue((int) user.getExp());
        expBar.setForeground(Color.BLUE);
        expBar.setString(user.getExp() + "/" + user.expCap());
        expBar.setStringPainted(true);
        expBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Dimension expBarSize = new Dimension(200, 20);
        expBar.setMaximumSize(expBarSize);

        bigProfilePanel.add(profilPhoto);
        bigProfilePanel.add(pseudo);
        bigProfilePanel.add(levelAccount2);
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

        levelAccount = new JLabel("  Nv du compte : " + user.getLvl() + " ");
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
