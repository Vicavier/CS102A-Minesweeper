package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Random;

public class TextField extends JTextField {
    public TextField(int x,int y,int width,int height,String text){
        setBounds(x,y,width,height);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setFont(new Font("Times New Roman",Font.BOLD,15));
        setText(text);
        /*addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                setText("");
            }
        });*/
    }
}
