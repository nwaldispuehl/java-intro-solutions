package exercise_03.alliance;

import java.awt.Image;
import java.util.Collection;

import exercise_03.gameboard.GameBoardElement;
import exercise_03.gameboard.Position;
import exercise_03.positionfindstrategies.RandomNextPositionStrategy;
import exercise_03.utils.ImageLoader;

/**
 * The friendly helpers of the protagonist.
 */
public class Robot extends RebelAllianceMember {

  /*
   * Icon by http://www.artua.com/, retrieved here:
   * http://www.iconarchive.com/show/star-wars-icons-by-artua.html
   */
  private static Image icon = new ImageLoader().loadIcon("robot.png");
  
  private static final int INITIAL_POWER = 15;
  
	private Position position;

	public Robot(Position position) {
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
    return new RandomNextPositionStrategy().findNextPosition(position, elements);
  }

}
