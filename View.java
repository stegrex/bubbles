import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
	//import java.awt.geom.Arc2D;
	import java.awt.Rectangle;
import java.awt.Color;

class View extends JPanel
{
	
	public GeneralPath blocksPath = new GeneralPath();
	public GeneralPath bubblesPath = new GeneralPath();
	public GeneralPath asplodeBubblesPath1 = new GeneralPath();
	
	public Graphics g;
	public Graphics2D g2d;
	
	public void clear ()
	{
		this.blocksPath = new GeneralPath();
		this.bubblesPath = new GeneralPath();
		this.asplodeBubblesPath1 = new GeneralPath();
	}
	
	public void paintComponent (Graphics g)
	{
		this.g = g;
		this.g2d = (Graphics2D) g;
		//Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		this.setBackground(Color.BLACK); // Setting background color.
		
		this.g2d.setPaint(Color.WHITE); // Sets the paint color.
		this.g2d.draw(this.blocksPath); // Draws the cube.
		this.g2d.setPaint(Color.CYAN);
		this.g2d.draw(this.bubblesPath); // Draws the bubbles.
		this.g2d.setPaint(Color.LIGHT_GRAY);
		this.g2d.draw(this.asplodeBubblesPath1); // Draws the bubbles.
	}
	
}