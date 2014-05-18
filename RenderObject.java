// UNUSED!

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
	import java.awt.geom.Arc2D;
	import java.awt.Rectangle;
import java.awt.Color;
import java.util.Random;

class RenderObject
{
	
	public Color color;
	public GeneralPath path;
	public AffineTransform rotation;
	
	public RenderObject (Color color, GeneralPath path, AffineTransform rotation)
	{
		this.color = color;
		this.path = path;
		this.rotation = rotation;
	}
	
}