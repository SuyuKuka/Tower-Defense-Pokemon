package graphics;

import java.awt.*;
import javax.swing.*;

import model.Ennemi;
import model.MapState;

public class GameScreen extends JPanel {
    private EnnemiPresent ePresent = new EnnemiPresent();
    private TowerPresent tPresent = new TowerPresent();
    private UpdateMap uMap;

    public GameScreen() {

        setLayout(new BorderLayout());

    }

    public void setmState(MapState mState) {
        UpdateMap uMap = new UpdateMap(mState, tPresent);
        this.uMap = uMap;
        /// add(ePresent, BorderLayout.WEST);
        add(uMap, BorderLayout.CENTER);
        // add(tPresent, BorderLayout.EAST);
    }

    public EnnemiPresent getePresent() {
        return ePresent;
    }

}
