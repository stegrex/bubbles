class Game
{
	
	// Calculate
	// Render
	
	// Game Component Objects
	
	// Game holds:
		// Object Pool
		// Loading methods
		// Control over object state changes
	
	public static Block[] blocks = new Block[50];
	public static Bubble[] bubbles1 = new Bubble[50];
	public static Bubble[] bubbles2 = new Bubble[50]; // Revisit. Is a second bubble object pool necessary?
	public static Bubble[] asplodeBubbles = new Bubble[50];
	public static Lever[] levers = new Lever[50];
	
	public Game ()
	{
		Game.load();
	}
	
	public static void load ()
	{
		// Create level by positioning elements, based on level index
		Game.blocks[0] = new Block(200, 200, 100, 10);
		Game.blocks[1] = new Block(50, 100, 50, 10);
		Game.levers[0] = new Lever(200, 300, 50, 5, 15);
	}
	
	public static void checkBubblePool () // Revisit. Destruction loop already written in main calculate.
	{
	}
	
	public static void createBubble (int x, int y)
	{
		// Create bubble object and add to bubble object pool.
		for (int i = 0; i < bubbles1.length; i++)
		{
			if (bubbles1[i] == null)
			{
				bubbles1[i] = new Bubble(x, y);
				break;
			}
		}
	}
	
	public static void createAsplodeBubble (Bubble bubble)
	{
		// Create bubble object and add to bubble object pool.
		for (int i = 0; i < asplodeBubbles.length; i++)
		{
			if (asplodeBubbles[i] == null)
			{
				asplodeBubbles[i] = bubble;
				break;
			}
		}
	}
	
	public static void calculate ()
	{
		// Iterate through the object pool and call the correct interact context between object types.
		
		// Iterate through bubbles object pool.
		for (int i = 0; i < bubbles1.length; i++)
		{
			if (bubbles1[i] != null)
			{
				boolean updated = false;
				// Calculate bubble - bubble.
				for (int i2 = 0; i2 < bubbles1.length; i2++)
				{
					if (bubbles1[i2] != null && bubbles1[i] != bubbles1[i2])
					{
						if (Calculate.calculateBubbleBubble(bubbles1[i], bubbles1[i2]) == true)
						{
							updated = true;
							bubbles1[i2] = null;
						}
					}
				}
				// Calculate bubble - block.
				for (int n = 0; n < blocks.length; n++)
				{
					if (blocks[n] != null)
					{
						updated = Calculate.calculateBubbleBlock(bubbles1[i], blocks[n]);
						if (updated == true)
						{
							break;
						}
					}
				}
				
				// Calculate asplode buddle.
				if (Calculate.calculateBubbleDestruct(bubbles1[i]) == true)
				{
					Game.createAsplodeBubble(bubbles1[i]);
					bubbles1[i] = null;
				}
				// Calculate bubble if no other updates.
				else if (updated == false)
				{
					Calculate.calculateBubble(bubbles1[i]);
				}
			}
		}
		
		// Iterate through Asplode bubbles pool.
		for (int i = 0; i < asplodeBubbles.length; i++)
		{
			if (asplodeBubbles[i] != null)
			{
				Calculate.calculateAsplodeBubble(asplodeBubbles[i]);
				if (asplodeBubbles[i].destroy == true)
				{
					asplodeBubbles[i] = null;
				}
			}
		}
	}
	
	public static void render ()
	{
		// Iterate through object pool and call the correct render method.
		
		// Clear the view.
		Render.clear();
		
		// Blocks
		for (int i = 0; i < blocks.length; i++)
		{
			if (blocks[i] != null)
			{
				Render.renderBlock(blocks[i]);
			}
		}
		
		// Bubbles
		for (int i = 0; i < bubbles1.length; i++)
		{
			if (bubbles1[i] != null)
			{
				Render.renderBubble(bubbles1[i]);
			}
		}
		
		// Asplode Bubbles
		for (int i = 0; i < asplodeBubbles.length; i++)
		{
			if (asplodeBubbles[i] != null)
			{
				Render.renderAsplodeBubble(asplodeBubbles[i]);
			}
		}
		
		// Levers
		for (int i = 0; i < levers.length; i++)
		{
			if (levers[i] != null)
			{
				Render.renderLever(levers[i]);
			}
		}
		
		Render.redraw();
	}
	
	// Debug
	public static void dumpGameState ()
	{
		int numBubbles = 0;
		for (int x = 0; x < bubbles1.length; x++)
		{
			if (bubbles1[x] != null)
			{
				numBubbles++;
				bubbles1[x].dumpObject();
			}
		}
		System.out.println(numBubbles);
	}
	
}