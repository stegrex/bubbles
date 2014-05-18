import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class GameLoop
{
	
	// Game
	// Render
	// MouseInput
	
	// GameLoop holds:
		// The main timer
		// The frontend
		// The input interface
		// Methods to call the game's Object Pool
	
	public boolean gameRunning;
	
	public Render render;
	public static MouseInput mouseInput;
	
	public GameLoop ()
	{
		Game.load();
		Game.dumpGameState(); // Debug
		
		this.render = new Render();
		this.mouseInput = new MouseInput();
		this.render.addMouseInput(mouseInput);
	}
	
	public void loop ()
	{
		long lastLoopTime = System.nanoTime();
		final int targetFPS = 300;
		final long timePerRender = 1000000000/targetFPS;
		int calcCount = 0;
		
		while (this.gameRunning == true)
		{
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			
			double delta = updateLength/(double)timePerRender;
			
			this.calculate(delta);
			
			calcCount++;
			if (calcCount == Settings.msPerRender)
			{
				this.render(delta);
				calcCount = 0;
			}
			
			try
			{
				Thread.sleep((lastLoopTime-System.nanoTime() + timePerRender)/1000000);
			}
			catch (Exception e)
			{
				this.loop();
			}
		}
	}
	
	public static void handleMouseClicked (int x, int y)
	{
		//System.out.println(x+"::"+y); // Debug
		Game.dumpGameState(); // Debug
		Game.createBubble(x, y);
	}
	
	private void calculate (double delta)
	{
		// Calculate the states of all the objects in the object pool.
		Game.calculate();
	}
	
	private void render (double delta)
	{
		Game.render();
		//System.out.println(delta);
	}
	
}