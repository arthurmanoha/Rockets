import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Container;
import java.awt.Color;
import java.util.Random;


public class Test{
   
    public static void main(String args[]){
       
        JFrame win=new JFrame();
       
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
        win.setSize(new Dimension(1480, 360));
       
        MyMenu  myMenu=new MyMenu();
        win.setJMenuBar(myMenu);
        win.add(new Timeline());
    }
}


