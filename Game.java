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
	public static Bubble[] bubbles1 = new Bubble[100];
	public static Bubble[] bubbles2 = new Bubble[50]; // Revisit. Is a second bubble object pool necessary?
	public static Bubble[] asplodeBubbles = new Bubble[50];
	public static Lever[] levers = new Lever[20];
	
	public static Random random = new Random();
	
	//public static double delta = 0; // Delta value for multiplication to every distance calculation per game frame.
	
	public Game ()
	{
		this.calculate = new Calculate();
		this.load();
		this.view = new View();
	}
	
	public void load ()
	{
		// Create level by positioning elements, based on level index
		Game.blocks[0] = new Block(200, 200, 100, 10);
		Game.blocks[1] = new Block(50, 100, 50, 10);
		//Game.slopedBlocks[0] = new SlopedBlock(150, 300, 250, 250, 0, 0); // Revisit. Last two params necessary?
		//Game.slopedBlocks[1] = new SlopedBlock(75, 150, 150, 250, 0, 0); // Revisit. Last two params necessary?
		
		Game.slopedBlocks[0] = new SlopedBlock(150, 300, 250, 290, 0, 0); // Revisit. Last two params necessary?
		Game.slopedBlocks[1] = new SlopedBlock(10, 200, 150, 250, 0, 0); // Revisit. Last two params necessary?
		Game.slopedBlocks[2] = new SlopedBlock(0, 135, 125, 80, 0, 0);
		Game.slopedBlocks[3] = new SlopedBlock(150, 200, 300, 300, 0, 0);
		
		Game.portals[0] = new Portal(140, 100, 20, 200, 180, 20);
		
		
		//Game.levers[0] = new Lever(200, 300, 50, 5, 15);
		//Game.levers[0].angle = 45; // Debug.
		
		//Game.levers[1] = new Lever(100, 200, 50, 5, 15);
		//Game.levers[1].angle = 0; // Debug.
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
	
	public void calculate (double delta)
	{
		// Iterate through the object pool and call the correct interact context between object types.
		this.calculate.setDelta(delta);
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
				// Calculate bubble - portal.
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
				///*
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
				//*/
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