class SlopedBlock
{
	
	public String type;
	public int index;
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public int w; // Revisit. Necessary?
	public int h;
	
	public SlopedBlock (int x1, int y1, int x2, int y2, int w, int h)
	{
		this.type = "Block";
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.w = w;
		this.h = h;
	}
	
	public void dumpObject ()
	{
		//System.out.println(this.type+": x"+this.x+" y"+this.y+" w"+this.w+" h"+this.h);
	}
	
}