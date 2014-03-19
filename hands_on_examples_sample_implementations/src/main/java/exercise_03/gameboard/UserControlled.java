package exercise_03.gameboard;

import exercise_03.positionfindstrategies.UserInput;

public interface UserControlled {

  Position calculateNextPositionByUserInput(UserInput userInput);
  
}
