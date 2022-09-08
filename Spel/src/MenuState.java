import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MenuState extends State{

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);	
		uiManager.addObject(new UIImageButton(handler.getWidth()/2 - 128/2,handler.getHeight()/6,128,64,Assets.btn_start, new ClickListerner() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getGame().setToGameState();
			}}));
	
		uiManager.addObject(new UIImageButton(handler.getWidth()/2 - 128/2,5*handler.getHeight()/6,128,64,Assets.btn_stop, new ClickListerner() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				System.exit(1);
			}}));
	
	}
	
	@Override
	public void tick() {
		uiManager.tick();
		//om vi trycker med pilar ist för mus
		//keyManager.tick();

		
		
		
//		if (handler.getMouseManager().isLeftPressed()) {
//			handler.getGame().setToGameState();
//		//State.setState(handler.getGame().gameState();  hans kod, där statesen i game är publica, inte private.
		
		}
	
	

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
		
//		g.setColor(Color.BLACK);
//		g.setFont(new Font("Serif", Font.PLAIN, 18));
//		g.drawString("Menu before game, Press left mousebutton to start! " , 130, 75);

		
		
	}

}
