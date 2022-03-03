package entity;

import javax.print.DocFlavor;
import javax.swing.*;
import java.util.Random;

public class Player {
    private static Random ran = new Random();

    private String userName;
    private int score = 0;
    private int mistake = 0;

    private int roundCount = 0;
    private ImageIcon roleImg;
    /**
     * 通过特定名字初始化一个玩家对象。
     *
     * @param userName 玩家的名字
     */
    public Player(String userName) {
        this.userName = userName;
    }

    /**
     * 通过默认名字初始化一个玩家对象。
     */
    public Player() {
    }

    /**
     * 为玩家加一分。
     */
    public void addScore() {
        score++;
    }

    /**
     * 为玩家扣一分。
     */
    public void costScore() {
        score--;
    }

    /**
     * 为玩家增加一次失误数。
     */
    public void addMistake() {
        mistake++;
    }

    /**
     * 增加角色行动数（回合数）
     */
    public void addRoundCount() {
        roundCount++;
    }
    public void cutRoundCount() { roundCount--; }
    public int getScore() {
        return score;
    }
    public void setScore(int score){
        this.score=score;
    }
    public String getUserName() {
        return userName;
    }

    public int getMistake() {
        return mistake;
    }
    public void setMistake(int mistake){
        this.mistake=mistake;
    }
    public int getRoundCount() {
        return roundCount;
    }
    public void setRoundCount(int roundCount){
        this.roundCount=roundCount;
    }

    public void setUserName(String name){
        this.userName=name;
    }

    public ImageIcon getRoleImg(){
        return roleImg;
    }
    public void setRoleImg(ImageIcon img){
        roleImg=img;
    }

    @Override
    public String toString() {
        return userName + " " + score + " " + mistake +
                " " + roundCount + " " + roleImg +"\n";
    }
}
