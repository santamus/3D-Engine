public class Vector {
    double x, y, z;
    public Vector(double x, double y, double z)
    {
        //Длина вектора
        double Length = Math.sqrt(x*x + y*y + z*z);

        //Нормализация
        if(Length>0)
        {
            this.x = x/Length;
            this.y = y/Length;
            this.z = z/Length;
        }

    }
    //Задание 2 точками
    public Vector(double x1, double y1, double z1,double x2, double y2, double z2)
    {
        x=x2-x1;
        y=y2-y1;
        z=z2-z1;
        double Length = Math.sqrt(x*x + y*y + z*z);

        if(Length>0)
        {
            this.x = x/Length;
            this.y = y/Length;
            this.z = z/Length;
        }

    }
//Векторное произведение

    Vector CrossProduct(Vector V)
    {
        Vector CrossVector = new Vector(
                y * V.z - z * V.y,
                z * V.x - x * V.z,
                x * V.y - y * V.x);
        return CrossVector;
    }
}


