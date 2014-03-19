package exercise_03.gameboard;

import java.awt.Image;
import java.util.Collection;

import exercise_03.utils.ImageLoader;

public class Rock implements GameBoardElement {

	private static Image icon = new ImageLoader().loadIcon("rock.png");
	
	private Position position;

	public Rock(Position position) {
		this.position = position;
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
		return position;
	}

	@Override
	public Image getImage() {
		return icon;
	}

}
