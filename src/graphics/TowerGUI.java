package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import control.Control;
import model.Tower;

public class TowerGUI extends JPanel {

    private Tower pkm;
    private GraphicsMaker maker = new GraphicsMaker();
    private Control control = new Control();

    public TowerGUI(Tower pkm) {
        this.pkm = pkm;
        if (pkm != null) {
            System.out.println(pkm.getName());
        }
        JLabel pkmName = new JLabel(pkm.getName());
        pkmName.setFont(maker.getCustomFont().deriveFont(20f));

        JPanel skillGrid = new JPanel();
        skillGrid.setLayout(new GridLayout(2, 2));

        setPreferredSize(new Dimension(0, 400));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(pkmName, BorderLayout.NORTH);
        add(skillGrid, BorderLayout.CENTER);

    }

    public Tower getPkm() {
        return this.pkm;
    }

    public void setPkm(Tower pkm) {
        this.pkm = pkm;
    }

}
