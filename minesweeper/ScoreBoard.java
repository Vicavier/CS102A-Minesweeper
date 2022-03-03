package minesweeper;

import components.GridComponent;
import entity.Player;

import javax.swing.*;
import java.awt.*;

/**
 * 此类的对象是一个计分板容器，通过传入玩家对象，
 * 可以用update()方法实时更新玩家的分数以及失误数。
 */
public class ScoreBoard extends JPanel {

    Player player;


    JLabel score = new JLabel();
    JLabel mistake=new JLabel();

    public ScoreBoard(Player player,int x,int y) {
        this.player = player;
        this.setBounds(x,y,100,60);

        score.setForeground(Color.WHITE);


        mistake.setForeground(Color.WHITE);


        this.setOpaque(false);

        this.add(score);
        this.add(mistake);
        update();
    }

    /**
     * 刷新计分板的数据。
     * 计分板会自动重新获取玩家的分数，并更新显示。
     */
    public void update() {
        score.setText(String.format("%d scores",player.getScore()));
        mistake.setText(String.format("%d mistakes",player.getMistake()));
    }

}
