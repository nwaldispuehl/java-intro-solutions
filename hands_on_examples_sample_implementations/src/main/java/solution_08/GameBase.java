package solution_08;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_KP_DOWN;
import static java.awt.event.KeyEvent.VK_KP_LEFT;
import static java.awt.event.KeyEvent.VK_KP_RIGHT;
import static java.awt.event.KeyEvent.VK_KP_UP;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import static solution_08.positionfindstrategies.UserInput.DOWN;
import static solution_08.positionfindstrategies.UserInput.LEFT;
import static solution_08.positionfindstrategies.UserInput.RIGHT;
import static solution_08.positionfindstrategies.UserInput.UP;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import solution_08.gameboard.FactionMember;
import solution_08.gameboard.GameBoard;
import solution_08.gameboard.GameBoardElement;
import solution_08.gameboard.Position;
import solution_08.positionfindstrategies.UserInput;
import solution_08.utils.ImageLoader;

/**
 * Abstract base class for NotTheDroidsWeAreLookingFor which does all the heavy work.
 */
public abstract class GameBase extends JFrame {

  private static final int FIELD_SIZE = 48;
  
  /* Below some configuration stuff. Ignore for now. If you're bold, you can play around with it a bit. */

  private static final long serialVersionUID = 1L;

  private GameBoard gameBoard;
  
  private int horizontalFields;
  
  private int verticalFields;

  protected abstract void configureGame();

  protected abstract void makeTurn(UserInput userInput);
  
  protected abstract void displayFinalResult();
  
  protected int getHorizontalFields() {
    return horizontalFields;
  }

  protected int getVerticalFields() {
    return verticalFields;
  }

  private void prepareTurn(UserInput userInput) {
    if (isGameFinished()) {
      displayFinalResult();
    }
    else {
      if (userInput != null) {
        makeTurn(userInput);
        repaint();
      }
    }
   
  }


  protected abstract boolean isGameFinished();

  
  protected void initializeGameBoardWithFieldSize(int horizontalFields, int verticalFields) {
    this.horizontalFields = horizontalFields;
    this.verticalFields = verticalFields;
    gameBoard = new GameBoard(horizontalFields, verticalFields);
  }
  
  protected GameBoard getGameBoard() {
    return gameBoard;
  }

  private JPanel background;

  protected static void start() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            new NotTheDroidsWeAreLookingFor().init();
	        }
	    });		
	}
  
  protected void init() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

    background = new JPanel() {
      private static final long serialVersionUID = 1L;



      @Override
      public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw tiles
        int counter = 0;
        for (int x = 0; x < horizontalFields; x++) {
          for (int y = 0; y < verticalFields; y++) {
            g2.drawImage(randomTiles.get(counter), x * FIELD_SIZE, y * FIELD_SIZE, null);
            counter++;
          }
        }

        // Draw game elements
        for (GameBoardElement element : gameBoard.getAllElements()) {
          drawElementTo(g2, element);
        }
      }
    };

    addKeyListenerTo(background);
    add(background);
    configureGame();
    prepareBackgroundTiles();
    setSize(horizontalFields * FIELD_SIZE, verticalFields * FIELD_SIZE);
    setVisible(true);
  }



  private void drawElementTo(Graphics2D g2, GameBoardElement element) {
    drawShadowTo(g2, element.getCurrentPosition());
    drawSpriteTo(g2, element);
    
    if (element instanceof FactionMember) {
      drawElementPowerTo(g2, (FactionMember) element);
    }
  }

  private void drawShadowTo(Graphics2D g2, Position p) {
    g2.drawImage(shadow, p.getX() * FIELD_SIZE, p.getY() * FIELD_SIZE, null);
  }
  
  private void drawSpriteTo(Graphics2D g2, GameBoardElement element) {
    Position p = element.getCurrentPosition();
    g2.drawImage(element.getImage(), p.getX() * FIELD_SIZE, p.getY() * FIELD_SIZE, null);
  }
  
  protected void drawElementPowerTo(Graphics2D g2, FactionMember factionMember) {
    Position p = factionMember.getCurrentPosition();
    g2.setColor(Color.WHITE);
    g2.drawString(String.valueOf(factionMember.getPower()), p.getX() * FIELD_SIZE, p.getY()* FIELD_SIZE);
  }
  
  private void addKeyListenerTo(JPanel background) {
    background.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {}

      @Override
      public void keyReleased(KeyEvent e) {}

      @Override
      public void keyPressed(KeyEvent e) {
        checkForQuitKey(e);
        UserInput direction = decodeDirectionFrom(e);
        prepareTurn(direction);
      }


      
    });

    background.setFocusable(true);
  }
  
  private void checkForQuitKey(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == KeyEvent.VK_Q) {
      System.exit(0);
    }
  }
  
  private UserInput decodeDirectionFrom(KeyEvent e) {
    int key = e.getKeyCode();
    
    if (VK_DOWN == key || VK_KP_DOWN == key) {
      return DOWN;
    }
    
    if (VK_UP == key || VK_KP_UP == key) {
      return UP;
    }
    
    if (VK_LEFT == key || VK_KP_LEFT == key) {
      return LEFT;
    }
    
    if (VK_RIGHT == key || VK_KP_RIGHT == key) {
      return RIGHT;
    }
    
    return null;
  }

  /**
   * We create a random arrangement of tiles, but need to use the same arrangement during the whole game.
   */
  private void prepareBackgroundTiles() {
    Random random = new Random();
    Image[] tiles = new Image[]{tile1, tile2, tile3};
    for (int i = 0; i < horizontalFields * verticalFields; i++) {
      randomTiles.add(tiles[random.nextInt(3)]);
    }
  }

  private java.util.List<Image> randomTiles = new ArrayList<Image>();
  private ImageLoader loader = new ImageLoader();
  // Tiles from here: http://opengameart.org/content/lpc-tile-atlas
  private Image tile1 = loader.loadIcon("not_the_droids/tile1.png");
  private Image tile2 = loader.loadIcon("not_the_droids/tile2.png");
  private Image tile3 = loader.loadIcon("not_the_droids/tile3.png");
  private Image shadow = loader.loadIcon("not_the_droids/shadow.png");

  /**
   * Draws the provided text in the middle of the screen. 
   */
  protected void drawText(String text) {
    Graphics2D graphics2d = (Graphics2D) getGraphics();
    Font font = new Font(Font.SANS_SERIF, Font.BOLD | Font.TRUETYPE_FONT, 64);
    
    graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    graphics2d.setFont(font);
    FontMetrics fontMetrics = graphics2d.getFontMetrics();
    int messageHeight = fontMetrics.getHeight();
    int messageWidth = fontMetrics.stringWidth(text);
    
    graphics2d.drawString(text, (getWidth() - messageWidth) / 2, (getHeight() - messageHeight) / 2);
    graphics2d.setColor(Color.WHITE);
    graphics2d.drawString(text, 3 + (getWidth() - messageWidth) / 2, 3 + (getHeight() - messageHeight) / 2);
  }
  

  /**
   * Lets the thread sleep for the number of milliseconds provided.
   * 
   * @param milliseconds
   *          how long should I sleep?
   */
  protected void sleepMilliseconds(long milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  


}
