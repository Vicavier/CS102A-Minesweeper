package entity;

import components.GridComponent;
import minesweeper.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

//initialPrama类中的值由SettingFrame中ok按钮的onMouseClicked（）方法获取，并将值传给controller
public class initialPrama {
    public static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static int xCount = 12;
    public static int yCount = 10;
    public static int mineCount = 16;
    public static int remainMine=16;
    public static int roundCount = 3;
    public static int gridSize = 25;
    public static String p1Name = "Player 1";
    public static String p2Name = "Player 2";

    public static ImageIcon p1Role;
    public static ImageIcon p2Role;
    public static Player p1 = new Player();
    public static Player p2 = new Player();
    public static Player onTurn;


    public static int[][] ChessBoard;
    public static GridStatus[][] status;

    public static int firstClick = 0;

    public int getxCount() {
        return xCount;
    }

    public int getyCount() {
        return yCount;
    }


    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(String txt) {
        int mineCount = Integer.parseInt(txt);
        this.mineCount = mineCount;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(String TXT) {
        int roundCount = Integer.parseInt(TXT);
        this.roundCount = roundCount;
    }

    public static void init() {
        xCount = 12;
        yCount = 10;
        mineCount = 16;
        roundCount = 3;
        gridSize = 25;
        p1Name = "Player 1";
        p2Name = "Player 2";
        p1 = new Player();
        p2 = new Player();
        firstClick = 0;
    }


    public static void readFileData(String fileName) {
        //todo: read date from file
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader("saves\\" + fileName);
            reader = new BufferedReader(fr);
            String temp = null;
            ArrayList<String[]> readList = new ArrayList<>();
            while ((temp = reader.readLine()) != null) {
                String[] information = temp.split(" ");
                readList.add(information);
            }

            p1.setUserName(readList.get(0)[0]);
            p1.setScore(Integer.parseInt(readList.get(0)[1]));
            p1.setMistake(Integer.parseInt(readList.get(0)[2]));
            p1.setRoundCount(Integer.parseInt(readList.get(0)[3]));


            p2.setUserName(readList.get(1)[0]);
            p2.setScore(Integer.parseInt(readList.get(1)[1]));
            p2.setMistake(Integer.parseInt(readList.get(1)[2]));
            p2.setRoundCount(Integer.parseInt(readList.get(1)[3]));

            //加载执子方
            if (readList.get(2)[0].equals(p1.getUserName()))
                onTurn = p1;
            else
                onTurn = p2;

            int row = Integer.parseInt(readList.get(3)[0]);
            int col = Integer.parseInt(readList.get(3)[1]);//row=12 col=10
            firstClick = Integer.parseInt(readList.get(3)[2]);
            remainMine=Integer.parseInt(readList.get(3)[3]);
            xCount = row;
            yCount = col;

            int[][] loadChessboard = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    loadChessboard[i][j] = Integer.parseInt(readList.get(4 + i)[j]);
                }
            }
            ChessBoard = loadChessboard;

            GridStatus[][] loadStatus = new GridStatus[xCount][yCount];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    loadStatus[i][j] = GridStatus.Covered;
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    switch (readList.get(5 + row + i)[j]) {
                        case "0":
                            loadStatus[i][j] = GridStatus.Covered;
                            break;
                        case "1":
                            loadStatus[i][j] = GridStatus.Clicked;
                            break;
                        case "2":
                            loadStatus[i][j] = GridStatus.Flag;
                            break;
                        default:
                            break;
                    }
                }
            }
            status = loadStatus;

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Save Not Found!","Warning",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Wrong Save Format!","Warning",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }catch (ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null,"Wrong Save Format!","Warning",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(null,"Wrong Save Format!","Warning",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,"Wrong Save Format!","Warning",JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,"Wrong Save Format!","Warning",JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        }
    }


}
