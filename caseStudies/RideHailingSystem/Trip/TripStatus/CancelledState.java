package caseStudies.RideHailingSystem.Trip.TripStatus;

import caseStudies.RideHailingSystem.Trip.Trip;

public class CancelledState implements TripState {
  @Override
  public void next(Trip trip) {
    /* terminal */ }

  @Override
  public void cancel(Trip trip) {
    /* already cancelled */ }

  @Override
  public TripStatus getStatus() {
    return TripStatus.CANCELLED;
  }
}