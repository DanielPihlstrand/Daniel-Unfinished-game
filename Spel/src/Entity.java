import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.UUID;

public abstract class Entity {

	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected String id;

	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		bounds = new Rectangle(0, 0, width, height);
	}

			//Collisioner
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width,
				bounds.height);
	}

	public boolean checkEntityCollisions(float xOffset, float yOffset) {

		// Här kollar jag bara på dem omringande tile:sen, dvs 9 st, om det finns
		// entitites där, då kollar vi!
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Tile tile = handler.getWorld().getGridTile(getXTileLocation() - 1 + i, getYTileLocation() - 1 + j); // Om
																													// denna
																													// skiter
																													// sig,
																													// hämta
																													// getTile()
																													// istället
																													// från
																													// world
				if (!tile.isEmpty()) {
					for (Entity e : tile.entitites) {
						if (e.equals(this))
							continue;
						if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
							return true;
					}
				}
			}
		}

		return false;

		// HANS KOD FÖR ATT HANTERA COLLISIONS, OBS HAN TAR FÖR BARJE ENTITY I SPELET
		// for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
		// if (e.equals(this))
		// continue;
		// if (e.getCollisionBounds(0f,
		// 0f).intersects(getCollisionBounds(xOffset,yOffset)))
		// return true;
		// }
		// return false;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getX() {
		return x;
	}

	public void setId(String s) {
		id = s;
	}

	public String getId() {
		return id.toString();
	}

	//Ta reda på vilken Tile-x-koordinat entityn har, dvs vilket rad nmr.
	public int getXTileLocation() {
		int Tx = (int) (getX() + getWidth() / 2) / Tile.TILE_WIDTH;
		Tx = (int) (Math.floor((double) (Tx)));
		return Tx;
	}

	public int getYTileLocation() {
		int Ty = (int) (getY() + getHeight() / 2) / Tile.TILE_HEIGHT;
		Ty = (int) (Math.floor((double) (Ty)));
		return Ty;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

}
