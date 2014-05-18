import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

class KeyInput extends KeyAdapter
{
	
	public void keyPressed (KeyEvent e)
	{
	}
	public void keyReleased (KeyEvent e)
	{
	}
	public void keyTyped (KeyEvent e)
	{
		Main.handleKeyType(e.getKeyChar());
	}
	
}