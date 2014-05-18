class Main
{
	
	// GameLoop
	
	// Main holds:
		// Instantiation of the GameLoop
	
	//public static Game game;
	//public static View view;
	//public static Input input;
	public static GameLoop gameLoop;
	
	public static void main (String args[])
	{
		
		Main.gameLoop = new GameLoop();
		gameLoop.gameRunning = true;
		gameLoop.loop();
		
	}
	
}