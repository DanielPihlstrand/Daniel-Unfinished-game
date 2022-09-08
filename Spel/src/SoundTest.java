
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.Timer;

public class SoundTest {

	static void runApplication()  {
		// ljudfil finns i Test-projektet (kolla med utforskaren)

				// fixar Jframen och knappar o allt sånt
				JFrame jframe = new JFrame("Menu");
				JLabel label = new JLabel("1");
				jframe.getContentPane().add(label);
				JButton sbutton = new JButton("Spela upp ljud");
				JButton obutton = new JButton("Options");

				JButton ebutton = new JButton("Exit");

				sbutton.setBounds(130, 90, 90, 50);
				obutton.setBounds(400, 90, 90, 50);
				ebutton.setBounds(265, 150, 90, 50);

				Font bPlain = new Font("Arial", Font.PLAIN, 11);
				sbutton.setFont(bPlain);
				ebutton.setFont(bPlain);
				obutton.setFont(bPlain);

				jframe.add(sbutton);
				jframe.add(ebutton);
				jframe.add(obutton);
			
				jframe.setSize(600, 380);
				jframe.setLocationRelativeTo(null);
				
				jframe.setLayout(null);
				jframe.setVisible(true);
				jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				/// Knapparnas funktioner
				sbutton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						File Sound = new File("sounds/test.wav");
						PlaySound(Sound);
					}
				});

				ebutton.addActionListener(new ActionListener() {

					// Exit:ar med ljud
					public void actionPerformed(ActionEvent e) {
						File Sound = new File("sounds/exit.wav");
						jframe.setVisible(false); // you can't see me!
						jframe.dispose(); // döda resurser
						PlaySound(Sound);
						System.exit(0);
					}
				});

				obutton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						File Sound = new File("sounds/monster.wav");
						PlaySound(Sound);
						jframe.setVisible(false); // you can't see me!
						jframe.dispose();
						DrawCanvas(); // Nytt fönster, se metoden
						
					}
				});
	}
	
	
	public static void main(String[] args) {
		
		runApplication();
		
		// Kod för meny i texraden. Kommer inte köras
		boolean b = true;
		while (b) {
			System.out.println(" Press 1 to play sound. Press 2 to exit program");
			Scanner scan = new Scanner(System.in);
			int number = scan.nextInt();
			switch (number) {

			case 1:
				File Sound = new File("sounds/test.wav");
				System.out.println("Ljud!");
				PlaySound(Sound);
				break;
			case 2:
				// closing application
				scan.close();
				b = false;
				break;

			// Default mode telling us to run the main program (meny) again.
			default:
				main(args);

			}

		}

	}

	// Spelar ljudfil. Ljudfilen spelas en gång tills den är färdig innan nästa
	// session
	public static void PlaySound(File sound) {

		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			// Sänk volymen
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
			clip.start();
			// måste pausa så ljudfil hiiner köras
			Thread.sleep(clip.getMicrosecondLength() / 1000);
		} catch (Exception e) {

		}

	}

	public static void DrawCanvas() {
		
		
		
		
		final String title = "Test Window";
		final int width = 1200;
		final int height = width / 16 * 9;
		JFrame frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		// Creating the canvas.
		// EDIT: Om man skall rita egna grejjer på canvas får man göra en egen
		// canvas-class, som extendar canvas. Här ovverirar vi classens
		// paint-metod då och bestämmer i den vad som kmr ritas.
		MyCanvas mc = new MyCanvas(width, height);
		mc.setSize(width, height);
		mc.setBackground(Color.BLACK);
		frame.add(mc);
		
	}
	
			//Metod för att ladda en buffered-image enda settet att få det att funka
	private static BufferedImage LoadImage(String file)  {
		 BufferedImage image = null;
		 try
		 {
		     image = ImageIO.read(new File(file));
		 }
		 catch (IOException e)
		 {
		     System.out.println(e);
		 }
		 return image;
	 }
	
	// Egen canvas-klass, mini-spelet
	private static class MyCanvas  extends JPanel implements KeyListener, ActionListener  {
		
		//Load images
		BufferedImage img = LoadImage("images/bandit_very_small_black.png");
		SpriteSheet sheet = new SpriteSheet(LoadImage("images/tilesheet_64_64.png"));
		BufferedImage grass = sheet.crop(0, 0, 64, 64);
		BufferedImage rock = sheet.crop(64, 0, 64, 64);
		
		
		//Window dimensions 
		private int width;
		private int height;
		private Timer timer;
		//Player dimensions
		private int x = 480, y = 300;
		private int velX = 0, velY = 0;	
		private int xsize = 25, ysize = 25;
		
		//other objects dimensions n stuff
		private int xexit = 1000, yexit = 100;
		private int xobj =0, yobj =0,widthobj =75, heightobj =75,velXobj =2, velYobj =2;
		private int imgx = 300, imgy = 330, velimgx = 2, velimgy = 3, imgwidth = 102 /2, imgheight = 98/2;
		
		//hämtar buffered image via en statisk metod
		 
		
		
		private MyCanvas(int width, int height) {
			this.width = width;
			this.height = height;
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			timer = new Timer(8, this);
			timer.start();
		}

		public void paint(Graphics g) {
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);
			
			

			for (int j=0; j<=9;j++) {
				for (int i=0; i<=3;i++) {
					g.drawImage(grass,j*64,i*64,null);		
				}
				for (int i=4; i<=5;i++) {
					g.drawImage(rock,j*64,i*64,null);		
				}
			}
			
			g.setColor(Color.red);
			g.fillOval(xobj, yobj, 75, 75);
			
			g.drawImage(img, imgx, imgy, this);
			g.setColor(Color.white);
			g.setFont(new Font("Serif",Font.PLAIN,14));
			g.drawString("Position: "+x+" "+y ,50 ,50);
			
			g.setColor(Color.orange);
			g.fillOval(xexit, yexit, 25, 25);
			g.setFont(new Font("Serif",Font.PLAIN,14));
			g.drawString("Exit" ,xexit +2,yexit -10);
			
			// Vår spelare
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x, y, xsize, ysize);
			g.dispose();
			
		}

		public void actionPerformed(ActionEvent e) {
			timer.start();

			//Våra collissioner
			if (new Rectangle(x,y,xsize,ysize).intersects(new Rectangle(xexit,yexit,xsize-4,ysize-4))) {
				timer.stop();			
				
			}
			if (new Rectangle(x,y,xsize,ysize).intersects(new Rectangle(xobj,yobj,widthobj-10,heightobj-10))) {
				PlaySound(new File("sounds/monster.wav"));
				System.exit(0);	//Vill bara stänga fönstret, ej stänga hela jädra programmet	
				
				}
			
			if (new Rectangle(xobj,yobj,widthobj-10,heightobj-10).intersects(new Rectangle(imgx,imgy,imgwidth,imgheight))) {
				velXobj =-velXobj;
				velYobj =-velYobj;
				velimgx =-velimgx;
				velimgy =-velimgy;	
				
			}
			
			
			// Kollar om spelare är utanför gränser
			if (x < 0) {
				x = 0;
				velX = 0;
			}
				
			if (x > 1156) {
				x = 1156; //tvungen att mäta manuellt då fönstrets can't ej ligger på width
				velX = 0;
			}

			if (y > 608) {
				y = 608;
				velY = 0;
			}

			if (y < 0) {
				y = 0;
				velY = 0;
			}
			
			//uppdate players coord
			x +=  velX;
			y +=  velY;
			
			
			//Vårt "ojekts" mekanik
			if (xobj> 1156 -widthobj/2 || xobj <0) {
				velXobj =-velXobj;
			}
			
			if (yobj >608 - heightobj/2 ||yobj <0) {
				velYobj =-velYobj;
				
			}
			
			xobj +=velXobj;
			yobj +=velYobj;
			
			//BIldens "mekanik"
			if (imgx > 1156 -imgwidth-20 || imgx <0) {
				velimgx =-velimgx;
			}		
			if (imgy >608 -imgheight-20 ||imgy <0) {
				velimgy =-velimgy;				
			}		
			imgx +=velimgx;
			imgy +=velimgy;
			
			repaint();

			//slut på actionperformed
		}

		// När vi trycker på tangenter
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {				
				velX -= 1;
			}

			if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
				velX += 1;
			}

			if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				velY -= 1;
			}

			if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
				velY += 1;
			}

			if (key == KeyEvent.VK_SPACE) {
				y-=15;
			}
			
		}
		
		
		public void keyReleased(KeyEvent arg0) {
		}

		public void keyTyped(KeyEvent arg0) {
		}

		//Slut på minCanvas
	}

}

