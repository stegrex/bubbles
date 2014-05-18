class Calculate
{
	
	// Calculate holds:
		// Static methods that provide object-to-object state calculation
	
	//public static Block calculateBlock (Block block)
	public static void calculateBlock (Block block)
	{
		//block.y -= 1;
		//return block;
	}
	
	private static double calculateBubbleRadius (Bubble bubble)
	{
		return Math.sqrt(Math.pow(Settings.bubbleRadius, 2)*bubble.weight);
	}
	
	public static void calculateBubble (Bubble bubble)
	{
		bubble.r = Calculate.calculateBubbleRadius(bubble);
		bubble.y = bubble.y-Settings.bubbleSpeed;
		bubble.moving = true;
		//if (this.y <= (10+Math.random()*30) || (maxBubbleWeight >= 1 && this.weight >= maxBubbleWeight)) // Asplode the bubble if it's too big, or is leaving the play area.
		if (bubble.y <= 10 || (Settings.maxBubbleWeight >= 1 && bubble.weight >= Settings.maxBubbleWeight)) // Asplode the bubble if it's too big, or is leaving the play area.
		{
			//this.asplodePrepare();
			bubble.destruct(); // Using this for now for debug.
		}
	}
	
	public static boolean calculateBubbleBubble (Bubble bubble1, Bubble bubble2)
	{
		bubble1.r = Calculate.calculateBubbleRadius(bubble1);
		bubble2.r = Calculate.calculateBubbleRadius(bubble2);
		if ((bubble1.weight >= bubble2.weight) && (Math.sqrt(Math.pow(bubble1.x-bubble2.x, 2)+Math.pow(bubble1.y-bubble2.y, 2)) <= bubble1.r+bubble2.r))
		{
			double origWeight = bubble1.weight;
			bubble1.weight += bubble2.weight;
			bubble1.y += Math.sqrt(Math.pow(Settings.bubbleRadius, 2)*bubble2.weight);
			bubble1.x = (0 == 0 ? ((bubble1.x*origWeight+bubble2.x*bubble2.weight)/(origWeight+bubble2.weight)) : bubble2.x);
			bubble2 = null; // Might not be necessary.
			return true;
		}
		return false;
	}
	
	public static boolean calculateBubbleBlock (Bubble bubble, Block block)
	{
		boolean returnVal = false;
		bubble.r = Calculate.calculateBubbleRadius(bubble);
		if ((bubble.x >= block.x && bubble.x <= block.x+block.w) && (bubble.y >= block.y+block.h+bubble.r && bubble.y <= block.y+block.h+bubble.r+1))
		{
			bubble.moving = false;
			return true;
		}
		return returnVal;
	}
	
}