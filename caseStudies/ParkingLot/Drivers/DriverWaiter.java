package caseStudies.ParkingLot.Drivers;

import caseStudies.ParkingLot.ParkingLotManager.WaitlistObserver;

public class DriverWaiter implements WaitlistObserver {
  @Override
  public void onSpotAvailable(Driver driver) {
    System.out.println(
        "Notify " + driver.getName() + " (" + driver.getPhoneNumber() + "): A parking spot is now available.");
  }
}
