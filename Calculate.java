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
	}
	
	public static boolean calculateBubbleBubble (Bubble bubble1, Bubble bubble2)
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
	
	// Redo. Needs to be mapped to asplode bubble stuff.
	public static boolean calculateBubbleDestruct (Bubble bubble)
	{
		// Redo
		//if (this.y <= (10+Math.random()*30) || (maxBubbleWeight >= 1 && this.weight >= maxBubbleWeight)) // Asplode the bubble if it's too big, or is leaving the play area.
		if (bubble.y <= 10 || (Settings.maxBubbleWeight >= 1 && bubble.weight >= Settings.maxBubbleWeight)) // Asplode the bubble if it's too big, or is leaving the play area.
		{
			//this.asplodePrepare();
			bubble.asplode(); // Redo. Using this for now for debug. Should use the bubble's asplode methods.
			return true;
		}
		return false;
	}
	
	public static boolean calculateAsplodeBubble (Bubble bubble)
	{
		if (bubble.r >= bubble.rStart/15)
		{
			bubble.d += 0.1;
			bubble.r -= bubble.rStart/Settings.asplodeBubbleRate;
			bubble.y -= bubble.rStart/Settings.asplodeBubbleRate;
			/*
			canvas.ctx.beginPath();
			canvas.ctx.strokeStyle = "LightSkyBlue";
			canvas.ctx.arc(this.x+Math.sqrt(this.r)-2*Math.random()*Math.sqrt(this.r), this.y, this.r, 1.5*Math.PI-this.d-Math.random()*1, 1.5*Math.PI+this.d+Math.random()*1, true);
			canvas.ctx.stroke();
			canvas.ctx.beginPath();
			canvas.ctx.strokeStyle = "PowderBlue";
			canvas.ctx.arc(this.x, this.y, (this.r-3 >= 0 ? this.r-3 : 0), 1.5*Math.PI-this.d, 1.5*Math.PI+this.d, true);
			canvas.ctx.stroke();
			canvas.ctx.beginPath();
			canvas.ctx.strokeStyle = "White";
			canvas.ctx.arc(this.x, this.y, this.r, 1.5*Math.PI+this.d, 1.5*Math.PI-this.d, true);
			canvas.ctx.stroke();
			*/
		}
		else // Destruct the bubble once it is too small.
		{
			bubble.destruct();
		}
		return true;
	}
	
	public static boolean calculateBubbleBlock (Bubble bubble, Block block)
	{
		bubble.r = Calculate.calculateBubbleRadius(bubble);
		if ((bubble.x >= block.x && bubble.x <= block.x+block.w) && (bubble.y >= block.y+block.h+bubble.r && bubble.y <= block.y+block.h+bubble.r+Settings.bubbleSpeed))
		{
			bubble.y = block.y+block.h+bubble.r;
			bubble.moving = false;
			return true;
		}
		return false;
	}
	
}