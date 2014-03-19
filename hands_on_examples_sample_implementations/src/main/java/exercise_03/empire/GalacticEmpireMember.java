package exercise_03.empire;

import exercise_03.gameboard.AbstractFactionMember;

public abstract class GalacticEmpireMember extends AbstractFactionMember {

  @Override
  public String getFactionName() {
    return "Empire";
  }
}
