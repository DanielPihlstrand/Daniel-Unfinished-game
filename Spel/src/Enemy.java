
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy extends Creature {

	// Räknar ticks, när denna nått en siffra ska fienden gå lite
	private int count;

	//anim
	private Animation animDown, animLeft, animUp,animRight;
	
		//Vår blåa man
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH + 24, Creature.DEFAULT_CREATURE_HEIGHT + 24);

		// var vi ritar hitbox på Enemey
		bounds.x = 8;
		bounds.y = 8;
		bounds.width = 25;
		bounds.height = 25;
		count = 0;

		//anim
		animDown = new Animation(200, Assets.man_down);
		animUp = new Animation(200, Assets.man_down);
		animLeft = new Animation(200, Assets.man_down);
		animRight =  new Animation(200, Assets.man_down);


		
	}

			//barbaren
	public Enemy(Handler handler, float x, float y, int b) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH + 70, Creature.DEFAULT_CREATURE_HEIGHT + 70);

		// var vi ritar hitbox på Enemey
		bounds.x = Creature.DEFAULT_CREATURE_WIDTH+4 ;
		bounds.y = Creature.DEFAULT_CREATURE_HEIGHT + 54/3;
		bounds.width = 25;
		bounds.height = 45;
		count = 0;

		//anim
		animDown = new Animation(80, Assets.barb_down);
		animLeft = new Animation(80, Assets.barb_left);
		animUp = new Animation(80, Assets.barb_up);
		animRight = new Animation(80, Assets.barb_right);

		
	}
	@Override
	public void tick() {
		
		//animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		think();
		move();

	}

	private void think() {
		if (count >= 70) {
			count = 0;
//			System.out.println("Tx: " + (int) x / (int) (handler.getWorld().getWith()));
//			System.out.println("Ty: " + (int) y / (int) (handler.getWorld().getHeight()));
			
			//64 = tile_width
//			System.out.println("x: "+ (x + Creature.DEFAULT_CREATURE_WIDTH + 12) /64);  
//			System.out.println("y: "+ (y + Creature.DEFAULT_CREATURE_HEIGHT + 12) /64);

			double rand = Math.random();
			if (rand <= 0.40) {
				xMove = 0;
				yMove = 0;
				count = -110;
				return;
			}

			else if (rand >= 0.4 && rand <= 0.55) {
				yMove = 1;
				xMove = 0;
				return;
			}

			if (rand >= 0.55 && rand <= 0.75) {
				yMove = -1;
				xMove = 0;
				return;
			}

			if (rand >= 0.75 && rand <= 0.88) {
				xMove = -1;
				yMove = 0;
				return;
			} else if (rand >= 0.88) {
				xMove = 1;
				yMove = 0;
			}

			return;
		}
		count++;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawImage(getCurrentAnimationsFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height,null);


		// Rita rectangle på coordinater som följer playern och kamera
		g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

	private BufferedImage getCurrentAnimationsFrame() {
		if (xMove < 0) {
			return animLeft.getCurrentFrames();
		} else if (xMove > 0) {
			return animRight.getCurrentFrames();
		} else if (yMove < 0) {
			return animUp.getCurrentFrames();
		} else {
			return animDown.getCurrentFrames();
		}
	}
}
