package solution_01;

import java.awt.Image;

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
		return new ImageLoader().loadIcon(path);
	}
	
	GameBoard getGameBoard() {
		return gameBoard;
	}

}
