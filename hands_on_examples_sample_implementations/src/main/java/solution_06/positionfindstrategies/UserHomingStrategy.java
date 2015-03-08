package solution_06.positionfindstrategies;

import java.util.Collection;

import solution_06.alliance.Protagonist;
import solution_06.gameboard.FindNextPositionStrategy;
import solution_06.gameboard.GameBoardElement;
import solution_06.gameboard.Position;

/**
 * A strategy which looks for a Protagonist game board element and homes for it. If there is none, the current position is returned.
 */
public class UserHomingStrategy implements FindNextPositionStrategy {

  @Override
  public Position findNextPosition(Position ownPosition,
      Collection<GameBoardElement> allElements) {
    
    GameBoardElement protagonist = findProtagonistIn(allElements);
    
    if (protagonist == null) {
      return null;
    }
    
    // For convenience, we fill the positions into short variables
    int x = ownPosition.getX();
    int y = ownPosition.getY();
    int otherX = protagonist.getCurrentPosition().getX();
    int otherY = protagonist.getCurrentPosition().getY();
    
    // If the x position of the enemy is smaller than the own, we decrement our own x position by one ...
    if (otherX < x) {
      return new Position(x - 1, y);
    }
    
    // ... and so on ...
    if (x < otherX) {
      return new Position(x + 1, y);
    }
    
    if (otherY < y) {
      return new Position(x, y - 1);
    }
    
    if (y < otherY) {
      return new Position(x, y + 1);
    }
    
    return ownPosition;
  }
  
  /**
   * Just goes through all game board elements provided and returns it if it is a Protagonist. 
   * If none is found, null is returned.
   * 
   */
  private GameBoardElement findProtagonistIn(Collection<GameBoardElement> allElements) {
    for (GameBoardElement e : allElements) {
      if ((e instanceof Protagonist)) {
        return e;
      }
    }
    
    return null;
  }

}
