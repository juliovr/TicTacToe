/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotos;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 *
 * @author julio
 */
public class ImagenFondo implements Border{

    public BufferedImage back;
    public String nombreFoto;
    
    public ImagenFondo(String nombreFoto){
        try {
            URL imagePath = new URL(getClass().getResource("/fotos/" + nombreFoto).toString());
            back = ImageIO.read(imagePath);
            this.nombreFoto = nombreFoto;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawImage(back, (x + (width - back.getWidth())/2),(y + (height - back.getHeight())/2), null);
    }
 
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }
 
    @Override
    public boolean isBorderOpaque() {
        return false;
    }
    
}
