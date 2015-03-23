
	import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/* This file describes the tool that allows the user to visualize
   all the commands graphically sorted by time.
   Features:
   -Click-select any instant to set the simulation to;
   -Scroll and zoom;
   -Add, set, move and remove commands that will be sent to the rocket
   at a specified time.
*/
public class Timeline extends JPanel{
	private float x0;
	private float zoom;
	private Universe universe;
	private ActionListener listener;
	public Timeline(){
		this.x0=0;
		this.zoom=1;
		this.listener=new TimelineMouseListener();
	}
	private class TimelineMouseListener implements ActionListener{
		public TimelineMouseListener(){
			super();
		}
		public void actionPerformed(ActionEvent e){
			// TODO: Deal with the event.
		}
	}
}
