package projava;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SampleForm2 {

    public static void main(String[] args){
        var frame = new JFrame("drawing");
        frame.setSize(600, 400);
//        画面を閉じた場合にプログラムを終了させるための処理
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var label = new JLabel("test");
        frame.add(label);

        var image = new BufferedImage(600,400,BufferedImage.TYPE_INT_BGR);
        label.setIcon(new ImageIcon(image));

        var g = image.createGraphics();
        g.drawLine(0,0,600,400);
        g.setColor(Color.RED);
        g.fillOval(300,200,150,100);

        g.drawLine(600,0,0,400);
        g.setColor(Color.BLUE);
        g.fillOval(100,100,50,50);

        frame.setVisible(true);

    }
}
