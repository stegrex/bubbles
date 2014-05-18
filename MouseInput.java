import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MouseInput implements MouseListener
{
	
	public UserInput userInput;
	
	//public MouseInput (String inputType)
	//{
		//this.userInput = new UserInput(inputType);
	//}
	
	public void mouseClicked (MouseEvent e)
	{
	}
	public void mousePressed (MouseEvent e)
	{
	}
	public void mouseReleased (MouseEvent e)
	{
		Game.handleMouseClick(e.getX(), e.getY());
	}
	public void mouseEntered (MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public void mouseDragged (MouseEvent e)
	{
	}
	public void mouseMoved (MouseEvent e)
	{
	}
}