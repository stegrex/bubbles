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
	public static Bubble[] bubbles2 = new Bubble[50];
	public static Bubble[] asplodeBubbles = new Bubble[50];
	//public static Lever[] levers = new Lever[50];
	
	public Game ()
	{
		Game.load();
	}
	
	public static void load ()
	{
		// Create level by positioning elements, based on level index
		Game.blocks[0] = new Block(200, 200, 100, 10);
		Game.blocks[1] = new Block(50, 100, 50, 10);
	}
	
	public static void checkBubblePool ()
	{
	}
	
	public static void createBubble (int x, int y)
	{
		// Debug
		int numBubbles = 0;
		// Debug
		for (int i = 0; i < bubbles1.length; i++)
		{
			if (bubbles1[i] == null)
			{
				bubbles1[i] = new Bubble(x, y);
				break;
			}
		}
		System.out.println(numBubbles);
	}
	
	public static void calculate ()
	{
		// Iterate through the object pool and call the correct interact context between object types.
		for (int i = 0; i < bubbles1.length; i++)
		{
			if (bubbles1[i] != null)
			{
				boolean updated = false;
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
				if (updated == false)
				{
					Calculate.calculateBubble(bubbles1[i]);
				}
				if (bubbles1[i].destroy == true)
				{
					bubbles1[i] = null;
				}
			}
		}
		
	}
	
	public static void render ()
	{
		// Iterate through object pool and call the correct render context.
		//Render.render();
		Render.clear();
		
		for (int i = 0; i < blocks.length; i++)
		{
			if (blocks[i] != null)
			{
				Render.renderBlock(blocks[i]);
			}
		}
		
		for (int i = 0; i < bubbles1.length; i++)
		{
			if (bubbles1[i] != null)
			{
				Render.renderBubble(bubbles1[i]);
			}
		}
		
		Render.redraw();
		
	}
	
	// Debug
	public static void dumpGameState ()
	{
		/*
		for (int x = 0; x < blocks.length; x++)
		{
			if (blocks[x] != null)
			{
				blocks[x].dumpObject();
			}
		}
		*/
		for (int x = 0; x < bubbles1.length; x++)
		{
			if (bubbles1[x] != null)
			{
				bubbles1[x].dumpObject();
			}
		}
	}
	
}