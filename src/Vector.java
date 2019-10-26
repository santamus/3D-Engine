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


