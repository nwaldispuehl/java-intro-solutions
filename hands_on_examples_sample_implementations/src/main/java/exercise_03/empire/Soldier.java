package exercise_03.empire;

import java.awt.Image;
import java.util.Collection;

import exercise_03.gameboard.GameBoardElement;
import exercise_03.gameboard.Position;
import exercise_03.positionfindstrategies.UserHomingStrategy;
import exercise_03.utils.ImageLoader;

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
