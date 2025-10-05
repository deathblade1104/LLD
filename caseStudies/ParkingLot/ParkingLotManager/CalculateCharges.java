package caseStudies.ParkingLot.ParkingLotManager;

import java.util.Date;

public class CalculateCharges implements ChargingStrategy {

  private static final int RATE_PER_HOUR_PER_SPOT = 20;

  @Override
  public int calculateCharge(Date inTime, int spotsOccupied) {
    Date now = new Date();
    long durationMillis = now.getTime() - inTime.getTime();
    long hours = (long) Math.ceil(durationMillis / (1000.0 * 60 * 60));
    if (hours == 0)
      hours = 1;
    return (int) (hours * RATE_PER_HOUR_PER_SPOT * spotsOccupied);
  }

}
