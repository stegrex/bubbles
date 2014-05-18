import java.util.*;
//import java.util.Scanner;

class GameLevel
{
	
	// Loading and saving game states can also be done in here (possibly as an object, class defined elsewhere).
	// Basically to save, iterate through each object in object pool, dump the parameters into JSON.
	
	private String levelsJSON;
	private BubblesJSONObject levelsObject;
	private BubblesJSONObject currentLevelObject;
	
	private BubblesJSONObject bubblesJSONObject;
	private BubblesJSONParser bubblesJSONParser;
	public int currentLevel;
	
	public GameLevel ()
	{
		Scanner fileScanner = new Scanner(this.getClass().getResourceAsStream("/"+"levels"));
		while (fileScanner.hasNext()) // Iterates through the lines of the file.
		{
			this.levelsJSON += fileScanner.nextLine();
		}
		BubblesJSONParser bubblesJSONParser = new BubblesJSONParser(this.levelsJSON);
		this.levelsObject = bubblesJSONParser.parseString();
		//System.out.println(this.levelsObject.toFormattedString()); // Debug
		this.currentLevel = 0;
	}
	
	public void setCurrentLevel (int levelNumber)
	{
		this.currentLevel = levelNumber;
		this.currentLevelObject = this.levelsObject.getObject("LEVELS").getObject(levelNumber); // Revisit. Define the key constants?
		//System.out.println(this.currentLevelObject.toFormattedString()); // Debug
	}
	
	private boolean isObjectValidForIteration (String keyType)
	{
		if (this.currentLevelObject.doesKeyExist(keyType) == true && this.currentLevelObject.getObject(keyType).isObjectEmpty() == false)
		{
			return true;
		}
		return false;
	}
	
	private Set<Object> getCurrentLevelObjectKeySet (String keyType)
	{
		return this.currentLevelObject.getObject(keyType).getKeySet();
	}
	
	private Object getObjectParameter (String type, Object key, String parameter)
	{
		return (this.currentLevelObject.getObject(type).getObject(key).get(parameter));
	}
	
