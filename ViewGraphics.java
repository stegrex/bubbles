//package stegrex.bubbles.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
	//import java.awt.geom.Arc2D;
	import java.awt.Rectangle;
import java.awt.Color;
import java.awt.RenderingHints;

class ViewGraphics extends JPanel
{
	
	public Render render = new Render();
	
	public Portal[] portals;
	public Block[] blocks;
	public SlopedBlock[] slopedBlocks;
	public Bubble[] bubbles1;
	public Bubble[] bubbles2;
	public Bubble[] asplodeBubbles;
	public Lever[] levers;
	
	public GeneralPath[] paths = new GeneralPath[500];
	
	public GeneralPath blocksPath = new GeneralPath();
	public GeneralPath slopedBlocksPath = new GeneralPath();
	public GeneralPath bubblesPath = new GeneralPath();
	public GeneralPath asplodeBubblesPath1 = new GeneralPath();
	public GeneralPath asplodeBubblesPath2 = new GeneralPath();
	public GeneralPath asplodeBubblesPath3 = new GeneralPath();
	public GeneralPath leversPath = new GeneralPath();
	public AffineTransform leverTransform = new AffineTransform();
	
	public Graphics g;
	public Graphics2D g2d;
	
	public boolean antialias;
	
	public void paintComponent (Graphics g)
	{
		this.g = g;
		this.g2d = (Graphics2D) g;
		this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		super.paintComponent(g);
		this.setBackground(Color.BLACK); // Set background color.
		
		// Handle static objects that can be processed in a single pool.
		this.g2d.setPaint(Color.RED);
		for (int x = 0; (this.portals != null && x < this.portals.length); x++)
		{
			if (this.portals[x] != null)
			{
				this.g2d.draw(this.render.renderPortal(this.portals[x]));
			}
		}
		this.g2d.setPaint(Color.WHITE);
		for (int x = 0; (this.blocks != null && x < this.blocks.length); x++)
		{
			if (this.blocks[x] != null)
			{
				this.g2d.draw(this.render.renderBlock(this.blocks[x]));
			}
		}
		this.g2d.setPaint(Color.CYAN);
		for (int x = 0; (this.slopedBlocks != null && x < this.slopedBlocks.length); x++)
		{
			if (this.slopedBlocks[x] != null)
			{
				this.g2d.draw(this.render.renderSlopedBlock(this.slopedBlocks[x]));
			}
		}
		this.g2d.setPaint(Color.CYAN);
		for (int x = 0; (this.bubbles1 != null && x < this.bubbles1.length); x++)
		{
			if (this.bubbles1[x] != null)
			{
				this.g2d.draw(this.render.renderBubble(this.bubbles1[x]));
			}
		}
		for (int x = 0; (this.asplodeBubbles != null && x < this.asplodeBubbles.length); x++)
		{
			if (this.asplodeBubbles[x] != null)
			{
				this.g2d.setPaint(Color.CYAN);
				this.g2d.draw(this.render.renderAsplodeBubble1(this.asplodeBubbles[x]));
				this.g2d.setPaint(Color.CYAN);
				this.g2d.draw(this.render.renderAsplodeBubble2(this.asplodeBubbles[x]));
				this.g2d.setPaint(Color.WHITE);
				this.g2d.draw(this.render.renderAsplodeBubble3(this.asplodeBubbles[x]));
			}
		}
		
		// Handle non static objects that need to be processed individually.
		this.g2d.setPaint(Color.CYAN);
		for (int x = 0; (this.levers != null && x < this.levers.length); x++)
		{
			if (this.levers[x] != null)
			{
				this.leverTransform = new AffineTransform();
				this.leverTransform.rotate(Math.PI*levers[x].angle/180, levers[x].x, levers[x].y);
				this.g2d.transform(this.leverTransform);
				this.g2d.draw(this.render.renderLever(this.levers[x]));
				this.leverTransform = new AffineTransform();
				this.leverTransform.rotate(-Math.PI*levers[x].angle/180, levers[x].x, levers[x].y);
				this.g2d.transform(this.leverTransform);
			}
		}
		
	}
	
}