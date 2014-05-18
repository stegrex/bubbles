//package stegrex.bubbles.game;

import java.util.Random;

class Game
{
	
	// Calculate
	// Render
	
	// Game Component Objects
	// Game Delta
	
	// Game holds:
		// Object Pool
		// Loading methods
		// Control over object state changes
		// Current game delta value
	
	public Calculate calculate;
	public View view;
	
	public static Portal[] portals = new Portal[20];
	public static Block[] blocks = new Block[20];
	public static SlopedBlock[] slopedBlocks = new SlopedBlock[20];
	public static Bubble[] bubbles1 = new Bubble[50];
	public static Bubble[] bubbles2 = new Bubble[50]; // Revisit. Is a second bubble object pool necessary?
	public static Bubble[] asplodeBubbles = new Bubble[50];
	public static Lever[] levers = new Lever[20];
	
	public static Random random = new Random();
	
	public GameLevel gameLevel; // Implement.
	
	public long initializeTime;
	public boolean splashDemoLoaded = false;
	
	public double defaultBubbleSpeed;
	
	//public static double delta = 0; // Delta value for multiplication to every distance calculation per game frame.
	
	public Game ()
	{
		this.gameLevel = new GameLevel();
		this.calculate = new Calculate();
		this.view = new View();
		this.initializeTime = System.currentTimeMillis();
		this.runSplashDemo();
	}
	
	public void runSplashDemo ()
	{
		if (this.splashDemoLoaded == false)
		{
			this.defaultBubbleSpeed = Settings.bubbleSpeed;
			Settings.bubbleSpeed = 0.15;
			this.load(0);
			this.splashDemoLoaded = true;
		}
		if (System.currentTimeMillis() >= this.initializeTime+10000)
		{
			Settings.bubbleSpeed = this.defaultBubbleSpeed;
			this.splashDemoLoaded = false;
			this.initializeTime = 0;
			this.unload();
			this.load(1);
		}
	}
	
	public void load (int levelNumber)
	{
		if (this.gameLevel == null)
		{
			this.gameLevel = new GameLevel();
		}
		System.out.println("Level"+levelNumber); // Debug
		this.gameLevel.load(levelNumber);
	}
	
	public void unload ()
	{
		for (int x = 0; x < Game.portals.length; x++)
		{
			Game.portals[x] = null;
		}
		for (int x = 0; x < Game.blocks.length; x++)
		{
			Game.blocks[x] = null;
		}
		for (int x = 0; x < Game.slopedBlocks.length; x++)
		{
			Game.slopedBlocks[x] = null;
		}
		for (int x = 0; x < Game.bubbles1.length; x++)
		{
			Game.bubbles1[x] = null;
		}
		for (int x = 0; x < Game.bubbles2.length; x++)
		{
			Game.bubbles2[x] = null;
		}
		for (int x = 0; x < Game.asplodeBubbles.length; x++)
		{
			Game.asplodeBubbles[x] = null;
		}
		for (int x = 0; x < Game.levers.length; x++)
		{
			Game.levers[x] = null;
		}
	}
	
	public void addMouseInput (MouseInput mouseInput)
	{
		this.view.addMouseInput(mouseInput);
	}
	
	public static void handleMouseClick (int x, int y)
	{
		Game.createBubble(x, y);
		//Game.dumpGameState(); // Debug
	}
	
	public void checkBubblePool () // Revisit. Destruction loop already written in main calculate.
	{
	}
	
	public static void createBubble (int x, int y)
	{
		// Create bubble object and add to bubble object pool.
		for (int i = 0; i < Game.bubbles1.length; i++)
		{
			if (Game.bubbles1[i] == null)
			{
				Game.bubbles1[i] = new Bubble(x, y);
				break;
			}
		}
	}
	
	public static void createRandomBubble ()
	{
		Game.createBubble(Game.random.nextInt(Settings.canvasX), Game.random.nextInt(Settings.canvasY));
	}
	
