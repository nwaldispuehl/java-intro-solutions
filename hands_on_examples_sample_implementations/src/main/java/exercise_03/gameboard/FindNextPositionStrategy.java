package exercise_03.gameboard;

import java.util.Collection;

/**
 * Strategies to find the position an element should go in the next round.
 */
public interface FindNextPositionStrategy {

  Position findNextPosition(Position ownPosition, Collection<GameBoardElement> allElements);
  
}
