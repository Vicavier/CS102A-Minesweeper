package components;

import Frames.MainFrame;
import Frames.ResultFrame;
import entity.GridStatus;
import entity.Images;
import entity.initialPrama;
import minesweeper.GamePanel;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;

public class GridComponent extends BasicComponent {
    public static int gridSize = initialPrama.gridSize;//格子大小

    //该grid所在行数
    private int row;
    //该grid所在列数
    private int col;
    //初始化格子状态
    private GridStatus status ;
    //content=0代表雷
    private int content = 0;

    //构造方法
    public GridComponent(int x, int y) {
        this.setSize(gridSize, gridSize);
        this.row = x;
        this.col = y;
        status= GridStatus.Covered;
    }

    public GridComponent() {
    }

    //重写鼠标单击方法
    @Override
    public void onMouseLeftClicked() {
        MainFrame.controller.getTextArea().append(String.format("    Gird (%d,%d) is left-clicked.\n", row, col));
        if(this.status==GridStatus.Cheat){
            this.status=GridStatus.Clicked;
            repaint();
            addRound();
            MainFrame.controller.setRemainMine();
            MainFrame.controller.costPoint();
            MainFrame.controller.addMistake();
            judgeWinner();
            MainFrame.controller.nextTurn();
        }
        if (this.status == GridStatus.Covered) {
            addRound();//增加行动数
            leftJudge();//判断是否碰雷,并且判断是否首发触雷；
            Open_up(this.row, this.col);//判断开一片
            initialPrama.firstClick++;
            judgeWinner();
            MainFrame.controller.nextTurn();//当行动数满足要求后交给下一角色
        }

        //TODO: 在左键点击一个格子的时候，还需要做什么？
    }

    //重写鼠标右键方法
    @Override
    public void onMouseRightClicked() {
        MainFrame.controller.getTextArea().append(String.format("    Gird (%d,%d) is right-clicked.\n", row, col));
        //插旗数不能大于雷数(双人模式不需要，双人模式需及时判断）
        if(this.status==GridStatus.Cheat){
            this.status=GridStatus.Flag;
            addRound();
            MainFrame.controller.addPoint();
            MainFrame.controller.setRemainMine();
            initialPrama.firstClick++;
            judgeWinner();
            MainFrame.controller.nextTurn();
            repaint();
        }
        if (this.status == GridStatus.Covered) {
            this.status = GridStatus.Flag;
            addRound();
            rightJudge();
            repaint();
//            System.out.println(MainFrame.controller.getRemainMine());

            initialPrama.firstClick++;
            judgeWinner();
            MainFrame.controller.nextTurn();
        }
        //TODO: 在右键点击一个格子的时候，还需要将格子”锁定“，不能再被一片打开。考虑在open_up的判断条件中入手
    }

    public void draw(Graphics g) {
        if (this.status == GridStatus.Covered) {
            Images.coverImg.paintIcon(this, g, 0, 0);
        }
        if (this.status == GridStatus.Cheat) {
            Images.boomCheatImg.paintIcon(this, g, 0, 0);
        }

        if (this.status == GridStatus.Clicked) {
            //首先判断是不是雷
            if (content != -1 && content != -2) {
                //再判断是不是0，是0就不显示数字
                if (content == 0) {
                    Images.clickImg.paintIcon(this, g, 0, 0);
                } else {//不是0就在方格内画出数字
                    Images.clickImg.paintIcon(this, g, 0, 0);
                    g.setColor(new Color(0, 0, 0));
                    g.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    g.drawString(Integer.toString(content), getWidth() / 2 - 4, getHeight() / 2 + 4);
                }
            } else {//是雷就画出TNT
                Images.clickImg.paintIcon(this, g, 0, 0);
                Images.TNTImg.paintIcon(this, g, 0, 0);
            }
        }
        if (this.status == GridStatus.Flag) {

            Images.coverImg.paintIcon(this, g, 0, 0);
            Images.flagImg.paintIcon(this, g, 6, 3);
        }
    }

    public void setContent(int content) {
        this.content = content;
    }

    //如果点到0，就开启周围一片，用递归
    private void Open_up(int i, int j) {
        if (i < 0 || i >= initialPrama.xCount || j < 0 || j >= initialPrama.yCount
                || GamePanel.mineField[i][j].status == GridStatus.Clicked||GamePanel.mineField[i][j].content==-2)//越界
            return;
        GamePanel.mineField[i][j].status = GridStatus.Clicked;//将点到的这个的status改为Clicked，因为这个已经背点开，

        //判断这个格子是不是0，是0就向周围继续使用该方法。
        if (GamePanel.mineField[i][j].content == 0) {
            Open_up(i - 1, j - 1);
            Open_up(i - 1, j);
            Open_up(i - 1, j + 1);
            Open_up(i + 1, j - 1);
            Open_up(i + 1, j);
            Open_up(i + 1, j + 1);
            Open_up(i, j + 1);
            Open_up(i, j - 1);
        }

        GamePanel.mineField[i][j].repaint();
    }

