package minesweeper;

import Frames.MainFrame;
import components.GridComponent;
import entity.initialPrama;


import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class GamePanel extends JPanel {
    public static GridComponent[][] mineField;
    public static int[][] chessboard;
    private final Random random = new Random();

    /**
     * 初始化一个具有指定行列数格子、并埋放了指定雷数的雷区。
     *
     * @param xCount    count of grid in column
     * @param yCount    count of grid in row
     * @param mineCount mine count
     */
    public GamePanel(int xCount, int yCount, int mineCount) {

        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
//        this.setBackground(new Color(0xEAA9EA));
        this.setOpaque(false);

//设置面板的位置，相对于窗体
        this.setBounds((800 - yCount * GridComponent.gridSize) / 2, 30, yCount * GridComponent.gridSize, xCount * GridComponent.gridSize);
//初始化游戏模式
        initialGame(xCount, yCount, mineCount);

        repaint();
    }

    public GamePanel(int xCount, int yCount) {

        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
//        this.setBackground(new Color(0xEAA9EA));
        this.setOpaque(false);

//设置面板的位置，相对于窗体
        this.setBounds((800 - yCount * GridComponent.gridSize) / 2, 30, yCount * GridComponent.gridSize, xCount * GridComponent.gridSize);
//初始化游戏模式
        loadGame(xCount,yCount);

        repaint();
    }



    public void initialGame(int xCount, int yCount, int mineCount) {
        mineField = new GridComponent[xCount][yCount];
        generateChessBoard(xCount, yCount, mineCount);

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = new GridComponent(i, j);
                gridComponent.setContent(chessboard[i][j]);
                //位每一个按钮设置位置,相对于面板
                gridComponent.setLocation(j * initialPrama.gridSize, i * initialPrama.gridSize);
                mineField[i][j] = gridComponent;
                //在panel上画上mineField[i][j]组件
                this.add(mineField[i][j]);
            }
        }
    }

    public void loadGame(int xCount,int yCount){
        mineField = new GridComponent[xCount][yCount];
        chessboard=initialPrama.ChessBoard;
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = new GridComponent(i, j);
                gridComponent.setContent(chessboard[i][j]);
                gridComponent.setStatus(initialPrama.status[i][j]);
                //位每一个按钮设置位置,相对于面板
                gridComponent.setLocation(j * GridComponent.gridSize, i * GridComponent.gridSize);
                mineField[i][j] = gridComponent;
                //在panel上画上mineField[i][j]组件
                this.add(mineField[i][j]);
            }
        }
    }

    public void generateChessBoard(int xCount, int yCount, int mineCount) {
        String[][] board = new String[xCount][yCount];

        for (int p = 0; p < mineCount; p++) {
            int x = random.nextInt(xCount);
            int y = random.nextInt(yCount);
            if (board[x][y] == null) {
                board[x][y] = "*";
            } else
                p--;
        }
        for (int q = 0; q < xCount; q++) {
            for (int m = 0; m < yCount; m++) {
                if (board[q][m] == null)
                    board[q][m] = "-";
            }
        }
        int[][] newBoard = new int[xCount][yCount];
        //(0,0)
        if (board[0][0].equals("*")) {
            newBoard[0][0] = -1;
        } else {
            int k = 0;
            if (board[0][1].equals("*"))
                k++;
            if (board[1][1].equals("*"))
                k++;
            if (board[1][0].equals("*"))
                k++;
            newBoard[0][0] = k;
        }
        //(0,size-1)
        if (board[xCount - 1][0].equals("*")) {
            newBoard[yCount - 1][0] = -1;
        } else {
            int k = 0;
            if (board[xCount - 1][1].equals("*"))
                k++;
            if (board[xCount - 2][1].equals("*"))
                k++;
            if (board[xCount - 2][0].equals("*"))
                k++;
            newBoard[xCount - 1][0] = k;
        }
        //(0,size-1)
        if (board[0][yCount - 1].equals("*")) {
            newBoard[0][yCount - 1] = -1;
        } else {
            int k = 0;
            if (board[0][yCount - 2].equals("*"))
                k++;
            if (board[1][yCount - 2].equals("*"))
                k++;
            if (board[1][yCount - 1].equals("*"))
                k++;
            newBoard[0][yCount - 1] = k;
        }
        //(size-1,size-1)
        if (board[xCount - 1][yCount - 1].equals("*")) {
            newBoard[xCount - 1][yCount - 1] = -1;
        } else {
            int k = 0;
            if (board[xCount - 2][yCount - 1].equals("*"))
                k++;
            if (board[xCount - 2][yCount - 2].equals("*"))
                k++;
            if (board[xCount - 1][yCount - 2].equals("*"))
                k++;
            newBoard[xCount - 1][yCount - 1] = k;
        }
        //第一行
        for (int j = 1; j < board[0].length - 1; j++) {
            if (board[0][j].equals("*")) {
                newBoard[0][j] = -1;
            } else {
                int k = 0;
                if (board[0][j - 1].equals("*"))
                    k++;
                if (board[0][j + 1].equals("*"))
                    k++;
                if (board[1][j - 1].equals("*"))
                    k++;
                if (board[1][j].equals("*"))
                    k++;
                if (board[1][j + 1].equals("*"))
                    k++;
                newBoard[0][j] = k;
            }
        }
        //最后一行
        for (int j = 1; j < board[0].length - 1; j++) {
            if (board[xCount - 1][j].equals("*")) {
                newBoard[xCount - 1][j] = -1;
            } else {
                int k = 0;
                if (board[xCount - 1][j - 1].equals("*"))
                    k++;
                if (board[xCount - 1][j + 1].equals("*"))
                    k++;
                if (board[xCount - 2][j - 1].equals("*"))
                    k++;
                if (board[xCount - 2][j].equals("*"))
                    k++;
                if (board[xCount - 2][j + 1].equals("*"))
                    k++;
                newBoard[xCount - 1][j] = k;
            }
        }
        //左边列
        for (int i = 1; i < board.length - 1; i++) {
            if (board[i][0].equals("*")) {
                newBoard[i][0] = -1;
            } else {
                int k = 0;
                if (board[i - 1][0].equals("*"))
                    k++;
                if (board[i + 1][0].equals("*"))
                    k++;
                if (board[i - 1][1].equals("*"))
                    k++;
                if (board[i][1].equals("*"))
                    k++;
                if (board[i + 1][1].equals("*"))
                    k++;
                newBoard[i][0] = k;
            }
        }
        //右边列
        for (int i = 1; i < board.length - 1; i++) {
            if (board[i][yCount - 1].equals("*")) {
                newBoard[i][yCount - 1] = -1;
            } else {
                int k = 0;
                if (board[i - 1][yCount - 1].equals("*"))
                    k++;
                if (board[i + 1][yCount - 1].equals("*"))
                    k++;
                if (board[i - 1][yCount - 2].equals("*"))
                    k++;
                if (board[i][yCount - 2].equals("*"))
                    k++;
                if (board[i + 1][yCount - 2].equals("*"))
                    k++;
                newBoard[i][yCount - 1] = k;
            }
        }
        //中间
        for (int i = 1; i < xCount - 1; i++) {
            for (int j = 1; j < yCount - 1; j++) {
                if (board[i][j].equals("*"))
                    newBoard[i][j] = -1;
                else {
                    int k = 0;
                    if (board[i][j + 1].equals("*"))
                        k++;
                    if (board[i + 1][j + 1].equals("*"))
                        k++;
                    if (board[i + 1][j].equals("*"))
                        k++;
                    if (board[i + 1][j - 1].equals("*"))
                        k++;
                    if (board[i][j - 1].equals("*"))
                        k++;
                    if (board[i - 1][j - 1].equals("*"))
                        k++;
                    if (board[i - 1][j].equals("*"))
                        k++;
                    if (board[i - 1][j + 1].equals("*"))
                        k++;
                    newBoard[i][j] = k;
                }
            }
        }

        chessboard = new int[xCount][yCount];
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                // suppose -1 represents mine
                chessboard[i][j] = newBoard[i][j];
            }
        }

    }

    /**
     * 获取一个指定坐标的格子。
     * 注意请不要给一个棋盘之外的坐标哦~
     *
     * @param x 第x列
     * @param y 第y行
     * @return 该坐标的格子
     */
    public GridComponent getGrid(int x, int y) {
        try {
            return mineField[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }
    public GridComponent[][] getMineField(){
        return mineField;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
