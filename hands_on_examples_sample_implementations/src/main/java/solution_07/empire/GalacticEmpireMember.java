package solution_07.empire;

import solution_07.gameboard.AbstractFactionMember;
import solution_07.gameboard.Position;

public abstract class GalacticEmpireMember extends AbstractFactionMember {

	public GalacticEmpireMember(Position position) {
		super(position);
	}

	@Override
	public String getFactionName() {
		return "Empire";
	}
}
