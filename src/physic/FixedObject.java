package physic;

import visualEngine.DSprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FixedObject {
    public DSprite sprite;
    ArrayList<double[]> points = new ArrayList<>();
    double[] curPoint,nextPoint;
    int npIndex;
    double collizionRadius = 3;
    double speed=2;
    public FixedObject(BufferedImage img,ArrayList<double[]> p){
        sprite = new DSprite(p.get(0)[0],p.get(0)[1],p.get(0)[2],img);
        points = p;
        curPoint=points.get(0);
        nextPoint=points.get(1);
        npIndex=1;
    }
    public FixedObject(BufferedImage img,double x, double y, double z){
        sprite = new DSprite(x,y,z,img);
        double[] point = {x,y,z};
        points.add(point);
        curPoint=points.get(0);
        if (points.size()==2) npIndex=1;
    }
    public void addPoint(double x,double y,double z){
        points.add(new double[]{x,y,z});
        if (points.size()==2){ npIndex=1;
        nextPoint=points.get(npIndex);}
    }
    public void update(){
        if (points.size()!=1){
          //  System.out.println(curPoint[0]+" "+nextPoint[0]);
           // double[] newCurPoint = {};
            double a = nextPoint[0]-curPoint[0];
            double dx;
            if (a==0) dx = 0; else dx = (nextPoint[0]-curPoint[0])/1000;

            a = nextPoint[1]-curPoint[1];
            double dy;
            if (a==0) dy=0; else dy = (nextPoint[1]-curPoint[1])/1000;

            a = nextPoint[2]-curPoint[2];
            double dz;
            if (a==0) dz=0; else dz = (nextPoint[2]-curPoint[2])/1000;

            sprite.move(speed*(dx),speed*(dy),speed*(dz));

//            if ((Math.abs(sprite.x-nextPoint[0])<0.01))
//            System.out.println((sprite.x-nextPoint[0])+" "+(sprite.y-nextPoint[1]));
            if ((Math.abs(sprite.x-nextPoint[0])<0.01)&&(Math.abs(sprite.y-nextPoint[1])<0.01)&&(Math.abs(sprite.z-nextPoint[2])<0.01)){
         //       System.out.println("THERE");
                npIndex++;

            if(npIndex==points.size()) npIndex=0;
            curPoint=nextPoint;
            nextPoint=points.get(npIndex); }


        }

        sprite.updateSprite();
    }

    public boolean  checkCollision(double x, double y,double z){
        return ((sprite.x-collizionRadius)<x&&(sprite.x+collizionRadius)>x
                &&(sprite.y-collizionRadius)<y&&(sprite.y+collizionRadius)>y
                &&(sprite.z-collizionRadius)<z&&(sprite.z+collizionRadius)>z);
    }
}
