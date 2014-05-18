import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
	import java.awt.geom.Arc2D;
	import java.awt.Rectangle;
import java.awt.Color;

class Render
{
	
	// View
	
	public static View view;
	
	public Render ()
	{
		JFrame frame = new JFrame("Bubbles");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Render.view = new View();
		frame.add(view);
		frame.setSize(Settings.canvasX, Settings.canvasY);
		frame.setVisible(true);
		//view.addMouseListener(new MouseInput());
	}
	
	public void addMouseInput (MouseInput mouseInput)
	{
		view.addMouseListener(mouseInput);
	}
	
	public static void redraw ()
	{
		Render.view.repaint();
	}
	
	public static void clear ()
	{
		Render.view.clear();
	}
	
	// For each render method, pass in objects, and set the correct paths.
	public static void renderBlock (Block block)
	{
		// Look at GeneralPath method append()
		Render.view.blocksPath.moveTo(block.x, block.y);
		Render.view.blocksPath.lineTo(block.x+block.w, block.y);
		Render.view.blocksPath.lineTo(block.x+block.w, block.y+block.h);
		Render.view.blocksPath.lineTo(block.x, block.y+block.h);
		Render.view.blocksPath.lineTo(block.x, block.y);
	}
	
	public static void renderBubble (Bubble bubble) // Pass in different Object types to be rendered.
	{
		//Shape bubbleShape = new Arc2D.Double();
		Render.view.bubblesPath.append(new Arc2D.Double(bubble.x, bubble.y, (int)bubble.r, (int)bubble.r, 0, 360, Arc2D.OPEN), false);
	}
	
	public static void renderBubbleAsplode (Bubble bubble)
	{
		
	}
	
}