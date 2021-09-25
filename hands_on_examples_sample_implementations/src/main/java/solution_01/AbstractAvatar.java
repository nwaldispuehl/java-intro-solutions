package solution_01;

import java.awt.Image;

abstract class AbstractAvatar extends BoardItem implements Runnable {
	
	//---- Fields
	
	Direction[] directions = new Direction[] { Direction.TOP, Direction.RIGHT, Direction.BOTTOM, Direction.LEFT };
	Image[] sprites;
	int direction = 0;
	private Gem gem;
	
	enum Direction { 
		TOP(0), RIGHT(1), BOTTOM(2), LEFT(3);
		
		private int id;
		
		private Direction(int id) {
			this.id = id;
		}
		int id() {
			return id;
		}
		
	}
	
	//---- Constructor
	
	AbstractAvatar(GameBoard gameBoard, String upSprite, String downSprite, String leftSprite, String rightSprite) {
		super(gameBoard, upSprite);
		sprites = new Image[] {getImageFrom(upSprite), getImageFrom(rightSprite), getImageFrom(downSprite), getImageFrom(leftSprite)};
	}
	
	//---- Methods

	@Override
	public void run() {
		// We also make a short nap before any movement so that the board is visible:
		sleep();
		
		move();
		
		// At the end of the move we check if we were successful.
		if (gem != null) {
			getGameBoard().setGameOverWith("You win!");
			log("Won game.");
		}
		else {
			getGameBoard().setGameOverWith("You failed!");
			log("Lost game.");
		}
		redraw();
	}
	
	abstract void move();

	boolean isObstacleAhead() {
		boolean result = isObstacleInDirection(direction);
		log("Is obstacle ahead: " + result);
		return result;
	}

	boolean isObstacleRight() {
		boolean result = isObstacleInDirection(modulus(direction + 1, directions.length));
		log("Is obstacle right: " + result);
		return result;
	}

	boolean isObstacleLeft() {
		boolean result = isObstacleInDirection(modulus(direction - 1, directions.length));
		log("Is obstacle left: " + result);
		return result;
	}
	
	/**
	 * Since '%' only provides the remainder, and not the modulus, we got to deal with negative results.
	 */
	private int modulus(int i, int n) {
		return ((i % n) + n) % n;
	}

	private boolean isObstacleInDirection(int direction) {
		switch (directions[direction]) {
		case TOP:
			return getGameBoard().isObstacleNorthOf(this);
		case RIGHT:
			return getGameBoard().isObstacleEastOf(this);
		case BOTTOM:
			return getGameBoard().isObstacleSouthOf(this);
		case LEFT:
			return getGameBoard().isObstacleWestOf(this);
		default:
			throw new IllegalArgumentException("No known direction");
		}
	}
	
	boolean hasGem() {
		return gem != null;
	}

	void moveForwards() {
		log("Move forwards");
		moveInDirection();
		finishMove();
	}
	
	private void moveInDirection() {
		switch (directions[direction]) {
		case TOP:
			getGameBoard().moveNorth((Avatar) this);
			break;
		case RIGHT:
			getGameBoard().moveEast((Avatar) this);
			break;
		case BOTTOM:
			getGameBoard().moveSouth((Avatar) this);
			break;
		case LEFT:
			getGameBoard().moveWest((Avatar) this);
			break;
		}
	}

	void turnLeft() {
		log("Turn left");
		updateDirectionBy(-1);
		finishMove();
	}

	void turnRight() {
		log("Turn right");
		updateDirectionBy(+1);
		finishMove();
	}
	
	void pickUp(Gem gem) {
		this.gem = gem;
		log("Picked up gem");
	}
	
	private void log(String message) {
		System.out.println(message);
	}
	
	private void updateDirectionBy(int directionDelta) {
		direction = Math.floorMod(direction + directionDelta, directions.length);
	}
	
	@Override
	Image getSprite() {
		return sprites[direction];
	}
	
	private void finishMove() {
		redraw();
		sleep();
	}
	
	private void sleep() {
		sleepFor(getSleepTimeInMilliSeconds());
	}
	
	private void sleepFor(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void redraw() {
		getGameBoard().redraw();
	}
	
	abstract long getSleepTimeInMilliSeconds();
	
}
