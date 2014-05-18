
class Settings
{
	public static int gameMode = 1;
	public static int autoShoot = 0; // Shoots a bunch of bubbles randomly if set to 1. Default 0.
	public static int bubbleWeight = 1; // Bubble size. Default 1.
	public static double bubbleSpeed = 1; // Pixel per ms bubbles float upwards. Default 1.5.
	public static double bubbleRadius = 10; // Bubble radius. Default 5.
	// var maxBubbles = 25; // Maximum number of bubbles allowed in the game.
	public static int maxBubbleWeight = 15; // Maximum weight of the bubble before it asplodes. Default 15.
	public static int bubbleFrequency = 150; // Milliseconds in between firings. Default 150.
	public static int combineBubbleMode = 0;
	public static int canvasX = 320;
	public static int canvasY = 480;
	public static int msPerRender = 4; // Milliseconds per animation rendering.
}