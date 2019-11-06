package models;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Permet d'afficher l'image quand on clique sur une image
 * @author Asus
 *
 */
public class PanelPictures extends JPanel{
	private BufferedImage bi;
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(bi, 0,0,this.getWidth(),this.getHeight(),null);
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}

	public BufferedImage getBi() {
		return bi;
	}
}
