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
	
	public static Random random = new Random();
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
		Render.view.bubblesPath.append(new Arc2D.Double(bubble.x-bubble.r, bubble.y-bubble.r, bubble.r*2, bubble.r*2, 0, 360, Arc2D.OPEN), false);
	}
	
	public static void renderAsplodeBubble (Bubble bubble)
	{
		Render.view.asplodeBubblesPath1.append(new Arc2D.Double((bubble.r*2-6 >= 0 ? bubble.x-bubble.r+3 : bubble.x-bubble.r), (bubble.r*2-6 >= 0 ? bubble.y-bubble.r+3 : bubble.y-bubble.r), (bubble.r*2-6 >= 0 ? bubble.r*2-6 : 0), (bubble.r*2-6 >= 0 ? bubble.r*2-6 : 0), 90+bubble.d, 360-2*bubble.d, Arc2D.OPEN), false);
		Render.view.asplodeBubblesPath2.append(new Arc2D.Double(bubble.x-bubble.r+Math.sqrt(bubble.r)*(1-2*Render.random.nextDouble()), bubble.y-bubble.r, bubble.r*2, bubble.r*2, 90+bubble.d+Render.random.nextDouble()*10, 360-2*bubble.d-Render.random.nextDouble()*10, Arc2D.OPEN), false);
		Render.view.asplodeBubblesPath3.append(new Arc2D.Double(bubble.x-bubble.r, bubble.y-bubble.r, bubble.r*2, bubble.r*2, 90-bubble.d, 2*bubble.d, Arc2D.OPEN), false);
	}
	
	public static void renderLever (Lever lever)
	{
		Render.view.leversPath.moveTo(lever.x-lever.w/2, lever.y);
		Render.view.leversPath.lineTo(lever.x+lever.w-lever.w/2, lever.y);
		Render.view.leversPath.lineTo(lever.x+lever.w-lever.w/2, lever.y+lever.h);
		Render.view.leversPath.lineTo(lever.x-lever.w/2, lever.y+lever.h);
		Render.view.leversPath.lineTo(lever.x-lever.w/2, lever.y);
	}
	
}