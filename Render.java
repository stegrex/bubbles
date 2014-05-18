//package stegrex.bubbles.view;

//import java.awt.Graphics;
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
		
	}
	
	// For each render method, pass in objects, and set the correct paths.
	public GeneralPath renderPortal (Portal portal)
	{
		GeneralPath path = new GeneralPath();
		path.append(new Arc2D.Double(portal.entranceX-portal.entranceW/2, portal.entranceY-portal.entranceW/2, portal.entranceW, portal.entranceW, 0, 180, Arc2D.OPEN), false);
		path.append(new Arc2D.Double(portal.exitX-portal.exitW/2, portal.exitY-portal.exitW/2, portal.exitW, portal.exitW, 180, 180, Arc2D.OPEN), false);
		return path;
	}
	public GeneralPath renderBlock (Block block)
	{
		// Redo. Look at GeneralPath method append()
		GeneralPath path = new GeneralPath();
		path.moveTo(block.x-block.w/2, block.y-block.h/2);
		path.lineTo(block.x+block.w/2, block.y-block.h/2);
		path.lineTo(block.x+block.w/2, block.y+block.h/2);
		path.lineTo(block.x-block.w/2, block.y+block.h/2);
		path.lineTo(block.x-block.w/2, block.y-block.h/2);
		return path;
	}
	
	public GeneralPath renderSlopedBlock (SlopedBlock slopedBlock)
	{
		// Redo. Look at GeneralPath method append()
		GeneralPath path = new GeneralPath();
		path.moveTo(slopedBlock.x1, slopedBlock.y1);
		path.lineTo(slopedBlock.x2, slopedBlock.y2);
		return path;
	}
	
	public GeneralPath renderBubble (Bubble bubble)
	{
		GeneralPath path = new GeneralPath();
		path.append(new Arc2D.Double(bubble.x-bubble.r+(bubble.moving ? 1.5*Math.sqrt(bubble.r)*(0.5-this.random.nextDouble()) : 0), bubble.y-bubble.r, bubble.r*2, bubble.r*2, 0, 360, Arc2D.OPEN), false);
		
		// Debug, random vertical just for fun:
		//path.append(new Arc2D.Double(bubble.x-bubble.r+(bubble.moving == true? Settings.bubbleWiggleRatio*Math.sqrt(bubble.r)*(0.5-this.random.nextDouble()) : 0), bubble.y-bubble.r+(bubble.moving == true ? Settings.bubbleWiggleRatio*Math.sqrt(bubble.r)*(0.5-this.random.nextDouble()) : 0), bubble.r*2, bubble.r*2, 0, 360, Arc2D.OPEN), false);
		//path.append(new Arc2D.Double(bubble.x-bubble.r+0.25, bubble.y-bubble.r+0.25, bubble.r*2-0.5, bubble.r*2-0.5, 0, 360, Arc2D.OPEN), false); // Debug. Making bubble line thicker for fun.
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
		path.append(new Arc2D.Double(bubble.x-bubble.r+Settings.asplodeWiggleRatio*Math.sqrt(bubble.r)*(0.5-1*this.random.nextDouble()), bubble.y-bubble.r+Settings.asplodeWiggleRatio*Math.sqrt(bubble.r)*(0.5-1*this.random.nextDouble()), bubble.r*2, bubble.r*2, 90+bubble.d+this.random.nextDouble()*10, 360-2*bubble.d-this.random.nextDouble()*10, Arc2D.OPEN), false);
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
		// Redo. Look at GeneralPath method append()
		GeneralPath path = new GeneralPath();
		path.moveTo(lever.x-lever.w/2, lever.y);
		path.lineTo(lever.x+lever.w-lever.w/2, lever.y);
		path.lineTo(lever.x+lever.w-lever.w/2, lever.y+lever.h);
		path.lineTo(lever.x-lever.w/2, lever.y+lever.h);
		path.lineTo(lever.x-lever.w/2, lever.y);
		return path;
	}
	
}