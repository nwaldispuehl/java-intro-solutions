package solution_07.gameboard;

import solution_07.positionfindstrategies.UserInput;

public interface UserControlled {

  Position calculateNextPositionByUserInput(UserInput userInput);
  
}
