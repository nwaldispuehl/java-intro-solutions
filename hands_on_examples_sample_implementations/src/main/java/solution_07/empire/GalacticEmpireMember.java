package solution_07.empire;

import solution_07.gameboard.AbstractFactionMember;

public abstract class GalacticEmpireMember extends AbstractFactionMember {

  @Override
  public String getFactionName() {
    return "Empire";
  }
}
