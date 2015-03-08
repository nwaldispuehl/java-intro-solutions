package solution_06.empire;

import java.awt.Image;
import java.util.Collection;

import solution_06.gameboard.GameBoardElement;
import solution_06.gameboard.Position;
import solution_06.positionfindstrategies.UserHomingStrategy;
import solution_06.utils.ImageLoader;

public class Antagonist extends GalacticEmpireMember {

	private static Image icon = new ImageLoader().loadIcon("antagonist.png");
	
	private static final int INITIAL_POWER = 12;

	public Antagonist(Position position) {
		super(position);
		setPower(INITIAL_POWER);
	}

	@Override
	public Position calculateNextPosition(Collection<GameBoardElement> elements) {
		return new UserHomingStrategy().findNextPosition(getCurrentPosition(), elements);
	}

	@Override
	public Image getImage() {
		return icon;
	}

}
