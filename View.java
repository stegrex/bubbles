//package stegrex.bubbles.view;

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
class View
{
	
	// ViewGraphics
	// ViewRender
	
	public Random random = new Random();
	public ViewGraphics viewGraphics;
	
	public View ()
	{
		JFrame frame = new JFrame("Bubbles");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.viewGraphics = new ViewGraphics();
		frame.add(this.viewGraphics);
		frame.setSize(Settings.canvasX, Settings.canvasY);
		frame.setVisible(true);
	}
	
	public void addMouseInput (MouseInput mouseInput)
	{
		this.viewGraphics.addMouseListener(mouseInput);
	}
	
	public void redraw ()
	{
		this.viewGraphics.repaint();
	}
	
	// Render pool set methods.
	public void setPortals (Portal[] portals)
	{
		this.viewGraphics.portals = portals;
	}
	public void setBlocks (Block[] blocks)
	{
		this.viewGraphics.blocks = blocks;
	}
	public void setSlopedBlocks (SlopedBlock[] slopedBlocks)
	{
		this.viewGraphics.slopedBlocks = slopedBlocks;
	}
	public void setBubbles1 (Bubble[] bubbles1)
	{
		this.viewGraphics.bubbles1 = bubbles1;
	}
	public void setBubbles2 (Bubble[] bubbles2)
	{
		this.viewGraphics.bubbles2 = bubbles2;
	}
	public void setAsplodeBubbles (Bubble[] asplodeBubbles)
	{
		this.viewGraphics.asplodeBubbles = asplodeBubbles;
	}
	public void setLevers (Lever[] levers)
	{
		this.viewGraphics.levers = levers;
	}
	
}