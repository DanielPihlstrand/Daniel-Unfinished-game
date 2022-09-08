 import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 32, height = 32;

	public static BufferedImage grass1, grass2, dirt, bandit, rock, man, tree, tree2, tree3;
	// mina nyare egna (förutom grass)
	public static BufferedImage rocknew, treenew, trees, trees2, treerock, grass_high_def;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] btn_stop;

	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] man_down;

	public static BufferedImage[] krita;
	public static BufferedImage[] heroes4;
	public static BufferedImage[] barb_down;
	public static BufferedImage[] barb_left;
	public static BufferedImage[] barb_up;
	public static BufferedImage[] barb_right;
	public static BufferedImage[] barb_idle;

	// Tester 64an
	public static BufferedImage lightgrass, dark_grass_pix, dark_grass, green_grass_pix, green_grass_mod;
	public static BufferedImage snow, snow_pix;
	// Tester 128an
	public static BufferedImage tree_pine, snow_128, dark_grass_128, light_grass_128, stone_128, treestone_128;
	public static BufferedImage yelltree_128, pine_128, pine_128_pix, snow_128_pix, dark_grass_128_pix;

	public static void init() {

		
		
		//Sheet_copy testar heroes 4 combat tiles. Barb är från diablo 2, pole_guy ngn från forum.
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("textures/tilesheet_old.png"));
		SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("textures/sheet.png"));
		SpriteSheet sheet4 = new SpriteSheet(ImageLoader.loadImage("textures/grid_64_trans.png"));
		SpriteSheet sheet5 = new SpriteSheet(ImageLoader.loadImage("textures/grid_128_trans.png"));
		SpriteSheet sheet_copy = new SpriteSheet(ImageLoader.loadImage("textures/grid_64_trans_copy.png"));
		SpriteSheet sheet_barb = new SpriteSheet(ImageLoader.loadImage("textures/barbar.png"));
		SpriteSheet sheet_pole_guy = new SpriteSheet(ImageLoader.loadImage("textures/pole_arm.png"));

		
		//heroes 4 är gjort för 880 x 764 fönster
		
		// 64or
		lightgrass = 		sheet4.crop(0, 64, width * 2, height * 2);
		dark_grass_pix = 	sheet4.crop(64, 64, width*2 , height*2 );
		dark_grass = 		sheet4.crop(2 * 64, 64*3, width*2 , height*2 );
		green_grass_pix = 	sheet4.crop(3 * 64, 64, width * 2, height * 2);
		green_grass_mod = 	sheet4.crop(4 * 64, 64, width * 2, height * 2);
		snow = 				sheet4.crop(0, 2*64, width * 2, height * 2);
		snow_pix = 			sheet4.crop(64, 2*64, width * 2, height * 2);

		//heroes_4, 0-2 gräs, 3-5 sand, 6 vann, 7träd, 8 dirt, 9-10 snö
		heroes4 = new BufferedImage[11];
		heroes4[0] = sheet_copy.crop(0, 64*4, width*2, height*2);
		heroes4[1] = sheet_copy.crop(64, 64*4, width*2, height*2);
		heroes4[2] = sheet_copy.crop(64*2, 64*4, width*2, height*2);
		heroes4[3] = sheet_copy.crop(64*3, 64*4, width*2, height*2);
		heroes4[4] = sheet_copy.crop(64*4, 64*4, width*2, height*2);
		heroes4[5] = sheet_copy.crop(64*5, 64*4, width*2, height*2);
		heroes4[6] = sheet_copy.crop(64*6, 64*4, width*2, height*2);
		heroes4[7] = sheet_copy.crop(64*7, 64*4, width*2, height*2);
		heroes4[8]= sheet_copy.crop(64*8, 64*4, width*2, height*2);
		heroes4[9] = sheet_copy.crop(64*9, 64*4, width*2, height*2);
		heroes4[10] = sheet_copy.crop(64*10, 64*4, width*2, height*2);
		
		
		//från Krita, 5 PLATSER FÖR TILLFÄLLLET
		krita = new BufferedImage[5];
		krita[0] =  sheet_copy.crop(64*10, 64*2, width*2, height*2);
		krita[1] =  sheet_copy.crop(64*12, 64*2, width*2, height*2);

		
		
		//barb walk down
		barb_down = new BufferedImage[8];
		barb_left = new BufferedImage[8];
		barb_up = new BufferedImage[8];
		barb_right = new BufferedImage[8];
		barb_idle = new BufferedImage[15];

		
		// XPOSITION, YPOSITION, BREDD, HÖJD
		for (int j=0;j<8;j++) {
			barb_down[j]=sheet_barb.crop(0 + j*(624/8), 13, 624/8, (1596-13)/14 -8);
			barb_left[j]=sheet_barb.crop(0 + j*(624/8), (1596/16)*4 + 13, 624/8 , (1596-13) /14 - 8);//(111-13)*4);
			barb_up[j]=sheet_barb.crop(0 + j*(624/8), (1596/16)*8 + 13, 624/8 , (1596-13)/14 - 8);
			barb_right[j]=sheet_barb.crop(0 + j*(624/8), (1596/16)*12 + 12, 624/8 , (1596-13)/14 - 8);
		} //0 - 624

		for (int j=0;j<15;j++) {
			barb_idle[j] = sheet_barb.crop(625 + j*((1713 - 625)/16), 13 , 624/8 , (1596-13)/14 - 8) ;
		}
		
		
		
		
		//		624 /7; 13 - 1596
