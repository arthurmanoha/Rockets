
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyMenu extends JMenuBar{
	
	private DividableContainer owner;
	
	private JMenu menu1;
	private JMenuItem item1_1;
	private JMenuItem item1_2;
	private JMenu menu2;
	private JMenuItem item2_1;
	private JMenuItem item2_2;
	
	public MyMenu(DividableContainer ownerParam){
		super();
		
		this.owner=ownerParam;
		
		this.menu1=new JMenu("Menu1");
		this.add(this.menu1);
       
		this.item1_1=new MyMenuItem("MenuItem1.1");
		this.menu1.add(this.item1_1);
		this.item1_2=new MyMenuItem("MenuItem1.2");
		this.menu1.add(this.item1_2);
       
		this.menu2=new JMenu("Menu2");
		this.add(this.menu2);
       
		this.item2_1=new MyMenuItem("MenuItem2.1");
		this.menu2.add(this.item2_1);
		this.item2_2=new MyMenuItem("MenuItem2.2");
		this.menu2.add(this.item2_2);
       
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