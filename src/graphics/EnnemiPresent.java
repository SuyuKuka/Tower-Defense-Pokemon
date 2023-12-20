package graphics;

import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.ArrayList;

import model.Ennemi;

public class EnnemiPresent extends JPanel{
    private ArrayList<Ennemi> ennemiDisplayed = new ArrayList<Ennemi>();
    private ArrayList<Ennemi> ennemiGone = new ArrayList<Ennemi>();
    JPanel container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("ressources/template/ennemi.png")), 0, 0, 600, 1000, this);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

    public EnnemiPresent(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(254, 0));

        // Ennemi
        container.setOpaque(false);

        BoxLayout boxLayout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(boxLayout);

        JLabel ennemiLabel = new JLabel("Ennemi sur le terrain");
        ennemiLabel.setOpaque(false);
        ennemiLabel.setForeground(Color.WHITE);
        ennemiLabel.setFont(new Font(Font.SERIF, Font.BOLD, 22));
        ennemiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(ennemiLabel, BorderLayout.NORTH);

        JScrollPane ennemiList = new JScrollPane(container);
        ennemiList.setOpaque(false);
        ennemiList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        ennemiList.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // TROP PUISSANT
        setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // TROP PUISSANT

        add(ennemiList);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("ressources/template/ennemi.png")), 0, 0, 600, 720, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setEnnemiPresent(ArrayList<Ennemi> ennemiPresent) {
        for (Ennemi e : ennemiGone) {
            deleteEnnemiLabel(e);
        }
        
        for (Ennemi e : ennemiPresent) {
            if(!ennemiDisplayed.contains(e)){
                createEnnemiLabel(e);
            }
        }
    
        ennemiGone.clear();
        container.revalidate();
        container.repaint();
    }

    public void createEnnemiLabel(Ennemi e) {
        JPanel ennemiInfo = new JPanel(new GridBagLayout());
        ennemiInfo.setOpaque(false);

        JLabel profilPhoto = new JLabel(new ImageIcon("ressources/profile/profil_photo.png"));
        profilPhoto.setOpaque(false);
        profilPhoto.setPreferredSize(new Dimension(50, 50));

        JLabel nameLabel = new JLabel(e.getName());
        nameLabel.setOpaque(false);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);

        JProgressBar healthBar = new JProgressBar();
        healthBar.setOpaque(false);
        healthBar.setMinimum(0);
        healthBar.setMaximum((int) e.getTotalPv());
        healthBar.setValue((int) e.getPv());
        healthBar.setForeground(Color.RED);
        healthBar.setStringPainted(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.ipadx = 10;
        ennemiInfo.add(profilPhoto, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 8, 0, 0);
        ennemiInfo.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        ennemiInfo.add(healthBar, gbc);

        container.add(ennemiInfo);
        ennemiDisplayed.add(e);
    }

    public void deleteEnnemiLabel(Ennemi e) {
        for (Component c : container.getComponents()) {
            if (c instanceof JPanel) {
                JPanel panel = (JPanel) c;
                if (panel.getComponent(1) instanceof JLabel && ((JLabel) panel.getComponent(1)).getText().equals(e.getName())) {
                    container.remove(panel);
                    break;
                }
            }
        }
        ennemiDisplayed.remove(e);
        
        container.revalidate();
        container.repaint();
    }

    public ArrayList<Ennemi> getEnnemiGone() {
        return ennemiGone;
    }
}
