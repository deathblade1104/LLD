package caseStudies.ParkingLot.ParkingLotManager;

import java.util.Date;

public interface ChargingStrategy {
  int calculateCharge(Date inTime, int spotsOccupied);
}

