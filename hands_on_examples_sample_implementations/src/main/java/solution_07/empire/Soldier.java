package solution_07.empire;

import java.awt.Image;
import java.util.Collection;

import solution_07.gameboard.GameBoardElement;
import solution_07.gameboard.Position;
import solution_07.positionfindstrategies.UserHomingStrategy;
import solution_07.utils.ImageLoader;

public class Soldier extends GalacticEmpireMember {

  /*
   * Icon by Everaldo Coelho http://www.everaldo.com/, retrieved here:
   * https://www.iconfinder.com/icons/15483/clone_droid_helmet_star_wars_storm_trooper_icon
   */
  private static Image icon = new ImageLoader().loadIcon("soldier.png");
  
  private static final int INITIAL_POWER = 10;
  
  private Position position;
  
  
  public Soldier(Position position) {
    this.position = position;
    setPower(INITIAL_POWER);
  }

  @Override
  public Position getCurrentPosition() {
    return position;
  }
  
  @Override
  public void setPosition(Position position) {
    this.position = position;
  }

  @Override
  public Image getImage() {
    return icon;
  }

  @Override
  public Position calculateNextPosition(Collection<GameBoardElement> elements) {
    return new UserHomingStrategy().findNextPosition(position, elements);
  }

}
