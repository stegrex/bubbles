import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
	import java.awt.Rectangle;
import java.awt.Color;

class View extends JPanel
{
	
	public GeneralPath blocksPath = new GeneralPath();
	public GeneralPath bubblesPath = new GeneralPath();
	
	public Graphics g;
	public Graphics2D g2d;
	
	public void clear ()
	{
		this.blocksPath = new GeneralPath();
		this.bubblesPath = new GeneralPath();
	}
	
	public void paintComponent (Graphics g)
	{
		this.g = g;
		this.g2d = (Graphics2D) g;
		//Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		this.setBackground(Color.BLACK); // Setting background color.
		
		/*
		int[][] xMov = {{100, 100, 140, 140}, {100, 200, 140, 240}, {100, 100, 200, 200}}; // Arrays of all the vertice positions.
		int[][] yMov = {{100, 200, 80, 180}, {100, 100, 80, 80}, {100, 200, 100, 200}};
		int[] xPos = {100, 0, 40}; // Arrays of translations to be made from the above vertices.
		int[] yPos = {0, 100, -20};
		
		for (int i = 0; i < xPos.length; i++)
		{
			for (int x = 0; x < xMov[i].length; x++)
			{
				this.path.moveTo(xMov[i][x], yMov[i][x]); // Moves to a new vertice position.
				this.path.lineTo(xMov[i][x]+xPos[i], yMov[i][x]+yPos[i]); // Applies the corresponding translation movement.
			}
		}
		*/
		
		this.g2d.setPaint(Color.WHITE); // Sets the paint color.
		this.g2d.draw(this.blocksPath); // Draws the cube.
		
		//g2d.setPaint(Color.WHITE); // Sets the paint color.
		//g2d.draw(this.blocksPath); // Draws the cube.
		
	}
	
}