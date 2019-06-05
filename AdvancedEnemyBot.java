package moylan;

import robocode.ScannedRobotEvent;

public class AdvancedEnemyBot extends EnemyBot {
  private double x;
  private double y;
  
  public AdvancedEnemyBot() {
    reset();
  }
  
  public void reset()
  {
    super.reset();
    
    x = 0.0D;
    y = 0.0D;
  }
  
  public void update(ScannedRobotEvent paramScannedRobotEvent, robocode.Robot paramRobot)
  {
    super.update(paramScannedRobotEvent);
    



    double d = paramRobot.getHeading() + paramScannedRobotEvent.getBearing();
    if (d < 0.0D) { d += 360.0D;
    }
    
    x = (paramRobot.getX() + Math.sin(Math.toRadians(d)) * paramScannedRobotEvent.getDistance());
    

    y = (paramRobot.getY() + Math.cos(Math.toRadians(d)) * paramScannedRobotEvent.getDistance());
  }
  

  public double getX() { return x; }
  public double getY() { return y; }
  




  public double getFutureX(long paramLong)
  {
    return x + Math.sin(Math.toRadians(getHeading())) * getVelocity() * paramLong;
  }
  




  public double getFutureY(long paramLong)
  {
    return y + Math.cos(Math.toRadians(getHeading())) * getVelocity() * paramLong;
  }
}