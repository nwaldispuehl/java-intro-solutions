package solution_01;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

abstract class AbstractTreasureHunt extends Application {

	//---- Fields
	
	static final int WIDTH = 7;
	static final int HEIGHT = 7;
	
	GameBoard gameBoard = new GameBoard(this, WIDTH, HEIGHT);
	
	BoardItem placeholder = new BoardItem(gameBoard, "planetCute/placeholder.png");
	BoardItem grass = new BoardItem(gameBoard, "planetCute/grass_block.png");
	BoardItem dirt = new BoardItem(gameBoard, "planetCute/dirt_block.png");
	BoardItem stone = new BoardItem(gameBoard, "planetCute/stone_block.png");
	Rock rock = new Rock(gameBoard);
	Gem gem = new Gem(gameBoard);
	
	Avatar avatar = new Avatar(gameBoard);

	GridPane foreground;
	BorderPane overlay;
	
	
	//---- Methods

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setOnCloseRequest((event) -> System.exit(0));
		
		StackPane stackPane = new StackPane();
		
		GridPane underground = createGridPane();
		initializeUndergroundIn(underground);
		stackPane.getChildren().add(underground);
		
		GridPane ground = createGridPane();
		ground.setTranslateY(-41);
		initializeBackgroundIn(ground);
		stackPane.getChildren().add(ground);
		
		foreground = createGridPane();
		foreground.setTranslateY(-82);
		stackPane.getChildren().add(foreground);
		
		overlay = new BorderPane();
		stackPane.getChildren().add(overlay);
		
		Scene scene = new Scene(stackPane, 840, 840);
		primaryStage.setTitle("Treasure Hunt");
        primaryStage.setScene(scene);
        primaryStage.show();
        
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
		paintForegroundIn(foreground);
		if (gameBoard.isGameOver()) {
			printToOverlay(gameBoard.getGameOverMessage());
		}
	}

	private void printToOverlay(String gameOverMessage) {
		Text gameOverBanner = new Text(gameOverMessage);
		gameOverBanner.setFont(Font.font("Arial", FontWeight.BLACK, 100));
		gameOverBanner.setFill(Paint.valueOf("white"));
		overlay.setCenter(gameOverBanner);
	}

	GridPane createGridPane() {
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setVgap(-86);
		gridpane.setHgap(0);		
		return gridpane;
	}
	
	void initializeUndergroundIn(GridPane underground) {
		// We pave the background with grass
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				underground.add(new ImageView(dirt.getSprite()), j, i);
			}
		}	
	}

	void initializeBackgroundIn(GridPane background) {
		// We pave the background with grass
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				background.add(new ImageView(grass.getSprite()), j, i);
			}
		}	
		// Add a little paved way at the bottom:
		background.add(new ImageView(stone.getSprite()), 1, 5);
		background.add(new ImageView(stone.getSprite()), 1, 6);
	}
	
	void paintForegroundIn(GridPane gridPane) {
		gridPane.getChildren().clear();
		
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				gridPane.add(new ImageView(placeholder.getSprite()), j, i);
			}
		}
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				BoardItem boardItem = gameBoard.getItemFor(j, i);
				if (boardItem != null) {
					gridPane.add(new ImageView(boardItem.getSprite()), j, i);
				}
			}
		}	
	}
 
}
