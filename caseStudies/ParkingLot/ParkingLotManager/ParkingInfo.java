package caseStudies.ParkingLot.ParkingLotManager;

import java.util.Date;

import caseStudies.ParkingLot.ParkingLot.ParkingPosition;

public class ParkingInfo {
  private final ParkingPosition position;
  private final Date inTime;

  public ParkingInfo(ParkingPosition position) {
    this.position = position;
    this.inTime = new Date();
  }

  public ParkingPosition getPosition() {
    return position;
  }

  public Date getInTime() {
    return inTime;
  }
}
