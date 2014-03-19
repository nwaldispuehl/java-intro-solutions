package exercise_03.gameboard;

import java.util.*;

import exercise_03.positionfindstrategies.UserInput;

/**
 * The game board holds information about all game board elements.
 */
public class GameBoard {

  List<GameBoardElement> elements = new ArrayList<GameBoardElement>();
  Map<Position, GameBoardElement> positionLookup = new HashMap<Position, GameBoardElement>();
  
  GameBoardElement userControlledElement;
  
  private int horizontalFields;
  private int verticalFields;

  /**
   * Initializes the game board with the provided game board size.
   * 
   * @param horizontalFields the number of fields across
   * @param verticalFields the number of fields down
   */
  public GameBoard(int horizontalFields, int verticalFields) {
    this.horizontalFields = horizontalFields;
    this.verticalFields = verticalFields;
  }

  /**
   * Adds a {@link GameBoardElement} to the list of game board elements of the game.
   * If there is already an element at the same position, the old element gets overwritten.
   */
  public void add(GameBoardElement element) {
    Position p = element.getCurrentPosition();
    if (isValidPosition(p) && !positionIsAlreadyTaken(p)) {
      elements.add(element);
      positionLookup.put(p, element);
    }
  }
  
  /**
   * Removes the element from the game.
   */
  public void remove(GameBoardElement element) {
    elements.remove(element);
    positionLookup.remove(element.getCurrentPosition());
  }

  /**
   * @return a list with all elements which exist on the game board.
   */
  public Collection<GameBoardElement> getAllElements() {
    return elements;
  }
  
  /**
   * Determines if there is already an element at the position p.
   *
   * @return true if there is already an element a the provided position.
   */
  public boolean positionIsAlreadyTaken(Position p) {
    return positionLookup.containsKey(p);
  }
  
  /**
   * Determines if the provided position is valid. This is the case if it is not null,
   * all components are larger or equal than null and smaller than the field size.
   */
  public boolean isValidPosition(Position p) {
    return p != null && isPositive(p) && isInBoard(p);
  }

  private boolean isPositive(Position p) {
    return 0 <= p.getX() && 0 <= p.getY();
  }
  
  private boolean isInBoard(Position p) {
    return p.getX() < horizontalFields && p.getY() < verticalFields;
  }



  /**
   * Performs a game turn of a single element to a new position. Checks if element and position are valid and 
   * only performs the move if this is the case. If there is an enemy on the new position, a fight happens.
   * 
   * @param element the element to move.
   * @param newPosition the position this element likes to be placed on afterwards.
   */
  public void performTurnTo(GameBoardElement element, Position newPosition) {
    if (!elements.contains(element)) {
      return;
    }
    
    if (!isValidPosition(newPosition)) {
      return;
    }
    
    if (!distanceIsOne(element, newPosition)) {
      return;
    }
    
    if (positionIsAlreadyTaken(newPosition)) {
      
      GameBoardElement otherElement = positionLookup.get(newPosition);
      
      if (isFactionMember(element) && isFactionMember(otherElement)) {
        fightAgainst((FactionMember) element, (FactionMember) otherElement);
      }
      
    }
    else {
      moveElementTo(element, newPosition);
    }
  }

  private boolean distanceIsOne(GameBoardElement element, Position newPosition) {
    return 1 == element.getCurrentPosition().getDistanceTo(newPosition);
  }

  private void fightAgainst(FactionMember offender, FactionMember defender) {
    if (offender.isInTheSameFaction(defender)) {
      return;
    }
    
    print("FIGHT: " + offender + " vs " + defender);
    offender.fightWith(defender);
    
    if (defender.isDead()) {
      remove(defender);
      
      print(defender + " dies");
    }

    if (offender.isDead()) {
      remove(offender);
      
      print(offender + " dies");
    }
    else {
      moveElementTo(offender, defender.getCurrentPosition());
      
    }
  }

  private void moveElementTo(GameBoardElement element, Position newPosition) {
    positionLookup.remove(element.getCurrentPosition());
    element.setPosition(newPosition);
    positionLookup.put(newPosition, element);
  }
  
  private boolean isFactionMember(GameBoardElement element) {
    return (element instanceof FactionMember);
  }
  
  public void makeTurnWithUserInput(UserInput userInput) {
    for (GameBoardElement element : copyList(getAllElements())) {
      Position newPosition;
      
      if (isUserControlled(element)) {
        newPosition = ((UserControlled) element).calculateNextPositionByUserInput(userInput);
      }
      else {
        newPosition = element.calculateNextPosition(getAllElements());
      }
     
      performTurnTo(element, newPosition);
    }
  }
  
  /**
   * Determines whether this element is user controlled.
   */
  protected boolean isUserControlled(GameBoardElement element) {
    return element instanceof UserControlled;
  }
  
  /**
   * Creates a copy of a list.
   */
  protected <T> List<T> copyList(Collection<T> list) {
    return new ArrayList<T>(list);
  }
  
  private void print(String message) {
    System.out.println(message);
  }
  
}
