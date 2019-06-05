package moylan;

import robocode.ScannedRobotEvent;

public class MuyMuyMoylan extends robocode.AdvancedRobot
{
  public MuyMuyMoylan() {}
  
  private AdvancedEnemyBot enemy = new AdvancedEnemyBot();
  private byte radarDirection = 1;
  private byte moveDirection = 1;
  
  public void run() {
    setColors(java.awt.Color.orange, java.awt.Color.black, java.awt.Color.orange);
    setAdjustRadarForGunTurn(true);
    setAdjustGunForRobotTurn(true);
    for (;;)
    {
      doRadar();
      doMove();
      doGun();
      execute();
    }
  }
  


  public void onScannedRobot(ScannedRobotEvent paramScannedRobotEvent)
  {
    if ((enemy.none()) || (paramScannedRobotEvent.getDistance() < enemy.getDistance() - 70.0D) || 
      (paramScannedRobotEvent.getName().equals(enemy.getName())))
    {

      enemy.update(paramScannedRobotEvent, this);
    }
  }
  
  public void onHitByBullet(robocode.HitByBulletEvent paramHitByBulletEvent) {
    setTurnRadarRight(360.0D);
  }
  
  public void onRobotDeath(robocode.RobotDeathEvent paramRobotDeathEvent)
  {
    if (paramRobotDeathEvent.getName().equals(enemy.getName())) {
      enemy.reset();
    }
  }
  
  void doRadar() {
    if (enemy.none())
    {
      setTurnRadarRight(360.0D);
    }
    else {
      double d = getHeading() - getRadarHeading() + enemy.getBearing();
      d += 30 * radarDirection;
      setTurnRadarRight(normalizeBearing(d));
      radarDirection = ((byte)(radarDirection * -1));
    }
  }
  

  public void doMove()
  {
    setTurnRight(normalizeBearing(enemy.getBearing() + 90.0D - 15 * moveDirection));
    

    if (getTime() % 20L == 0L) {
      moveDirection = ((byte)(moveDirection * -1));
      setAhead(150 * moveDirection);
    }
  }
  
  void doGun()
  {
    if (enemy.none()) {
      return;
    }
    double d1 = Math.min(400.0D / enemy.getDistance(), 3.0D);
    double d2 = 20.0D - d1 * 3.0D;
    long l = (enemy.getDistance() / d2);
    double d3 = enemy.getFutureX(l);
    double d4 = enemy.getFutureY(l);
    double d5 = absoluteBearing(getX(), getY(), d3, d4);
    setTurnGunRight(normalizeBearing(d5 - getGunHeading()));
    
    if ((getGunHeat() == 0.0D) && (Math.abs(getGunTurnRemaining()) < 10.0D)) {
      setFire(d1);
    }
  }
  
  double absoluteBearing(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d1 = paramDouble3 - paramDouble1;
    double d2 = paramDouble4 - paramDouble2;
    double d3 = java.awt.geom.Point2D.distance(paramDouble1, paramDouble2, paramDouble3, paramDouble4);
    double d4 = Math.toDegrees(Math.asin(d1 / d3));
    double d5 = 0.0D;
    
    if ((d1 > 0.0D) && (d2 > 0.0D)) {
      d5 = d4;
    } else if ((d1 < 0.0D) && (d2 > 0.0D)) {
      d5 = 360.0D + d4;
    } else if ((d1 > 0.0D) && (d2 < 0.0D)) {
      d5 = 180.0D - d4;
    } else if ((d1 < 0.0D) && (d2 < 0.0D)) {
      d5 = 180.0D - d4;
    }
    
    return d5;
  }
  
  double normalizeBearing(double paramDouble)
  {
    while (paramDouble > 180.0D) paramDouble -= 360.0D;
    while (paramDouble < -180.0D) paramDouble += 360.0D;
    return paramDouble;
  }
}