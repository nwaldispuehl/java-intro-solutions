package exercise_03.gameboard;

/**
 * Faction members belong to a faction, and are able to fight with each other.
 */
public interface FactionMember extends GameBoardElement {

  /**
   * Returns the current remaining power of this faction member.
   */
  int getPower();
  
  /**
   * Sets the power to the new, provided value.
   */
  void setPower(int newPower);
  
  /**
   * Fight with another faction member.
   */
  void fightWith(FactionMember f);
  
  /**
   * Determines if the other faction member is of the same faction.
   */
  boolean isInTheSameFaction(FactionMember f);
  
  /**
   * Determines if this player is dead.
   */
  boolean isDead();
  
  /**
   * Returns the faction name of the current faction.
   */
  String getFactionName();
  
}
