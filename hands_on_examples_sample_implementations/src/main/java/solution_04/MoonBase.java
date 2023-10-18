package solution_04;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import solution_08.utils.ImageLoader;

/**
 * Abstract base class for ThatsNoMoon which does all the heavy work.
 */
public abstract class MoonBase extends JFrame implements Runnable {

  private JPanel background;
  private int initialWindowWidth = 800;
  private int initialWindowHeight = 600;

  protected abstract void updateValues();

  protected abstract void drawImageOn(Graphics2D g2);

  protected static void start() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            new ThatsNoMoon().init();
	        }
	    });		
	}
  
  @Override
  public void run() {
    while (true) {
      updateValues();
      repaint();
      sleepMilliseconds(20);
    }
  }

  /**
   * Does the initial configuration of the program window.
   */
  private void configureWindow() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    background.setBackground(Color.BLACK);
    setSize(initialWindowWidth, initialWindowHeight);
    setVisible(true);
  }

  /**
   * Lets the thread sleep for the number of milliseconds provided.
   * 
   * @param milliseconds
   *          how long should I sleep?
   */
  private void sleepMilliseconds(long milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  protected void init() {

    background = new JPanel() {

      @Override
      public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        drawImageOn(g2);
      }
    };

    add(background);
    configureWindow();

    new Thread(this).start();
  }
  
  @Override
	public void repaint() {
		super.repaint();
		// Prevents the visual stutter:
		Toolkit.getDefaultToolkit().sync();
	}

  /**
   * Loads the icon and returns an images which is paintable by the drawImage
   * method.
   * 
   * @return loaded icon, or null if there were problems loading it.
   */
  protected Image loadIcon() {
    /*
     * Icon by http://www.artua.com/, retrieved here:
     * http://www.iconarchive.com/show/star-wars-icons-by-artua.html
     */
    return new ImageLoader().loadIcon("moon.png");
  }

}
