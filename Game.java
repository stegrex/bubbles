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
	public Render render;
	
	public static Block[] blocks = new Block[50];
	public static Bubble[] bubbles1 = new Bubble[50];
	public static Bubble[] bubbles2 = new Bubble[50]; // Revisit. Is a second bubble object pool necessary?
	public static Bubble[] asplodeBubbles = new Bubble[50];
	public static Lever[] levers = new Lever[50];
	
	public static double delta = 0;
	
	public Game ()
	{
		this.calculate = new Calculate();
		this.render = new Render();
		this.load();
	}
	
	public void load ()
	{
		// Create level by positioning elements, based on level index
		Game.blocks[0] = new Block(200, 200, 100, 10);
		Game.blocks[1] = new Block(50, 100, 50, 10);
		Game.levers[0] = new Lever(200, 300, 50, 5, 15);
	}
	
	public void addMouseInput (MouseInput mouseInput)
	{
		this.render.addMouseInput(mouseInput);
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
	
	public void calculate ()
	{
		// Iterate through the object pool and call the correct interact context between object types.
		
		// Iterate through bubbles object pool.
		for (int i = 0; i < Game.bubbles1.length; i++)
		{
			if (Game.bubbles1[i] != null)
			{
				boolean updated = false;
				// Calculate bubble - bubble.
				for (int i2 = 0; i2 < Game.bubbles1.length; i2++)
				{
					if (Game.bubbles1[i2] != null && Game.bubbles1[i] != Game.bubbles1[i2])
					{
						if (this.calculate.calculateBubbleBubble(Game.bubbles1[i], Game.bubbles1[i2]) == true)
						{
							updated = true;
							Game.bubbles1[i2] = null;
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
		
		// Clear the view.
		this.render.clear();
		
		// Blocks
		for (int i = 0; i < Game.blocks.length; i++)
		{
			if (Game.blocks[i] != null)
			{
				this.render.renderBlock(Game.blocks[i]);
			}
		}
		
		// Bubbles
		for (int i = 0; i < Game.bubbles1.length; i++)
		{
			if (Game.bubbles1[i] != null)
			{
				this.render.renderBubble(Game.bubbles1[i]);
			}
		}
		
		// Asplode Bubbles
		for (int i = 0; i < Game.asplodeBubbles.length; i++)
		{
			if (Game.asplodeBubbles[i] != null)
			{
				this.render.renderAsplodeBubble(Game.asplodeBubbles[i]);
			}
		}
		
		// Levers
		for (int i = 0; i < Game.levers.length; i++)
		{
			if (Game.levers[i] != null)
			{
				this.render.renderLever(Game.levers[i]);
			}
		}
		
		this.render.redraw();
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