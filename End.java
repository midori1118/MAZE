import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class End extends JPanel{
  Main pole=new Main();
  public void paint(Graphics g){
    super.paintComponent(g);
     Font font= new Font("SansSerif",Font.BOLD,62);
    g.setFont(font);
    g.drawString("ゴール",21*7,21*6);
    Font font2= new Font("SansSerif",Font.BOLD,22);
    g.setFont(font2);
    g.drawString("歩いた歩数 : "+pole.walkcount, 21*8,21*10);
    g.drawString("タイム = "+pole.hh+":"+pole.ss+":"+pole.mm/10,21*8,21*13);
  //   g.setColor(Color.blue);
  //  g.fillOval(100,100,150,150);
  }
}
