

	import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
/* This class describes the graphic panel that displays the universe
   (i.e. the planets, the rockets, ...). */
public class FlightPanel extends JPanel{
	/* The data that we want to display. */
	private Universe universe;
	double zoom, x0, y0;
	/* The parent Component. */
	private Component parent;
   
	public FlightPanel(Universe u, double zoom, double x0, double y0){
		super();
		this.universe=u;
		this.zoom=zoom;
		this.x0=x0;
		this.y0=y0;
		this.repaint();
	}
	public Universe getUniverse(){
       
		return this.universe;
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.red);
		g.fillRect(0, 0, 100, 100);
		g.setColor(Color.blue);
		g.fillOval(10, -15, 45, 55);
	}
}