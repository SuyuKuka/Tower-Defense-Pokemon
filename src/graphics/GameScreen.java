package graphics;

import java.awt.*;
import javax.swing.*;

import model.MapState;

import javax.swing.border.LineBorder;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameScreen extends JPanel {
    private EnnemiPresent ePresent = new EnnemiPresent();
    private TowerPresent tPresent = new TowerPresent();
    private UpdateMap uMap;
    private GraphicsMaker maker = new GraphicsMaker();

    private static JPanel container = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                for (int i = 0; i < getSize().getWidth() / 400; i++) {
                    for (int j = 0; j < getSize().getHeight() / 400; j++) {

                        g.drawImage(ImageIO.read(new File("ressources/tiles/grasstiles.png")), i * 400, j * 400, 400,
                                400,
                                this);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    public GameScreen() {

        setLayout(new BorderLayout());

        container.setLayout(new BorderLayout());
        container.setOpaque(false);
        setOpaque(true);

    }

    public void setmState(MapState mState) {
        UpdateMap uMap = new UpdateMap(mState, tPresent);
        this.uMap = uMap;

        JPanel borderbot = maker.imagePanel("ressources/background/banner.png");
        borderbot.setPreferredSize(new Dimension(0, 70));

        JPanel bordertop = maker.imagePanel("ressources/background/banner.png");
        bordertop.setPreferredSize(new Dimension(0, 70));

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

}
