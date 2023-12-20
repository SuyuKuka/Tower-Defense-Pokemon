package graphics;

import java.awt.*;
import javax.swing.*;

import model.MapState;

public class GameScreen2 extends JPanel{
    EnnemiPresent ePresent = new EnnemiPresent();
    public GameScreen2(MapState mState){
        setBackground( new Color(111, 160, 83));
        setLayout(new BorderLayout());
        UpdateMap uMap = new UpdateMap(mState);
        TowerPresent tPresent = new TowerPresent();


        add(ePresent, BorderLayout.WEST);
        add(uMap, BorderLayout.CENTER);
        add(tPresent, BorderLayout.EAST);
    }

    public EnnemiPresent getePresent() {
        return ePresent;
    }
    
}
