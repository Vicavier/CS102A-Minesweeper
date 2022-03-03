package controller;

import Frames.MainFrame;
import components.TextArea;
import entity.GridStatus;
import minesweeper.GamePanel;
import entity.Player;
import entity.initialPrama;
import minesweeper.ScoreBoard;

import javax.swing.*;
import java.io.*;


public class GameController {

    private int xCount = initialPrama.xCount;
    private int yCount = initialPrama.yCount;
    private int mineCount = initialPrama.mineCount;
    private int roundCount = initialPrama.roundCount;
    private int remainMine=initialPrama.remainMine;

    private Player p1;
    private Player p2;
    private String p1Name = initialPrama.p1Name;
    private String p2Name = initialPrama.p2Name;

    private Player onTurn;

    private GamePanel gamePanel;
    private ScoreBoard scoreBoard1;
    private ScoreBoard scoreBoard2;

    private JFrame frame;
    private TextArea area = new TextArea();

    private int [][] chessboard=initialPrama.ChessBoard;


    public GameController(Player p1, Player p2, int mineCount, int roundCount,JFrame frame) {
        this.init(p1, p2, mineCount, roundCount);
        this.onTurn = p1;
        this.frame=frame;

    }
    public GameController(Player p1,Player p2,JFrame frame){
        this.init(p1,p2);
        this.onTurn=initialPrama.onTurn;
        this.frame=frame;
        this.remainMine = initialPrama.remainMine;
    }
    /**
     * 初始化游戏。在开始游戏前，应先调用此方法，给予游戏必要的参数。
     *
     * @param p1        玩家1
     * @param p2        玩家2
     * @param mineCount 雷数
     */
    public void init(Player p1, Player p2, int mineCount, int roundCount) {
        this.p1 = p1;
        this.p2 = p2;
        this.onTurn = p1;
        p1.setUserName(p1Name);
        p2.setUserName(p2Name);
        p1.setRoleImg(initialPrama.p1Role);
        p2.setRoleImg(initialPrama.p2Role);
        this.roundCount = roundCount;
        //TODO: 在初始化游戏的时候，还需要做什么？
    }
    public void init(Player p1, Player p2){
        this.p1=p1;
        this.p2=p2;
    }
    /**
     * 进行下一个回合时应调用本方法。
     * 在这里执行每个回合结束时需要进行的操作。
     * <p>
     * (目前这里没有每个玩家进行n回合的计数机制的，请自行修改完成哦~）
     */
    public void nextTurn() {
        if (onTurn == p1 && p1.getRoundCount() == MainFrame.controller.roundCount) {
            onTurn = p2;
            p1.setRoundCount(0);
            MainFrame.controller.area.append("Now it is " + onTurn.getUserName() + "'s turn.\n");
        } else if (onTurn == p2 && p2.getRoundCount() == MainFrame.controller.roundCount) {
            onTurn = p1;
            p2.setRoundCount(0);
            MainFrame.controller.area.append("Now it is " + onTurn.getUserName() + "'s turn.\n");
        }
        scoreBoard1.update();
        scoreBoard2.update();
    }

    public void addPoint() {
        getOnTurnPlayer().addScore();
    }

    public void costPoint() {
        getOnTurnPlayer().costScore();
    }

    public void addMistake() {
        getOnTurnPlayer().addMistake();
    }

    /**
     * 获取正在进行当前回合的玩家。
     *
     * @return 正在进行当前回合的玩家
     */
    public Player getOnTurnPlayer() {
        return onTurn;
    }

    public int getxCount() {
        return xCount;
    }

    public void setxCount(int x) {
        this.xCount = x;
    }

    public int getyCount() {
        return yCount;
    }

    public void setyCount(int y) {
        this.yCount = y;
    }

    public int getMineCount() {
        return mineCount;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP1Name(String name) {
        p1Name = name;
    }

    public void setP2Name(String name) {
        p2Name = name;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }


    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setScoreBoard(ScoreBoard scoreBoard1, ScoreBoard scoreBoard2) {
        this.scoreBoard1 = scoreBoard1;
        this.scoreBoard2 = scoreBoard2;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public int getRemainMine() {
        return remainMine;
    }

    public void setRemainMine() {
        remainMine=remainMine-1;
    }

    public TextArea getTextArea() {
        return area;
    }

    public int[][] getChessboard(){
        return chessboard;
    }

    public void writeDataToFile(String fileName) {
        //todo: write data into file
        File f = new File("saves", fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.append(p1.toString());
            fw.append(p2.toString());
            fw.append(onTurn.getUserName() + "\n"+xCount+" "+yCount+" "+initialPrama.firstClick+" "+MainFrame.controller.getRemainMine()+"\n");

            for (int i = 0; i < getGamePanel().chessboard.length; i++) {
                for (int j = 0; j < getGamePanel().chessboard[0].length; j++) {
                    fw.append(getGamePanel().chessboard[i][j] + " ");
                }
                fw.append("\n");
            }
            fw.append("\n");
            for (int i = 0; i < getGamePanel().mineField.length; i++) {
                for (int j = 0; j < getGamePanel().mineField[0].length; j++) {
                    if (getGamePanel().mineField[i][j].getStatus() == GridStatus.Covered) {
                        fw.append("0 ");
                    } else if (getGamePanel().mineField[i][j].getStatus() == GridStatus.Clicked) {
                        fw.append("1 ");
                    } else
                        fw.append("2 ");
                }
                fw.append("\n");
            }
            fw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Fail Saved!","Warning",JOptionPane.ERROR_MESSAGE);

        }
    }
}
