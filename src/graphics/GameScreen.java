package graphics;

import java.awt.*;
import javax.swing.*;

import model.MapState;

public class GameScreen extends JPanel {
    EnnemiPresent ePresent = new EnnemiPresent();
    TowerPresent tPresent = new TowerPresent();

    public GameScreen(MapState mState) {
        setBackground(new Color(111, 160, 83));
        setLayout(new BorderLayout());
        UpdateMap uMap = new UpdateMap(mState, tPresent);

        // add(ePresent, BorderLayout.WEST);
        add(uMap, BorderLayout.CENTER);
        add(tPresent, BorderLayout.EAST);

    }

    public EnnemiPresent getePresent() {
        return ePresent;
    }
}
