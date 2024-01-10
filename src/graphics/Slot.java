package graphics;

import javax.swing.*;
import javax.swing.border.*;

import characters.Ecrapince;
import model.Pc;
import model.Tower;
import model.Types;
import model.Tower.Rayon;
import model.Tower.State;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

    public class Slot extends JPanel {
        private Pc pc = Pc.INSTANCE;
        private GraphicsMaker maker = new GraphicsMaker();
        private Tower pkm;
        private static Slot firstSelected;
    
    public Slot(Tower pkm, MainMenu container) {
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
                container.revalidate();
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

    public Slot(MainMenu container) {//slot vide
        this(new Tower("nothing", -1, -1, Types.NONE, Types.NONE, "ressources/sprite/nothing.png", State.PC, -1, Rayon.NONE, -1), container);
        // setOpaque(true);
        // setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // setBorder(new EmptyBorder(1, 1, 1, 1));
        // setBackground(new Color(50, 50, 50));

        // JLabel pokeicon = maker.resizeJLabel("ressources/sprite/nothing.png", 32);
        // pokeicon.setOpaque(false);
        // add(pokeicon);

        // addMouseListener(new MouseAdapter() {
        //     @Override
        //         public void mouseClicked(MouseEvent e) {
        //             pc.selected(pkm);
        //             if (pc.getSelect1()==null) {
        //                 reset();
        //             } else {
        //                 adding();
        //             }
        //             repaint();
        //         }

        //     @Override
        //     public void mouseEntered(MouseEvent e) {
        //         setBackground(new Color(100, 100, 100));
        //     }

        //     @Override
        //     public void mouseExited(MouseEvent e) {
        //         setBackground(new Color(50, 50, 50));
        //     }
        // });
    }

    public void adding(){
        setBorder(BorderFactory.createLineBorder(Color.RED));
        if(Slot.firstSelected==null){
            pkm.setSlot(this);
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

    public void setPkm(Tower pkm) {
        System.out.println(pkm.getName());
        this.pkm = pkm;
    }
}