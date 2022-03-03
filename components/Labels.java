package components;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class Labels extends JLabel {
    //图片标签
    public Labels(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setVisible(true);
    }

}
