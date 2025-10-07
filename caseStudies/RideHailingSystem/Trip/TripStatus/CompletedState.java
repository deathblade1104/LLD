package caseStudies.RideHailingSystem.Trip.TripStatus;

import caseStudies.RideHailingSystem.Trip.Trip;

public class CompletedState implements TripState {
  @Override
  public void next(Trip trip) {
    // terminal
  }

  @Override
  public void cancel(Trip trip) {
    // cannot cancel after completion
  }

  @Override
  public TripStatus getStatus() {
    return TripStatus.COMPLETED;
  }
}