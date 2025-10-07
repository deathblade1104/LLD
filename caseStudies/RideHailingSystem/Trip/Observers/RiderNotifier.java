package caseStudies.RideHailingSystem.Trip.Observers;

import caseStudies.RideHailingSystem.Trip.Trip;

public class RiderNotifier implements TripObserver {
  @Override
  public void update(Trip trip, String evt) {
    System.out.println("📱 [RiderNotify][" + trip.getRider().getName() + "]: " + evt);
  }
}