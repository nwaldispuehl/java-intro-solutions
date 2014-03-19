package exercise_03.gameboard;

import java.awt.*;
import java.util.Collection;

/**
 * The game board element interface...
 */
public interface GameBoardElement {

	/**
	 * @return the current position of this game board element.
	 */
	Position getCurrentPosition();
	
	/**
	 * Updates the position of an element.
	 * 
	 * @param position
	 */
	void setPosition(Position position);

	/**
	 * Takes the list of all elements as input and calculates the position where this element wants to go next.
	 * 
	 * @return the position this element wants to go next.
	 */
	Position calculateNextPosition(Collection<GameBoardElement> elements);
	
	/**
	 * @return the avatar image of this game board element.
	 */
	Image getImage();
	
}
