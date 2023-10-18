package solution_04;

import java.awt.Graphics2D;
import java.awt.Image;

public class ThatsNoMoon extends MoonBase {

  Image icon = loadIcon();

  int iconCoordinateX = 100;
  int iconCoordinateY = 50;
  
  double speed = 1;
  double acceleration = 1.5;
  double dampingFactor = 0.98;

  /**
   * This method is periodically called. You can manipulate the x and y
   * coordinates. See what happens if you do that and start the program.
   */
  public void updateValues() {

	  // Acceleration (speed changes over time)
	  speed += acceleration;
	  
	  // We apply some damping to the speed.
	  speed *= dampingFactor;
	  
	  // Applying speed
	  iconCoordinateY += speed;
    
	  // If we get out of the windows at the upper border, we negate speed.
	  if (iconCoordinateY < 0) {
		  speed = - speed;
	  }
	  
	  // If we get out of the window at the lower border, we also negate speed (-> Bouncing).
	  if ((getHeight() - icon.getHeight(null)) < iconCoordinateY) {
		  speed = - speed;
	  }
  }
  
  // You can safely ignore the following method for now. :)

  @Override
  protected void drawImageOn(Graphics2D g2) {
    g2.drawImage(icon, iconCoordinateX, iconCoordinateY, null);
  }
  
  public static void main(String[] args) {
		start();
	}
}
