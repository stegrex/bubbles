//package stegrex.bubbles.game;

class Settings
{
	
	// Game File settings
	public static String gameFile = "levels.wad";
	
	// Environment settings
	public static int canvasX = 320; // Horizontal size of window. Default 320.
	public static int canvasY = 480; // Vertical size of window. Default 480.
	// For fun.
	/*
	public static int canvasX = 1440; // Horizontal size of window. Default 320.
	public static int canvasY = 900; // Vertical size of window. Default 480.
	*/
	
	// Game settings
	
	public static int msPerRender = 4; // Milliseconds per animation rendering.
	public static int gameMode = 1;
	public static int autoShoot = 0; // Shoots a bunch of bubbles randomly if set to 1. Default 0.
	public static boolean runSplashDemo = false;
	
	// Object settings
	
	// Bubble
	public static double bubbleWeight = 1; // Bubble size. Default 1.
	public static double bubbleSpeed = 1.5; // Pixel per ms bubbles float upwards. Default 1.5.
	public static double bubbleRadius = 5; // Bubble radius. Default 5.
	public static int maxBubbleWeight = 15; // Maximum weight of the bubble before it asplodes. Default 15.
	public static int bubbleFrequency = 150; // Milliseconds in between firings. Default 150.
	public static int combineBubbleMode = 0;
	public static int asplodeBubbleRate = 150; // Part of the original radius that gets subtracted every frame. Default 150.
	public static double bubbleWiggleRatio = 1.5; // The amount of wiggling when bubble is moving. Default 1.5.
	public static double asplodeWiggleRatio = 3; // The amount of wiggling when bubble is asploding. Default 3.
	public static boolean freeBubblePlacement = false;
	
	// Reticle
	public static int reticleSize = 2;
	public static boolean showReticleLaser = true;
	public enum ReticleColor {GREEN, RED};
	public static ReticleColor reticleColor = ReticleColor.RED;
	
}