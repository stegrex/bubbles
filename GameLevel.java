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
	
	// Maybe break out the saving and loading functions into GameLoad and GameSave classes.
	private BubblesJSONObject gameSave;
	private int saveSlot;
	
	public GameLevel ()
	{
		Scanner fileScanner = new Scanner(this.getClass().getResourceAsStream("/"+Settings.gameFile));
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
	
	public void createNewGameSave (int saveSlot)
	{
		this.saveSlot = 0; // Debug
		this.gameSave = new BubblesJSONObject(this.saveSlot, new BubblesJSONObject());
		this.gameSave.getObject(this.saveSlot).add("LEVEL", this.currentLevel);
	}
	/*
	public void addGameSaveElement (string elementType, BubblesJSONObject object)
	{
		this.gameSave.getObject(this.saveSlot).add(elementType, object);
	}
	*/
	public void commitGameSave () // Revisit. Look into returning a boolean on successful save.
	{
		System.out.println(this.gameSave.toFormattedString()); // Debug
		this.gameSave = null;
	}
	
	public void savePortals (Portal[] portals)
	{
		BubblesJSONObject portalsObject = new BubblesJSONObject();
		BubblesJSONObject portalObject;
		int count = 0;
		for (int i = 0; i < portals.length; i++)
		{
			if (portals[i] != null)
			{
				portalObject = new BubblesJSONObject();
				portalObject.add("entranceX", portals[i].entranceX);
				portalObject.add("entranceY", portals[i].entranceY);
				portalObject.add("entranceW", portals[i].entranceW);
				portalObject.add("exitX", portals[i].exitX);
				portalObject.add("exitY", portals[i].exitY);
				portalObject.add("exitW", portals[i].exitW);
				portalsObject.add(i, portalObject);
				count++;
			}
		}
		if (count != 0)
		{
			this.gameSave.getObject(this.saveSlot).add("PORTALS", portalsObject);
		}
	}
	public void saveBlocks (Block[] blocks)
	{
		BubblesJSONObject blocksObject = new BubblesJSONObject();
		BubblesJSONObject blockObject;
		int count = 0;
		for (int i = 0; i < blocks.length; i++)
		{
			if (blocks[i] != null)
			{
				blockObject = new BubblesJSONObject();
				blockObject.add("x", blocks[i].x);
				blockObject.add("y", blocks[i].y);
				blockObject.add("w", blocks[i].w);
				blockObject.add("h", blocks[i].h);
				blocksObject.add(i, blockObject);
				count++;
			}
		}
		if (count != 0)
		{
			this.gameSave.getObject(this.saveSlot).add("BLOCKS", blocksObject);
		}
	}
	public void saveSlopedBlocks (SlopedBlock[] slopedBlocks)
	{
		BubblesJSONObject slopedBlocksObject = new BubblesJSONObject();
		BubblesJSONObject slopedBlockObject;
		int count = 0;
		for (int i = 0; i < slopedBlocks.length; i++)
		{
			if (slopedBlocks[i] != null)
			{
				slopedBlockObject = new BubblesJSONObject();
				slopedBlockObject.add("x1", slopedBlocks[i].x1);
				slopedBlockObject.add("x2", slopedBlocks[i].x2);
				slopedBlockObject.add("y1", slopedBlocks[i].y1);
				slopedBlockObject.add("y2", slopedBlocks[i].y2);
				slopedBlockObject.add("w", slopedBlocks[i].w);
				slopedBlockObject.add("h", slopedBlocks[i].h);
				slopedBlocksObject.add(i, slopedBlockObject);
				count++;
			}
		}
		if (count != 0)
		{
			this.gameSave.getObject(this.saveSlot).add("SLOPED_BLOCKS", slopedBlocksObject);
		}
	}
	public void saveBubbles1 (Bubble[] bubbles1)
	{
		BubblesJSONObject bubbles1Object = new BubblesJSONObject();
		BubblesJSONObject bubbleObject;
		int count = 0;
		for (int i = 0; i < bubbles1.length; i++)
		{
			if (bubbles1[i] != null)
			{
				bubbleObject = new BubblesJSONObject();
				bubbleObject.add("isBlocked", bubbles1[i].isBlocked);
				bubbleObject.add("x", bubbles1[i].x);
				bubbleObject.add("y", bubbles1[i].y);
				bubbleObject.add("r", bubbles1[i].r);
				bubbleObject.add("weight", bubbles1[i].weight);
				bubbleObject.add("moving", bubbles1[i].moving);
				bubbleObject.add("asploding", bubbles1[i].asploding);
				bubbleObject.add("destroy", bubbles1[i].destroy);
				bubbles1Object.add(i, bubbleObject);
				count++;
			}
		}
		if (count != 0)
		{
			this.gameSave.getObject(this.saveSlot).add("BUBBLES1", bubbles1Object);
		}
	}
	public void saveBubbles2 (Bubble[] bubbles2)
	{
		BubblesJSONObject bubbles2Object = new BubblesJSONObject();
		BubblesJSONObject bubbleObject;
		int count = 0;
		for (int i = 0; i < bubbles2.length; i++)
		{
			if (bubbles2[i] != null)
			{
				bubbleObject = new BubblesJSONObject();
				bubbleObject.add("isBlocked", bubbles2[i].isBlocked);
				bubbleObject.add("x", bubbles2[i].x);
				bubbleObject.add("y", bubbles2[i].y);
				bubbleObject.add("r", bubbles2[i].r);
				bubbleObject.add("weight", bubbles2[i].weight);
				bubbleObject.add("moving", bubbles2[i].moving);
				bubbleObject.add("asploding", bubbles2[i].asploding);
				bubbleObject.add("destroy", bubbles2[i].destroy);
				bubbles2Object.add(i, bubbleObject);
				count++;
			}
		}
		if (count != 0)
		{
			this.gameSave.getObject(this.saveSlot).add("BUBBLES2", bubbles2Object);
		}
	}
	public void saveAsplodeBubbles (Bubble[] asplodeBubbles)
	{
		BubblesJSONObject asplodeBubblesObject = new BubblesJSONObject();
		BubblesJSONObject bubbleObject;
		int count = 0;
		for (int i = 0; i < asplodeBubbles.length; i++)
		{
			if (asplodeBubbles[i] != null)
			{
				bubbleObject = new BubblesJSONObject();
				bubbleObject.add("isBlocked", asplodeBubbles[i].isBlocked);
				bubbleObject.add("x", asplodeBubbles[i].x);
				bubbleObject.add("y", asplodeBubbles[i].y);
				bubbleObject.add("r", asplodeBubbles[i].r);
				bubbleObject.add("weight", asplodeBubbles[i].weight);
				bubbleObject.add("moving", asplodeBubbles[i].moving);
				bubbleObject.add("asploding", asplodeBubbles[i].asploding);
				bubbleObject.add("destroy", asplodeBubbles[i].destroy);
				bubbleObject.add("d", asplodeBubbles[i].d);
				bubbleObject.add("rStart", asplodeBubbles[i].rStart);
				asplodeBubblesObject.add(i, bubbleObject);
				count++;
			}
		}
		if (count != 0)
		{
			this.gameSave.getObject(this.saveSlot).add("ASPLODE_BUBBLES", asplodeBubblesObject);
		}
	}
	public void saveLevers (Lever[] levers)
	{
		BubblesJSONObject leversObject = new BubblesJSONObject();
		BubblesJSONObject leverObject;
		int count = 0;
		for (int i = 0; i < levers.length; i++)
		{
			if (levers[i] != null)
			{
				leverObject = new BubblesJSONObject();
				leverObject.add("x", levers[i].x);
				leverObject.add("y", levers[i].y);
				leverObject.add("w", levers[i].w);
				leverObject.add("h", levers[i].h);
				leverObject.add("inertia", levers[i].inertia);
				leverObject.add("leftWeight", levers[i].leftWeight);
				leverObject.add("rightWeight", levers[i].rightWeight);
				leverObject.add("moving", levers[i].moving);
				leverObject.add("angle", levers[i].angle);
				leversObject.add(i, leverObject);
				count++;
			}
		}
		if (count != 0)
		{
			this.gameSave.getObject(this.saveSlot).add("LEVERS", leversObject);
		}
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
		String type = "SLOPED_BLOCKS";
		if (this.isObjectValidForIteration(type))
		{
			for (Object x : (this.getCurrentLevelObjectKeySet(type)))
			{
				slopedBlocks[(Integer)x] = new SlopedBlock((Integer)getObjectParameter(type, x, "x1"), (Integer)getObjectParameter(type, x, "y1"), (Integer)getObjectParameter(type, x, "x2"), (Integer)getObjectParameter(type, x, "y2"), (Integer)getObjectParameter(type, x, "w"),(Integer)getObjectParameter(type, x, "h"));
			}
		}
	}
	public void loadBubbles1 (Bubble[] bubbles1)
	{
		String type = "BUBBLES1";
		if (this.isObjectValidForIteration(type))
		{
			for (Object x : (this.getCurrentLevelObjectKeySet(type)))
			{
				bubbles1[(Integer)x] = new Bubble((Double)getObjectParameter(type, x, "x"), (Double)getObjectParameter(type, x, "y"));
				bubbles1[(Integer)x].destroy = (Boolean)getObjectParameter(type, x, "destroy");
				bubbles1[(Integer)x].weight = (Double)getObjectParameter(type, x, "weight");
				bubbles1[(Integer)x].moving = (Boolean)getObjectParameter(type, x, "moving");
				bubbles1[(Integer)x].r = (Double)getObjectParameter(type, x, "r");
				bubbles1[(Integer)x].asploding = (Boolean)getObjectParameter(type, x, "asploding");
				bubbles1[(Integer)x].isBlocked = (Boolean)getObjectParameter(type, x, "isBlocked");
			}
		}
	}
	/*
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
	*/
	public void loadBubbles2 (Bubble[] bubbles2)
	{
		String type = "BUBBLES2";
		if (this.isObjectValidForIteration(type))
		{
			for (Object x : (this.getCurrentLevelObjectKeySet(type)))
			{
				bubbles2[(Integer)x] = new Bubble((Double)getObjectParameter(type, x, "x"), (Double)getObjectParameter(type, x, "y"));
				bubbles2[(Integer)x].destroy = (Boolean)getObjectParameter(type, x, "destroy");
				bubbles2[(Integer)x].weight = (Double)getObjectParameter(type, x, "weight");
				bubbles2[(Integer)x].moving = (Boolean)getObjectParameter(type, x, "moving");
				bubbles2[(Integer)x].r = (Double)getObjectParameter(type, x, "r");
				bubbles2[(Integer)x].asploding = (Boolean)getObjectParameter(type, x, "asploding");
				bubbles2[(Integer)x].isBlocked = (Boolean)getObjectParameter(type, x, "isBlocked");
			}
		}
	}
	public void loadAsplodeBubbles (Bubble[] asplodeBubbles)
	{
		String type = "ASPLODE_BUBBLES";
		if (this.isObjectValidForIteration(type))
		{
			for (Object x : (this.getCurrentLevelObjectKeySet(type)))
			{
				asplodeBubbles[(Integer)x] = new Bubble((Double)getObjectParameter(type, x, "x"), (Double)getObjectParameter(type, x, "y"));
				asplodeBubbles[(Integer)x].destroy = (Boolean)getObjectParameter(type, x, "destroy");
				asplodeBubbles[(Integer)x].weight = (Double)getObjectParameter(type, x, "weight");
				asplodeBubbles[(Integer)x].moving = (Boolean)getObjectParameter(type, x, "moving");
				asplodeBubbles[(Integer)x].r = (Double)getObjectParameter(type, x, "r");
				asplodeBubbles[(Integer)x].asploding = (Boolean)getObjectParameter(type, x, "asploding");
				asplodeBubbles[(Integer)x].isBlocked = (Boolean)getObjectParameter(type, x, "isBlocked");
				asplodeBubbles[(Integer)x].d = (Double)getObjectParameter(type, x, "d");
				asplodeBubbles[(Integer)x].rStart = (Double)getObjectParameter(type, x, "rStart");
			}
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
	
}