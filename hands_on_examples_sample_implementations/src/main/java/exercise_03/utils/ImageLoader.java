package exercise_03.utils;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A helper class which loads an image into a image object.
 */
public class ImageLoader {

	/**
	 * Loads the icon and returns an images which is paintable by the drawImage method.
	 * 
	 * @return loaded icon, or null if there were problems loading it.
	 */
	public Image loadIcon(String fileName) {
	  
		try {
      return ImageIO.read(getClass().getClassLoader().getResource(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
