package solution_07.alliance;

import solution_07.gameboard.AbstractFactionMember;
import solution_07.gameboard.Position;

public abstract class RebelAllianceMember extends AbstractFactionMember {

	public RebelAllianceMember(Position position) {
		super(position);
	}

	@Override
	public String getFactionName() {
		return "Rebels";
	}
}
