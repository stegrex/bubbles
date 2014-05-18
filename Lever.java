//package stegrex.bubbles.classes;


class Lever
{
	public String type;
	public int index;
	public double x;
	public double y;
	public double w;
	public double h;
	public int inertia;
	public int leftWeight;
	public int rightWeight;
	public boolean moving;
	public int angle;
	
	public Lever (int x, int y, int w, int h, int inertia)
	{
		this.type = "Lever";
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.inertia = inertia;
		this.leftWeight = 0;
		this.rightWeight = 0;
		this.moving = false;
		this.angle = 0;
	}
	
	public void dumpObject ()
	{
		System.out.println(this.type+": x"+this.x+" y"+this.y+" w"+this.w+" h"+this.h);
	}
	
}