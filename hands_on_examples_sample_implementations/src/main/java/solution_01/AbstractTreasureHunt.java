package solution_01;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;


abstract class AbstractTreasureHunt extends JFrame {

	//---- Fields

	static final long serialVersionUID = 1L;

	static final int WIDTH = 7;
	static final int HEIGHT = 7;

	private JPanel background;

	GameBoard gameBoard = new GameBoard(this, WIDTH, HEIGHT);

	BoardItem placeholder = new BoardItem(gameBoard, "planetCute/placeholder.png");
	BoardItem grass = new BoardItem(gameBoard, "planetCute/grass_block.png");
	BoardItem dirt = new BoardItem(gameBoard, "planetCute/dirt_block.png");
	BoardItem stone = new BoardItem(gameBoard, "planetCute/stone_block.png");
	Rock rock = new Rock(gameBoard);
	Gem gem = new Gem(gameBoard);

	Avatar avatar = new Avatar(gameBoard);

	//---- Methods

	protected static void start() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TreasureHunt().init();
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
				paintUndergroundIn(g2);
				paintBackgroundIn(g2);
				paintForegroundIn(g2);
			}
		};

		add(background);
		setSize(840, 840);
		setMenuBar(null);
		setTitle("Treasure Hunt");

		setVisible(true);
		
		placeGameBoardItems();
		startMovement();
	}

	void placeGameBoardItems() {
		// Obstacles
		add(rock, 1, 3);
		add(rock, 2, 3);
		add(rock, 5, 2);
		add(rock, 4, 1);
		add(rock, 0, 1);
		add(rock, 2, 0);
		add(rock, 4, 5);
		add(rock, 6, 4);

		// Game goal
		add(gem, 5, 1);

		// Avatar
		gameBoard.setAvatarAt(avatar, 1, 6);

		redraw();
	}

	void add(BoardItem boardItem, int x, int y) {
		gameBoard.addAt(boardItem, x, y);
	}

	void startMovement() {
		gameBoard.startMovement();
	}

	public void redraw() {
		repaint();
		if (gameBoard.isGameOver()) {
			drawText(gameBoard.getGameOverMessage());
		}
	}

	/**
	 * Draws the provided text in the middle of the screen. 
	 */
	private void drawText(String text) {
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

	void paintUndergroundIn(Graphics2D g2) {
		// We fill the underground with dirt
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				paintIn(g2, dirt, j, i, 0, 82);
			}
		}	
	}

	void paintBackgroundIn(Graphics2D g2) {
		// We pave the background with grass
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				paintIn(g2, grass, j, i,  0, 41);
			}
		}	
		// Add a little paved way at the bottom:
		paintIn(g2, stone, 1, 5,  0, 41);
		paintIn(g2, stone, 1, 6,  0, 41);
	}

	void paintForegroundIn(Graphics2D g2) {	
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				paintIn(g2, placeholder, j, i);
			}
		}
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				BoardItem boardItem = gameBoard.getItemFor(j, i);
				if (boardItem != null) {
					paintIn(g2, boardItem, j, i);
				}
			}
		}	
	}

	void paintIn(Graphics2D g2, BoardItem boardItem, int x, int y) {
		paintIn(g2, boardItem, x, y, 0, 0);
	}

	void paintIn(Graphics2D g2, BoardItem boardItem, int x, int y, int xOffset, int yOffset) {
		int spriteWidth = boardItem.getSprite().getWidth(null);
		int spriteHeight = boardItem.getSprite().getHeight(null) - 86;
		
		// We do a little hack to center the game board in the game window.
		int horizontalOffset = (getWidth() - WIDTH * spriteWidth) / 2; 
		
		g2.drawImage(boardItem.getSprite(), horizontalOffset + x * spriteWidth + xOffset, y * spriteHeight + yOffset, null);
	}

}
