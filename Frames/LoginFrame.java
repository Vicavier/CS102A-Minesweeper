package Frames;

import components.Button;
import entity.Images;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Minesweeper");
        setBounds((MainFrame.width - 800) / 2, (MainFrame.height - 600) / 2, 800, 600);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //设置背景
        Image smallImage = Images.logBgImg.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        Images.logBgImg.setImage(smallImage);
        JLabel background = new JLabel(Images.logBgImg);
        background.setBounds(0, 0, 800, 600);
        background.setVisible(true);

        //添加标题
        JLabel title = new JLabel(Images.TitleImg);
        title.setBounds(100, 50, 600, 60);
        title.setVisible(true);

        //添加按钮
        components.Button localGame = new components.Button(Images.Localgame0Img, Images.LocalgameImg, (800 - 308) / 2, 180, 308, 28, this, "RoleSelect");
        this.add(localGame);
        components.Button loadingGame = new Button(Images.loadgame0Img, Images.loadgameImg, (800 - 308) / 2, 220, 308, 28, this, "LoadingFrame");
        this.add(loadingGame);
        components.Button setting = new components.Button(Images.setting0Img, Images.settingImg, (800 - 308) / 2, 260, 144, 28, this, "SettingFrame");
        this.add(setting);
        components.Button ExitGame = new components.Button(Images.exit0Img, Images.exitImg, (800 - 308) / 2+164, 260, 144, 28, this, "Exit");
        this.add(ExitGame);
        add(title);
        add(background);

        //刷新窗体
        this.validate();

    }
}
