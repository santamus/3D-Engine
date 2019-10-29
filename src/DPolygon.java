
    import java.awt.Color;

    public class DPolygon {
        Color color;
        //Координаты точек включаемых в полигон
        double[] x, y, z;
        boolean draw = true;
        double[] CalcPos, newX, newY;
        PolygonObject DrawablePolygon;
        double AvgDist;

        public DPolygon(double[] x, double[] y,  double[] z, Color color)
        {
            this.x = x;
            this.y = y;
            this.z = z;
            this.color = color;

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
                if(Calculator.t < 0)
                    draw = false;
            }

//		calcLighting();

            DrawablePolygon.draw = draw;
            DrawablePolygon.updatePolygon(newX, newY);
            AvgDist = GetDist();
        }



        double GetDist()
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
    }


