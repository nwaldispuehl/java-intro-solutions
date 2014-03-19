package exercise_03.alliance;

import exercise_03.gameboard.AbstractFactionMember;

public abstract class RebelAllianceMember extends AbstractFactionMember {

  @Override
  public String getFactionName() {
    return "Rebels";
  }
}
