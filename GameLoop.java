import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class GameLoop
{
	
	// Game
	// MouseInput
	
	// GameLoop holds:
		// The main timer
		// The input interface
		// Methods to call the game's Object Pool
	
	public Game game;
	public MouseInput mouseInput;
	
	public boolean gameRunning;
	
	public GameLoop ()
	{
		this.game = new Game();
		this.game.dumpGameState(); // Debug
		this.mouseInput = new MouseInput();
		this.game.addMouseInput(mouseInput);
		this.gameRunning = true;
	}
	
	public void addMouseInput (MouseInput mouseInput)
	{
		this.game.addMouseInput(mouseInput);
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
			
			this.setDelta(delta);
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
	
	private void setDelta (double delta)
	{
		this.game.delta = delta;
	}
	
	private void calculate (double delta)
	{
		// Calculate the states of all the objects in the object pool.
		this.game.calculate();
	}
	
	private void render (double delta)
	{
		this.game.render();
		//System.out.println(delta);
	}
	
}