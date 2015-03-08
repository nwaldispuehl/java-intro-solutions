package solution_06.empire;

import solution_06.gameboard.AbstractFactionMember;
import solution_06.gameboard.Position;

public abstract class GalacticEmpireMember extends AbstractFactionMember {

	public GalacticEmpireMember(Position position) {
		super(position);
	}

	@Override
	public String getFactionName() {
		return "Empire";
	}
}
