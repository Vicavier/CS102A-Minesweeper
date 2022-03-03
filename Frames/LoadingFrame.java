package Frames;

import entity.Images;
import entity.initialPrama;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class LoadingFrame extends JFrame{
    public LoadingFrame(){
        setTitle("Minesweeper");
        setBounds((MainFrame.width - 800) / 2, (MainFrame.height - 600) / 2, 800, 600);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //设置下拉列表框
        File file = new File("saves");
        String [] items=file.list();
        JComboBox<String> comboBox=new JComboBox<>(items);
        comboBox.setBounds(350,295,200,30);
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
        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                initialPrama.readFileData(comboBox.getSelectedItem().toString());
                new MainFrame(1);
                dispose();
            }
        });
        add(ok);



        //设置背景
        JLabel background = new JLabel(Images.loadImg);
        background.setBounds(0, 0, 800, 600);
        background.setVisible(true);
        add(background);
    }
}
