package solution_08.gameboard;

import java.awt.Image;
import java.util.Collection;

import solution_08.utils.ImageLoader;

public class Rock extends AbstractGameBoardElement {

	private static Image icon = new ImageLoader().loadIcon("not_the_droids/rock.png");
	
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
