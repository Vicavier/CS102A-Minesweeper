package Frames;

import entity.Images;
import entity.initialPrama;
import components.TextField;
import sun.applet.Main;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingFrame extends JFrame {
    public SettingFrame(){
        setTitle("Minesweeper");
        setBounds((MainFrame.width - 800) / 2, (MainFrame.height - 600) / 2, 800, 600);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //设置背景
        JLabel background = new JLabel(Images.settingBgImg);
        background.setBounds(0, 0, 800, 600);
        background.setVisible(true);

        //设置文本框
        TextField t1=new TextField(360,155,200,30,"9");
        add(t1);
        TextField t2=new TextField(360,231,200,30,"9");
        add(t2);
        TextField t3=new TextField(360,302,200,30,"10");
        add(t3);
        TextField t4=new TextField(430,373,130,30,"3");
        add(t4);

        //设置下拉列表框
        String [] items={"EASY","MEDIUM","DIFFICULT","CUSTOMIZE"};
        JComboBox<String> comboBox=new JComboBox<>(items);
        comboBox.setBounds(360,80,200,30);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBox.getSelectedIndex()){
                    case 0:
                        t1.setText("9");
                        t2.setText("9");
                        t3.setText("10");
                        t4.setText("3");
                        t1.setEditable(false);
                        t2.setEditable(false);
                        t3.setEditable(false);
                        t4.setEditable(false);
                        break;
                    case 1:
                        t1.setText("16");
                        t2.setText("16");
                        t3.setText("40");
                        t4.setText("3");
                        t1.setEditable(false);
                        t2.setEditable(false);
                        t3.setEditable(false);
                        t4.setEditable(false);
                        break;
                    case 2:
                        t1.setText("21");
                        t2.setText("16");
                        t3.setText("75");
                        t4.setText("5");
                        t1.setEditable(false);
                        t2.setEditable(false);
                        t3.setEditable(false);
                        t4.setEditable(false);
                        break;
                    case 3:
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");
                        break;
                    default:
                        break;
                }
            }
        });
        add(comboBox);

        //设置按钮
        components.Button returnButton = new components.Button(Images.return0Img, Images.returnImg, 30, 500, 120, 30,this,"LoginFrame");
        add(returnButton);
        JButton ok=new JButton();
        ok.setIcon(Images.ok0Img);
        ok.setBorder(null);
        ok.setBounds(500,500,100,30);
        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ok.setIcon(Images.okImg);
            }
        });
        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                ok.setIcon(Images.ok0Img);
            }
        });
        //点击ok键，将用户设置加载到游戏初始化参数
        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                initialPrama.xCount=Integer.parseInt(t1.getText());
                initialPrama.yCount=Integer.parseInt(t2.getText());
                initialPrama.mineCount=Integer.parseInt(t3.getText());
                initialPrama.remainMine=Integer.parseInt(t3.getText());
                initialPrama.roundCount=Integer.parseInt(t4.getText());
                //此时还没创建MainFrame窗口，所以会报错nullpointer
                if(Integer.parseInt(t3.getText())<=(Integer.parseInt(t1.getText())*Integer.parseInt(t2.getText()))/2){
                    new LoginFrame();
                    dispose();
                }
                else
                    t3.setText("");
            }
        });
        add(ok);

        add(background);
    }
    public void openFrame(){

    }
}
