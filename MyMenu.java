

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
	
	DividableContainer owner;
	
	public MyMenu(DividableContainer param){
		super();
		System.out.println("new Menu");
		
		this.menuWindow=new JMenu("Window");
		this.add(this.menuWindow);
		this.splitH=new MyMenuItem("Split Horizontally");
		this.menuWindow.add(this.splitH);
		this.splitV=new MyMenuItem("Split Vertically");
		this.menuWindow.add(this.splitV);
		
		this.owner=param;
		
		this.setVisible(true);
	}
	private class MyMenuItem extends JMenuItem implements ActionListener {
		public MyMenuItem(String text) {
			super(text);
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			/* The owner is notified with the String that contains info about the button that was clicked. */
			owner.receiveButtonInstruction(e.getActionCommand());
		}
	}
}

