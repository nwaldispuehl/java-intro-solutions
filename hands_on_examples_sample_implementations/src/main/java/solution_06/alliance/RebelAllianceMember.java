package solution_06.alliance;

import solution_06.gameboard.AbstractFactionMember;
import solution_06.gameboard.Position;

public abstract class RebelAllianceMember extends AbstractFactionMember {

	public RebelAllianceMember(Position position) {
		super(position);
	}

	@Override
	public String getFactionName() {
		return "Rebels";
	}
}
