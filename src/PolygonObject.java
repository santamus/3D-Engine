import java.awt.*;

public class PolygonObject {
	//2d полигон из swing для отрисовки
	Polygon P;
	Color c;

	boolean draw = true;
	//уровень светимости от 0 до 1
	double lighting = 0;
	
	public PolygonObject(double[] x, double[] y, Color c)
	{
		P = new Polygon();
		for(int i = 0; i<x.length; i++)
			P.addPoint((int)x[i], (int)y[i]);
		this.c = c;

	}
	
	void updatePolygon(double[] x, double[] y)
	{
		//сброс настроек полигона и изменение на на входные данные
		P.reset();
		for(int i = 0; i<x.length; i++)
		{
			P.xpoints[i] = (int) x[i];
			P.ypoints[i] = (int) y[i];
			//кол-во точек
			P.npoints = x.length;
		}
	}
	
	void drawPolygon(Graphics g)
	{

		if(draw)
		{

			g.setColor(new Color((int)(c.getRed() * lighting), (int)(c.getGreen() * lighting), (int)(c.getBlue() * lighting)));

			    //заполнение цветом
				g.fillPolygon(P);

			    //обводка
				g.setColor(new Color(0, 0, 0));
				g.drawPolygon(P);



		}
	}


	public void setLighting(double[] lighting) {

		this.lighting=0;
		for (int i =0;i<lighting.length;i++){
			this.lighting+=lighting[i];

		}
		if(this.lighting>1) this.lighting=1;
	}
	public void addLighting(double lighting) {


			this.lighting+=lighting;


		if(this.lighting>1) this.lighting=1;
	}

}
