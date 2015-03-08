package solution_06.positionfindstrategies;

import java.util.Collection;
import java.util.Random;

import solution_06.gameboard.FindNextPositionStrategy;
import solution_06.gameboard.GameBoardElement;
import solution_06.gameboard.Position;

public class RandomNextPositionStrategy implements FindNextPositionStrategy {

  @Override
  public Position findNextPosition(Position ownPosition, Collection<GameBoardElement> allElements) {
    Random random = new Random();
    
    int x = ownPosition.getX();
    int y = ownPosition.getY();
    
    if (random.nextBoolean()) {
      if (random.nextBoolean()) {
        x += 1;
      }
      else {
        x -= 1;
      }
    }
    else {
      if (random.nextBoolean()) {
        y += 1;
      }
      else {
        y -= 1;
      }
    }
    
    return new Position(x, y);
  }

}
