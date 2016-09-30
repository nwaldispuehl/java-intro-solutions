package solution_01;

import javafx.scene.image.Image;

class BoardItem {
	
	//---- Fields
	
	Image sprite;
	GameBoard gameBoard;
	
	
	//---- Constructor
	
	BoardItem(GameBoard gameBoard, String spritePath) {
		this.gameBoard = gameBoard;
		sprite = getImageFrom(spritePath);
	}

	
	//---- Methods
	
	Image getSprite() {
		return sprite;
	}
	
	Image getImageFrom(String path) {
		return new Image(getClass().getClassLoader().getResource(path).toString());
	}
	
	GameBoard getGameBoard() {
		return gameBoard;
	}

}
