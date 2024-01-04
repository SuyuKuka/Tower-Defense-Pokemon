package graphics;

import java.awt.*;
import javax.swing.*;

import model.MapState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameScreen extends JPanel {
    private CardLayout towerCardLayout= new CardLayout();
    private EnnemiPresent ePresent = new EnnemiPresent();
    private TowerPresent tPresent = new TowerPresent();
    private UpdateMap uMap;
    private GraphicsMaker maker = new GraphicsMaker();

    private JPanel container = maker.imagePanel("ressources/tiles/grass.png");// TODO REFAIRE COLLAGE

    public GameScreen() {

        setLayout(new BorderLayout());
        setOpaque(true);

        container.setLayout(new BorderLayout());
        container.setOpaque(false);

    }

    public void setmState(MapState mState) {
        UpdateMap uMap = new UpdateMap(mState, tPresent);
        this.uMap = uMap;

        JPanel borderbot = maker.imagePanel("ressources/background/banner.png");
        borderbot.setPreferredSize(new Dimension(0, 52));

        JPanel bordertop = maker.imagePanel("ressources/background/banner.png");
        bordertop.setPreferredSize(new Dimension(0, 53));

        container.add(ePresent, BorderLayout.WEST);
        container.add(borderbot, BorderLayout.SOUTH);
        container.add(bordertop, BorderLayout.NORTH);
        container.add(uMap, BorderLayout.CENTER);
        container.add(tPresent, BorderLayout.EAST);

        add(container, BorderLayout.CENTER);
    }

    public EnnemiPresent getePresent() {
        return ePresent;
    }

    public UpdateMap getuMap() {
        return uMap;
    }

}
