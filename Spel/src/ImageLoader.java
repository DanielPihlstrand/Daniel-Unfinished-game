import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
			//return ImageIO.read(ImageLoader.class.getResource(path));
					//ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Det är förmodligen felaktigt filnamn vi skrivit in i koden på filen som ska laddas");
			System.exit(1);
		}
		return null;
	}
	
}