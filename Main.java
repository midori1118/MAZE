/*  棒倒し
1.迷路全体を構成する２次元配列を、幅高さ５以上の奇数で生成。
2.迷路の外周を壁とし、それ以外を通路とする。
3.外周の内側に基準となる壁(棒)を1セルおき(x, y ともに偶数の座標)に配置します。
4.内側の壁(棒)を走査し、ランダムな方向に倒して壁とします。(ただし以下に当てはまる方向以外に倒します。)
    ・1行目の内側の壁以外では上方向に倒してはいけない。
    ・すでに棒が倒され壁になっている場合、その方向には倒してはいけない。
*/

import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements ActionListener{

  static int WIDTH=51;  //迷路の横幅
  static int LENGTH=51; //迷路の縦幅
  static int[][] MAP; //マップ
  static int[] dircheck={0,0,0,0}; //進める方向の確認
  static Main maze=new Main();
  static int agentX=1;
  static int agentY=1;
  static JFrame fr=new JFrame();
  static Main panel=new Main();
  static Timer timer = new Timer(1,panel);
  static boolean paintflag=false;
  static int walkcount=0;
  static int level=0;
  static int ss=0;
  static int hh=0;
  static int mm=0;
  String[] levelarray={"簡単","普通","やや難しい","難しい"};
  String levelprint;
  /*マップをターミナルに表示する*/
  void PrintMap(int[][] maze){

    for(int i=0;i<LENGTH;i++){
      for(int j=0;j<WIDTH;j++){
        System.out.print(maze[i][j]);
      }
        System.out.println();
    }
  }

  int ChooseDirection(int line,int x,int y){
    Random rnd = new Random();
    int dir;

    while(true){
      dir=rnd.nextInt(4);
      if(line==0){
        if(dir==0)break;
        if(dir==1&&MAP[x][y+1]==0)break;
        if(dir==2&&MAP[x+1][y]==0)break;
        if(dir==3&&MAP[x][y-1]==0)break;
      }else{
        if(dir==0&&MAP[x-1][y]==0)break;
        if(dir==1&&MAP[x][y+1]==0)break;
        if(dir==2&&MAP[x+1][y]==0)break;
        if(dir==3&&MAP[x][y-1]==0)break;
      }
    }
    return dir;
  }

  /*0=上、1=右、2=下、3=左
    座標を受け取ってそこから穴を掘っていく
  */
  void DigMap(){
    int dir;
    int x=2,y=2;
    int line=0;

    while(x<LENGTH-3||y<WIDTH-3){
      dir=ChooseDirection(line,x,y);
      line=1;
      System.out.println("--------");
      System.out.println("dir"+dir);
      if(dir==0)MAP[x-1][y]=1;
      if(dir==1)MAP[x][y+1]=1;
      if(dir==2)MAP[x+1][y]=1;
      if(dir==3)MAP[x][y-1]=1;
      System.out.println("x="+x+"y="+y);
      if(y==WIDTH-3){
        y=0;
        x=x+2;
      }
      y=y+2;
    }

  }

  //イベント(タイマーによる呼び出し)が発生したときの処理
  public void actionPerformed(ActionEvent e){
    repaint();
    mm+=2.7;
    if(mm>=1000){
      mm=0;
      ss+=1;
    }
    if(ss>=60){
      ss=0;
      hh+=1;
    }
  }

  class Keyboard_input implements KeyListener
  {
    public void keyPressed(KeyEvent e)
   {

     if(level==0){
       switch(e.getKeyCode()) {
         case KeyEvent.VK_1:
         LENGTH=21;
         WIDTH=21;
         panel.init();
         System.out.println("1が押されました");
         level=1;
         paintflag=true;
         levelprint=levelarray[0];
         panel.DrawMap();
         break;
         case KeyEvent.VK_2:
         LENGTH=31;
         WIDTH=31;
         panel.init();
         System.out.println("2が押されました");
         paintflag=true;
         levelprint=levelarray[1];
         level=1;
         panel.DrawMap();
         break;
         case KeyEvent.VK_3:
         LENGTH=41;
         WIDTH=41;
         panel.init();
         System.out.println("3が押されました");
         paintflag=true;
         levelprint=levelarray[2];
         level=1;
         panel.DrawMap();
         break;
         case KeyEvent.VK_4:
         panel.init();
         System.out.println("4が押されました");
         levelprint=levelarray[3];
         level=1;
         paintflag=true;
         panel.DrawMap();
         break;
        }
        timer.start();
      }

      if(level==1){
      switch(e.getKeyCode()) {
   		case KeyEvent.VK_UP:
   			System.out.println("上が押されました");
             if(MAP[agentX-1][agentY]==0){
             MAP[agentX][agentY]=0;
             agentX=agentX-1;
                walkcount++;
             }
   			break;
   		case KeyEvent.VK_DOWN:
             if(MAP[agentX+1][agentY]==0){
             MAP[agentX][agentY]=0;
             agentX=agentX+1;
                walkcount++;
             }
   			System.out.println("下が押されました");
   			break;
   		case KeyEvent.VK_RIGHT:
   		    if(MAP[agentX][agentY+1]==0){
             MAP[agentX][agentY]=0;
             agentY=agentY+1;
                walkcount++;
             }
   			System.out.println("右が押されました");
   			break;
       case KeyEvent.VK_LEFT:
            if(MAP[agentX][agentY-1]==0){
             MAP[agentX][agentY]=0;
             agentY=agentY-1;
                walkcount++;
             }
     		System.out.println("左が押されました");
     		break;
      }
      System.out.println(walkcount);
    }
     MAP[agentX][agentY]=3;
  //  repaint();

   }

   public void keyReleased(KeyEvent event)
   {

   }

   public void keyTyped(KeyEvent e)
   {


   }

}

  public void paint(Graphics g){
    super.paintComponent(g);
    for(int i=0;i<LENGTH;i++){
      for(int j=0;j<WIDTH;j++){
        if(MAP[i][j]==1){
          g.setColor(Color.black);
          g.fillRect(j*(int)510/LENGTH-1,i*(int)540/WIDTH-1,(int)510/LENGTH+1,(int)540/WIDTH+1);
        }else if(MAP[i][j]==3){
          g.setColor(Color.green);
          g.fillRect(j*(int)510/LENGTH,i*(int)540/WIDTH,(int)510/LENGTH+1,(int)540/WIDTH+1);


        }else if(i==LENGTH-2&&j==WIDTH-2&&level==1){
          g.setColor(Color.red);
          g.fillRect(j*(int)510/LENGTH,i*(int)540/WIDTH,(int)510/LENGTH+1,(int)540/WIDTH+1);


        }else{
          g.setColor(Color.white);
          g.fillRect(j*(int)510/LENGTH,i*(int)540/WIDTH,(int)510/LENGTH-1,(int)540/WIDTH-1);

        }

        if(MAP[i][j]==3&&i==LENGTH-2&&j==WIDTH-2){
          DrawEnd();
        }
      }
    }
    Font font2= new Font("SansSerif",Font.BOLD,22);
    g.setFont(font2);
    g.drawString("難易度 : "+levelprint+"      TIME = "+hh + ":"+ss+ ":"+mm/10,80,565);

  }

public void Drawstart(){
  Start panel1=new Start();
  panel1.setPreferredSize(new Dimension(510,540));
  panel1.setBackground(Color.white);
  /*
  panel1.setLayout(null);

  JButton button1 = new JButton("簡単（21x21)");
  button1.setBounds(170, 110, 150, 50);

  button1.addActionListener(e -> {
    System.out.println("簡単が押された");
  });
  // ボタン2を作成
  JButton button2 = new JButton("普通（31x31）");
  button2.setBounds(170, 180, 150, 50);

  button2.addActionListener(e -> {
    System.out.println("普通が押された");
  });
  // ボタン3を作成
  JButton button3 = new JButton("やや難しい（41x41）");
  button3.setBounds(170, 250, 150, 50);

  button3.addActionListener(e -> {
    System.out.println("やや難しいが押された");


  });

  // ボタン4を作成
  JButton button4 = new JButton("難しい（51x51）");
  button4.setBounds(170, 320, 150, 50);

  button4.addActionListener(e -> {
    System.out.println("難しいが押された");
  //  System.out.println("スタートパネル");

  });
  // 各ボタンをウィンドウに追加
  panel1.add(button1);
  panel1.add(button2);
  panel1.add(button3);
  panel1.add(button4);

//  button1.addKeyListener(new Keyboard_input_start());
//  button2.addKeyListener(new Keyboard_input_start());
//  button3.addKeyListener(new Keyboard_input_start());
//  button4.addKeyListener(new Keyboard_input_start());
*/
  fr.add(panel1);
  fr.pack();
  fr.setTitle("迷路ゲーム");
  fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  fr.setResizable(false);

  fr.setVisible(true);

  //フレームにキーボード入力の受信を登録
  fr.addKeyListener(new Keyboard_input());
  // イベントを定期的に発生させるためのタイマー
}
public void DrawMap(){
    panel.setPreferredSize(new Dimension(505,570));
    panel.setBackground(Color.white);
      repaint();
        fr.add(panel);
        fr.pack();
        fr.setTitle("迷路ゲーム");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);

        fr.setVisible(true);

        //フレームにキーボード入力の受信を登録
      //  fr.addKeyListener(new Keyboard_input());
        // イベントを定期的に発生させるためのタイマー

  }


  public void DrawEnd(){
    timer.stop();
    System.out.println(timer);
    paintflag=false;
  //  panel.removeAll();

    End panel2=new End();
    panel2.setBackground(Color.white);
    fr.add(panel2);//パネルをフレームにはめ込む
    fr.setTitle("GOAL");
    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    fr.setResizable(false);
    fr.setVisible(true);

}

  public void init(){
      MAP=new int[LENGTH][WIDTH]; //マップ

          //通路を０、壁を１とする。
          for(int i=0;i<LENGTH;i++){
            for(int j=0;j<WIDTH;j++){
                if(i==0||i==LENGTH-1||j==0||j==WIDTH-1){
                  MAP[i][j]=1;
                }else if((i>=2||i<=LENGTH-2)&&(j>=2||j<=WIDTH-2)&&(i%2==0&&j%2==0)){
                  MAP[i][j]=1;
                }else{
                  MAP[i][j]=0;
                }
            }
          }
        //  maze.DrawMap();
          //MAPの表示確認
          System.out.println("MAP");
          maze.PrintMap(MAP);
          //maze.DrawMap();
          System.out.println("START MAZE");

          maze.DigMap();
          MAP[1][1]=3;
        //  MAP[LENGTH-2][WIDTH-2]=4;
          for(int i=0;i<LENGTH;i++){
            for(int j=0;j<WIDTH;j++){
                if(MAP[i][j]==1){
                  System.out.print("●");
                }else{
                  System.out.print(" ");
                }
            }
            System.out.println("");
          }

  }
  public static void main(String[] args){
    panel.Drawstart();
  }
}
