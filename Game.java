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
	
	public static void calculate ()
	{
		// Iterate through the object pool and call the correct interact context between object types.
		for (int x = 0; x < blocks.length; x++)
		{
			if (blocks[x] != null)
			{
				//blocks[x] = Calculate.calculateBlock(blocks[x]);
				Calculate.calculateBlock(blocks[x]);
			}
		}
		
	}
	
	public static void render ()
	{
		// Iterate through object pool and call the correct render context.
		//Render.render();
		Render.clear();
		
		for (int x = 0; x < blocks.length; x++)
		{
			if (blocks[x] != null)
			{
				Render.renderBlock(blocks[x]);
			}
		}
		
		Render.redraw();
		
	}
	
	// Debug
	public static void dumpGameState ()
	{
		for (int x = 0; x < blocks.length; x++)
		{
			if (blocks[x] != null)
			{
				blocks[x].dumpObject();
			}
		}
	}
	
}