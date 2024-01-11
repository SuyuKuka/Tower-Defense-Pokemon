package graphics;

import control.Control;
import model.MapState;
import model.Player;
import model.Tower;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static javax.swing.JLayeredPane.MODAL_LAYER;

public class Tiles extends JPanel {
    private MapState state;
    private TowerPresent tPresent;
    private GraphicsMaker maker = new GraphicsMaker();
    private Control control = Control.INSTANCE;

    private JPanel gameMap;

    public static ArrayList<Tiles> tilesList =new ArrayList<>();

    public static Tiles INSTANCE=new Tiles();
    private UpdateMap uMap;
    private int cellSize = 32;
    private int mapWidth;
    private int mapHeight;
    private int[] pos;
    private JLabel spriteOnIt;
    private JLabel empty = new JLabel(new ImageIcon("ressources/sprite/nothing.png"));
    private boolean occupied = false;
    private GridBagConstraints constraints = new GridBagConstraints();

    private MouseAdapter listener;

    private Tiles(){

    }



    public Tiles(int[] pos) {

        this.pos = pos;
        tilesList.add(this);
        spriteOnIt = empty;

        setLayout(new GridBagLayout());

        setMinimumSize(new Dimension(cellSize, cellSize));
        setBorder(new EmptyBorder(1, 1, 1, 1));

        add(empty, constraints);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent evt) {
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                if (!occupied) {
                    setBorder(new LineBorder(Color.RED, 1));
                }

            }

            @Override
            public void mouseExited(MouseEvent evt) {
                setBorder(new EmptyBorder(1, 1, 1, 1));
            }

            @Override
            public void mouseClicked(MouseEvent evt) {
                Tower[] team = Player.INSTANCE.getTeam();

                int row = pos[0];
                int col = pos[1];

                if (tPresent.getSelectedPokemon() != null) {
                    Tower pkm = team[tPresent.getSelectedPokemon().getNumero() - 1];
                    if (!state.getTowerPresent().contains(tPresent.getSelectedPokemon().getPokemon()) && pkm.getPrice() < state.getMoney()) {
                        int[] towerOnMapPos = new int[] {row, col};
                        control.setTowerOnMap(towerOnMapPos, pkm, state);

                        JLabel sprite = new JLabel(new ImageIcon(pkm.getSprite()));
                        addSpriteOnTiles(sprite, constraints);

                        tPresent.getSelectedPokemon().updateManager(pkm);

                        JPanel towerRange = uMap.makeTowerRange(pkm, row, col);
                        towerRange.setVisible(false);
                        uMap.add(towerRange, MODAL_LAYER);

                        giveTiles(pkm); // CONTROL
                        control.giveRangePanel(pkm, towerRange);
                    }
                    if (state.getTowerPresent().contains(tPresent.getSelectedPokemon().getPokemon())) {
                        tPresent.getSelectedPokemon().setBorder(BorderFactory.createEmptyBorder());
                        tPresent.getSelectedPokemon().getPriceLabel().setText("Nv : " + String.valueOf(tPresent.getSelectedPokemon().getPokemon().getLevel()));
                    }
                }

            }
        });
    }


    public boolean addSpriteOnTiles(JLabel jlabel, GridBagConstraints gridBagConstraints) {
        if (spriteOnIt == empty) {
            remove(spriteOnIt);
            spriteOnIt = jlabel;
            this.add(jlabel, gridBagConstraints);
            jlabel.getParent().revalidate();
            return true;
        } else {
            return false;
        }
    }

    private void giveTiles(Tower pkm) {
        listener=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) { // FRAGILE
                if (SwingUtilities.isLeftMouseButton(evt)) {
                    if (tPresent.getComponentCount() < 2 && !occupied) {

                        tPresent.getComponent(0).setVisible(false);
                        tPresent.add(pkm.getGUI(), BorderLayout.SOUTH);

                        pkm.getGUI().setVisible(true);
                        pkm.getRangePanel().setVisible(true);

                        setBackground(Color.BLUE);
                        setOpaque(true);
                    }
                }
                if (SwingUtilities.isRightMouseButton(evt)) {

                    tPresent.remove(pkm.getGUI());
                    tPresent.getComponent(0).setVisible(true);

                    pkm.getGUI().setVisible(false);
                    pkm.getRangePanel().setVisible(false);

                    setOpaque(false);
                }
            }
        };

        this.addMouseListener(listener);
        control.giveTiles(pkm, this);
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void resetTile() {
        this.removeAll();
        spriteOnIt = empty;
        this.removeMouseListener(listener);
    }

    public void replaceSprite(String spritePath) {
        JLabel jlabel = new JLabel(new ImageIcon(spritePath));
        remove(spriteOnIt);
        spriteOnIt = jlabel;
        this.add(jlabel, new GridBagConstraints());
        jlabel.getParent().revalidate();
    }

    public int[] getPos() {
        return pos;
    }
    public static ArrayList<Tiles> getTilesList() {
        return tilesList;
    }

    public JLabel getSpriteOnIt() {
        return spriteOnIt;
    }

    public void settPresent(TowerPresent tPresent) {
        this.tPresent = tPresent;
    }

    public void setState(MapState state) {
        this.state = state;
    }

    public void setuMap(UpdateMap uMap) {
        this.uMap = uMap;
    }
}