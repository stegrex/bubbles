import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
	//import java.awt.geom.Arc2D;
	import java.awt.Rectangle;
import java.awt.Color;

class View extends JPanel
{
	
	public GeneralPath blocksPath = new GeneralPath();
	public GeneralPath bubblesPath = new GeneralPath();
	public GeneralPath asplodeBubblesPath1 = new GeneralPath();
	public GeneralPath asplodeBubblesPath2 = new GeneralPath();
	public GeneralPath asplodeBubblesPath3 = new GeneralPath();
	public GeneralPath leversPath = new GeneralPath();
	public AffineTransform leverTransform = new AffineTransform();
	
	public Graphics g;
	public Graphics2D g2d;
	
	public void clear ()
	{
		this.blocksPath = new GeneralPath();
		this.bubblesPath = new GeneralPath();
		this.asplodeBubblesPath1 = new GeneralPath();
		this.asplodeBubblesPath2 = new GeneralPath();
		this.asplodeBubblesPath3 = new GeneralPath();
		this.leversPath = new GeneralPath();
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
		this.g2d.setPaint(Color.CYAN);
		this.g2d.draw(this.asplodeBubblesPath2); // Draws the bubbles.
		this.g2d.setPaint(Color.CYAN);
		this.g2d.draw(this.asplodeBubblesPath1); // Draws the bubbles.
		this.g2d.setPaint(Color.WHITE);
		this.g2d.draw(this.asplodeBubblesPath3); // Draws the bubbles.
		
		//leverTransform.translate(200, 300);
		this.leverTransform.rotate(Math.PI/10, 150, 300);
		this.g2d.setPaint(Color.WHITE);
		this.g2d.transform(leverTransform);
		this.g2d.draw(this.leversPath); // Draws the bubbles.
		
	}
	
}