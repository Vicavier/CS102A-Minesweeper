package Frames;

import components.Button;
import components.Labels;
import components.RoleButton;
import components.TextField;
import entity.Images;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RoleSelect extends JFrame {

    private ArrayList<RoleButton> roleButtons1 = new ArrayList<>();
    private ArrayList<RoleButton> roleButtons2 = new ArrayList<>();

    public RoleSelect() {
        setTitle("Minesweeper");
        setBounds((MainFrame.width - 800) / 2, (MainFrame.height - 600) / 2, 800, 600);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //最上面一行：Select Your Role
        Labels l1 = new Labels(10, 10, 780, 30);
        l1.setIcon(Images.SYRImg);

        //人物选择界面左
        Labels l2 = new Labels(100, 80, 250, 375);
        l2.setIcon(Images.roleBgImg);
        TextField tf1 = new TextField(100, 90, 250, 20, "Player#" + (new Random().nextInt(9000) + 1000));
        add(tf1);


        RoleButton role1 = new RoleButton(1,Images.R1Img, Images.Locating1Img, 130, 140, roleButtons1);
        add(role1);
        RoleButton role2 = new RoleButton(1,Images.R2Img, Images.Locating2Img, 235, 140, roleButtons1);
        add(role2);
        RoleButton role3 = new RoleButton(1,Images.R3Img, Images.Locating3Img, 130, 245, roleButtons1);
        add(role3);
        RoleButton role4 = new RoleButton(1,Images.R4Img, Images.Locating4Img, 235, 350, roleButtons1);
        add(role4);
        RoleButton role5 = new RoleButton(1,Images.R5Img, Images.Locating5Img, 130, 350, roleButtons1);
        add(role5);
        RoleButton role6 = new RoleButton(1,Images.R6Img, Images.Locating6Img, 235, 245, roleButtons1);
        add(role6);

        roleButtons1.add(role1);
        roleButtons1.add(role2);
        roleButtons1.add(role3);
        roleButtons1.add(role4);
        roleButtons1.add(role5);
        roleButtons1.add(role6);

        Labels text = new Labels(100, 115, 250, 20);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Times New Roman", Font.BOLD, 20));
        text.setText("Choose Your Role");
        add(text);


        //人物选择界面右
        Labels l3 = new Labels(450, 80, 250, 375);
        l3.setIcon(Images.roleBgImg);
        TextField tf2 = new TextField(450, 90, 250, 20, "Player#" + (new Random().nextInt(9000) + 1000));
        add(tf2);

        RoleButton role11 = new RoleButton(2,Images.R1Img, Images.Locating1Img, 480, 140, roleButtons2);
        add(role11);
        RoleButton role22 = new RoleButton(2,Images.R2Img, Images.Locating2Img, 585, 140, roleButtons2);
        add(role22);
        RoleButton role33 = new RoleButton(2,Images.R3Img, Images.Locating3Img, 480, 245, roleButtons2);
        add(role33);
        RoleButton role44 = new RoleButton(2,Images.R4Img, Images.Locating4Img, 585, 350, roleButtons2);
        add(role44);
        RoleButton role55 = new RoleButton(2,Images.R5Img, Images.Locating5Img, 480, 350, roleButtons2);
        add(role55);
        RoleButton role66 = new RoleButton(2,Images.R6Img, Images.Locating6Img, 585, 245, roleButtons2);
        add(role66);

        roleButtons2.add(role11);
        roleButtons2.add(role22);
        roleButtons2.add(role33);
        roleButtons2.add(role44);
        roleButtons2.add(role55);
        roleButtons2.add(role66);

        Labels text2 = new Labels(450, 115, 250, 20);
        text2.setForeground(Color.WHITE);
        text2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        text2.setText("Choose Your Role");
        add(text2);

        //返回按钮，开始按钮
        components.Button returnButton = new components.Button(Images.return0Img, Images.returnImg, 10, 500, 120, 30, this, "LoginFrame");
        components.Button startButton = new Button(Images.start0Img, Images.startImg, 170, 500, 630, 30, this, "MainFrame", tf1, tf2);

        //设置背景
        Image smallImage = Images.selectBgImg.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        Images.selectBgImg.setImage(smallImage);
        JLabel background = new JLabel(Images.selectBgImg);
        background.setBounds(0, 0, 800, 600);
        background.setVisible(true);

        add(startButton);
        add(returnButton);
        add(l3);
        add(l2);
        add(l1);
        add(background);
    }

    public ArrayList<RoleButton> getRoleButtons1() {
        return roleButtons1;
    }

    public ArrayList<RoleButton> getRoleButtons2() {
        return roleButtons2;
    }

}
