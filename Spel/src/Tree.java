import java.awt.Graphics;

public class Tree extends StaticEntity{

	
	
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, (int)(Tile.TILE_WIDTH*1.4), (int)(Tile.TILE_HEIGHT*1.4));
		
		//Collisionbox
		bounds.x = 26;
		bounds.y = 50;
		bounds.width = 30;
		bounds.height = 30;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.treenew, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
		
		//Collisionbox		
		g.drawRect((int)  (x + bounds.x - handler.getGameCamera().getxOffset()), 
		(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
		bounds.width, bounds.height);
	
	}

}
