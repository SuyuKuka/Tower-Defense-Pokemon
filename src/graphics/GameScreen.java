package graphics;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.MapState;

public class GameScreen extends JPanel {
    private EnnemiPresent ePresent = new EnnemiPresent();
    private TowerPresent tPresent = new TowerPresent();
    private InGamePc inGamePc = new InGamePc();
    private InGameMenu inGameButton;
    private InGameMenu inGameInfo;
    private UpdateMap uMap;
    private GraphicsMaker maker = new GraphicsMaker();
    private MainGraphics main;

    private JPanel container = maker.imagePanel("ressources/tiles/" + Options.selectedMap + "/grass.png");

    public GameScreen(MainGraphics main) {
        this.main = main;

        setLayout(new BorderLayout());
        setOpaque(true);

        container.setLayout(new BorderLayout());
        container.setOpaque(false);
    }

    public void setmState(MapState mState) {
        this.uMap =  new UpdateMap(mState, tPresent);

        inGameButton = new InGameMenu(true, mState, this);
        inGameInfo = new InGameMenu(false, mState, this);

        container.add(ePresent, BorderLayout.WEST);
        container.add(inGameInfo, BorderLayout.SOUTH);
        container.add(inGameButton, BorderLayout.NORTH);
        container.add(uMap, BorderLayout.CENTER);
        container.add(inGamePc, BorderLayout.EAST);
        container.add(tPresent, BorderLayout.EAST);
        add(container, BorderLayout.CENTER);
    }

    public void changeRightSide() {
        if (InGameMenu.isPc()) {
            container.add(inGamePc, BorderLayout.EAST);
            inGamePc.setVisible(true);
            tPresent.setVisible(false);
        } else {
          tPresent.updatePresent();
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

    public JPanel getContainerScreen() {
        return container;
    }

    public void setContainer(JPanel container) {
        this.container = container;
    }

    public InGamePc getInGamePc() {
        return inGamePc;
    }
}
