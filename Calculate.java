//package stegrex.bubbles.game;

class Calculate
{
	
	// Calculate holds:
		// Static methods that provide object-to-object state calculation
	
	public double delta;
	
	public Calculate ()
	{
		this.delta = 1;
	}
	
	public void setDelta (double delta)
	{
		this.delta = delta;
	}
	
	public void calculateBlock (Block block) // Revisit. Possibly unused.
	{
		//block.y -= 1;
		//return block;
	}
	
	private double calculateBubbleRadius (Bubble bubble)
	{
		return Math.sqrt(Math.pow(Settings.bubbleRadius, 2)*bubble.weight);
	}
	
	public void calculateBubble (Bubble bubble)
	{
		bubble.r = this.calculateBubbleRadius(bubble);
		bubble.y = bubble.y-Settings.bubbleSpeed*this.delta;
		bubble.moving = true;
	}
	
	public boolean calculateBubbleBubble (Bubble bubble1, Bubble bubble2)
	{
		bubble1.r = this.calculateBubbleRadius(bubble1);
		bubble2.r = this.calculateBubbleRadius(bubble2);
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
			bubble.y -= bubble.rStart/(Settings.asplodeBubbleRate*this.delta);
		}
		else // Destruct the bubble once it is too small.
		{
			bubble.destruct();
		}
		return true;
	}
	
	public boolean calculateBubblePortal (Bubble bubble, Portal portal)
	{
		bubble.r = this.calculateBubbleRadius(bubble); // Revisit. Probably not necessary to make so many calculations per update. Perhaps calculate during calculateBubbleBubble only.
		if ((bubble.x >= portal.entranceX && bubble.x <= portal.entranceX+portal.entranceW) && (bubble.y >= portal.entranceY+portal.entranceW/2+bubble.r && bubble.y <= portal.entranceY+portal.entranceW/2+bubble.r+Settings.bubbleSpeed*this.delta))
		{
			bubble.x = portal.exitX+portal.exitW/2;
			bubble.y = portal.exitY-portal.exitW/2-bubble.r;
			//bubble.moving = true;
			return true;
		}
		return false;
	}
	
	public boolean calculateBubbleBlock (Bubble bubble, Block block)
	{
		bubble.r = this.calculateBubbleRadius(bubble);
		if ((bubble.x >= block.x && bubble.x <= block.x+block.w) && (bubble.y >= block.y+block.h+bubble.r && bubble.y <= block.y+block.h+bubble.r+Settings.bubbleSpeed*this.delta))
		{
			bubble.y = block.y+block.h+bubble.r;
			bubble.moving = false;
			return true;
		}
		return false;
	}
	
	public boolean calculateBubbleSlopedBlock (Bubble bubble, SlopedBlock slopedBlock)
	{
		bubble.r = this.calculateBubbleRadius(bubble);
		// Revisit. Write out a better explanation of what's happening.
		double slope = (double)(slopedBlock.y2-slopedBlock.y1)/(slopedBlock.x2-slopedBlock.x1);
		double perpendicularSlope = 0-1/slope;
		double lineConstant = slopedBlock.y1-slope*slopedBlock.x1;
		
		double bufferSpace = 100; // Debug. Buffer space for stop calculation.
		
		//if (bubble.x >= slopedBlock.x1+Math.sqrt(bubble.r*(bubble.r-Math.pow(slope, 2)+1)) && bubble.x <= slopedBlock.x2+Math.sqrt(bubble.r*(bubble.r-Math.pow(slope, 2)+1))) // Debug
		if (bubble.x >= slopedBlock.x1 && bubble.x <= slopedBlock.x2)
		{
			
			//System.out.println(slope); // Debug
			if (bubble.y < (slope*bubble.x+lineConstant+bubble.r*Math.sqrt(Math.pow(slope, 2)+1)+Settings.bubbleSpeed*this.delta) && bubble.y >= (slope*bubble.x+lineConstant+bubble.r*Math.sqrt(Math.pow(slope, 2)+1)-bubble.r))
			//if (bubble.y <= (slope*bubble.x+lineConstant+bubble.r*Math.sqrt(Math.pow(slope, 2)+1)+Settings.bubbleSpeed*this.delta))
			{
				double bubbleSpeedRatio = (double)(slopedBlock.y2-slopedBlock.y1)/(Math.abs(slopedBlock.y2-slopedBlock.y1)+Math.abs(slopedBlock.x2-slopedBlock.x1));
				//System.out.println(bubbleSpeedRatio+" :: "+slope); // Debug
				// REDO!
				//bubble.x = slope > 0 ? bubble.x+perpendicularSlope*Settings.bubbleSpeed*this.delta : (slope < 0 ? bubble.x+perpendicularSlope*Settings.bubbleSpeed*this.delta : bubble.x); // Debug
				if (bubbleSpeedRatio != 0) {
					bubble.x = bubble.x-Settings.bubbleSpeed*bubbleSpeedRatio*this.delta;
				}
				bubble.y = slope*bubble.x+lineConstant+bubble.r*Math.sqrt(Math.pow(slope, 2)+1);
				bubble.moving = false;
				return true;
			}
			return false;
		}
		return false;
	}
	
	public void calculateBubbleLever (Bubble bubble, Lever lever)
	{
		bubble.r = this.calculateBubbleRadius(bubble);
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