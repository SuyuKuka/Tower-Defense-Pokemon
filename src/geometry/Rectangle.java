package geometry;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class Rectangle extends JPanel implements MouseInputListener {
    private boolean hasMove;
    private double xClick;
    private double yClick;
    private int numero;
    private int arcWidth = 30;
    private int arcHeight = 30;

    public Rectangle(int numero) {//faudra surement mettre en argument la tour
        setLayout(null); 
        setOpaque(false);
        if (numero <= 6) {
            setForeground(new Color(0, 120, 0));
        } else {
            setForeground(Color.ORANGE);
        }
        this.numero = numero;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getForeground());
        g2d.fill(roundedRectangle);
		g2d.setColor(Color.BLACK);
        g2d.draw(roundedRectangle);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		//Affiche les infos 
		if(!hasMove) {
			System.out.println("Rectangle " + numero + " - entrée mouvement");
			xClick=e.getX();
			yClick=e.getY();
			hasMove = true;
		}else {
			System.out.println("Rectangle " + numero + " - sortie mouvement");
			hasMove = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// System.out.println("Released");
		//Pose a ces coordonnées la
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int newX = (int) (getLocation().getX() + e.getX() - xClick - getWidth() / 2);
		int newY = (int) (getLocation().getY() + e.getY() - yClick - getHeight() / 2);
        setLocation(newX, newY);
        repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
