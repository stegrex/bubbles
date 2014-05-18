import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
	import java.awt.geom.Arc2D;
	import java.awt.Rectangle;
import java.awt.Color;
import java.util.Random;

// This render version is specific to Swing.
class Render
{
	
	// View
	
	public Random random = new Random();
	public View view;
	
	public Render ()
	{
		JFrame frame = new JFrame("Bubbles");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.view = new View();
		frame.add(this.view);
		frame.setSize(Settings.canvasX, Settings.canvasY);
		frame.setVisible(true);
	}
	
	public void addMouseInput (MouseInput mouseInput)
	{
		this.view.addMouseListener(mouseInput);
	}
	
	public void redraw ()
	{
		this.view.repaint();
	}
	
	public void clear ()
	{
		this.view.clear();
	}
	
	// For each render method, pass in objects, and set the correct paths.
	public void renderBlock (Block block)
	{
		// Look at GeneralPath method append()
		this.view.blocksPath.moveTo(block.x, block.y);
		this.view.blocksPath.lineTo(block.x+block.w, block.y);
		this.view.blocksPath.lineTo(block.x+block.w, block.y+block.h);
		this.view.blocksPath.lineTo(block.x, block.y+block.h);
		this.view.blocksPath.lineTo(block.x, block.y);
	}
	
	public void renderBubble (Bubble bubble) // Pass in different Object types to be rendered.
	{
		// Revisit. Add random x motion if bubble is moving.
		//Shape bubbleShape = new Arc2D.Double();
		this.view.bubblesPath.append(new Arc2D.Double(bubble.x-bubble.r, bubble.y-bubble.r, bubble.r*2, bubble.r*2, 0, 360, Arc2D.OPEN), false);
		this.view.bubblesPath.append(new Arc2D.Double(bubble.x-bubble.r+0.25, bubble.y-bubble.r+0.25, bubble.r*2-0.5, bubble.r*2-0.5, 0, 360, Arc2D.OPEN), false); // Revisit. Making bubble line thicker.
	}
	
	public void renderAsplodeBubble (Bubble bubble)
	{
		this.view.asplodeBubblesPath1.append(new Arc2D.Double((bubble.r*2-6 >= 0 ? bubble.x-bubble.r+3 : bubble.x-bubble.r), (bubble.r*2-6 >= 0 ? bubble.y-bubble.r+3 : bubble.y-bubble.r), (bubble.r*2-6 >= 0 ? bubble.r*2-6 : 0), (bubble.r*2-6 >= 0 ? bubble.r*2-6 : 0), 90+bubble.d, 360-2*bubble.d, Arc2D.OPEN), false);
		this.view.asplodeBubblesPath2.append(new Arc2D.Double(bubble.x-bubble.r+Math.sqrt(bubble.r)*(1-2*this.random.nextDouble()), bubble.y-bubble.r, bubble.r*2, bubble.r*2, 90+bubble.d+this.random.nextDouble()*10, 360-2*bubble.d-this.random.nextDouble()*10, Arc2D.OPEN), false);
		this.view.asplodeBubblesPath3.append(new Arc2D.Double(bubble.x-bubble.r, bubble.y-bubble.r, bubble.r*2, bubble.r*2, 90-bubble.d, 2*bubble.d, Arc2D.OPEN), false);
	}
	
	public void renderLever (Lever lever)
	{
		this.view.leversPath.moveTo(lever.x-lever.w/2, lever.y);
		this.view.leversPath.lineTo(lever.x+lever.w-lever.w/2, lever.y);
		this.view.leversPath.lineTo(lever.x+lever.w-lever.w/2, lever.y+lever.h);
		this.view.leversPath.lineTo(lever.x-lever.w/2, lever.y+lever.h);
		this.view.leversPath.lineTo(lever.x-lever.w/2, lever.y);
	}
	
}