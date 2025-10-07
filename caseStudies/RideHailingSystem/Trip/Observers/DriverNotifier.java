package caseStudies.RideHailingSystem.Trip.Observers;

import caseStudies.RideHailingSystem.Trip.Trip;

public class DriverNotifier implements TripObserver {
  @Override
  public void update(Trip trip, String evt) {
    if (trip.getDriver() != null) {
      System.out.println("🚗 [DriverNotify][" + trip.getDriver().getName() + "]: " + evt);
    }
  }
}
