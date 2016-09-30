package solution_08.empire;

import solution_08.gameboard.AbstractFactionMember;
import solution_08.gameboard.Position;

public abstract class GalacticEmpireMember extends AbstractFactionMember {

	public GalacticEmpireMember(Position position) {
		super(position);
	}

	@Override
	public String getFactionName() {
		return "Empire";
	}
}
