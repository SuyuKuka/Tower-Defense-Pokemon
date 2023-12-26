package graphics;

import java.awt.*;
import javax.swing.*;

import model.Ennemi;
import model.MapState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.border.LineBorder;

public class GameScreen extends JPanel {
    private EnnemiPresent ePresent = new EnnemiPresent();
    private TowerPresent tPresent = new TowerPresent();
    private UpdateMap uMap;
    private GraphicsMaker maker = new GraphicsMaker();

    public GameScreen() {

        setLayout(new BorderLayout());

    }

    public void setmState(MapState mState) {
        UpdateMap uMap = new UpdateMap(mState, tPresent);
        this.uMap = uMap;
        uMap.setBorder(new LineBorder(Color.orange));

        JPanel borderbot = maker.imagePanel("ressources/background/banner.png");
        borderbot.setPreferredSize(new Dimension(0, 70));

        JPanel bordertop = maker.imagePanel("ressources/background/banner.png");
        bordertop.setPreferredSize(new Dimension(0, 70));

        add(ePresent, BorderLayout.WEST);
        add(borderbot, BorderLayout.SOUTH);
        add(bordertop, BorderLayout.NORTH);
        add(uMap, BorderLayout.CENTER);
        add(tPresent, BorderLayout.EAST);
    }

    public EnnemiPresent getePresent() {
        return ePresent;
    }

}
