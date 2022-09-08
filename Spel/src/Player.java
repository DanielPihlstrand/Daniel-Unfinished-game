import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature {

	
	//counter
	private int count;
	
	// Animations
	private Animation animDown, animUp, animLeft, animRight, animIdle;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH + 42, Creature.DEFAULT_CREATURE_HEIGHT + 42);

		// var vi ritar hitbox på player
		bounds.x = 20;
		bounds.y = 20;
		bounds.width = 30;
		bounds.height = 30;
		// Animations
		animDown = new Animation(50, Assets.barb_down);
		animUp = new Animation(50, Assets.barb_up);
		animLeft = new Animation(50, Assets.barb_left);
		animRight = new Animation(50, Assets.barb_right);
		animIdle = new Animation(50, Assets.barb_idle);

		count =0;
		
	}

	public void tick() {
		
		if (count>=100) {
			System.out.println("X,Y: "+(int) x +"," +(int)y);
			count =0;
		}
		count++;
		
		// Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		animIdle.tick();
		
		// movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		// movement
		if (handler.getKeyManager().up)
			yMove = -speed;
		if (handler.getKeyManager().down)
			yMove = speed;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right)
			xMove = speed;

		// movement with shift
		if (handler.getKeyManager().up && handler.getKeyManager().shift)
			yMove = -speed * 3;
		if (handler.getKeyManager().down && handler.getKeyManager().shift)
			yMove = speed * 3;
		if (handler.getKeyManager().left && handler.getKeyManager().shift)
			xMove = -speed * 3;
		if (handler.getKeyManager().right && handler.getKeyManager().shift)
			xMove = speed * 3;

		// teleport
		if (handler.getKeyManager().enter) {
			x = 64;
			y = 64;
		}

		if ( handler.getMouseManager().isRightPressed()) {
			handler.getGame().setToMenuState();

		}
		
		
		// Ta bort alla entitites, iklusive spelare. Lägg till spelare, pause
		// Game-klassen i 500ms.
		if (handler.getKeyManager().space) {
			handler.getWorld().getEntityManager().removeEntities();
			handler.getWorld().getEntityManager().addEntity(new Player(handler, 100, 100));
			handler.getGame().wait(300);

		}

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawImage(getCurrentAnimationsFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

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
		} else if (yMove > 0){
			return animDown.getCurrentFrames();
		}
		return animIdle.getCurrentFrames();
	}
}
