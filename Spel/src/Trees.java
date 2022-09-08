import java.awt.Graphics;

public class Trees extends StaticEntity {

	// Trees
	public Trees(Handler handler, float x, float y) {
		super(handler, x, y, (int) (Tile.TILE_WIDTH * 1.8), (int) (Tile.TILE_HEIGHT * 1.8));

		// Collisionbox
		bounds.x = 30;
		bounds.y = 50;
		bounds.width = 30;
		bounds.height = 30;

	}

	// Trees 2
	public Trees(Handler handler, float x, float y, int a) {
		super(handler, x, y, (int) (Tile.TILE_WIDTH * 1.3), (int) (Tile.TILE_HEIGHT * 1.3));

		// Collisionbox
		bounds.x = 30;
		bounds.y = 50;
		bounds.width = 30;
		bounds.height = 30;

	}

	@Override
	public void tick() {

	}

	@Override // two trees, one green, the other not
	public void render(Graphics g) {
		g.drawImage(Assets.trees, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		// Collisionbox
		g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);

	}

	// two green trees
	public void render2(Graphics g) {
		g.drawImage(Assets.trees2, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		// Collisionbox
		g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);

	}

	// treerock
	public void render3(Graphics g) {
		g.drawImage(Assets.treerock, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		// Collisionbox
		g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);

	}
}
