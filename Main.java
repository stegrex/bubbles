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
	
	public static void handleMouseClick (int x, int y)
	{
		Main.gameLoop.handleMouseClick(x, y);
	}
	
}