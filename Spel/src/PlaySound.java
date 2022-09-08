import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class PlaySound {

	private File sound;

	public PlaySound(String snd){
		 
		//skicka in filsökvägen för ljudet i constructorn
		this.sound= new File(snd);
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
}
