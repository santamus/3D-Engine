package visualEngine;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main
{
    static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static JTextField TF;

    public static void main(String[] args) throws IOException {
        JFrame F = new JFrame();
        Screen ScreenObject = new Screen();
        F.add(ScreenObject);
        F.setUndecorated(true);
        F.setSize(ScreenSize);
        F.setVisible(true);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
