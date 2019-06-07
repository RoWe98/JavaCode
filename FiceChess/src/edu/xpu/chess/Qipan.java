package edu.xpu.chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Qipan extends JPanel implements Goconfig{
    static {
        //活连 遇到空位就停止；
        //活一连
        map.put("010",40);
        map.put("020",40);
        //活二连
        map.put("0110",400);
        map.put("0220",400);
        //活三连
        map.put("01110", 3000);
        map.put("02220", 3000);
        //活四连
        map.put("011110", 10000);
        map.put("022220", 10000);
        //眠一连
        map.put("012",20);
        map.put("021",20);
        //眠二连
        map.put("0112",200);
        map.put("0221",200);
        //眠三连
        map.put("01112",500);
        map.put("02221",500);
        //眠四连  只有能在连成五子的情况下才有价值
        map.put("022221", 3000);
        map.put("011112", 3000);
    }

    //维护一个15*15的表来记录每个点的落子情况；
    private void PieceInit() {
        for(int i=0;i<15;i++) {
            for(int j=0;j<15;j++) {
                go[i][j]=new Piece(0);
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Qipan qp=new Qipan();
        qp.Init();
        qp.PieceInit();
    }

    public void Init() {
        /*
         * jf的设置顺序：名字，打开位置，可调节大小，大小，关闭方式，布局方式，是否可见；
         * Dimension可以用来统一组件的大小；
         */
        JFrame jf=new JFrame();
        jf.setTitle("五子棋");
        jf.setSize(650, 580);        //先设大小，再设居中;
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(3);
        jf.setLayout(new BorderLayout());
        jf.add(this);
        JPanel eastp=new JPanel();
        eastp.setPreferredSize(new Dimension(100,0));
        JButton buttonStart=new JButton("开始游戏");
        JButton buttonregret=new JButton("悔棋");
        JButton buttonlose=new JButton("认输");
        String[] itemArray = { "人人对战", "人机对战" };
        JComboBox<String> cbItem = new JComboBox<String>(itemArray);
        buttonStart.setPreferredSize(new Dimension(90,40));
        buttonregret.setPreferredSize(new Dimension(90,40));
        buttonlose.setPreferredSize(new Dimension(90,40));
        cbItem.setPreferredSize(new Dimension(90,40));
        eastp.add(buttonStart);
        eastp.add(buttonregret);
        eastp.add(buttonlose);
        eastp.add(cbItem);
        jf.add(eastp,BorderLayout.EAST);
        jf.setVisible(true);
        ChessListener cl=new ChessListener(this,cbItem);
        buttonStart.addActionListener(cl);
        buttonregret.addActionListener(cl);
        buttonlose.addActionListener(cl);
        cbItem.addActionListener(cl);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < 15; i++) {
            g.drawLine(30, 30 + i * 35, 30 + 35 * 14, 30 + i * 35);// 横线
            g.drawLine(30 + i * 35, 30, 30 + i * 35, 30 + 35 * 14);// 竖线
        }
        for(int i=0;i<15;i++) {
            for(int j=0;j<15;j++) {
                if(go[i][j].color!=0) {
                    int x=30+i*35;
                    int y=30+j*35;
                    if(go[i][j].color==1)
                        g.setColor(Color.black);
                    else
                        g.setColor(Color.WHITE);
                    g.fillOval(x-12, y-12, 24, 24);
                }
            }
        }
    }
}