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
		// Object Pool
		// Game loading methods
		// Control over object state changes
		// Current game delta value
	
	public boolean gameRunning;
	
	public Calculate calculate;
	public View view;
	
	public Reticle reticle = new Reticle();
	
	public Portal[] portals = new Portal[20];
	public Block[] blocks = new Block[20];
	public SlopedBlock[] slopedBlocks = new SlopedBlock[20];
	public Bubble[] bubbles1 = new Bubble[50];
	public Bubble[] bubbles2 = new Bubble[50]; // Revisit. Is a second bubble object pool necessary?
	public Bubble[] asplodeBubbles = new Bubble[50];
	public Lever[] levers = new Lever[20];
	
	public GameEvent[] gameEvents = new GameEvent[10];
	
	public Random random = new Random(); // Revisit. Random used in multiple places.
	
	public GameLevel gameLevel; // Implement.
	
	public long initializeTime; // Revisit. Needed?
	
	public double defaultBubbleSpeed;
	
	public boolean fireBubbleSafety = false;
	public int lastPressedButton = 0;
	
	//public static double delta = 0; // Delta value for multiplication to every distance calculation per game frame.
	
	public Game ()
	{
		this.gameLevel = new GameLevel();
		this.calculate = new Calculate();
		this.view = new View();
		//this.initializeTime = System.currentTimeMillis(); // Revisit
		if (Settings.runSplashDemo == true)
		{
			this.createGameEvent(GameEvent.Type.SPLASH_DEMO, 500); // Should be roughly 8 seconds.
		}
		else
		{
			this.load(1);
		}
		this.gameRunning = true;
		//this.createGameEvent(GameEvent.Type.DEBUG_PAUSE, 5000); // Debug
	}
	
	public void load (int levelNumber)
	{
		this.unload();
		if (this.gameLevel == null)
		{
			this.gameLevel = new GameLevel();
		}
		//System.out.println("Level"+levelNumber); // Debug
		this.gameLevel.setCurrentLevel(levelNumber);
		this.gameLevel.loadPortals(this.portals);
		this.gameLevel.loadBlocks(this.blocks);
		this.gameLevel.loadSlopedBlocks(this.slopedBlocks);
		this.gameLevel.loadBubbles1(this.bubbles1);
		this.gameLevel.loadBubbles2(this.bubbles2);
		this.gameLevel.loadAsplodeBubbles(this.asplodeBubbles);
		this.gameLevel.loadLevers(this.levers);
	}
	
	public void unload ()
	{
		for (int x = 0; x < this.portals.length; x++)
		{
			this.portals[x] = null;
		}
		for (int x = 0; x < this.blocks.length; x++)
		{
			this.blocks[x] = null;
		}
		for (int x = 0; x < this.slopedBlocks.length; x++)
		{
			this.slopedBlocks[x] = null;
		}
		for (int x = 0; x < this.bubbles1.length; x++)
		{
			this.bubbles1[x] = null;
		}
		for (int x = 0; x < this.bubbles2.length; x++)
		{
			this.bubbles2[x] = null;
		}
		for (int x = 0; x < this.asplodeBubbles.length; x++)
		{
			this.asplodeBubbles[x] = null;
		}
		for (int x = 0; x < this.levers.length; x++)
		{
			this.levers[x] = null;
		}
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
			///*
			if (this.gameEvents[i] != null)
			{
				this.gameEvents[i].dumpObject();
			}
			//*/
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
		this.turnOnReticle(x);
		this.fireBubbleSafety = false;
		this.lastPressedButton = MouseEvent.BUTTON1;
	}
	public void handleMouseRightPress (int x, int y)
	{
		this.turnOffReticle();
		this.fireBubbleSafety = true;
		this.lastPressedButton = MouseEvent.BUTTON3;
	}
	public void handleMouseRelease (int x, int y)
	{
		this.turnOffReticle();
		if (this.fireBubbleSafety == false)
		{
			if (Settings.freeBubblePlacement == true)
			{
				this.createBubble(x, y);
			}
			else
			{
				this.createBubble(x, Settings.canvasY);
			}
		}
		this.fireBubbleSafety = false;
		this.lastPressedButton = MouseEvent.NOBUTTON;
	}
	public void handleMouseDrag (int x, int y)
	{
		if (this.lastPressedButton == MouseEvent.BUTTON1)
		{
			this.turnOnReticle(x);
		}
	}
	
	public void checkBubblePool () // Revisit. Destruction loop already written in main calculate.
	{
	}
	
	public void turnOnReticle (int x)
	{
		this.reticle.x = x;
		//this.reticle.y = 0; // Debug
		this.reticle.isOn = true;
	}
	
	public void turnOffReticle ()
	{
		this.reticle.isOn = false;
	}
	
	// Revisit. Possibly move this out to its own GamePool class.
	public void createBubble (int x, int y)
	{
		// Create bubble object and add to bubble object pool.
		for (int i = 0; i < this.bubbles1.length; i++)
		{
			if (this.bubbles1[i] == null)
			{
				this.bubbles1[i] = new Bubble(x, y);
				break;
			}
		}
		//this.dumpGameState(); // Debug
	}
	
	// Revisit. Possibly move this out to its own GamePool class.
	public void createRandomBubble ()
	{
		this.createBubble(this.random.nextInt(Settings.canvasX), this.random.nextInt(Settings.canvasY));
	}
	
	// Revisit. Possibly move this out to its own GamePool class.
	public void createAsplodeBubble (Bubble bubble)
	{
		// Create bubble object and add to bubble object pool.
		for (int i = 0; i < this.asplodeBubbles.length; i++)
		{
			if (this.asplodeBubbles[i] == null)
			{
				this.asplodeBubbles[i] = bubble;
				break;
			}
		}
	}
	
	// Revisit. Possibly move this out to its own GamePool class.
	public void calculate (double delta, double lastDelta)
	{
		
		this.runGameEvents();
		//this.dumpGameState(); // Debug
		
		// Calculate the reticle.
		
		this.reticle.y = 0;
		
		// Revisit. Possibly use this to leverage sorting objects into pools where the bubble y value is within the limits.
		for (int n = 0; n < this.bubbles1.length; n++)
		{
			if (this.bubbles1[n] != null)
			{
				this.calculate.calculateReticleBubble(this.reticle, this.bubbles1[n]);
			}
		}
		for (int n = 0; n < this.blocks.length; n++)
		{
			if (this.blocks[n] != null)
			{
				this.calculate.calculateReticleBlock(this.reticle, this.blocks[n]);
			}
		}
		for (int n = 0; n < this.portals.length; n++)
		{
			if (this.portals[n] != null)
			{
				this.calculate.calculateReticlePortal(this.reticle, this.portals[n]);
			}
		}
		for (int n = 0; n < this.slopedBlocks.length; n++)
		{
			if (this.slopedBlocks[n] != null)
			{
				this.calculate.calculateReticleSlopedBlock(this.reticle, this.slopedBlocks[n]);
			}
		}
		
		// Iterate through the object pool and call the correct interact context between object types.
		this.calculate.setDelta(delta);
		this.calculate.setLastDelta(lastDelta);
		// Iterate through bubbles object pool.
		for (int i = 0; i < this.bubbles1.length; i++)
		{
			if (this.bubbles1[i] != null)
			{
				boolean combined = false;
				boolean updated = false;
				// Calculate bubble - bubble.
				for (int n = 0; n < this.bubbles1.length; n++)
				{
					if (this.bubbles1[n] != null && this.bubbles1[i] != this.bubbles1[n])
					{
						combined = this.calculate.calculateBubbleBubble(this.bubbles1[i], this.bubbles1[n]);
						if (combined == true)
						{
							this.bubbles1[n] = null;
						}
					}
				}
				// Calculate bubble - block.
				for (int n = 0; n < this.blocks.length; n++)
				{
					if (this.blocks[n] != null)
					{
						updated = this.calculate.calculateBubbleBlock(this.bubbles1[i], this.blocks[n]);
						if (updated == true)
						{
							break;
						}
					}
				}
				// Calculate bubble - portal.
				if (updated == false)
				{
					for (int n = 0; n < this.portals.length; n++)
					{
						if (this.portals[n] != null)
						{
							updated = this.calculate.calculateBubblePortal(this.bubbles1[i], this.portals[n]);
							if (updated == true)
							{
								break;
							}
						}
					}
				}
				// Calculate bubble - sloped block.
				if (updated == false)
				{
					for (int n = 0; n < this.slopedBlocks.length; n++)
					{
						if (this.slopedBlocks[n] != null)
						{
							updated = this.calculate.calculateBubbleSlopedBlock(this.bubbles1[i], this.slopedBlocks[n]);
							if (updated == true)
							{
								break;
							}
						}
					}
				}
				
				// Calculate asplode buddle.
				if (this.calculate.calculateBubbleDestruct(this.bubbles1[i]) == true)
				{
					this.createAsplodeBubble(this.bubbles1[i]);
					this.bubbles1[i] = null;
				}
				// Calculate bubble if no other updates.
				else if (updated == false)
				{
					this.calculate.calculateBubble(this.bubbles1[i]);
				}
			}
		}
		
		// Iterate through Asplode bubbles pool.
		for (int i = 0; i < this.asplodeBubbles.length; i++)
		{
			if (this.asplodeBubbles[i] != null)
			{
				this.calculate.calculateAsplodeBubble(this.asplodeBubbles[i]);
				if (this.asplodeBubbles[i].destroy == true)
				{
					this.asplodeBubbles[i] = null;
				}
			}
		}
	}
	
	public void render ()
	{
		// Iterate through object pool and call the correct render method.
		
		//this.view.clear();
		
		this.view.setReticle(this.reticle);
		
		this.view.setPortals(this.portals);
		this.view.setBlocks(this.blocks);
		this.view.setSlopedBlocks(this.slopedBlocks);
		this.view.setBubbles1(this.bubbles1);
		this.view.setAsplodeBubbles(this.asplodeBubbles);
		this.view.setLevers(this.levers);
		
		this.view.redraw();
	}
	
	// Debug
	public void dumpGameState ()
	{
		int numBubbles = 0;
		for (int x = 0; x < this.bubbles1.length; x++)
		{
			if (this.bubbles1[x] != null)
			{
				numBubbles++;
				this.bubbles1[x].dumpObject();
			}
		}
		System.out.println(numBubbles);
	}
	
}