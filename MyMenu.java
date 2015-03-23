

 import javax.swing.JMenu;
 import javax.swing.JMenuBar;
 import javax.swing.JMenuItem;
 import javax.swing.event.MenuKeyListener;
 import javax.swing.event.MenuKeyEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.ActionEvent;

 public class MyMenu extends JMenuBar{
     JMenu menuWindow;
     JMenuItem splitH;
     JMenuItem splitV;
     JMenu menu2;
     JMenuItem item2_1;
     JMenuItem item2_2;
   
     public MyMenu(){
         super();
        System.out.println("new Menu");
         this.menuWindow=new JMenu("Window");
         this.add(this.menuWindow);
         this.splitH=new MyMenuItem("Split Horizontally");
         this.menuWindow.add(this.splitH);
         this.splitV=new MyMenuItem("Split Vertically");
         this.menuWindow.add(this.splitV);

         this.setVisible(true);
     }
     private class MyMenuItem extends JMenuItem implements ActionListener {
     public MyMenuItem(String text) {
         super(text);
         addActionListener(this);
     }
     public void actionPerformed(ActionEvent e) {
         System.out.println("Item clicked: "+e.getActionCommand());
     }
     }
 }

