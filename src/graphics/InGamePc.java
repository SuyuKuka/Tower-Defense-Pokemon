package graphics;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import control.Control;
import model.Pc;
import model.Player;

public class InGamePc extends JPanel{
    private Control control = Control.INSTANCE;
    private GraphicsMaker maker = new GraphicsMaker();
    
    JPanel teamPanel = new JPanel();
    JPanel pcPanel = new JPanel();
    JPanel box = new JPanel();
    
    public InGamePc(){
        Pc.INSTANCE.setInGamePc(this);
        setOpaque(false);
        pcPanel.setOpaque(false);
        box.setOpaque(false);
        setPreferredSize(new Dimension(250, 500));

        teamPanel();
        pcPanel();

        add(pcPanel);
    }

    private void pcPanel() {
        JLabel pcTitle = new JLabel("PC");
        pcTitle.setFont(maker.getCustomFont());
        pcTitle.setForeground(Color.WHITE);
    
        JPanel heading = maker.imagePanel("ressources/background/pcheading.png");
        heading.setBorder(new LineBorder(Color.BLACK, 5));
        heading.setPreferredSize(new Dimension(250, 75));
        heading.add(pcTitle);
    
        box.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        box.setLayout(new GridLayout(8, 3, 5, 5));
        placePc();
    
        pcPanel.setLayout(new BorderLayout());
        pcPanel.add(heading, BorderLayout.NORTH);
        pcPanel.add(box, BorderLayout.SOUTH);
    }
    
    public void placePc(){
        if(box != null){
            box.removeAll();
        }
        for (int i = 0; i < Pc.INSTANCE.getHeight() * Pc.INSTANCE.getWidth(); i++) {
            Slot slot = new Slot(Pc.INSTANCE.getPc().get(i), this);
            slot.setPreferredSize(new Dimension(50, 56));
            slot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            box.add(slot);
        }
        pcPanel.add(box);
        if(box != null){
            box.repaint();
        } 
    }

    public void teamPanel() {
        teamPanel.setPreferredSize(new Dimension(300, 42));
        teamPanel.setBackground(new Color(40, 40, 40));
        teamPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        placeTeam();
    }

    public void placeTeam(){
        teamPanel.removeAll();
        for (int i = 0; i < 6; i++) {
            Slot slot = new Slot(Player.INSTANCE.getTeam()[i],this);
            Player.INSTANCE.getTeam()[i].setSlot(slot);
            teamPanel.add(slot);
        }
        teamPanel.revalidate();
    }

    public JPanel getTeamPanel() {
        return teamPanel;
    }
}
