/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludigame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author pixel
 */
public class ViewItem implements Drawable {

    private Item item;
    private BufferedImage itemImg;

    public ViewItem(Item item, String imagePath) {
        this.item = item;
        setImage(imagePath);

    }

    @Override
    public void draw(Graphics g) {
       g.drawImage(itemImg,item.getPosX(),item.getPosY(), null);//To change body of generated methods, choose Tools | Templates.
    }

    public void setImage(String imagePath) {
        try {
            this.itemImg = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("Error in some sort of an Item View: " +
            		"Item Image could not be loaded!"+e.getMessage());
        }


    }
    //To change body of generated methods, choose Tools | Templates.
}

