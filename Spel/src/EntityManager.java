import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private int namegiver;
	private Comparator <Entity> renderSorter = new Comparator<Entity>() {
		
		public int compare (Entity a, Entity b) {
			if(a.getY() + a .getHeight() < b.getY() +  b.getHeight()) {
				return -1;
			}
			return 1;
		} 
		
	};
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i++) { // Old fashinoed foor lopp because vi ska hantera collisions med
													// dessa
			Entity e = entities.get(i);
			e.tick();
		}
		entities.sort(renderSorter);
	}

	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}

	}

	public void addEntity(Entity e) {
		entities.add(e);
		entities.get(entities.size()-1).setId("Obj"+namegiver);
		namegiver++;
	}


	public void removeEntity(Entity e) {
		if(entities.contains(e)) {
			entities.remove(e);
		}
	}
	
	public void removeEntities() {
		entities.clear();
	}
	
	// Getter and Setters
	public Handler getHandler() {
		return handler;
	}

	public int getNamegiver() {
		return namegiver;
	}
	
	public void IncrementNamegiver() {
		namegiver++;
	}
	
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
