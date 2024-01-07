package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import control.Control;
import model.Skill;
import model.Tower;
import model.Types;

public class TowerGUI extends JPanel {

    private GraphicsMaker maker = new GraphicsMaker();
    private Control control = new Control();
    private Tower pkm;

    private JLabel pkmName;
    private JPanel type = new JPanel();
    private JPanel types = new JPanel();

    public TowerGUI() {
        type.setLayout(new BorderLayout());
        types.setLayout(new BorderLayout());

        pkmName = new JLabel();
        pkmName.setFont(maker.getCustomFont().deriveFont(20f));//
        pkmName.setForeground(Color.WHITE);

        setPreferredSize(new Dimension(0, 400));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(pkmName, BorderLayout.NORTH);
        add(types, BorderLayout.CENTER);
    }

    public Tower getPkm() {
        return this.pkm;
    }

    public void setPkm() {

    }

    public void updatePkm(Tower pkm) {
        if (pkm.getType()[1] == Types.NONE) {
            type = maker.imagePanel("ressources/type/" + pkm.getType()[0] + ".png");
        } else {
            type = maker.imagePanel("ressources/type/" + pkm.getType()[0] + ".png");
        }
        types.add(type, BorderLayout.CENTER);
        pkmName.setText(pkm.getName());
    }

}
