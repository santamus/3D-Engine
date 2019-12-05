package visualEngine;

import java.awt.image.BufferedImage;

public class DSprite {
 public   double x,y,z;

    double[] pos;
    boolean draw = true;
    Sprite drawableSprite;
    BufferedImage img;
    public DSprite(double x, double y,  double z,BufferedImage img)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.img=img;
        createSprite();
    }

    void createSprite()
    {
        drawableSprite = new Sprite(x,y,img);
    }

   public void move(double x, double y,double z){
       this.x+=x;
       this.y+=y;
       this.z+=z;
    }
    public void updateSprite(){
        draw = true;
        pos = Calculator.CalculatePositionP(Screen.ViewFrom, x, y, z);
        double  newX = (Main.ScreenSize.getWidth()/2 - Calculator.CalcFocusPos[0]) + pos[0] ;
        double  newY = (Main.ScreenSize.getHeight()/2 - Calculator.CalcFocusPos[1]) + pos[1];
        if(Calculator.t < 0)
            draw = false;
        drawableSprite.draw=draw;
        drawableSprite.updateSprite(newX,newY,calcSize());
    }

    double calcSize(){
        double dist = Math.sqrt((Screen.ViewFrom[0]-x)*(Screen.ViewFrom[0]-x) +
                (Screen.ViewFrom[1]-y)*(Screen.ViewFrom[1]-y) +
                (Screen.ViewFrom[2]-z)*(Screen.ViewFrom[2]-z));
        if (dist==0) dist=0.0001;
        return  10/dist;
    }

}