	public void loadPortals (Portal[] portals)
	{
		String type = "PORTALS";
		if (this.isObjectValidForIteration(type))
		{
			for (Object x : (this.getCurrentLevelObjectKeySet(type)))
			{
				portals[(Integer)x] = new Portal((Integer)getObjectParameter(type, x, "entranceX"), (Integer)getObjectParameter(type, x, "entranceY"), (Integer)getObjectParameter(type, x, "entranceW"), (Integer)getObjectParameter(type, x, "exitX"), (Integer)getObjectParameter(type, x, "exitY"), (Integer)getObjectParameter(type, x, "exitW"));
			}
		}
	}
	public void loadBlocks (Block[] blocks)
	{
		String type = "BLOCKS";
		if (this.isObjectValidForIteration(type))
		{
			for (Object x : (this.getCurrentLevelObjectKeySet(type)))
			{
				//System.out.println((Integer)(getObjectParameter(type, x, "x"))); // Debug
				blocks[(Integer)x] = new Block((Integer)getObjectParameter(type, x, "x"), (Integer)getObjectParameter(type, x, "y"), (Integer)getObjectParameter(type, x, "w"), (Integer)getObjectParameter(type, x, "h"));
			}
		}
	}
	public void loadSlopedBlocks (SlopedBlock[] slopedBlocks)
	{
		switch (this.currentLevel)
		{
			case 0:
				break;
			case 1:
				//slopedBlocks[0] = new SlopedBlock(150, 300, 250, 290, 0, 0); // Revisit. Last two params necessary?
				//slopedBlocks[1] = new SlopedBlock(10, 200, 150, 250, 0, 0); // Revisit. Last two params necessary?
				slopedBlocks[2] = new SlopedBlock(0, 135, 125, 80, 0, 0);
				//slopedBlocks[3] = new SlopedBlock(150, 200, 300, 300, 0, 0);
				break;
		}
	}
	public void loadBubbles1 (Bubble[] bubbles1)
	{
		switch (this.currentLevel)
		{
			case 0:
				bubbles1[0] = new Bubble(15*(Settings.canvasX/36), 1*Settings.canvasY/24+100);
				bubbles1[1] = new Bubble(22*(Settings.canvasX/36), 1*Settings.canvasY/24+100);
				bubbles1[2] = new Bubble(10*(Settings.canvasX/36), 2*Settings.canvasY/24+100);
				bubbles1[3] = new Bubble(27*(Settings.canvasX/36), 2*Settings.canvasY/24+100);
				bubbles1[4] = new Bubble(8*(Settings.canvasX/36), 3*Settings.canvasY/24+100);
				bubbles1[5] = new Bubble(29*(Settings.canvasX/36), 3*Settings.canvasY/24+100);
				bubbles1[6] = new Bubble(8*(Settings.canvasX/36), 4*Settings.canvasY/24+100);
				bubbles1[7] = new Bubble(29*(Settings.canvasX/36), 4*Settings.canvasY/24+100);
				bubbles1[8] = new Bubble(8*(Settings.canvasX/36), 5*Settings.canvasY/24+100);
				bubbles1[9] = new Bubble(29*(Settings.canvasX/36), 5*Settings.canvasY/24+100);
				bubbles1[10] = new Bubble(8*(Settings.canvasX/36), 6*Settings.canvasY/24+100);
				bubbles1[11] = new Bubble(14*(Settings.canvasX/36), 6*Settings.canvasY/24+100);
				bubbles1[12] = new Bubble(23*(Settings.canvasX/36), 6*Settings.canvasY/24+100);
				bubbles1[13] = new Bubble(29*(Settings.canvasX/36), 6*Settings.canvasY/24+100);
				bubbles1[14] = new Bubble(7*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				bubbles1[15] = new Bubble(12*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				bubbles1[16] = new Bubble(16*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				bubbles1[17] = new Bubble(21*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				bubbles1[18] = new Bubble(25*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				bubbles1[19] = new Bubble(30*(Settings.canvasX/36), 7*Settings.canvasY/24+100);
				bubbles1[20] = new Bubble(7*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				bubbles1[21] = new Bubble(13*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				bubbles1[22] = new Bubble(18*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				bubbles1[23] = new Bubble(19*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				bubbles1[24] = new Bubble(24*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				bubbles1[25] = new Bubble(30*(Settings.canvasX/36), 8*Settings.canvasY/24+100);
				bubbles1[26] = new Bubble(8*(Settings.canvasX/36), 9*Settings.canvasY/24+100);
				bubbles1[27] = new Bubble(18*(Settings.canvasX/36), 9*Settings.canvasY/24+100);
				bubbles1[28] = new Bubble(19*(Settings.canvasX/36), 9*Settings.canvasY/24+100);
				bubbles1[29] = new Bubble(29*(Settings.canvasX/36), 9*Settings.canvasY/24+100);
				bubbles1[30] = new Bubble(9*(Settings.canvasX/36), 10*Settings.canvasY/24+100);
				bubbles1[31] = new Bubble(12*(Settings.canvasX/36), 10*Settings.canvasY/24+100);
				bubbles1[32] = new Bubble(25*(Settings.canvasX/36), 10*Settings.canvasY/24+100);
				bubbles1[33] = new Bubble(28*(Settings.canvasX/36), 10*Settings.canvasY/24+100);
				bubbles1[34] = new Bubble(10*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				bubbles1[35] = new Bubble(13*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				bubbles1[36] = new Bubble(16*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				bubbles1[37] = new Bubble(21*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				bubbles1[38] = new Bubble(24*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				bubbles1[39] = new Bubble(27*(Settings.canvasX/36), 11*Settings.canvasY/24+100);
				bubbles1[40] = new Bubble(10*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				bubbles1[41] = new Bubble(17*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				bubbles1[42] = new Bubble(18*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				bubbles1[43] = new Bubble(19*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				bubbles1[44] = new Bubble(20*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				bubbles1[45] = new Bubble(27*(Settings.canvasX/36), 12*Settings.canvasY/24+100);
				bubbles1[46] = new Bubble(12*(Settings.canvasX/36), 13*Settings.canvasY/24+100);
				bubbles1[47] = new Bubble(25*(Settings.canvasX/36), 13*Settings.canvasY/24+100);
				bubbles1[48] = new Bubble(16*(Settings.canvasX/36), 14*Settings.canvasY/24+100);
				bubbles1[49] = new Bubble(21*(Settings.canvasX/36), 14*Settings.canvasY/24+100);
				break;
			case 1:
				break;
		}
	}
	public void loadBubbles2 (Bubble[] bubbles2)
	{
		switch (this.currentLevel)
		{
			case 0:
				break;
			case 1:
				break;
		}
	}
	public void loadAsplodeBubbles (Bubble[] asplodeBubbles)
	{
		switch (this.currentLevel)
		{
			case 0:
				break;
			case 1:
				break;
		}
	}
	public void loadLevers (Lever[] levers)
	{
		switch (this.currentLevel)
		{
			case 0:
				break;
			case 1:
				break;
		}
	}
	
	/*
	public void load (int levelNumber) // Redo. Should not be void.
	{
		switch (levelNumber)
		{
			case 0:
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
	*/
	
	public void save () // Redo. Should not be void.
	{
		
	}
	
}