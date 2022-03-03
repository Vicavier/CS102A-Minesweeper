package Frames;

import javax.swing.*;


import entity.Images;
import entity.Player;
import entity.initialPrama;

import java.awt.*;

public class ResultFrame extends JDialog {
    public ResultFrame(int a,JFrame frame){
        super(frame,"Minesweeper",true);
        setTitle("Minesweeper");
        setBounds((initialPrama.width-400)/2,(initialPrama.height-300)/2,400,300);
        setLayout(null);
        switch (a){
            case 1:
                JLabel background1 = new JLabel(Images.winImg);
                background1.setBounds(0,0,this.getWidth(),this.getHeight());

                JTextField t1=new JTextField(MainFrame.controller.getP1().getUserName());
                t1.setBounds(110,100,300,30);
                t1.setEditable(false);
                t1.setBorder(null);
                t1.setOpaque(false);
                t1.setForeground(Color.white);
                t1.setFont(new Font("Times New Roman",Font.BOLD,30));
                this.add(t1);
                this.add(background1);
                break;
            case 2:
                JLabel background2 = new JLabel(Images.winImg);
                background2.setBounds(0,0,this.getWidth(),this.getHeight());

                JTextField t2=new JTextField(MainFrame.controller.getP2().getUserName());
                t2.setBounds(110,100,300,30);
                t2.setEditable(false);
                t2.setBorder(null);
                t2.setOpaque(false);
                t2.setForeground(Color.white);
                t2.setFont(new Font("Times New Roman",Font.BOLD,30));
                this.add(t2);
                this.add(background2);
                break;
            case 3:
                JLabel background3 = new JLabel(Images.winImg);
                background3.setBounds(0,0,this.getWidth(),this.getHeight());
                JTextField t3=new JTextField(compare(MainFrame.controller.getP1(),MainFrame.controller.getP2()).getUserName());
                t3.setBounds(110,100,300,30);
                t3.setEditable(false);
                t3.setBorder(null);
                t3.setOpaque(false);
                t3.setForeground(Color.white);
                t3.setFont(new Font("Times New Roman",Font.BOLD,30));
                this.add(t3);
                this.add(background3);

                break;
            case 4:
                JLabel background4 = new JLabel(Images.drawImg);
                background4.setBounds(0,0,this.getWidth(),this.getHeight());
                this.add(background4);
                break;
        }
        this.setVisible(true);
    }

    private  Player compare(Player p1, Player p2){
        if(p1.getScore()>p2.getScore())
            return p1;
        else
            return p2;
    }
}