//		barb_down[0]= sheet_barb.crop(0, 13, 80, 98);
//		barb_down[1]= sheet_barb.crop(78-1, 13, 80, 98);
//		barb_down[2]= sheet_barb.crop(78*2-1, 13, 80, 98);
//		barb_down[3]= sheet_barb.crop(78*3-1, 13, 80, 98);
//		barb_down[4]= sheet_barb.crop(78*4-1, 13, 80, 98);
//		barb_down[5]= sheet_barb.crop(78*5-1, 13, 80, 98);
//		barb_down[6]= sheet_barb.crop(78*6-1, 13, 80, 98);
//		barb_down[7]= sheet_barb.crop(78*7-2, 13, 80, 98);
		
		
		
		// 128or
		tree_pine = 		sheet5.crop(0, 128, width*2, height*2);
		snow_128 = 			sheet5.crop(128, 0, width*4, height*4);
		dark_grass_128 = 	sheet5.crop(128*2, 128, width*2, height*2);
		light_grass_128 = 	sheet5.crop(128*3, 128, width*2, height*2);
		stone_128 = 		sheet5.crop(128*4, 128, width*2, height*2);
		treestone_128 = 	sheet5.crop(128*5, 128, width*2, height*2);
		yelltree_128 = 		sheet5.crop(0, 128, width*2, height*2);
		pine_128 = 			sheet5.crop(128, 128, width*2, height*2);
		pine_128_pix = 		sheet5.crop(128*2, 128, width*2, height*2);
		snow_128_pix = 		sheet5.crop(128*3, 128, width*2, height*2);
		dark_grass_128_pix =sheet5.crop(128*4, 128, width, height);

		// Start button
		btn_start = new BufferedImage[2];
		btn_stop = new BufferedImage[2];

		btn_start[0] = sheet4.crop(64 * 6, 0, width * 6, height * 2);
		btn_start[1] = sheet4.crop(64 * 6, 64, width * 6, height * 2);
		btn_stop[0] = sheet4.crop(64 * 9, 0, width * 6, height * 2);
		btn_stop[1] = sheet4.crop(64 * 9, 64, width * 6, height * 2);

		
		
		// Hans player anims
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];

		player_down[0] = sheet2.crop(width * 4, 0, width, height);
		player_down[1] = sheet2.crop(width * 5, 0, width, height);
		player_up[0] = sheet2.crop(width * 6, 0, width, height);
		player_up[1] = sheet2.crop(width * 7, 0, width, height);
		player_left[0] = sheet2.crop(width * 6, width, height, height);
		player_left[1] = sheet2.crop(width * 7, width, height, height);
		player_right[0] = sheet2.crop(width * 4, width, height, height);
		player_right[1] = sheet2.crop(width * 5, width, height, height);

		// min man
		man_down = new BufferedImage[3];
		man_down[0] = sheet.crop(64, 64, width * 2, height * 2);
		man_down[1] = sheet.crop(64 * 2, 64, width * 2, height * 2);
		man_down[2] = sheet.crop(64 * 3, 64, width * 2, height * 2);

		// gamla tiles
		grass1 = sheet4.crop(4*64, 64*2, 64, 64);
		grass2 = sheet.crop(64 * 2, 0, width * 2, height * 2);
		dirt = sheet.crop(64, 0, 64, 64);
		bandit = sheet.crop(0, 64, width * 2, height * 2);
		rock = sheet_copy.crop(64 * 3, 4*64, width * 2, height * 2);
		man = sheet.crop(64, 64, width * 2, height * 2);
		tree = sheet4.crop(64, 0, width * 2, height * 2);
		tree2 = sheet.crop(64 * 5, 1, width * 2, height * 2);
		tree3 = sheet.crop(64 * 6, 1, width * 2, height * 2);

		// Nya finare
		rocknew = sheet4.crop(0, 0, width * 2, height * 2);
		treenew = sheet4.crop(64, 0, width * 2, height * 2);
		trees = sheet4.crop(64 * 2, 64, width * 2, height * 2);
		trees2 = sheet4.crop(64 * 3, 0, width * 2, height * 2);
		treerock = sheet4.crop(64 * 4, 0, width * 2, height * 2);
		grass_high_def = sheet4.crop(64 * 5, 0, width * 2, height * 2);
	}

}
