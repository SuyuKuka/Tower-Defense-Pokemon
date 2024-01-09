package graphics;

import javax.swing.*;
import javax.swing.border.*;

import model.Pc;
import model.Tower;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

    public class Slot extends JPanel {
        private Pc pc = Pc.INSTANCE;
        private GraphicsMaker maker = new GraphicsMaker();
        private Tower pkm;
        private static Slot firstSelected;
    
    public Slot(Tower pkm) {
        this.pkm = pkm;

        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(1, 1, 1, 1));
        setBackground(new Color(50, 50, 50));

        JLabel pokeicon = new JLabel(new ImageIcon(pkm.getSprite()));
        pokeicon.setOpaque(false);
        add(pokeicon);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pc.selected(pkm);
                if (pc.getSelect1()==null) {
                    reset();
                } else {
                    adding();
                }
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(100, 100, 100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(50, 50, 50));
            }
        });
    }

    public Slot() {
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(1, 1, 1, 1));
        setBackground(new Color(50, 50, 50));

        JLabel pokeicon = maker.resizeJLabel("ressources/sprite/nothing.png", 32);
        pokeicon.setOpaque(false);
        add(pokeicon);

        addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) {
                    pc.selected(pkm);
                    if (pc.getSelect1()==null) {
                        reset();
                    } else {
                        adding();
                    }
                    repaint();
                }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(100, 100, 100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(50, 50, 50));
            }
        });
    }

    public void adding(){
        setBorder(BorderFactory.createLineBorder(Color.RED));
        if(Slot.firstSelected==null){
            Slot.firstSelected = this;
        }
    }

    public void reset(){
        Slot.firstSelected.setBorder(new EmptyBorder(1, 1, 1, 1));
        Slot.firstSelected= null;
    }

    public Tower getPkm() {
        return pkm;
    }
}