package components;

import entity.Images;

import javax.swing.*;
import java.awt.*;

public class TextArea extends JTextArea {
    public TextArea(){

        setText("");

        setEditable(false);
        setForeground(Color.black);
        setOpaque(false);
        setBounds(620,80,155,380);
    }

}
