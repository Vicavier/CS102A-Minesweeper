package components;

import Frames.*;
import entity.initialPrama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 按钮的构造器，img传入按钮未被选中时的贴图，changImg用于按钮选中时的贴图，x，y，width，height传给按钮位置和大小，
 * inWhichFrame用于onMouseClicked（）方法来关闭按钮当前窗体，openWhichFrame用于判断该开启哪一个窗体
 */
public class Button extends JButton {
    public Button(ImageIcon img, ImageIcon changImg, int x, int y, int width, int height, JFrame inWhichFrame, String openWhichFrame) {
        setBounds(x, y, width, height);
        setIcon(img);
        setMargin(new Insets(0, 0, 0, 0));
        setFocusPainted(true);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setVisible(true);

        onMouseMoved(changImg);
        onMouseExited(img);
        onMouseClicked(inWhichFrame, openWhichFrame);
    }

    /**
     * 专门为Start按钮写的构造器
     */
    public Button(ImageIcon img, ImageIcon changImg, int x, int y, int width, int height, JFrame inWhichFrame, String openWhichFrame, JTextField jt1, JTextField jt2) {
        setBounds(x, y, width, height);
        setIcon(img);
        setMargin(new Insets(0, 0, 0, 0));
        setFocusPainted(true);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setVisible(true);

        onMouseMoved(changImg);
        onMouseExited(img);
        onMouseClicked(inWhichFrame, openWhichFrame, jt1, jt2);
    }

    /**
     * 选择人物的角色头像
     *
     * @param img
     */
    public Button(ImageIcon img) {

    }

    public void onMouseMoved(ImageIcon changeImg) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Button.this.setIcon(changeImg);
            }
        });
    }

    public void onMouseExited(ImageIcon retureImg) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                Button.this.setIcon(retureImg);
            }
        });
    }

    public void onMouseClicked(JFrame inWhichFrame, String openWhichFrame) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openFrame(openWhichFrame);
                inWhichFrame.dispose();

            }
        });
    }

    public void onMouseClicked(JFrame inWhichFrame, String openWhichFrame, JTextField jf1, JTextField jf2) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initialPrama.p1Name = jf1.getText();
                initialPrama.p2Name = jf2.getText();
                openFrame(openWhichFrame);
                inWhichFrame.dispose();
                initialPrama i = new initialPrama();
                MainFrame.controller.setxCount(i.getxCount());
                MainFrame.controller.setyCount(i.getyCount());
                MainFrame.controller.setMineCount(i.getMineCount());
                MainFrame.controller.setRoundCount(i.getRoundCount());
            }
        });
    }

    public void openFrame(String frame) {
        switch (frame) {
            case "RoleSelect":
                new RoleSelect();
                break;
            case "MainFrame":
                new MainFrame();
                break;
            case "LoginFrame":
                new LoginFrame();
                break;
            case "SettingFrame":
                new SettingFrame();
                break;
            case "LoadingFrame":
                new LoadingFrame();
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                break;

        }
    }
}