    /**
     * 每左击一个格子进行判断加减分
     */
    public void leftJudge() {
        if (this.content == -1 && initialPrama.firstClick == 0) {
            this.content = 0;
            MainFrame.controller.setRemainMine();//开局触雷，雷数-1
            try {
                MainFrame.controller.getGamePanel().getMineField()[this.row - 1][this.col].content -= 1;
                MainFrame.controller.getGamePanel().getMineField()[this.row - 1][this.col + 1].content -= 1;
                MainFrame.controller.getGamePanel().getMineField()[this.row][this.col + 1].content -= 1;
                MainFrame.controller.getGamePanel().getMineField()[this.row + 1][this.col + 1].content -= 1;
                MainFrame.controller.getGamePanel().getMineField()[this.row + 1][this.col].content -= 1;
                MainFrame.controller.getGamePanel().getMineField()[this.row + 1][this.col - 1].content -= 1;
                MainFrame.controller.getGamePanel().getMineField()[this.row][this.col - 1].content -= 1;
                MainFrame.controller.getGamePanel().getMineField()[this.row - 1][this.col - 1].content -= 1;
            } catch (ArrayIndexOutOfBoundsException e) {

            } catch (NullPointerException E) {

            }
        } else if ((this.content == -1 || this.content == -2) && initialPrama.firstClick != 0) {
            MainFrame.controller.costPoint();
            MainFrame.controller.addMistake();
            MainFrame.controller.getTextArea().append("    BOOM!!! Points-1\n");
            MainFrame.controller.setRemainMine();
        }
    }

    /**
     * 每右键一个格子进行判断加减分
     */
    public void rightJudge() {
        if (this.content == -1||this.status==GridStatus.Cheat) {
            this.status=GridStatus.Flag;
            MainFrame.controller.addPoint();
            MainFrame.controller.setRemainMine();
        } else {
            MainFrame.controller.addMistake();
            this.status = GridStatus.Covered;
            MainFrame.controller.getTextArea().append("    Wrong Sign!! Mistake+1                 ");
            cutRound();//因为鼠标左击方法会增加行动数，所以在这里需要减一
            onMouseLeftClicked();
        }
    }

    /**
     * 胜负判定机制
     */
    public void judgeWinner() {
        if (Math.abs(MainFrame.controller.getP1().getScore() - MainFrame.controller.getP2().getScore()) > MainFrame.controller.getRemainMine()) {
            //TODO:显示弹窗”游戏结束，xxx获胜“
//            System.out.printf("游戏结束,%s获胜\n", MainFrame.controller.getOnTurnPlayer().getUserName());
            new ResultFrame(3,MainFrame.controller.getFrame());

        } else if (MainFrame.controller.getRemainMine() == 0) {
            if (MainFrame.controller.getP1().getScore() > MainFrame.controller.getP2().getScore()) {
//                System.out.printf("游戏结束,%s获胜\n", MainFrame.controller.getP1().getUserName());
                new ResultFrame(1,MainFrame.controller.getFrame());
            }
            if (MainFrame.controller.getP1().getScore() < MainFrame.controller.getP2().getScore()) {
                new ResultFrame(2,MainFrame.controller.getFrame());
//                System.out.printf("游戏结束,%s获胜\n", MainFrame.controller.getP2().getUserName());
            }

            if (MainFrame.controller.getP1().getScore() == MainFrame.controller.getP2().getScore()) {
                if (MainFrame.controller.getP1().getMistake() > MainFrame.controller.getP2().getMistake()) {
                    new ResultFrame(2,MainFrame.controller.getFrame());
//                    System.out.printf("游戏结束,%s获胜\n", MainFrame.controller.getP2().getUserName());
                }
                if (MainFrame.controller.getP1().getMistake() < MainFrame.controller.getP2().getMistake()) {
                    new ResultFrame(1,MainFrame.controller.getFrame());
//                    System.out.printf("游戏结束,%s获胜\n", MainFrame.controller.getP1().getUserName());
                }

                if (MainFrame.controller.getP1().getMistake() == MainFrame.controller.getP2().getMistake()) {
                    new ResultFrame(4,MainFrame.controller.getFrame());
//                    System.out.printf("游戏结束,平局\n");
                }

            }
        }
    }

    /**
     * 调用Player类里的addRoundCount方法对角色的回合数进行操作
     */
    public void addRound() {
        MainFrame.controller.getOnTurnPlayer().addRoundCount();
    }

    public void cutRound() {
        MainFrame.controller.getOnTurnPlayer().cutRoundCount();
    }

    public GridStatus getStatus() {
        return status;
    }

    public void setStatus(GridStatus status) {
        this.status = status;
    }

    public int getContent() {
        return content;
    }
    public void setRow(int row){
        this.row=row;
    }
    public void setCol(int col){
        this.col=col;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        draw(g);
    }

}
