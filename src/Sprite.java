
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    double dx,dy;

    BufferedImage img;
    boolean draw = true;
    double size=5;
    public Sprite(double x,double y, BufferedImage img){
        this.dx=x;
        this.dy=y;

        this.img=img;
    }
    public void drawSprite(Graphics g){

        if(draw)
        {
            g.drawImage(img,(int)(dx-img.getWidth()*size/2),(int)(dy-img.getHeight()*size/2),(int) (img.getWidth()*size),(int) (img.getHeight()*size),null);


        }

    }
    public void updateSprite(double x,double y,double size){
        dx=x;
        dy=y;
        this.size=size;
    }
}
