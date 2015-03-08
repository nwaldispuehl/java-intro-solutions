package solution_06.gameboard;

import java.awt.Image;
import java.util.Collection;

import solution_06.utils.ImageLoader;

public class Rock extends AbstractGameBoardElement {

	private static Image icon = new ImageLoader().loadIcon("rock.png");
	
	public Rock(Position position) {
		super(position);
	}

	@Override
	public Position calculateNextPosition(Collection<GameBoardElement> elements) {
		return getCurrentPosition();
	}

	@Override
	public Image getImage() {
		return icon;
	}

}
