package Frames;

import components.Button;
import entity.GridStatus;
import minesweeper.ScoreBoard;
import controller.GameController;
import entity.Images;
import entity.Player;
import entity.initialPrama;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MainFrame extends JFrame {
    public static GameController controller;
    private int xCount;
    private int yCount;
    private int mineCount;
    private int roundCount;
    private int i = 1;

    public static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public MainFrame() {
        //todo: change the count of xCount, yCount and mineCount by passing parameters from constructor
        Player p1 = new Player();
        Player p2 = new Player();
        controller = new GameController(p1, p2, mineCount, roundCount, this);
        this.xCount = controller.getxCount();//grid of row
        this.yCount = controller.getyCount();// grid of column
        this.mineCount = controller.getMineCount();// mine count
        this.roundCount = controller.getRoundCount();
        controller.setFrame(this);

        this.setTitle("Minesweeper");
        this.setLayout(null);
        this.setBounds((width - 800) / 2, (height - 600) / 2, 800, 600);
        setResizable(false);

        minesweeper.GamePanel gamePanel = new minesweeper.GamePanel(xCount, yCount, mineCount);
        controller.setGamePanel(gamePanel);

        //设置积分板
        ScoreBoard p1Score = new ScoreBoard(p1, 70, 180);
        ScoreBoard p2Score = new ScoreBoard(p2, 70, 405);
        controller.setScoreBoard(p1Score, p2Score);


        this.add(gamePanel);
        this.add(p1Score);
        this.add(p2Score);

        JButton save = new JButton(Images.save0Img);
        add(save);
        save.setBorder(null);
        save.setBounds(610, 500, 80, 30);
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                save.setIcon(Images.saveImg);
            }
        });
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                save.setIcon(Images.save0Img);
            }
        });
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String fileName = JOptionPane.showInputDialog("Input Your Filename Here", "save");
                try {
                    controller.writeDataToFile(fileName);
                } catch (NullPointerException exception) {
                    System.exit(0);
                }
            }
        });


        //设置角色名称
        JLabel p1Name = new JLabel(p1.getUserName());
        p1Name.setBounds(70, 160, 130, 20);
        p1Name.setForeground(Color.WHITE);
        p1Name.setBackground(Color.black);

        JLabel p2Name = new JLabel(p2.getUserName());
        p2Name.setBounds(70, 385, 130, 20);
        p2Name.setForeground(Color.WHITE);
        p2Name.setBackground(Color.black);

        add(p1Name);
        add(p2Name);

        //设置角色头像
        JLabel p1Img = new JLabel(p1.getRoleImg());
        p1Img.setBounds(14, 65, 85, 85);

        JLabel p2Img = new JLabel(p2.getRoleImg());
        p2Img.setBounds(14, 290, 85, 85);
        add(p2Img);
        add(p1Img);
        //左旁栏
        JLabel bg = new JLabel(Images.zuopanglanImg);
        bg.setBounds(0, 30, 180, 450);
        add(bg);


        // 对战动态

        setLayout(null);
        controller.getTextArea().setText("Now it is " + p1.getUserName() + "'s turn.\n");
        JScrollPane jsp = new JScrollPane(controller.getTextArea());
        jsp.setBounds(620, 80, 185, 380);
        jsp.setBorder(null);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(jsp);


        //右旁栏

        JLabel bg2 = new JLabel(Images.fightInformationImg);
        bg2.setBounds(610, 30, 180, 450);
        add(bg2);


        //设置按钮
        components.Button returnButton = new components.Button(Images.return0Img, Images.returnImg, 10, 500, 120, 30, this, "RoleSelect");
        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initialPrama.firstClick = 0;
            }
        });
        add(returnButton);

        //作弊按钮
        JButton cheat = new JButton();
        cheat.setBounds(100, 5, 70, 20);
        cheat.setIcon(Images.cheating0Img);
        cheat.setMargin(new Insets(0, 0, 0, 0));
        cheat.setFocusPainted(true);
        cheat.setBorderPainted(false);
        cheat.setContentAreaFilled(false);
        setVisible(true);
        cheat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cheat.setIcon(Images.cheatingImg);
            }
        });
        cheat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                cheat.setIcon(Images.cheating0Img);
            }
        });

        cheat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (i % 2) {
                    case 1:
                        for (int i = 0; i < xCount; i++) {
                            for (int j = 0; j < yCount; j++) {
                                if ((gamePanel.getMineField()[i][j].getContent() == -1 || gamePanel.getMineField()[i][j].getContent() == -2)
                                        && gamePanel.getMineField()[i][j].getStatus() != GridStatus.Flag && gamePanel.getMineField()[i][j].getStatus() != GridStatus.Clicked) {
                                    gamePanel.getMineField()[i][j].setStatus(GridStatus.Cheat);
                                    gamePanel.getMineField()[i][j].repaint();
                                }
                            }
                        }
                        i++;
                        break;
                    case 0:
                        for (int i = 0; i < xCount; i++) {
                            for (int j = 0; j < yCount; j++) {
                                if (gamePanel.getMineField()[i][j].getStatus() == GridStatus.Cheat) {
                                    gamePanel.getMineField()[i][j].setStatus(GridStatus.Covered);
                                    gamePanel.getMineField()[i][j].repaint();
                                }
                            }
                        }
                        i++;
                        break;
                }

            }
        });
        add(cheat);

        components.Button reStart = new Button(Images.reStart0Img, Images.reStartImg, 700, 500, 80, 30, this, "LoginFrame");
        add(reStart);
        //设置背景
        Image smallImage = Images.MainBg2Img.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        Images.MainBg2Img.setImage(smallImage);
        JLabel background = new JLabel(Images.MainBg2Img);
        background.setBounds(0, 0, 800, 600);
        background.setVisible(true);
        add(background);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public MainFrame(int a) {
        Player p1 = initialPrama.p1;
        Player p2 = initialPrama.p2;
        controller = new GameController(p1, p2, this);

        controller.setFrame(this);

        this.setTitle("Minesweeper");
        this.setLayout(null);
        this.setBounds((width - 800) / 2, (height - 600) / 2, 800, 600);
        setResizable(false);

        //设置积分板
        ScoreBoard p1Score = new ScoreBoard(p1, 70, 180);
        ScoreBoard p2Score = new ScoreBoard(p2, 70, 405);
        controller.setScoreBoard(p1Score, p2Score);

        minesweeper.GamePanel gamePanel = new minesweeper.GamePanel(initialPrama.xCount, initialPrama.yCount);
        this.add(gamePanel);
        this.add(p1Score);
        this.add(p2Score);

        //存档按钮
        JButton save = new JButton(Images.save0Img);
        add(save);
        save.setBorder(null);
        save.setBounds(610, 500, 80, 30);
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                save.setIcon(Images.saveImg);
            }
        });
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                save.setIcon(Images.save0Img);
            }
        });
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String fileName = JOptionPane.showInputDialog("Input Your Filename Here", "save");
                try {
                    controller.writeDataToFile(fileName);
                } catch (NullPointerException exception) {
                    System.exit(0);
                }
            }
        });


        //设置角色名称
        JLabel p1Name = new JLabel(p1.getUserName());
        p1Name.setBounds(70, 160, 130, 20);
        p1Name.setForeground(Color.WHITE);
        p1Name.setBackground(Color.black);

        JLabel p2Name = new JLabel(p2.getUserName());
        p2Name.setBounds(70, 385, 130, 20);
        p2Name.setForeground(Color.WHITE);
        p2Name.setBackground(Color.black);

        add(p1Name);
        add(p2Name);

        //设置角色头像
        JLabel p1Img = new JLabel(p1.getRoleImg());
        p1Img.setBounds(14, 65, 85, 85);

        JLabel p2Img = new JLabel(p2.getRoleImg());
        p2Img.setBounds(14, 290, 85, 85);
        this.add(p2Img);
        this.add(p1Img);
        //左旁栏

        JLabel bg = new JLabel(Images.zuopanglanImg);
        bg.setBounds(0, 30, 180, 450);
        this.add(bg);


        // 对战动态
        setLayout(null);
        controller.getTextArea().setText("Now it is " + initialPrama.onTurn.getUserName() + "'s turn.\n");
        JScrollPane jsp = new JScrollPane(controller.getTextArea());
        jsp.setBounds(620, 80, 185, 380);
        jsp.setBorder(null);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jsp);

        //右旁栏

        JLabel bg2 = new JLabel(Images.fightInformationImg);
        bg2.setBounds(610, 30, 180, 450);
        this.add(bg2);

        //设置按钮
        components.Button reStart = new Button(Images.reStart0Img, Images.reStartImg, 700, 500, 80, 30, this, "LoginFrame");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initialPrama.init();
            }
        });
        add(reStart);

        //作弊按钮
        JButton cheat = new JButton();
        cheat.setBounds(100, 5, 70, 20);
        cheat.setIcon(Images.cheating0Img);
        cheat.setMargin(new Insets(0, 0, 0, 0));
        cheat.setFocusPainted(true);
        cheat.setBorderPainted(false);
        cheat.setContentAreaFilled(false);
        setVisible(true);
        cheat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cheat.setIcon(Images.cheatingImg);
            }
        });
        cheat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                cheat.setIcon(Images.cheating0Img);
            }
        });

        cheat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (i%2){
                    case 1:
                        for(int i=0;i<initialPrama.xCount;i++){
                            for(int j=0;j<initialPrama.yCount;j++){
                                if((gamePanel.getMineField()[i][j].getContent()==-1||gamePanel.getMineField()[i][j].getContent()==-2)
                                        && gamePanel.getMineField()[i][j].getStatus() != GridStatus.Flag && gamePanel.getMineField()[i][j].getStatus() != GridStatus.Clicked){
                                    gamePanel.getMineField()[i][j].setStatus(GridStatus.Cheat);
                                    repaint();
                                }
                            }
                        }
                        i++;
                        break;
                    case 0:
                        for (int i = 0; i < initialPrama.xCount; i++) {
                            for (int j = 0; j < initialPrama.yCount; j++) {
                                if (gamePanel.getMineField()[i][j].getStatus() == GridStatus.Cheat) {
                                    gamePanel.getMineField()[i][j].setStatus(GridStatus.Covered);
                                    gamePanel.getMineField()[i][j].repaint();
                                }
                            }
                        }
                        i++;
                        break;
                }



            }
        });
        add(cheat);

        //设置背景
        Image smallImage = Images.MainBg2Img.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        Images.MainBg2Img.setImage(smallImage);
        JLabel background = new JLabel(Images.MainBg2Img);
        background.setBounds(0, 0, 800, 600);
        background.setVisible(true);
        add(background);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
