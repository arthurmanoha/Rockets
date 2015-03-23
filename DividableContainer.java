import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
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
	private JSplitPane split;
	private DividableContainer leftComponent;
	private DividableContainer rightComponent;
	/*
	  JButton horizSplitButton;
	  JButton verticSplitButton;
	  JButton mergeButton;
	*/
	
	private MyMenu menu;
	
	/* Elements linked to the game control. */
	/*	MouseListener mouseListener;
		Block focusedBlock; */
	
	
	public DividableContainer(JFrame window, Universe u, double zoom, double x0, double y0){
		this.window=window;
		this.setLayout(new BorderLayout());
		
		/*
		  this.horizSplitButton=new JButton("h");
		  this.horizSplitButton.setSize(new Dimension(40, 30));
		  this.add(this.horizSplitButton, BorderLayout.WEST);
		  this.horizSplitButton.addActionListener(new SplitButtonAction(this, this.horizSplitButton, JSplitPane.HORIZONTAL_SPLIT));
		  this.horizSplitButton.setVisible(true);
		
		  this.verticSplitButton=new JButton("v");
		  this.verticSplitButton.setPreferredSize(new Dimension(40, 30));
		  this.add(this.verticSplitButton, BorderLayout.EAST);
		  this.verticSplitButton.addActionListener(new SplitButtonAction(this, this.verticSplitButton, JSplitPane.VERTICAL_SPLIT));
		  this.verticSplitButton.setVisible(true);
		*/
		
		this.menu=new MyMenu(this);
		this.add(this.menu);
		this.isFinal=true;
		this.panel=new FlightPanel(u, zoom, x0, y0);
		this.setPreferredSize(this.window.getSize());
	}
	
	public void paintComponent(Graphics g){
		if(this.panel!=null){
			this.panel.paintComponent(g);
		}
	}
	
	/* When a DividableContained is splitted, we want to hide the now useless buttons. */
	protected void hideButtons(){
		/*
		  this.horizSplitButton.setVisible(false);
		  this.verticSplitButton.setVisible(false);
		*/
	}
	

	/* Possible values: JSplitPane.HORIZONTAL_SPLIT and JSplitPane.VERTICAL_SPLIT; */
	public void toto(int typeOfSplit){

		DividableContainer owner;
		Dimension sizeofContainer=this.getSize();
		JButton splitButton;
		
		/* A container may be splitted either horizontally or vertically,
		   this is specified by the value of the parameter 'orientation'. */
		
		/* We split the panel only if it is final. */
		if(this.isFinal){

			/* The button that was clicked at the beginning must now be invisible
			   until the new two containers are merged. */
			this.hideButtons();
				
			Universe u=this.panel.getUniverse();
				
			this.isFinal=false; /* Prevent any subsequent split. */
			double zoom=this.panel.zoom;
			double x0=this.panel.x0;
			double y0=this.panel.y0;
				
			this.leftComponent=new DividableContainer(this.window, u, zoom, x0, y0);
			this.rightComponent=new DividableContainer(this.window, u, zoom, x0, y0);
			
			this.split=new JSplitPane(typeOfSplit,
						  true, /* Continuous layout. */
						  this.leftComponent,
						  this.rightComponent);
			this.split.setLeftComponent(this.leftComponent);
			this.split.setRightComponent(this.rightComponent);
			this.split.setVisible(true);
			this.add(this.split, BorderLayout.CENTER);
			this.leftComponent.setVisible(true);
			this.rightComponent.setVisible(true);
				
			/* Manually resize the JFrame to force the repaint. */
			this.window.setSize(new Dimension(
								this.window.getSize().width+1,
								this.window.getSize().height+1));
			this.window.setSize(new Dimension(
								this.window.getSize().width-1,
								this.window.getSize().height-1));
				
			/* Center the split, using the size of the two new sub-components. */
			sizeofContainer=this.getSize();
			if(typeOfSplit==JSplitPane.HORIZONTAL_SPLIT){
				this.split.setDividerLocation(sizeofContainer.width/2);
			}
			else{
				this.split.setDividerLocation(sizeofContainer.height/2);
			}
				
				
		}
	}	
}

