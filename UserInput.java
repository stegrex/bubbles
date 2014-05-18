// UNUSED!

class UserInput
{
	
	public String inputType;
	
	public int mouseClickX;
	public int mouseClickY;
	
	public UserInput (String inputType)
	{
		this.inputType = inputType;
	}
	
	public void handleMouseClick (int x, int y)
	{
		this.mouseClickX = x;
		this.mouseClickY = y;
		
		if (this.inputType == "main")
		{
			Game.handleMouseClick (x, y);
		}
	}
	
}