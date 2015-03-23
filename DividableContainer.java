

import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Container;
/* This class describes the panel that will display the universe in which the
   rockets and planets will evolve. */
public class DividableContainer extends Container{
	/* The window that contains the DividableContainer. */
	private JFrame window;
	/* The JPanel that is contained if this DividableContainer is final
	   (if not, the DividableContainer contains two additional DividableContainers). */
	private FlightPanel panel;
	/* The characteristics linked to the panel separation. */
	private boolean isFinal;
	JSplitPane split;
	DividableContainer leftComponent;
	DividableContainer rightComponent;
   
	MyMenu menu;
   
	/* Elements linked to the game control. */
	/* MouseListener mouseListener;
	   Block focusedBlock; */
	public DividableContainer(JFrame window, Universe u, double zoom, double x0, double y0){
		super();
		System.out.println("new DivCont");
		this.window=window;
		//     this.setLayout(new GridLayout(2, 1));
		this.setLayout(new BorderLayout());
   
		this.menu=new MyMenu(this);
		this.menu.setPreferredSize(new Dimension(100, 25));
		this.menu.setSize(new Dimension(100, 25));
		this.add(menu, BorderLayout.NORTH);
   
		this.isFinal=true;
		this.panel=new FlightPanel(u, zoom, x0, y0);
		this.setPreferredSize(this.window.getSize());
		this.add(this.panel);
		this.repaint();
		this.panel.repaint();
	}
	public void paintComponent(Graphics g){
		System.out.println("DivCont.repaint()");
		if(this.panel!=null){
			this.panel.paintComponent(g);
		}
	}
	/* When a DividableContained is splitted, we want to hide the now useless buttons. */
	protected void hideButtons(){
		this.menu.setVisible(false);
	}
	
	
	public void receiveButtonInstruction(String command){
		System.out.println("DivCont receive "+command);
			
	
		DividableContainer owner;
		Dimension sizeofContainer;
		int typeOfSplit; /* Possible values: JSplitPane.HORIZONTAL_SPLIT and JSplitPane.VERTICAL_SPLIT; */

		/* A container may be splitted either horizontally or vertically,
		   this is specified by the value of the parameter 'orientation'. */
		
		/* We split the panel only if it is final. */
		if(this.isFinal){
			System.out.println("split because not final.");
			/* The button that was clicked at the beginning must now be invisible
			   until the new two containers are merged. */
			this.hideButtons();
			
			Universe u=this.panel.getUniverse();
			this.isFinal=false; /* Prevent any subsequent split. */
			double zoom=this.panel.zoom;
			double x0=this.panel.x0;
			double y0=this.panel.y0;
			this.panel=null;
			
			sizeofContainer=this.getSize();
			
			if(command.contentEquals("Split Horizontally")){
				typeOfSplit=JSplitPane.HORIZONTAL_SPLIT;
			}
			else{
				typeOfSplit=JSplitPane.VERTICAL_SPLIT;
			}
			
			this.leftComponent=new DividableContainer(this.window, u, zoom, x0, y0);
			this.rightComponent=new DividableContainer(this.window, u, zoom, x0, y0);
			this.split=new JSplitPane(typeOfSplit,
						  true, /* Continuous layout. */
						  this.leftComponent,
						  this.rightComponent);
			this.split.setOneTouchExpandable(true);
			/*
			  this.split.setLeftComponent(this.leftComponent);
			  this.split.setRightComponent(this.rightComponent);
			*/
			this.split.setVisible(true);
			this.add(this.split);
			
			/* Center the split, using the size of the two new sub-components. */
			sizeofContainer=this.getSize();
			if(command.contentEquals("Split Horizontally")){
				this.split.setDividerLocation(sizeofContainer.width/2);
			}
			else{
				this.split.setDividerLocation(sizeofContainer.height/2);
			}
			
			this.leftComponent.setVisible(true);
			this.rightComponent.setVisible(true);
			
			/* Manually resize the JFrame to force the repaint. */
			this.window.setSize(new Dimension(
								this.window.getSize().width+1,
								this.window.getSize().height+1));
			this.window.setSize(new Dimension(
								this.window.getSize().width-1,
								this.window.getSize().height-1));

		}
		else{
			System.out.println("no split because final.");
		}
	}
}