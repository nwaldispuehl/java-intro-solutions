package solution_08.gameboard;


public abstract class AbstractGameBoardElement implements GameBoardElement {

	private Position position;
	
	public AbstractGameBoardElement(Position position) {
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

}
