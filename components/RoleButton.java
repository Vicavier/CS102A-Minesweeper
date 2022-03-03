package components;
import entity.Images;
import entity.Player;
import entity.initialPrama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RoleButton extends JButton {
    boolean whetherClicked=false;

    public RoleButton(int player,ImageIcon img, ImageIcon choose, int x, int y,  ArrayList<RoleButton> whichList){
        Image R1 = img.getImage().getScaledInstance(105, 105, Image.SCALE_DEFAULT);
        img.setImage(R1);
        setIcon(img);
        setBounds(x,y, 85, 85);
        setVisible(true);
        setBorder(null);

        onMouseEntered(choose);
        onMouseExit(img);
        onMouseClicked(player,img,choose,whichList);



    }

    public void onMouseEntered(ImageIcon choose){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!whetherClicked){
                    setIcon(choose);
                }
            }
        });
    }
    public void onMouseExit(ImageIcon origin){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if(!whetherClicked){
                    setIcon(origin);
                }
            }
        });
    }
    public void onMouseClicked(int player,ImageIcon img, ImageIcon choose, ArrayList<RoleButton> list){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!whetherClicked){
                    setIcon(choose);
                    if(player==1)
                        initialPrama.p1Role=img;
                    else if(player==2)
                        initialPrama.p2Role=img;
                    for (RoleButton rb:list) {
                        rb.whetherClicked=true;
                    }
                }
            }
        });
    }

}
