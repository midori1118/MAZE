import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Start extends JPanel{
//  Main pole=new Main();

  public void paint(Graphics g){
    super.paintComponent(g);
     Font font= new Font("SansSerif",Font.BOLD,62);
     g.setFont(font);
     g.drawString("迷路ゲーム",21*6,21*6);
     Font font2= new Font("SansSerif",Font.BOLD,22);
     g.setFont(font2);
     g.drawString("簡単:1", 21*8,21*10);
		 g.drawString("普通:2", 21*8,21*12);
		 g.drawString("やや難しい:3", 21*8,21*14);
			g.drawString("難しい:4", 21*8,21*16);


  //   g.setColor(Color.blue);
  //  g.fillOval(100,100,150,150);
  }

  public static void main(String[] args){
  }
}
