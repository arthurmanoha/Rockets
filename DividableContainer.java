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
	JSplitPane split;
	DividableContainer leftComponent;
	DividableContainer rightComponent;
	JButton horizSplitButton;
	JButton verticSplitButton;
	JButton mergeButton;
	
	/* Elements linked to the game control. */
	/*	MouseListener mouseListener;
		Block focusedBlock; */
	
	
	public DividableContainer(JFrame window, Universe u, double zoom, double x0, double y0){
		this.window=window;
		this.setLayout(new BorderLayout());
		
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
		this.horizSplitButton.setVisible(false);
		this.verticSplitButton.setVisible(false);
	}
	
	
	private class SplitButtonAction implements ActionListener{
		
		private DividableContainer owner;
		private Dimension sizeofContainer;
		private int typeOfSplit; /* Possible values: JSplitPane.HORIZONTAL_SPLIT and JSplitPane.VERTICAL_SPLIT; */
		private JButton splitButton;
		
		/* A container may be splitted either horizontally or vertically,
		   this is specified by the value of the parameter 'orientation'. */
		public SplitButtonAction(DividableContainer owner, JButton splitButton, int typeOfSplit){
			this.owner=owner;
			this.typeOfSplit=typeOfSplit;
			this.splitButton=splitButton;
		}
		
		public void actionPerformed(ActionEvent e){
			/* We split the panel only if it is final. */
			if(this.owner.isFinal){

				/* The button that was clicked at the beginning must now be invisible
				   until the new two containers are merged. */
				this.owner.hideButtons();
				
				Universe u=this.owner.panel.getUniverse();
				
				this.owner.isFinal=false; /* Prevent any subsequent split. */
				double zoom=this.owner.panel.zoom;
				double x0=this.owner.panel.x0;
				double y0=this.owner.panel.y0;
				
				this.owner.leftComponent=new DividableContainer(this.owner.window, u, zoom, x0, y0);
				this.owner.rightComponent=new DividableContainer(this.owner.window, u, zoom, x0, y0);
				
				this.owner.split=new JSplitPane(typeOfSplit,
								true, /* Continuous layout. */
								this.owner.leftComponent,
								this.owner.rightComponent);
				this.owner.split.setLeftComponent(this.owner.leftComponent);
				this.owner.split.setRightComponent(this.owner.rightComponent);
				this.owner.split.setVisible(true);
				this.owner.add(this.owner.split, BorderLayout.CENTER);
				this.owner.leftComponent.setVisible(true);
				this.owner.rightComponent.setVisible(true);
				
				/* Manually resize the JFrame to force the repaint. */
				this.owner.window.setSize(new Dimension(
					this.owner.window.getSize().width+1,
					this.owner.window.getSize().height+1));
				this.owner.window.setSize(new Dimension(
					this.owner.window.getSize().width-1,
					this.owner.window.getSize().height-1));
				
				/* Center the split, using the size of the two new sub-components. */
				this.sizeofContainer=this.owner.getSize();
				if(this.typeOfSplit==JSplitPane.HORIZONTAL_SPLIT){
					this.owner.split.setDividerLocation(this.sizeofContainer.width/2);
				}
				else{
					this.owner.split.setDividerLocation(this.sizeofContainer.height/2);
				}
				
				
			}
		}	
	}
}
