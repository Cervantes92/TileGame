package dev.alpete.tilegame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	//Font 
	//public static Font font28;
	public static Font timesRoman;
	
	//Menu Items
	public static BufferedImage[] startButton;
	
	//Tile Items
	public static BufferedImage player, grass, dirt, forest, fruitTree, tree, monster, stone, 
	brick, door, mountain, openedDoor, rock;
	
	//Item Items
	public static BufferedImage apple, log, stoneItem;
	
	//Creatures
	public static BufferedImage[] plRight, plLeft, plUp, plDown;
	public static BufferedImage[] plAtkRight, plAtkDown, plAtkLeft, plAtkUp;
	public static BufferedImage[] shRight, shLeft, shUp, shDown;
	
	//Screens
	public static BufferedImage inventoryScreen;
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static void init() {
		//font
		//TODO: Fix or work around loading issue
		//font28 = FontLoader.loadFont("/res/Fonts/slkscre.ttf", 24);
		//WORKAROUND
		timesRoman = new Font("TimesRoman", Font.PLAIN, 20);
		
		//Sprite Sheets
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/res/SpriteSheet/SpriteSheet3232320-alpha.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/res/SpriteSheet/MnChrAniSheet.png"));
		SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("/res/SpriteSheet/MenuButtons.png"));		
		SpriteSheet shaggySheet = new SpriteSheet(ImageLoader.loadImage("/res/SpriteSheet/ShaggyAniSheet.png"));
		SpriteSheet itemSheet = new SpriteSheet(ImageLoader.loadImage("/res/SpriteSheet/itemSheet.png"));
		
		//Screens
		inventoryScreen = ImageLoader.loadImage("/res/SpriteSheet/inventoryScreen.png");
		
		//Terrain Sheet
		player = sheet.crop(0,  0, width, height);
		grass = sheet.crop(width, 0, width, height);
		dirt = sheet.crop(width * 2, 0, width, height);
		forest = sheet.crop(width * 3, 0, width, height);
		fruitTree = sheet.crop(width *4,  0,  width,  height);
		tree = sheet.crop(width * 5,  0, width, height);
		rock = sheet.crop(width * 6,  0, width, height);
		monster = sheet.crop(0, height,  width, height);
		stone = sheet.crop(width, height, width, height);
		brick = sheet.crop(width * 2,  height, width, height);
		door = sheet.crop(width * 3,  height,  width, height);
		openedDoor = sheet.crop(width * 4, height, width, height);
		mountain = sheet.crop(width * 5, height, width, height);
		
		//Item Sheet
		apple = itemSheet.crop(0, 0, width, height);
		log = itemSheet.crop(width, 0, width, height);
		stoneItem = itemSheet.crop(width * 2, 0, width, height);
		
		//Player Animation Sheet
		plRight = new BufferedImage[2];
		plDown = new BufferedImage[2];
		plLeft = new BufferedImage[2];
		plUp = new BufferedImage[2];
		
		plAtkRight = new BufferedImage[3];
		plAtkDown = new BufferedImage[3];
		plAtkLeft = new BufferedImage[3];
		plAtkUp = new BufferedImage[3];

		for(int i = 0; i < 2; i++) { 
			plRight[i] = playerSheet.crop(width * i, 0, width, height);
			plDown[i] = playerSheet.crop(width * i, height, width, height);
			plLeft[i] = playerSheet.crop(width * i, height * 2, width, height);
			plUp[i] = playerSheet.crop(width * i, height * 3, width, height);
		}
		
		for(int j = 0; j < 3; j ++) {
			plAtkRight[j] = playerSheet.crop(width * (j + 2), 	0, 			width, height);
			plAtkDown[j] = 	playerSheet.crop(width * (j + 2), 	height, 	width, height);
			plAtkLeft[j] = 	playerSheet.crop(width * (j + 2),	height * 2, width, height);
			plAtkUp[j] = 	playerSheet.crop(width * (j + 2), 	height * 3, width, height);	
		}
		
		//Shaggy Animation Sheet
		shRight = new BufferedImage[2];
		shRight[0] = shaggySheet.crop(0, 0, width, height);
		shRight[1] = shaggySheet.crop(width, 0, width, height);
		
		shLeft = new BufferedImage[2];
		shLeft[0] = shaggySheet.crop(width, 0, width, height);
		shLeft[1] = shaggySheet.crop(width * 2, 0, width, height);
		
		shUp = new BufferedImage[2];
		shUp[0] = shaggySheet.crop(0, height, width, height);
		shUp[1] = shaggySheet.crop(width, height, width, height);
		
		shDown = new BufferedImage[2];
		shDown[0] = shaggySheet.crop(width, height, width, height);
		shDown[1] = shaggySheet.crop(width * 2, height, width, height);
		
		//Button Sheet
		startButton = new BufferedImage[2];
		startButton[0] = buttonSheet.crop(0, 0, width * 2, height);
		startButton[1] = buttonSheet.crop(0, height,  width*2 , height);
		
	}
}
