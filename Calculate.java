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
	
	public static void calculateBubble (Bubble bubble)
	{
		bubble.r = Math.sqrt(Math.pow(Settings.bubbleRadius, 2)*bubble.weight);
		bubble.y = bubble.y-(int)Settings.bubbleSpeed;
		bubble.moving = true;
	}
	
}