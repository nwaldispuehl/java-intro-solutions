package exercise_03.gameboard;

/**
 * Holds a cartesian position information.
 */
public class Position {

  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
  
  public double getDistanceTo(Position position) {
	  int dX = x - position.x;
	  int dY = y - position.y;
	  return Math.sqrt(dX * dX + dY * dY);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Position) {
      return x == ((Position) obj).getX() && y == ((Position) obj).getY();
    }

    return super.equals(obj);
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
