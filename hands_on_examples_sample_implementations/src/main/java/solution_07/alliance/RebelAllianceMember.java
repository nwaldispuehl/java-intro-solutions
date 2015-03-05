package solution_07.alliance;

import solution_07.gameboard.AbstractFactionMember;

public abstract class RebelAllianceMember extends AbstractFactionMember {

  @Override
  public String getFactionName() {
    return "Rebels";
  }
}
