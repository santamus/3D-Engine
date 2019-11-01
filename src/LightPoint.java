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
    double calcAngle(Vector v, Vector normal){
        return Math.toDegrees(Math.acos((v.x*normal.x+v.y*normal.y+v.z*normal.z)/Math.sqrt((v.x*v.x+v.y*v.y+v.z*v.z)*(normal.x*normal.x+normal.y*normal.y+normal.z*normal.z))));

    }
}
