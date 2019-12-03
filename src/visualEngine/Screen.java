package visualEngine;

import physic.FixedObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Screen extends JPanel implements KeyListener,  MouseMotionListener, MouseWheelListener {
    static ArrayList<DPolygon> DPolygons = new ArrayList<DPolygon>();
    static ArrayList<DSprite> dSprites = new ArrayList<>();
    static ArrayList<LightPoint> lightPoints = new ArrayList<>();
    static ArrayList<FixedObject> objects = new ArrayList<>();

// нажатые клавиши
    boolean[] Keys = new boolean[4];
    boolean camLight = false;
    private int[] newOrder;

    public Screen() {
     //   this.setBackground(Color.PINK);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);

        setFocusable(true);

        double[] values1 = new double[50];
        double[] values2 = new double[values1.length];
        double Size = 5;
      //  Random r = new Random();

//        lightPoints.add(new visualEngine.LightPoint(0,0,5));
      lightPoints.add(new LightPoint(75,75,5));

        for (int y = 0; y < values1.length/2; y+=1) {
            for (int x = 0; x < values1.length / 2; x++) {
                Screen.DPolygons.add(new DPolygon(new double[]{(Size * x), (Size * x), Size + (Size * x)}, new double[]{(Size * (y + 1)), Size + (Size * (y + 1)), Size + (Size * (y + 1))}, new double[]{values1[x], values2[x], values2[x + 1]}, Color.blue));
                Screen.DPolygons.add(new DPolygon(new double[]{(Size * x), Size + (Size * x), Size + (Size * x)}, new double[]{(Size * (y + 1)), Size + (Size * (y + 1)), (Size * (y + 1))}, new double[]{values1[x], values2[x + 1], values1[x + 1]}, Color.blue));
//                visualEngine.Screen.DPolygons.add(new visualEngine.DPolygon(new double[]{(Size * x), (Size * x), Size + (Size * x)}, new double[]{(Size * (y + 1)), Size + (Size * (y + 1)), Size + (Size * (y + 1))}, new double[]{values1[x]-0.01, values2[x]-0.01, values2[x + 1]-0.01}, Color.red));
//                visualEngine.Screen.DPolygons.add(new visualEngine.DPolygon(new double[]{(Size * x), Size + (Size * x), Size + (Size * x)}, new double[]{(Size * (y + 1)), Size + (Size * (y + 1)), (Size * (y + 1))}, new double[]{values1[x]-0.01, values2[x + 1]-0.01, values1[x + 1]-0.01}, Color.red));
//                visualEngine.Screen.DPolygons.add(new visualEngine.DPolygon(new double[]{(Size * x), (Size * x), Size + (Size * x)}, new double[]{(Size * (y + 1)), Size + (Size * (y + 1)), Size + (Size * (y + 1))}, new double[]{values1[x]+10, values2[x]+10, values2[x + 1]+10}, Color.blue));
//                visualEngine.Screen.DPolygons.add(new visualEngine.DPolygon(new double[]{(Size * x), Size + (Size * x), Size + (Size * x)}, new double[]{(Size * (y + 1)), Size + (Size * (y + 1)), (Size * (y + 1))}, new double[]{values1[x]+10, values2[x + 1]+10, values1[x + 1]+10}, Color.blue));
            } }


        try {
        //    dSprites.add(new DSprite(20,20,20, ImageIO.read(new File("src/s.png"))));
            FixedObject obj = new FixedObject(ImageIO.read(new File("src/s.png")),25,20,20);
            obj.addPoint(0,0,20);
//            obj.addPoint(50,50,20);
//            obj.addPoint(50,25,20);
   //         objects.add(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //cursor off
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
        Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, new Point(0,0), "InvisibleCursor");
        setCursor(invisibleCursor);


    }
//Ближайшие полигоны рисуются последними
    void setOrder()
    {

        double[] k = new double[DPolygons.size()];
        newOrder = new int[DPolygons.size()];

        for(int i=0; i<DPolygons.size(); i++)
        {
            k[i] = DPolygons.get(i).AvgDist;
            newOrder[i] = i;
        }

        double temp;
        int tempr;
        for (int a = 0; a < k.length-1; a++)
            for (int b = 0; b < k.length-1; b++)
                if(k[b] < k[b + 1])
                {
                    temp = k[b];
                    tempr = newOrder[b];
                    newOrder[b] = newOrder[b + 1];
                    k[b] = k[b + 1];

                    newOrder[b + 1] = tempr;
                    k[b + 1] = temp;
                }
    }
    
    static double[] ViewFrom = new double[] { 20, 20, 20},
            ViewTo = new double[] {0, 0, 1};

    //FPS
    double drawFPS = 0, MaxFPS = 1000, SleepTime = 1000.0/MaxFPS, LastRefresh = 0, StartTime = System.currentTimeMillis(), LastFPSCheck = 0, Checks = 0;
    //aim
    double aimSight = 4;

    static double staticPower=0.5,camPower=0.5;

    double movementSpeed=0.1;

    //mouse config
    static double mouseX = 0, mouseY = 0,vertLook = -0.9, horLook = 0,horRotSpeed = 900, vertRotSpeed = 2200;

    @Override
    public void paintComponent(Graphics g)
    {


        //Clear screen and draw background color
        g.setColor(Color.gray);
        g.fillRect(0, 0, (int)Main.ScreenSize.getWidth(), (int)Main.ScreenSize.getHeight());
        Calculator.SetPrederterminedInfo();
        setOrder();



        for(int i = 0; i < DPolygons.size(); i++){
            for(int j = 0; j < lightPoints.size(); j++){
                DPolygons.get(newOrder[i]).setLight();}
            if (camLight)
                DPolygons.get(newOrder[i]).setCamLighting();
            DPolygons.get(newOrder[i]).DrawablePolygon.drawPolygon(g);
        DPolygons.get(newOrder[i]).updatePolygon();}
//        for (int i =0;i<dSprites.size();i++){
//            dSprites.get(i).drawableSprite.drawSprite(g);
//            dSprites.get(i).updateSprite();
//        }

        for (int i =0;i<objects.size();i++){
            objects.get(i).sprite.drawableSprite.drawSprite(g);
            objects.get(i).update();
        }


//        for(int i = 0; i < DPolygons.size(); i++)
//            DPolygons.get(i).updatePolygon();




        CameraMovement();
        drawMouseAim(g);

        //FPS display
        g.drawString("FPS: " + (int)drawFPS , 40, 40);
        g.drawString("x:"+ViewFrom[0]+"  x:"+ViewTo[0],40,60);
        g.drawString("y:"+ViewFrom[1]+"  y:"+ViewTo[1],40,80);
        g.drawString("z:"+ViewFrom[2]+"  z:"+ViewTo[2],40,100);

//		repaintTime = System.currentTimeMillis() - repaintTime;
//		System.out.println(repaintTime);
        SleepAndRefresh();
    }
    void SleepAndRefresh()
    {
        long timeSLU = (long) (System.currentTimeMillis() - LastRefresh);
        //fps check
        Checks ++;
        if(Checks >= 15)
        {
            drawFPS = 1000*Checks/((System.currentTimeMillis() - LastFPSCheck));
            LastFPSCheck = System.currentTimeMillis();
            Checks = 0;
        }

        //fps lock
        if(timeSLU < 1000.0/MaxFPS)
        {
            try {
                Thread.sleep((long) (1000.0/MaxFPS - timeSLU));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        LastRefresh = System.currentTimeMillis();

        repaint();
    }

    void CameraMovement()
    {
        Vector ViewVector = new Vector(ViewTo[0] - ViewFrom[0], ViewTo[1] - ViewFrom[1], ViewTo[2] - ViewFrom[2]);
        double xMove = 0, yMove = 0, zMove = 0;
        Vector VerticalVector = new Vector (0, 0, 1);
        Vector SideViewVector = ViewVector.CrossProduct(VerticalVector);

        if(Keys[0])
        {
            xMove += ViewVector.x ;
            yMove += ViewVector.y ;
            zMove += ViewVector.z ;
        }

        if(Keys[2])
        {
            xMove -= ViewVector.x ;
            yMove -= ViewVector.y ;
            zMove -= ViewVector.z ;
        }

        if(Keys[1])
        {
            xMove += SideViewVector.x ;
            yMove += SideViewVector.y ;
            zMove += SideViewVector.z ;
        }

        if(Keys[3])
        {
            xMove -= SideViewVector.x ;
            yMove -= SideViewVector.y ;
            zMove -= SideViewVector.z ;
        }

        Vector MoveVector = new Vector(xMove, yMove, zMove);
        MoveTo(ViewFrom[0] + MoveVector.x * movementSpeed, ViewFrom[1] + MoveVector.y * movementSpeed, ViewFrom[2] + MoveVector.z * movementSpeed);
    }

    void drawMouseAim(Graphics g)
    {
        g.setColor(Color.black);
        g.drawLine((int)(Main.ScreenSize.getWidth()/2 - aimSight), (int)(Main.ScreenSize.getHeight()/2), (int)(Main.ScreenSize.getWidth()/2 + aimSight), (int)(Main.ScreenSize.getHeight()/2));
        g.drawLine((int)(Main.ScreenSize.getWidth()/2), (int)(Main.ScreenSize.getHeight()/2 - aimSight), (int)(Main.ScreenSize.getWidth()/2), (int)(Main.ScreenSize.getHeight()/2 + aimSight));
    }


    void MoveTo(double x, double y, double z)
    {
        ViewFrom[0] = x;
        ViewFrom[1] = y;
        ViewFrom[2] = z;
        double r = Math.sqrt(1 - (vertLook * vertLook));
        ViewTo[0] = ViewFrom[0] + r * Math.cos(horLook);
        ViewTo[1] = ViewFrom[1] + r * Math.sin(horLook);
        ViewTo[2] = ViewFrom[2] + vertLook;
    }


















    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W)
            Keys[0] = true;
        if(e.getKeyCode() == KeyEvent.VK_A)
            Keys[1] = true;
        if(e.getKeyCode() == KeyEvent.VK_S)
            Keys[2] = true;
        if(e.getKeyCode() == KeyEvent.VK_D)
            Keys[3] = true;
        if(e.getKeyCode() == KeyEvent.VK_E)
            camLight=!camLight;
        if(e.getKeyCode() == KeyEvent.VK_F){
            try {
                ArrayList<double[]> arr = new ArrayList<>();
                arr.add(new double[]{ViewFrom[0],ViewFrom[1],ViewFrom[2]});
                arr.add(new double[]{1000*(ViewTo[0]-ViewFrom[0]),1000*(ViewTo[1]-ViewFrom[1]),1000*(ViewTo[2]-ViewFrom[2])});
               // FixedObject obj = new FixedObject(ImageIO.read(new File("src/s.png")),arr);
                objects.add(new FixedObject(ImageIO.read(new File("src/s.png")),arr));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W)
            Keys[0] = false;
        if(e.getKeyCode() == KeyEvent.VK_A)
            Keys[1] = false;
        if(e.getKeyCode() == KeyEvent.VK_S)
            Keys[2] = false;
        if(e.getKeyCode() == KeyEvent.VK_D)
            Keys[3] = false;


    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        double rot = e.getWheelRotation();
        if (camLight) {
            if (rot < 0) {

                if (camPower <= 1)
                    camPower -= rot / 20;
            } else {

                if (camPower > 0.02)
                    camPower -= rot / 20;
            }
        } else {
            if (rot < 0) {

                if (staticPower <= 1)
                    staticPower -= rot / 20;
            } else {

                if (staticPower > 0.02)
                    staticPower -= rot / 20;
            }

        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseMovement(e.getX(), e.getY());
        mouseX = e.getX();
        mouseY = e.getY();
        CenterMouse();
    }
    void mouseMovement(double NewMouseX, double NewMouseY)
    {
        //смещение от центра
        double difX = (NewMouseX - Main.ScreenSize.getWidth()/2);
        double difY = (NewMouseY - Main.ScreenSize.getHeight()/2);

        //выравнивающий плавность перерасчет
        difY *= 6 - Math.abs(vertLook) * 5;
        vertLook -= difY  / vertRotSpeed;
        horLook += difX / horRotSpeed;

        if(vertLook>0.999)
            vertLook = 0.999;

        if(vertLook<-0.999)
            vertLook = -0.999;

        double r = Math.sqrt(1 - (vertLook * vertLook));
        ViewTo[0] = ViewFrom[0] + r * Math.cos(horLook);
        ViewTo[1] = ViewFrom[1] + r * Math.sin(horLook);
        ViewTo[2] = ViewFrom[2] + vertLook;
    }




    void CenterMouse()
    {
        try {
           Robot r = new Robot();
            r.mouseMove((int)Main.ScreenSize.getWidth()/2, (int)Main.ScreenSize.getHeight()/2);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }



}