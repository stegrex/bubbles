//package stegrex.bubbles.game;

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
	
	double lastDelta = 0;
	
	public boolean gameRunning;
	
	public GameLoop ()
	{
		this.game = new Game();
		//this.game.dumpGameState(); // Debug
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
			
			double lastDelta = this.lastDelta;
			double delta = updateLength/(double)timePerRender;
			
			if (Settings.autoShoot == 1)
			{
				for (int x = 0; x < 100; x++)
				{
					Game.createRandomBubble();
				}
			}
			
			this.lastDelta = delta;
			this.calculate(delta, lastDelta);
			calcCount++;
			if (calcCount == Settings.msPerRender)
			{
				this.render(delta);
				calcCount = 0;
			}
			
			try
			{
				//System.out.println(lastLoopTime-System.nanoTime()+timePerRender); // Debug
				Thread.sleep((lastLoopTime-System.nanoTime() + timePerRender)/1000000);
				//Thread.sleep(timePerRender/1000000); // Debug.
			}
			catch (Exception e)
			{
				this.loop();
			}
			
		}
	}
	
	private void calculate (double delta, double lastDelta) // Revisit. Variable lastDelta might not be needed.
	{
		// Calculate the states of all the objects in the object pool.
		this.game.calculate(delta, lastDelta);
	}
	
	private void render (double delta)
	{
		this.game.render();
		//System.out.println(delta);
	}
	
}