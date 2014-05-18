//package stegrex.bubbles.game;

import java.awt.event.MouseEvent;
import java.util.Random;

class Game
{
	
	// Calculate
	// Render
	
	// Game Component Objects
	// Game Delta
	
	// Game holds:
		// Game Pool
		// Game loading methods
		// Control over object state changes
		// Current game delta value // Revisit. Needed?
	
	// Data State Components
	public GamePool gamePool;
	public GameLevel gameLevel; // Implement.
	public GameEvent[] gameEvents;
	public Random random = new Random(); // Revisit. Random used in multiple places.
	
	// View Components
	public View view;
	
	// UI Components
	public int lastPressedButton = 0;
	
	// Property Values
	public boolean gameRunning;
	public boolean eventPolling;
	public double defaultBubbleSpeed;
	public long initializeTime; // Revisit. Needed?
	
	//public static double delta = 0; // Revisit. Needed? Delta value for multiplication to every distance calculation per game frame.
	
	public Game ()
	{
		this.gamePool = new GamePool();
		this.gameLevel = new GameLevel();
		this.gameEvents = new GameEvent[10];
		
		this.view = new View();
		if (Settings.runSplashDemo == true)
		{
			this.createGameEvent(GameEvent.Type.SPLASH_DEMO, 5000); // Should be roughly 8 seconds.
			// this.load(1); // Cool. Might be a good way to load a level.
		}
		else
		{
			this.load(1);
		}
		this.eventPolling = true;
		this.gameRunning = true;
		//this.createGameEvent(GameEvent.Type.DEBUG_PAUSE, 5000); // Debug
		//this.createGameEvent(GameEvent.Type.DEBUG_RESUME, 10000); // Debug
	}
	
	public void load (int levelNumber)
	{
		this.gameRunning = false;
		this.unload();
		this.gameRunning = true;
		if (this.gameLevel == null)
		{
			this.gameLevel = new GameLevel();
		}
		//System.out.println("Level"+levelNumber); // Debug
		this.gameLevel.setCurrentLevel(levelNumber);
		this.gameLevel.loadPortals(this.gamePool.portals);
		this.gameLevel.loadBlocks(this.gamePool.blocks);
		this.gameLevel.loadSlopedBlocks(this.gamePool.slopedBlocks);
		this.gameLevel.loadBubbles1(this.gamePool.bubbles1);
		this.gameLevel.loadBubbles2(this.gamePool.bubbles2);
		this.gameLevel.loadAsplodeBubbles(this.gamePool.asplodeBubbles);
		this.gameLevel.loadLevers(this.gamePool.levers);
	}
	
	public void unload ()
	{
		this.gamePool.unload();
	}
	
	// Duration is in ms.
	public void createGameEvent (GameEvent.Type command, long duration)
	{
		for (int i = 0; i < this.gameEvents.length; i++)
		{
			if (this.gameEvents[i] == null)
			{
				switch (command)
				{
					case SPLASH_DEMO:
						this.defaultBubbleSpeed = Settings.bubbleSpeed;
						Settings.bubbleSpeed = 0.15;
						this.load(0);
						break;
					default:
						break;
				}
				this.gameEvents[i] = new GameEvent(command, duration);
				break;
			}
		}
	}
	
	public void runGameEvents ()
	{
		// Checking whether the Splash Demo should be run.
		for (int i = 0; i < this.gameEvents.length; i++)
		{
			// Debug
			/*
			if (this.gameEvents[i] != null)
			{
				this.gameEvents[i].dumpObject();
			}
			*/
			if (this.gameEvents[i] != null && System.currentTimeMillis() >= this.gameEvents[i].endTime && this.gameEvents[i].executed == false)
			{
				switch (this.gameEvents[i].command)
				{
					case SPLASH_DEMO:
						Settings.bubbleSpeed = this.defaultBubbleSpeed;
						this.load(1);
						break;
					// Debug
					/*
					case DEBUG_PAUSE:
						this.gameRunning = false;
						break;
					case DEBUG_RESUME:
						this.gameRunning = true;
						break;
					*/
					default:
						break;
				}
				this.gameEvents[i].execute();
				this.gameEvents[i] = null;
			}
		}
	}
	
	public void addMouseInput (MouseInput mouseInput)
	{
		this.view.addMouseInput(mouseInput);
	}
	
	// Revisit. Possibly put the UI methods into its own class.
	public void handleMousePress (int x, int y)
	{
		this.gamePool.turnOnReticle(x);
		this.lastPressedButton = MouseEvent.BUTTON1;
	}
	public void handleMouseRightPress (int x, int y)
	{
		this.gamePool.turnOffReticle();
		this.lastPressedButton = MouseEvent.BUTTON3;
	}
	public void handleMouseRelease (int x, int y)
	{
		if (this.gameRunning == true)
		{
			this.gamePool.createBubble(x, y);
		}
		this.gamePool.turnOffReticle();
		this.lastPressedButton = MouseEvent.NOBUTTON;
	}
	public void handleMouseDrag (int x, int y)
	{
		if (this.lastPressedButton == MouseEvent.BUTTON1)
		{
			this.gamePool.turnOnReticle(x);
		}
	}
	
	public void createRandomBubble ()
	{
		this.gamePool.createRandomBubble();
	}
	
	public void calculate (double delta, double lastDelta)
	{
		this.runGameEvents();
		//this.gamePool.dumpGameState(); // Debug
		if (this.gameRunning == true)
		{
			this.gamePool.calculate(delta, lastDelta);
		}
	}
	
	public void render ()
	{
		// Iterate through object pool and call the correct render method.
		this.view.setReticle(this.gamePool.reticle);
		
		this.view.setPortals(this.gamePool.portals);
		this.view.setBlocks(this.gamePool.blocks);
		this.view.setSlopedBlocks(this.gamePool.slopedBlocks);
		this.view.setBubbles1(this.gamePool.bubbles1);
		this.view.setAsplodeBubbles(this.gamePool.asplodeBubbles);
		this.view.setLevers(this.gamePool.levers);
		
		this.view.redraw();
	}
	
	// Debug
	public void dumpGameState ()
	{
		this.gamePool.dumpGameState();
	}
	
}