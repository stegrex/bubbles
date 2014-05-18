class Calculate
{
	
	// Calculate holds:
		// Static methods that provide object-to-object state calculation
	
	public void calculateBlock (Block block)
	{
		//block.y -= 1;
		//return block;
	}
	
	private static double calculateBubbleRadius (Bubble bubble)
	{
		return Math.sqrt(Math.pow(Settings.bubbleRadius, 2)*bubble.weight);
	}
	
	public void calculateBubble (Bubble bubble)
	{
		bubble.r = Calculate.calculateBubbleRadius(bubble);
		bubble.y = bubble.y-Settings.bubbleSpeed*Game.delta;
		bubble.moving = true;
	}
	
	public boolean calculateBubbleBubble (Bubble bubble1, Bubble bubble2)
	{
		bubble1.r = Calculate.calculateBubbleRadius(bubble1);
		bubble2.r = Calculate.calculateBubbleRadius(bubble2);
		// Revisit. Should check bubble positions rather than weight for figuring out combination precedence.
		if ((bubble1.weight >= bubble2.weight) && (Math.sqrt(Math.pow(bubble1.x-bubble2.x, 2)+Math.pow(bubble1.y-bubble2.y, 2)) <= bubble1.r+bubble2.r))
		{
			double origWeight = bubble1.weight;
			bubble1.weight += bubble2.weight;
			bubble1.y += Math.sqrt(Math.pow(Settings.bubbleRadius, 2)*bubble2.weight);
			bubble1.x = (Settings.combineBubbleMode == 0 ? ((bubble1.x*origWeight+bubble2.x*bubble2.weight)/(origWeight+bubble2.weight)) : bubble2.x);
			return true;
		}
		return false;
	}
	
	public boolean calculateBubbleDestruct (Bubble bubble)
	{
		// Redo: Add random destruction points.
		//if (this.y <= (10+Math.random()*30) || (maxBubbleWeight >= 1 && this.weight >= maxBubbleWeight)) // Asplode the bubble if it's too big, or is leaving the play area.
		if (bubble.y <= 10 || (Settings.maxBubbleWeight >= 1 && bubble.weight >= Settings.maxBubbleWeight)) // Asplode the bubble if it's too big, or is leaving the play area.
		{
			bubble.asplode();
			return true;
		}
		return false;
	}
	
	public boolean calculateAsplodeBubble (Bubble bubble)
	{
		if (bubble.r >= bubble.rStart/15)
		{
			bubble.d += 2.2;
			bubble.r -= bubble.rStart/Settings.asplodeBubbleRate;
			bubble.y -= bubble.rStart/(Settings.asplodeBubbleRate*Game.delta);
		}
		else // Destruct the bubble once it is too small.
		{
			bubble.destruct();
		}
		return true;
	}
	
	public boolean calculateBubbleBlock (Bubble bubble, Block block)
	{
		bubble.r = Calculate.calculateBubbleRadius(bubble);
		if ((bubble.x >= block.x && bubble.x <= block.x+block.w) && (bubble.y >= block.y+block.h+bubble.r && bubble.y <= block.y+block.h+bubble.r+Settings.bubbleSpeed*Game.delta))
		{
			bubble.y = block.y+block.h+bubble.r;
			bubble.moving = false;
			return true;
		}
		return false;
	}
	
	public void calculateBubbleLever (Bubble bubble, Lever lever)
	{
		bubble.r = Calculate.calculateBubbleRadius(bubble);
		if ((bubble.x >= lever.x && bubble.x <= lever.x+lever.w/2) && (bubble.y >= lever.y+lever.h+bubble.r && bubble.y <= lever.y+lever.h+bubble.r+2))
		{
			if (bubble.moving)
			{
				lever.rightWeight += bubble.weight;
			}
			//returnVal = [x, y];
		}
		else if ((bubble.x >= lever.x-lever.w/2 && bubble.x <= lever.x) && (bubble.y >= lever.y+lever.h+bubble.r && bubble.y <= lever.y+lever.h+bubble.r+2))
		{
			if (bubble.moving)
			{
				lever.leftWeight += bubble.weight;
			}
			//returnVal = [x, y];
		}
		if (Math.abs(lever.leftWeight-lever.rightWeight) >= lever.inertia/2)
		{
			if (lever.leftWeight-lever.rightWeight > 0)
			{
				lever.angle = 1;
			}
			else
			{
				lever.angle = -1;
			}
		}
		if (Math.abs(lever.leftWeight-lever.rightWeight) >= lever.inertia)
		{
			lever.angle *= 2;
		}
	}
	
}