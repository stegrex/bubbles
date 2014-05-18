//package stegrex.bubbles.game;

class Main
{
	
	// GameLoop
	
	// Main holds:
		// Instantiation of the GameLoop
		// Input interface.
	
	public static GameLoop gameLoop;
	public static MouseInput mouseInput;
	
	public static void main (String args[])
	{
		Main.gameLoop = new GameLoop();
		Main.mouseInput = new MouseInput();
		Main.gameLoop.addMouseInput(Main.mouseInput);
		Main.gameLoop.loop();
	}
	
	public static void handleMousePress (int x, int y)
	{
		Main.gameLoop.handleMousePress(x, y);
	}
	public static void handleMouseRightPress (int x, int y)
	{
		Main.gameLoop.handleMouseRightPress(x, y);
	}
	public static void handleMouseRelease (int x, int y)
	{
		Main.gameLoop.handleMouseRelease(x, y);
	}
	public static void handleMouseDrag (int x, int y)
	{
		Main.gameLoop.handleMouseDrag(x, y);
	}
	
}