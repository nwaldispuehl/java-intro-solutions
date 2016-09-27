package solution_07.alliance;

import java.awt.*;
import java.util.Collection;

import solution_07.gameboard.GameBoardElement;
import solution_07.gameboard.Position;
import solution_07.gameboard.UserControlled;
import solution_07.positionfindstrategies.UserInput;
import solution_07.positionfindstrategies.UserInputPositionStrategy;
import solution_07.utils.ImageLoader;

/**
 * This is the hero of the game.
 */
public class Protagonist extends RebelAllianceMember implements UserControlled {

	/*
	 * Icon by http://www.artua.com/, retrieved here:
	 * http://www.iconarchive.com/show/star-wars-icons-by-artua.html
	 */
	private static Image icon = new ImageLoader().loadIcon("not_the_droids/protagonist.png");
  
  	private static final int INITIAL_POWER = 30;
	
	public Protagonist(Position position) {
		super(position);
		setPower(INITIAL_POWER);
	}

	@Override
	public Image getImage() {
		return icon;
	}

	@Override
	public Position calculateNextPosition(Collection<GameBoardElement> elements) {
		return null;
	}

	@Override
	public Position calculateNextPositionByUserInput(UserInput userInput) {
		return new UserInputPositionStrategy(userInput).findNextPosition(getCurrentPosition(), null);
	}

}
