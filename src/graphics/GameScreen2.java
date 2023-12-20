package graphics;

import java.awt.*;
import javax.swing.*;

import model.MapState;

public class GameScreen2 extends JPanel{
    EnnemiPresent ePresent = new EnnemiPresent();
    public GameScreen2(MapState mState){
        setLayout(new BorderLayout());
        UpdateMap uMap = new UpdateMap(mState);


        add(ePresent, BorderLayout.WEST);
        add(uMap, BorderLayout.CENTER);

    }

    public EnnemiPresent getePresent() {
        return ePresent;
    }
    
}
