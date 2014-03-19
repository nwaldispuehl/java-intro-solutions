package exercise_03.empire;

import java.awt.Image;
import java.util.Collection;

import exercise_03.gameboard.GameBoardElement;
import exercise_03.gameboard.Position;
import exercise_03.positionfindstrategies.UserHomingStrategy;
import exercise_03.utils.ImageLoader;

public class Antagonist extends GalacticEmpireMember {

	private static Image icon = new ImageLoader().loadIcon("antagonist.png");
	
	private static final int INITIAL_POWER = 12;
	
	private Position position;

	public Antagonist(Position position) {
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
	public Position calculateNextPosition(Collection<GameBoardElement> elements) {
		return new UserHomingStrategy().findNextPosition(position, elements);
	}

	@Override
	public Image getImage() {
		return icon;
	}

}
