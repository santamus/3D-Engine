
    import java.awt.Color;

    public class DPolygon {
        Color color;
        //Координаты точек включаемых в полигон
        double[] x, y, z;
        boolean draw = true;
        double[] CalcPos, newX, newY;
        PolygonObject DrawablePolygon;
        double AvgDist;
        Vector normal,negaNormal;

        public DPolygon(double[] x, double[] y,  double[] z, Color color)
        {
            this.x = x;
            this.y = y;
            this.z = z;
            this.color = color;

            Vector v1 = new Vector(x[0],y[0],z[0]);
            Vector v2 = new Vector(x[x.length-1],y[y.length-1],z[z.length-1]);
            normal = v1.CrossProduct(v2);
            negaNormal=normal;
            negaNormal.x*=-1;
            negaNormal.y*=-1;
            negaNormal.z*=-1;
            createPolygon();
        }

        void createPolygon()
        {
            DrawablePolygon = new PolygonObject(new double[x.length], new double[x.length],color);
        }

   void updatePolygon()
        {
            newX = new double[x.length];
            newY = new double[x.length];
            draw = true;
            for(int i=0; i<x.length; i++)
            {
                CalcPos = Calculator.CalculatePositionP(Screen.ViewFrom, x[i], y[i], z[i]);
                newX[i] = (Main.ScreenSize.getWidth()/2 - Calculator.CalcFocusPos[0]) + CalcPos[0] ;
                newY[i] = (Main.ScreenSize.getHeight()/2 - Calculator.CalcFocusPos[1]) + CalcPos[1];
                //t<0 означает что полигон за пределами камеры
                if(Calculator.t < 0)
                    draw = false;
            }

//		calcLighting();

            DrawablePolygon.draw = draw;
            DrawablePolygon.updatePolygon(newX, newY);
           AvgDist = GetAvgDist();
        }



        double GetAvgDist()
        {
            double total = 0;
            for(int i=0; i<x.length; i++)
                total += GetDistanceToP(i);
            return total / x.length;
        }

        double GetDistanceToP(int i)
        {
            return Math.sqrt((Screen.ViewFrom[0]-x[i])*(Screen.ViewFrom[0]-x[i]) +
                    (Screen.ViewFrom[1]-y[i])*(Screen.ViewFrom[1]-y[i]) +
                    (Screen.ViewFrom[2]-z[i])*(Screen.ViewFrom[2]-z[i]));
        }

        Vector getNormal(){

            return normal;
        }
        public void setCamLighting(){
            double dist = Math.abs(GetDistanceToP(0)/(25*Screen.power));
            double lighting;
            if (dist>1)
            lighting = 1/dist;
            else lighting = 1;
            DrawablePolygon.lighting=lighting;
        }

    }


