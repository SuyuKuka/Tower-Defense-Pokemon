package graphics;

import java.awt.*;
import javax.swing.*;

import model.MapState;

public class GameScreen extends JPanel {
    private EnnemiPresent ePresent = new EnnemiPresent();
    private TowerPresent tPresent = new TowerPresent();
    private InGamePc inGamePc = new InGamePc();
    private InGameMenu inGameButton;
    private InGameMenu inGameInfo;

    private UpdateMap uMap;
    private GraphicsMaker maker = new GraphicsMaker();

    private JPanel container = maker.imagePanel("ressources/tiles/automne/grass.png");
    private MainGraphics main;

    public GameScreen(MainGraphics main) {
        this.main = main;
        setLayout(new BorderLayout());
        setOpaque(true);

        container.setLayout(new BorderLayout());
        container.setOpaque(false);

    }

    public void setmState(MapState mState) {
        UpdateMap uMap = new UpdateMap(mState, tPresent);
        this.uMap = uMap;

        inGameButton = new InGameMenu(true, mState, this);
        inGameInfo = new InGameMenu(false, mState, this);

        container.add(ePresent, BorderLayout.WEST);
        container.add(inGameInfo, BorderLayout.SOUTH);
        container.add(inGameButton, BorderLayout.NORTH);
        container.add(uMap, BorderLayout.CENTER);
        container.add(inGamePc, BorderLayout.EAST);
        container.add(tPresent, BorderLayout.EAST);
        tPresent.setVisible(false);

        add(container, BorderLayout.CENTER);
    }

    public void changeRightSide(){
        if(InGameMenu.isPc()){
            container.remove(tPresent);
            container.add(inGamePc, BorderLayout.EAST);
            inGamePc.setVisible(true);
            tPresent.setVisible(false);

        }else{
            container.remove(inGamePc);
            container.add(tPresent, BorderLayout.EAST);
            inGamePc.setVisible(false);
            tPresent.setVisible(true);
        }
        repaint();
    }

    public EnnemiPresent getePresent() {
        return ePresent;
    }

    public TowerPresent gettPresent() {
        return tPresent;
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

    public MainGraphics getMain() {
        return main;
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        this.container = container;
    }

    public InGamePc getInGamePc() {
        return inGamePc;
    }
}
