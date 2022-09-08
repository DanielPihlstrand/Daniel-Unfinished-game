import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tile {

	//Store:a entity i tile:en
	public  ArrayList<Entity> entitites;
	private int x;
	private int y;
	
	
	//STATIC STUFF HERE
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile grassTile2 = new GrassTile(1, 0);  //constructor no 2 för grästile, för high_def-gräs
	public static Tile rockTile = new RockTile(2);
	public static Tile TreeTile = new TreeTile(3);
	public static Tile SnowTile = new SnowTile(4);
	public static Tile WaterTile = new WaterTile(5);

	
	//CLASS
	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
		entitites = new ArrayList<Entity>();
	}
	

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	
	public void tick() {
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	public int getId() {
		return id;
	}

	
	public boolean containsEntity(Entity e) {
		return entitites.contains(e);
		
	}
	
		

	public boolean isEmpty() {
		return entitites.isEmpty();
	}
	
	public void addEntity(Entity e) {
		entitites.add(e);
	}
	
	
	public boolean isSolid() {
		return false;
	}


	public static class WaterTile extends Tile{

		public WaterTile(int id) {
			super(Assets.krita[1], id);
			// TODO Auto-generated constructor stub
		}

		public boolean isSolid() {
			return true;
		}
		

		
		
		
	}





}
