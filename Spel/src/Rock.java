import java.awt.Graphics;

public class Rock extends StaticEntity{

	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

	
		//Collisionbox
		bounds.x = 20;
		bounds.y = 20;
		bounds.width = 30;
		bounds.height = 37;
	
	
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rocknew, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	
		//Collisionbox		
		g.drawRect((int)  (x + bounds.x - handler.getGameCamera().getxOffset()), 
		(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
		bounds.width, bounds.height);
	
	
	}

}
