package moylan;

import robocode.ScannedRobotEvent;


public class EnemyBot
{
  private double bearing;
  private double distance;
  private double energy;
  private double heading;
  private String name;
  private double velocity;
  
  public EnemyBot()
  {
    reset();
  }
  
  public void reset()
  {
    bearing = 0.0D;
    distance = 0.0D;
    energy = 0.0D;
    heading = 0.0D;
    name = "";
    velocity = 0.0D;
  }
  
  public final void update(ScannedRobotEvent paramScannedRobotEvent) {
    bearing = paramScannedRobotEvent.getBearing();
    distance = paramScannedRobotEvent.getDistance();
    energy = paramScannedRobotEvent.getEnergy();
    heading = paramScannedRobotEvent.getHeading();
    name = paramScannedRobotEvent.getName();
    velocity = paramScannedRobotEvent.getVelocity();
  }
  
  public boolean shouldTrack(ScannedRobotEvent paramScannedRobotEvent, long paramLong) {
    return (none()) || (paramScannedRobotEvent.getDistance() < getDistance() - 70.0D) || 
      (paramScannedRobotEvent.getName().equals(getName()));
  }
  
  public boolean none() { return name.equals(""); }
  

  public double getBearing() { return bearing; }
  public double getDistance() { return distance; }
  public double getEnergy() { return energy; }
  public double getHeading() { return heading; }
  public String getName() { return name; }
  public double getVelocity() { return velocity; }
}