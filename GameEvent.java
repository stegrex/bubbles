class GameEvent
{
	
	public enum Type
	{
		SPLASH_DEMO,
		DEBUG_PAUSE
	};
	
	public Type command;
	public long duration;
	public long endTime;
	
	public boolean executed;
	
	public GameEvent (Type command, long duration)
	{
		this.endTime = System.currentTimeMillis()+duration;
		this.command = command;
		this.duration = duration;
		this.executed = false;
	}
	
	public void execute ()
	{
		this.executed = true;
	}
	
	// Debug
	public void dumpObject ()
	{
		System.out.println(this.command+" duration: "+this.duration+" end: "+this.endTime+" now: "+System.currentTimeMillis()+" executed: "+this.executed);
	}
	
}