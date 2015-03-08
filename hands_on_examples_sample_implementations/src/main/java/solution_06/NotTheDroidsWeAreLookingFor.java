package solution_06;

import solution_06.alliance.Protagonist;
import solution_06.alliance.Robot;
import solution_06.empire.Antagonist;
import solution_06.empire.GalacticEmpireMember;
import solution_06.empire.Soldier;
import solution_06.gameboard.GameBoard;
import solution_06.gameboard.GameBoardElement;
import solution_06.gameboard.Position;
import solution_06.gameboard.Rock;
import solution_06.positionfindstrategies.UserInput;

public class NotTheDroidsWeAreLookingFor extends GameBase {

  /**
   * We use this variable to store the fact whether the empire has won or the
   * alliance.
   */
  private boolean empireHasWon = false;

  public NotTheDroidsWeAreLookingFor() {
    initializeGameBoardWithFieldSize(18, 12);
  }

  /**
   * Here we prepare the game board by adding all game players (aka elements).
   */
  public void configureGame() {
    GameBoard gameBoard = getGameBoard();

    gameBoard.add(new Protagonist(new Position(3, 4)));
    gameBoard.add(new Robot(new Position(6, 4)));

    gameBoard.add(new Soldier(new Position(12, 6)));
    gameBoard.add(new Soldier(new Position(13, 8)));
    gameBoard.add(new Soldier(new Position(14, 7)));

    // Adding some rocks:
    gameBoard.add(new Rock(new Position(10, 8)));
    gameBoard.add(new Rock(new Position(2, 1)));
    gameBoard.add(new Rock(new Position(17, 7)));

    // And finally the antagonist:
    gameBoard.add(new Antagonist(new Position(13, 10)));

  }

  /**
   * This is called every turn after the user pressed some arrow key.
   */
  public void makeTurn(UserInput userInput) {
    System.out.println("Turn triggered by user input: " + userInput);
    GameBoard gameBoard = getGameBoard();
    gameBoard.makeTurnWithUserInput(userInput);
  }

  /**
   * Is called at the end of every turn.
   * 
   * Implement some way to determine when the game is over. I'd propose to check
   * if either the Protagonist or the Antagonist is not existing anymore.
   */
  @Override
  protected boolean isGameFinished() {
    GameBoard gameBoard = getGameBoard();
    
    // We initialize the variables we use for counting who's still there.
    int empireMembers = 0;
    GameBoardElement protagonist = null;

    // We go through all game board elements we get from the gameboard and check if there is a Protagonist or an Empire Member left.
    for (GameBoardElement e : gameBoard.getAllElements()) {
      
      // If the game board element is a galactic empire member
      if (e instanceof GalacticEmpireMember) {
        // We increment the counter
        empireMembers++;
      }
      
      // If it is the protagonist, we assign it the variable
      if (e instanceof Protagonist) {
        protagonist = e;
      }
    }

    // Now we check if the game is finished.
    
    // If there was no protagonist, the game is finished and the empire has won
    if (protagonist == null) {
      empireHasWon = true;
      return true;
    }
    
    // If there was a protagonist and no empire members, the game is also finished, and the protagonist has won
    if (0 == empireMembers) {
      empireHasWon = false;
      return true;
    }
    
    // Otherwise the game is not yet finished
    return false;
  }

  /**
   * We can use this to display the game result in any way.
   */
  public void displayFinalResult() {
    
    // According to the value of the 'empireHasWon' variable, we set the message:
    if (empireHasWon) {
      drawText("GAME OVER");
    }
    else {
      drawText("YOU WIN");
    }
  }

  private static final long serialVersionUID = 1L;

}
