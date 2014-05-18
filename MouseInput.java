//package stegrex.bubbles.interaction;

import java.awt.event.MouseEvent;
//import javax.swing.event.MouseInputListener;
import javax.swing.event.MouseInputAdapter;
//import java.awt.event.MouseAdapter;

class MouseInput extends MouseInputAdapter
{
	
	//public UserInput userInput;
	
	//public MouseInput (String inputType)
	//{
		//this.userInput = new UserInput(inputType);
	//}
	
	public void mouseClicked (MouseEvent e)
	{
	}
	public void mouseEntered (MouseEvent e)
	{
	}
	public void mouseExited (MouseEvent e)
	{
	}
	public void mousePressed (MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			Main.handleMousePress(e.getX(), e.getY());
		}
		else if (e.getButton() == MouseEvent.BUTTON3)
		{
			Main.handleMouseRightPress(e.getX(), e.getY());
		}
	}
	public void mouseReleased (MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			Main.handleMouseRelease(e.getX(), e.getY());
		}
	}
	public void mouseDragged (MouseEvent e)
	{
		//if (e.getButton() == MouseEvent.BUTTON1) // Debug.
		{
			Main.handleMouseDrag(e.getX(), e.getY());
		}
	}
	public void mouseMoved (MouseEvent e)
	{
	}
}