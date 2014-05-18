//package stegrex.bubbles.classes;

class Portal
{
	
	public String type; // Revisit. Probably not needed.
	public int index; // Revisit. Probably not needed.
	public int entranceX;
	public int entranceY;
	public int entranceW;
	public int exitX;
	public int exitY;
	public int exitW;
	
	public Portal (int entranceX, int entranceY, int entranceW, int exitX, int exitY, int exitW)
	{
		this.type = "Portal";
		this.entranceX = entranceX;
		this.entranceY = entranceY;
		this.entranceW = entranceW;
		this.exitX = exitX;
		this.exitY = exitY;
		this.exitW = exitW;
	}
	
	public void dumpObject ()
	{
		//System.out.println(this.type+": x"+this.x+" y"+this.y+" w"+this.w+" h"+this.h);
	}
	
}