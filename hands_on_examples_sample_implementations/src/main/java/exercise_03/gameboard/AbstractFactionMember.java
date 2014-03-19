package exercise_03.gameboard;


public abstract class AbstractFactionMember implements FactionMember {

  private int power;
  
  @Override
  public void fightWith(FactionMember f) {
    if (isInTheSameFaction(f)) {
      return;
    }
    
    if (isWeakerThan(f)) {
      f.setPower(f.getPower() - power);
      setPower(0);
    }
    else {
      setPower(power - f.getPower());
      f.setPower(0);
    }
  }
  
  private boolean isWeakerThan(FactionMember f) {
    return power < f.getPower();
  }

  @Override
  public boolean isDead() {
    return power <= 0;
  }
  
  @Override
  public void setPower(int power) {
    this.power = power;
  }
  
  @Override
  public int getPower() {
    return power;
  }
  
  @Override
  public boolean isInTheSameFaction(FactionMember f) {
    return getFactionName().equals(f.getFactionName());
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + " (" + getFactionName() + ")";
  }
}