	public void createAsplodeBubble (Bubble bubble)
	{
		// Create bubble object and add to bubble object pool.
		for (int i = 0; i < Game.asplodeBubbles.length; i++)
		{
			if (Game.asplodeBubbles[i] == null)
			{
				Game.asplodeBubbles[i] = bubble;
				break;
			}
		}
	}
	
	public void calculate (double delta, double lastDelta)
	{
		
		// Checking whether the Splash Demo should be run.
		if (this.splashDemoLoaded == true)
		{
			this.runSplashDemo();
		}
		
		//Game.dumpGameState(); // Debug
		// Iterate through the object pool and call the correct interact context between object types.
		this.calculate.setDelta(delta);
		this.calculate.setLastDelta(lastDelta);
		// Iterate through bubbles object pool.
		for (int i = 0; i < Game.bubbles1.length; i++)
		{
			if (Game.bubbles1[i] != null)
			{
				boolean combined = false;
				boolean updated = false;
				// Calculate bubble - bubble.
				for (int n = 0; n < Game.bubbles1.length; n++)
				{
					if (Game.bubbles1[n] != null && Game.bubbles1[i] != Game.bubbles1[n])
					{
						combined = this.calculate.calculateBubbleBubble(Game.bubbles1[i], Game.bubbles1[n]);
						if (combined == true)
						{
							Game.bubbles1[n] = null;
						}
					}
				}
				// Calculate bubble - block.
				for (int n = 0; n < Game.blocks.length; n++)
				{
					if (this.blocks[n] != null)
					{
						updated = this.calculate.calculateBubbleBlock(Game.bubbles1[i], Game.blocks[n]);
						if (updated == true)
						{
							break;
						}
					}
				}
				// Calculate bubble - portal.
				if (updated == false)
				{
					for (int n = 0; n < Game.portals.length; n++)
					{
						if (this.portals[n] != null)
						{
							updated = this.calculate.calculateBubblePortal(Game.bubbles1[i], Game.portals[n]);
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
					for (int n = 0; n < Game.slopedBlocks.length; n++)
					{
						if (this.slopedBlocks[n] != null)
						{
							updated = this.calculate.calculateBubbleSlopedBlock(Game.bubbles1[i], Game.slopedBlocks[n]);
							if (updated == true)
							{
								break;
							}
						}
					}
				}
				
				// Calculate asplode buddle.
				if (this.calculate.calculateBubbleDestruct(Game.bubbles1[i]) == true)
				{
					this.createAsplodeBubble(Game.bubbles1[i]);
					Game.bubbles1[i] = null;
				}
				// Calculate bubble if no other updates.
				else if (updated == false)
				{
					this.calculate.calculateBubble(Game.bubbles1[i]);
				}
			}
		}
		
		// Iterate through Asplode bubbles pool.
		for (int i = 0; i < Game.asplodeBubbles.length; i++)
		{
			if (this.asplodeBubbles[i] != null)
			{
				this.calculate.calculateAsplodeBubble(Game.asplodeBubbles[i]);
				if (Game.asplodeBubbles[i].destroy == true)
				{
					Game.asplodeBubbles[i] = null;
				}
			}
		}
	}
	
	public void render ()
	{
		// Iterate through object pool and call the correct render method.
		
		//this.view.clear();
		
		this.view.setPortals(this.portals);
		this.view.setBlocks(this.blocks);
		this.view.setSlopedBlocks(this.slopedBlocks);
		this.view.setBubbles1(this.bubbles1);
		this.view.setAsplodeBubbles(this.asplodeBubbles);
		this.view.setLevers(this.levers);

		this.view.redraw();
	}
	
	// Debug
	public static void dumpGameState ()
	{
		int numBubbles = 0;
		for (int x = 0; x < Game.bubbles1.length; x++)
		{
			if (Game.bubbles1[x] != null)
			{
				numBubbles++;
				Game.bubbles1[x].dumpObject();
			}
		}
		System.out.println(numBubbles);
	}
	
}