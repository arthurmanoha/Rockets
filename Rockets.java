

	import javax.swing.JFrame;
import java.awt.Dimension;
public class Rockets{
	public static void main(String args[]){
		int mainWidth=700;
		int mainHeight=500;
		JFrame mainWindow=new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.setSize(new Dimension(mainWidth, mainHeight));
		Universe universe=new Universe();
		DividableContainer divCont=new DividableContainer(mainWindow, universe, 1, 0, 0);
       
		mainWindow.add(divCont);
		mainWindow.setSize(new Dimension(mainWidth, mainHeight));
	}
}