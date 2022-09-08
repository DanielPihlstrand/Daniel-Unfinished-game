import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

//Generera world

public class World {

	private Handler handler;
	private int width, height;
	private int[][] tiles; // innehåller alla tiles i vår värld, kan fungera som vår grid!s, nope innehåller bara ints.
	private int spawnX, spawnY;
	private Tile[][] grid;
	
	// Entitites
	private EntityManager entityManager;

	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		entityManager.addEntity(new Tree(handler, 100, 250));
		entityManager.addEntity(new Enemy(handler, 280, 250));
		entityManager.addEntity(new Enemy(handler, 6 * 64, 6 * 64));
		entityManager.addEntity(new Enemy(handler, 20, 20));
		entityManager.addEntity(new Rock(handler, 140, 140));
		entityManager.addEntity(new Trees(handler, 240, 160));
		entityManager.addEntity(new Rock(handler, 64*3, 0));
		entityManager.addEntity(new Trees(handler, 64*9, 64*3));
		entityManager.addEntity(new Rock(handler, 64*6, 300));
		entityManager.addEntity(new Rock(handler, 64*6, 330));
		entityManager.addEntity(new Enemy(handler, 190, 150,5));

		
		
		loadWorld(path);

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void tick() {
		entityManager.tick();
		emptyGrid();
		updateGrid();

	}

	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width,
				(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_WIDTH);
		int yEnd = (int) Math.min(height,
				(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}

		// Entities
		entityManager.render(g);

		// Håller reda på var alla är i griden
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.PLAIN, 18));
		StringBuilder sb = new StringBuilder();

		for (Entity e : entityManager.getEntities()) {
			sb.append(e.getId() + ": " + e.getXTileLocation() + "," + e.getYTileLocation() + "; ");
		}
		g.drawString("Ents: " + sb.toString(), 75, 75);

	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles = new int[width][height];
		grid = new Tile[width][height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
				grid[x][y] = Tile.tiles[tiles[x][y]];
				grid[x][y].setX(x); // tillsätter varje grid x-y koord i tile-format
				grid[x][y].setY(y);
			}
		}

		// Tillge varje entity i världen ett objektnamn
		
//		for (int j = 0; j < entityManager.getEntities().size(); j++) {
//			Entity e = entityManager.getEntities().get(j);
//			e.setId("Obj" + entityManager.getNamegiver());
//			entityManager.IncrementNamegiver();
//
//	}
}
	

	private void updateGrid() {
		for (Entity e : entityManager.getEntities()) {

			int Tx = (int) (e.getX() + e.getWidth() / 2) / Tile.TILE_WIDTH;
			int Ty = (int) (e.getY() + e.getHeight() / 2) / Tile.TILE_HEIGHT;

			Tx = (int) (Math.floor((double) (Tx)));
			Ty = (int) (Math.floor((double) (Ty)));

			if (Tx < 0 || Ty < 0 ||Tx >=width ||Ty >= height)
				continue;
			Tile t = grid[Tx][Ty];

			t.addEntity(e);

		}

	}

	private void emptyGrid() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				grid[x][y].entitites.clear();
			}
		}
	}

	public int getWith() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tile getTile(int x, int y) {
		// make sure game thinks he is on grasstile if
		// out of bounds
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}

		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.grassTile;
		return t;
	}

	public Tile getGridTile(int x, int y) {
		// make sure game thinks he is on grasstile if
		// out of bounds
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}

		Tile t = grid[x][y];
		if (t == null)
			return Tile.grassTile;
		return t;
	}
}
