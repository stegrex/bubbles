class Bubble
{
	
	public String type;
	public int index;
	public boolean isBlocked;
	public int x;
	public int y;
	public double r;
	public int weight;
	public boolean moving;
	public boolean asploding;
	
	// Asplode Bubble
	public double d;
	public double rStart;
	
	public Bubble (int x, int y)
	{
		this.type = "Bubble";
		this.x = x;
		this.y = y;
		this.r = 0;
		this.weight = ((Settings.bubbleWeight <= Settings.maxBubbleWeight-1 || Settings.maxBubbleWeight == 0) ? Settings.bubbleWeight : Settings.maxBubbleWeight-1);
		this.moving = true;
		this.asploding = false;
	}
	
	public void asplode ()
	{
		this.d = 0;
		this.r *= 2;
		this.rStart = this.r;
		this.y += this.r/4;
		this.destruct();
		this.asploding = true;
	}
	
	public void destruct ()
	{
		
	}
	
	public void dumpObject ()
	{
		System.out.println(this.type+": x"+this.x+" y"+this.y+" r"+this.r+" weight"+this.weight);
	}
	
}