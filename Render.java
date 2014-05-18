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
		/*
		JFrame frame = new JFrame("Bubbles");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.view = new View();
		frame.add(this.view);
		frame.setSize(Settings.canvasX, Settings.canvasY);
		frame.setVisible(true);
		*/
	}
	/*
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
	*/
	// For each render method, pass in objects, and set the correct paths.
	public GeneralPath renderBlock (Block block)
	{
		// Redo. Look at GeneralPath method append()
		GeneralPath path = new GeneralPath();
		path.moveTo(block.x, block.y);
		path.lineTo(block.x+block.w, block.y);
		path.lineTo(block.x+block.w, block.y+block.h);
		path.lineTo(block.x, block.y+block.h);
		path.lineTo(block.x, block.y);
		return path;
	}
	
	public GeneralPath renderBubble (Bubble bubble) // Pass in different Object types to be rendered.
	{
		// Revisit. Add random x motion if bubble is moving.
		//Shape bubbleShape = new Arc2D.Double();
		GeneralPath path = new GeneralPath();
		path.append(new Arc2D.Double(bubble.x-bubble.r, bubble.y-bubble.r, bubble.r*2, bubble.r*2, 0, 360, Arc2D.OPEN), false);
		path.append(new Arc2D.Double(bubble.x-bubble.r+0.25, bubble.y-bubble.r+0.25, bubble.r*2-0.5, bubble.r*2-0.5, 0, 360, Arc2D.OPEN), false); // Revisit. Making bubble line thicker.
		return path;
	}
	
	public GeneralPath renderAsplodeBubble1 (Bubble bubble)
	{
		GeneralPath path = new GeneralPath();
		path.append(new Arc2D.Double((bubble.r*2-6 >= 0 ? bubble.x-bubble.r+3 : bubble.x-bubble.r), (bubble.r*2-6 >= 0 ? bubble.y-bubble.r+3 : bubble.y-bubble.r), (bubble.r*2-6 >= 0 ? bubble.r*2-6 : 0), (bubble.r*2-6 >= 0 ? bubble.r*2-6 : 0), 90+bubble.d, 360-2*bubble.d, Arc2D.OPEN), false);
		return path;
	}
	public GeneralPath renderAsplodeBubble2 (Bubble bubble)
	{
		GeneralPath path = new GeneralPath();
		path.append(new Arc2D.Double(bubble.x-bubble.r+Math.sqrt(bubble.r)*(1-2*this.random.nextDouble()), bubble.y-bubble.r, bubble.r*2, bubble.r*2, 90+bubble.d+this.random.nextDouble()*10, 360-2*bubble.d-this.random.nextDouble()*10, Arc2D.OPEN), false);
		return path;
	}
	public GeneralPath renderAsplodeBubble3 (Bubble bubble)
	{
		GeneralPath path = new GeneralPath();
		path.append(new Arc2D.Double(bubble.x-bubble.r, bubble.y-bubble.r, bubble.r*2, bubble.r*2, 90-bubble.d, 2*bubble.d, Arc2D.OPEN), false);
		return path;
	}
	
	public GeneralPath renderLever (Lever lever)
	{
		GeneralPath path = new GeneralPath();
		path.moveTo(lever.x-lever.w/2, lever.y);
		path.lineTo(lever.x+lever.w-lever.w/2, lever.y);
		path.lineTo(lever.x+lever.w-lever.w/2, lever.y+lever.h);
		path.lineTo(lever.x-lever.w/2, lever.y+lever.h);
		path.lineTo(lever.x-lever.w/2, lever.y);
		return path;
	}
	
}