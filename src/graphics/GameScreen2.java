package graphics;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import model.MapState;

public class GameScreen2 extends JPanel {
    EnnemiPresent ePresent = new EnnemiPresent();
    TowerPresent tPresent = new TowerPresent();

    public GameScreen2(MapState mState) {

        setLayout(new BorderLayout());
        UpdateMap uMap = new UpdateMap(mState, tPresent);

        add(uMap, BorderLayout.CENTER);
        add(tPresent, BorderLayout.EAST);

    }

    public EnnemiPresent getePresent() {
        return ePresent;
    }
}
