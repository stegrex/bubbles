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
		bubble.y = bubble.y-(int)Settings.bubbleSpeed;
		bubble.moving = true;
	}
	
	public static boolean calculateBubbleBlock (Bubble bubble, Block block)
	{
		boolean returnVal = false;
		bubble.r = Calculate.calculateBubbleRadius(bubble);
		if ((bubble.x >= block.x && bubble.x <= block.x+block.w) && (bubble.y >= block.y+block.h+bubble.r && bubble.y <= block.y+block.h+bubble.r+1))
		{
			return true;
		}
		return returnVal;
	}
	
}