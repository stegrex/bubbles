//import org.json.JSONObject;


class GameLevel
{
	
	// Perhaps loading and saving game states can also be done in here.
	// Basically to save, iterate through each object in object pool, dump the parameters into JSON/XML.
	// To load, simply read through 
	
	//public JSONObject jsonObject;
	
	public int currentLevel;
	
	public GameLevel ()
	{
		this.currentLevel = 0;
		// Maybe use some sort of JSON/XML carrier for the game level objects.
	}
	
	public void load (int levelNumber) // Redo. Should not be void.
	{
		switch (levelNumber)
		{
			case 0:
				/*
				for (int x = 0; x < 50; x++)
				{
					Game.createBubble(x*(Settings.canvasX/50), x*(Settings.canvasY/50));
				}
				*/
				Game.createBubble(15*(Settings.canvasX/36), 1*Settings.canvasY/24+100);
				Game.createBubble(22*(Settings.canvasX/36), 1*Settings.canvasY/24+100);
				Game.createBubble(10*(Settings.canvasX/36), 2*Settings.canvasY/24+100);
				Game.createBubble(27*(Settings.canvasX/36), 2*Settings.canvasY/24+100);
				Game.createBubble(8*(Settings.canvasX/36), 3*Settings.canvasY/24+100);
				Game.createBubble(29*(Settings.canvasX/36), 3*Settings.canvasY/24+100);
				Game.createBubble(8*(Settings.canvasX/36), 4*Settings.canvasY/24+100);
				Game.createBubble(29*(Settings.canvasX/36), 4*Settings.canvasY/24+100);
				Game.createBubble(8*(Settings.canvasX/36), 5*Settings.canvasY/24+100);
				Game.createBubble(29*(Settings.canvasX/36), 5*Settings.canvasY/24+100);
				Game.createBubble(8*(Settings.canvasX/36), 6*Settings.canvasY/24+100);
				Game.createBubble(14*(Settings.canvasX/36), 6*Settings.canvasY/24+100);
				Game.createBubble(23*(Settings.canvasX/36), 6*Settings.canvasY/24+100);
				Game.createBubble(29*(Settings.canvasX/36), 6*Settings.canvasY/24+100);
				Game.createBubble(7*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				Game.createBubble(12*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				Game.createBubble(16*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				Game.createBubble(21*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				Game.createBubble(25*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				Game.createBubble(30*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				Game.createBubble(7*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				Game.createBubble(13*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				Game.createBubble(18*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				Game.createBubble(19*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				Game.createBubble(24*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				Game.createBubble(30*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				Game.createBubble(8*(Settings.canvasX/36), 9*Settings.canvasY/24+100);
				Game.createBubble(18*(Settings.canvasX/36), 9*Settings.canvasY/24+100);
				Game.createBubble(19*(Settings.canvasX/36), 9*Settings.canvasY/24+100);
				Game.createBubble(29*(Settings.canvasX/36), 9*Settings.canvasY/24+100);
				Game.createBubble(9*(Settings.canvasX/36), 10*Settings.canvasY/24+100);
				Game.createBubble(12*(Settings.canvasX/36), 10*Settings.canvasY/24+100);
				Game.createBubble(25*(Settings.canvasX/36), 10*Settings.canvasY/24+100);
				Game.createBubble(28*(Settings.canvasX/36), 10*Settings.canvasY/24+100);
				Game.createBubble(10*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				Game.createBubble(13*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				Game.createBubble(16*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				Game.createBubble(21*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				Game.createBubble(24*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				Game.createBubble(27*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				Game.createBubble(10*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				Game.createBubble(17*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				Game.createBubble(18*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				Game.createBubble(19*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				Game.createBubble(20*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				Game.createBubble(27*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				Game.createBubble(12*(Settings.canvasX/36), 13*Settings.canvasY/24+100);
				Game.createBubble(25*(Settings.canvasX/36), 13*Settings.canvasY/24+100);
				Game.createBubble(16*(Settings.canvasX/36), 14*Settings.canvasY/24+100);
				Game.createBubble(21*(Settings.canvasX/36), 14*Settings.canvasY/24+100);
				
				break;
			case 1:
				// Create level by positioning elements, based on level index
				Game.blocks[0] = new Block(250, 200, 100, 10);
				Game.blocks[1] = new Block(75, 100, 50, 10);
				//Game.slopedBlocks[0] = new SlopedBlock(150, 300, 250, 250, 0, 0); // Revisit. Last two params necessary?
				//Game.slopedBlocks[1] = new SlopedBlock(75, 150, 150, 250, 0, 0); // Revisit. Last two params necessary?
				
				Game.slopedBlocks[0] = new SlopedBlock(150, 300, 250, 290, 0, 0); // Revisit. Last two params necessary?
				Game.slopedBlocks[1] = new SlopedBlock(10, 200, 150, 250, 0, 0); // Revisit. Last two params necessary?
				Game.slopedBlocks[2] = new SlopedBlock(0, 135, 125, 80, 0, 0);
				Game.slopedBlocks[3] = new SlopedBlock(150, 200, 300, 300, 0, 0);
				
				//Game.portals[0] = new Portal(140, 100, 20, 200, 180, 20);
				Game.portals[0] = new Portal(150, 100, 10, 180, 140, 10);
				
				
				//Game.levers[0] = new Lever(200, 300, 50, 5, 15);
				//Game.levers[0].angle = 45; // Debug.
				
				//Game.levers[1] = new Lever(100, 200, 50, 5, 15);
				//Game.levers[1].angle = 0; // Debug.
				break;
		}
	}
	public void save () // Redo. Should not be void.
	{
		
	}
	
}