package controllers;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * Permet d'afficher la miniature de l'image quand on lance le programme
 * @author Asus
 *
 */
public class ImgCase extends JPanel {
    
    private Image image;
    
    public void changePicture(File file){
        try {
        	image = ImageIO.read(file);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);   
    }
}
