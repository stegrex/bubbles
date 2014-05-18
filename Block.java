class Block
{
	
	public String type;
	public int index;
	public int x;
	public int y;
	public int w;
	public int h;
	
	public Block (int x, int y, int w, int h)
	{
		this.type = "Block";
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void dumpObject ()
	{
		System.out.println(this.type+": x"+this.x+" y"+this.y+" w"+this.w+" h"+this.h);
	}
	
}