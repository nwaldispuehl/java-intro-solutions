package solution_01;

import java.util.ArrayList;
import java.util.List;

class GameBoard {

	//---- Fields
	
	boolean gameOver = false;
	String gameOverMessage = "";
	List<BoardItem> items = new ArrayList<>();
	BoardItem[][] board;
	AbstractTreasureHunt treasureHunt;
	AbstractAvatar avatar;
	int width;
	int height;
	
	
	//---- Constructor
	
	GameBoard(AbstractTreasureHunt treasureHunt, int width, int height) {
		this.treasureHunt = treasureHunt;
		this.width = width;
		this.height = height;
		board = new BoardItem[width][height];
	}
	
	
	//---- Methods
	
	void addAt(BoardItem boardItem, Position position) {
		addAt(boardItem, position.x, position.y);
	}
	
	void addAt(BoardItem boardItem, int x, int y) {
		items.add(boardItem);
		board[x][y] = boardItem;
	}
	
	void remove(BoardItem boardItem) {
		Position position = getPositionOf(boardItem);
		board[position.x][position.y] = null;
		items.remove(boardItem);
	}
	
	BoardItem getItemFor(Position position) {
		return getItemFor(position.x, position.y);
	}
	
	BoardItem getItemFor(int x, int y) {
		return board[x][y];
	}
	
	boolean hasItem(Position position) {
		return getItemFor(position) != null;
	}

	void startMovement() {
		new Thread(avatar).start();
	}
	
	void redraw() {
		treasureHunt.redraw();
	}

	void setAvatarAt(AbstractAvatar avatar, int x, int y) {
		this.avatar = avatar;
		addAt(avatar, x, y);
	}

	boolean isObstacleNorthOf(BoardItem boardItem) {
		return isObstacleTranslatedOf(boardItem, 0, -1);
	}

	boolean isObstacleEastOf(BoardItem boardItem) {
		return isObstacleTranslatedOf(boardItem, 1, 0);
	}

	boolean isObstacleSouthOf(BoardItem boardItem) {
		return isObstacleTranslatedOf(boardItem, 0, 1);
	}

	boolean isObstacleWestOf(BoardItem boardItem) {
		return isObstacleTranslatedOf(boardItem, -1, 0);
	}
	
	boolean isObstacleTranslatedOf(BoardItem boardItem, int xTrans, int yTrans) {
		Position position = getPositionOf(boardItem);
		Position newPosition = position.translateBy(xTrans, yTrans);
		
		if (!isOnBoard(newPosition)) {
			return true;
		}
		
		BoardItem item = getItemFor(newPosition);
		return item instanceof Rock;
	}

	Position getPositionOf(BoardItem boardItem) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null && board[i][j] == boardItem) {
					return new Position(i, j);
				}
			}
		}
		return null;
	}
	
	void moveNorth(Avatar avatar) {
		moveBy(avatar, 0, -1);
	}
	
	void moveEast(Avatar avatar) {
		moveBy(avatar, 1, 0);
	}
	
	void moveSouth(Avatar avatar) {
		moveBy(avatar, 0, 1);
	}
	
	void moveWest(Avatar avatar) {
		moveBy(avatar, -1, 0);
	}
	
	void moveBy(Avatar avatar, int xTrans, int yTrans) {
		Position position = getPositionOf(avatar);
		Position newPosition = position.translateBy(xTrans, yTrans);
		if (isValid(newPosition)) {
			remove(avatar);
			if (hasItem(newPosition) && getItemFor(newPosition) instanceof Gem) {
				avatar.pickUp((Gem) getItemFor(newPosition));
			}
			
			addAt(avatar, newPosition);
		}
	}
	
	boolean isValid(Position position) {
		return isOnBoard(position) && isPassable(position);
	}
	
	boolean isOnBoard(Position position) {
		return 0 <= position.x && position.x < width && 0 <= position.y && position.y < height;
	}
	
	boolean isPassable(Position position) {
		return !(board[position.x][position.y] instanceof Rock) ;
	}

	class Position {
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		final int x;
		final int y;
		
		Position translateBy(int xTrans, int yTrans) {
			return new Position(x + xTrans,  y + yTrans);
		}
	}
	
	void setGameOverWith(String message) {
		gameOver = true;
		this.gameOverMessage = message;
	}

	boolean isGameOver() {
		return gameOver;
	}

	String getGameOverMessage() {
		return gameOverMessage;
	}
	

}
