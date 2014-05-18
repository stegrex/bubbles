import java.util.Random;

class GamePool
{
	
	public Calculate calculate;
	
	public Random random = new Random(); // Revisit. Random used in multiple places.
	
	public Reticle reticle = new Reticle();
	public boolean fireBubbleSafety = false;
	
	public Portal[] portals = new Portal[20];
	public Block[] blocks = new Block[20];
	public SlopedBlock[] slopedBlocks = new SlopedBlock[20];
	public Bubble[] bubbles1 = new Bubble[50];
	public Bubble[] bubbles2 = new Bubble[50]; // Revisit. Is a second bubble object pool necessary?
	public Bubble[] asplodeBubbles = new Bubble[50];
	public Lever[] levers = new Lever[20];
	
	public GamePool ()
	{
		this.calculate = new Calculate();
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
	
	public void turnOnReticle (int x)
	{
		this.reticle.x = x;
		this.reticle.isOn = true;
		this.fireBubbleSafety = false;
	}
	
	public void turnOffReticle ()
	{
		this.reticle.isOn = false;
		this.fireBubbleSafety = true;
	}
	
	public void createBubble (int x, int y)
	{
		if (this.fireBubbleSafety == false)
		{
			// Create bubble object and add to bubble object pool.
			for (int i = 0; i < this.bubbles1.length; i++)
			{
				if (this.bubbles1[i] == null)
				{
					if (Settings.freeBubblePlacement == true)
					{
						this.bubbles1[i] = new Bubble(x, y);
					}
					else
					{
						this.bubbles1[i] = new Bubble(x, Settings.canvasY);
					}
					break;
				}
			}
		}
		this.fireBubbleSafety = false;
	}
	
	public void createRandomBubble ()
	{
		this.createBubble(this.random.nextInt(Settings.canvasX), this.random.nextInt(Settings.canvasY));
	}
	
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
	
	public void calculate (double delta, double lastDelta)
	{
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