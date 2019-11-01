public class LightPoint {
    double x,y,z;
    LightPoint(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    Vector vectorToPoint(double xp,double yp,double zp){
        return new Vector(x,y,z,xp,yp,zp);
    }

    double distanceToPoly(DPolygon p){

        return Math.sqrt((p.x[0]-x)*(p.x[0]-x) +
                (p.y[0]-y)*(p.y[0]-y) +
                (p.z[0]-z)*(p.z[0]-z));

    }
}
