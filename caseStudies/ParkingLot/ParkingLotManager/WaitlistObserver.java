package caseStudies.ParkingLot.ParkingLotManager;

import caseStudies.ParkingLot.Drivers.Driver;

public interface WaitlistObserver {
  void onSpotAvailable(Driver driver);
}
