package graphics;

import java.awt.*;
import javax.swing.*;

import model.MapState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameScreen extends JPanel {
    private EnnemiPresent ePresent = new EnnemiPresent();
    private TowerPresent tPresent = new TowerPresent();
    private InGameMenu inGameButton;
    private InGameMenu inGameInfo;

    private UpdateMap uMap;
    private GraphicsMaker maker = new GraphicsMaker();

    private JPanel container = maker.imagePanel("ressources/tiles/grass.png");

    public GameScreen() {

        setLayout(new BorderLayout());
        setOpaque(true);

        container.setLayout(new BorderLayout());
        container.setOpaque(false);

    }

    public void setmState(MapState mState) {
        UpdateMap uMap = new UpdateMap(mState, tPresent);
        this.uMap = uMap;

        inGameButton = new InGameMenu(true, mState);
        inGameInfo = new InGameMenu(false, mState);

        container.add(ePresent, BorderLayout.WEST);
        container.add(inGameInfo, BorderLayout.SOUTH);
        container.add(inGameButton, BorderLayout.NORTH);
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

    public InGameMenu getInGameInfo() {
        return inGameInfo;
    }

    public InGameMenu getInGameButton() {
        return inGameButton;
    }

}
